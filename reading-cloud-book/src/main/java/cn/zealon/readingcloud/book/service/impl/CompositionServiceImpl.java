package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.bean.PageBean;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.book.dao.CompositionDao;
import cn.zealon.readingcloud.book.service.CompositionService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作文表(Composition)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:14:01
 */
@Service("compositionService")
public class CompositionServiceImpl implements CompositionService {
    @Resource
    private CompositionDao compositionDao;


    @Override
    public PageBean<Composition> pageQuery(JSONObject jsonObject) {

        List<Composition> urf = this.compositionDao.queryAll(null);

        int totalRecord = 0;
        if (!CollectionUtils.isEmpty(urf)) {
            totalRecord = urf.size();
        }
        PageBean<Composition> pageBean = new PageBean<>(Integer.valueOf(String.valueOf(jsonObject.get("pageNum"))), Integer.valueOf(String.valueOf(jsonObject.get("pageSize"))), totalRecord);
        List<Composition> data = new ArrayList<>();
        for (int i = pageBean.getStart(); i < pageBean.getEnd(); i++) {
            data.add(urf.get(i));
        }
        pageBean.setData(data);
        pageBean.setTotalRecord(totalRecord);

        return pageBean;

    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Composition queryById(Long id) {
        return this.compositionDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param composition 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Composition> queryByPage(Composition composition, PageRequest pageRequest) {
        long total = this.compositionDao.count(composition);
        return new PageImpl<>(this.compositionDao.queryAllByLimit(composition, pageRequest), pageRequest, total);
    }
    @Override
    public List<Composition>queryAll(Composition composition){
        return this.compositionDao.queryAll(composition);
    }

    @Override
    public List<Map<String,String>>queryContent(){
        List<Map<String,String>>list=new ArrayList<>();
        List<Composition> compositionList=this.compositionDao.queryAll(new Composition());
        if (compositionList!=null){
            for (Composition cc: compositionList) {
                Map<String,String>map = new HashMap<>();
                map.put(cc.getCTitle(),cc.getCArticle());
                list.add(map);
}
        }
        return list;
    }
    @Override
    public List<Composition>queryRandoms(int size){
        return this.compositionDao.queryRand(size);
    }

    /**
     * 新增数据
     *
     * @param composition 实例对象
     * @return 实例对象
     */
    @Override
    public Composition insert(Composition composition) {
        this.compositionDao.insert(composition);
        return composition;
    }

    /**
     * 修改数据
     *
     * @param composition 实例对象
     * @return 实例对象
     */
    @Override
    public Composition update(Composition composition) {
        this.compositionDao.update(composition);
        return this.queryById(composition.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.compositionDao.deleteById(id) > 0;
    }
}
