package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.book.feign.client.CompositionClient;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.common.pojo.xzwtasks.ReadTask;
import cn.zealon.readingcloud.homepage.dao.ReadTaskDao;
import cn.zealon.readingcloud.homepage.service.ReadTaskService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 阅读任务表(ReadTask)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:11:42
 */
@Service("readTaskService")
public class ReadTaskServiceImpl implements ReadTaskService {
    @Resource
    private ReadTaskDao readTaskDao;

    @Resource
    private CompositionClient compositionClient;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReadTask queryById(Long id) {
        return this.readTaskDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param readTask    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ReadTask> queryByPage(ReadTask readTask, PageRequest pageRequest) {
        long total = this.readTaskDao.count(readTask);
        return new PageImpl<>(this.readTaskDao.queryAllByLimit(readTask, pageRequest), pageRequest, total);
    }

    @Override
    public List<ReadTask> queryAll(ReadTask readTask) {
        return this.readTaskDao.queryAll(readTask);
    }

    @Override
    public ReadTask queryOne(){
        ReadTask readTask=new ReadTask();
        readTask.setIsused(0);
        List<ReadTask> list = this.readTaskDao.queryAll(readTask);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void toReadTask(){
        //将昨天的任务改为已结束
        this.checkReadTask();
        List<Composition>compositions=this.compositionClient.queryRandoms(2);
        if (compositions.size() == 2) {
            ReadTask readTask=new ReadTask();
            readTask.setIsused(0);
            readTask.setCreateTime(new Date());
            readTask.setUpdateTime(new Date());
            readTask.setRTitle(compositions.get(0).getCTitle());
            readTask.setRSynopsis(compositions.get(1).getCTitle());
            try{
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String format = simpleDateFormat.format(date);
                Date today=simpleDateFormat.parse(format);
                readTask.setRBegintime(today);
                //获取明天日期
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                // 把日期往后增加一天,整数  往后推,负数往前移动
                calendar.add(Calendar.DATE, 1);
                // 这个时间就是日期往后推一天的结果
                date = calendar.getTime();
                format = simpleDateFormat.format(date);
                Date tomorrow=simpleDateFormat.parse(format);
                readTask.setREndtime(tomorrow);
            }catch (Exception e) {
            }
            readTask.setRStatus(1);
            readTask.setRTaskfirst(compositions.get(0).getId());
            readTask.setRTasksecond(compositions.get(1).getId());
            readTask.setRTime(20+"");
            this.readTaskDao.insert(readTask);
        }
    }

    public void checkReadTask(){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String format = simpleDateFormat.format(date);
            Date today=simpleDateFormat.parse(format);
            ReadTask oldTask=new ReadTask();
            oldTask.setIsused(0);
            oldTask.setRStatus(1);
            List<ReadTask> list = this.readTaskDao.queryAll(oldTask);
            if (list != null && list.size() > 0) {
                for (ReadTask r:list){
                    if (r.getRBegintime().before(today)){
                        r.setRStatus(2);
                        this.readTaskDao.update(r);
                    }
                }
            }
        }catch (Exception e) {

        }

    }

    /**
     * 新增数据
     *
     * @param readTask 实例对象
     * @return 实例对象
     */
    @Override
    public ReadTask insert(ReadTask readTask) {
        this.readTaskDao.insert(readTask);
        return readTask;
    }

    /**
     * 修改数据
     *
     * @param readTask 实例对象
     * @return 实例对象
     */
    @Override
    public ReadTask update(ReadTask readTask) {
        this.readTaskDao.update(readTask);
        return this.queryById(readTask.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.readTaskDao.deleteById(id) > 0;
    }
}
