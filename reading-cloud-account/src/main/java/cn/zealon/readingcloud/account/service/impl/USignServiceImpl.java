package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.dao.USigndetailDao;
import cn.zealon.readingcloud.account.service.USigndetailService;
import cn.zealon.readingcloud.common.pojo.xzwusers.USign;
import cn.zealon.readingcloud.account.dao.USignDao;
import cn.zealon.readingcloud.account.service.USignService;
import cn.zealon.readingcloud.common.pojo.xzwusers.USigndetail;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.beans.SimpleBeanInfo;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 签到表(USign)表服务实现类
 *
 * @author makejava
 * @since 2023-03-07 09:20:54
 */
@Service("uSignService")
public class USignServiceImpl implements USignService {
    @Resource
    private USignDao uSignDao;
    @Resource
    private USigndetailService uSigndetailService;




    @Override
    public USigndetail todayDetail(Long userId){
        USigndetail uSigndetail = null;
        List<USigndetail> uSigndetailList = uSigndetailService.queryAllByUserId(userId);
        if (uSigndetailList!=null) {
            LocalDate localDate =LocalDate.now();
            for (int i = 0; i < uSigndetailList.size(); i++) {
                DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String datestr= dateTimeFormatter.format(localDate);
                String dateSign=formatter.format(uSigndetailList.get(i).getSignDate());
                System.out.println(datestr);
                System.out.println(dateSign);
                if (dateSign.equals(datestr)) {
                    uSigndetail=uSigndetailList.get(i);
                }
            }
            return uSigndetail;
        }else {return null;}
    }

    @Override
    public USigndetail yesterdayDetail(Long userId){
        USigndetail uSigndetail = null;
        List<USigndetail> uSigndetailList = uSigndetailService.queryAllByUserId(userId);
        if (uSigndetailList!=null) {
            LocalDate localDate =LocalDate.now();
            LocalDate preDay=localDate.plusDays(-1);
            for (int i = 0; i < uSigndetailList.size(); i++) {
                DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String datestr= dateTimeFormatter.format(preDay);
                String dateSign=formatter.format(uSigndetailList.get(i).getSignDate());
                if (dateSign.equals(datestr)) {
                    uSigndetail=uSigndetailList.get(i);
                }
            }
            return uSigndetail;
        }else {return null;}
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public USign queryById(Long id) {
        return this.uSignDao.queryById(id);
    }

    @Override
    public USign queryByUserId(Long userId){
        USign uSign=new USign();
        uSign.setUserId(userId);
        List<USign>list=this.uSignDao.queryAll(uSign);
        if (list == null || list.size() == 0){
            return null;
        }else {
            return list.get(0);
        }

    }

    /**
     * 分页查询
     *
     * @param uSign       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<USign> queryByPage(USign uSign, PageRequest pageRequest) {
        long total = this.uSignDao.count(uSign);
        return new PageImpl<>(this.uSignDao.queryAllByLimit(uSign, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param uSign 实例对象
     * @return 实例对象
     */
    @Override
    public USign insert(USign uSign) {
        this.uSignDao.insert(uSign);
        return uSign;
    }

    /**
     * 修改数据
     *
     * @param uSign 实例对象
     * @return 实例对象
     */
    @Override
    public USign update(USign uSign) {
        this.uSignDao.update(uSign);
        return this.queryById(uSign.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uSignDao.deleteById(id) > 0;
    }
}
