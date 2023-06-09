package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CReadlog;
import cn.zealon.readingcloud.book.dao.CReadlogDao;
import cn.zealon.readingcloud.book.service.CReadlogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 阅读记录表(CReadlog)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 17:51:35
 */
@Service("cReadlogService")
public class CReadlogServiceImpl implements CReadlogService {
    @Resource
    private CReadlogDao cReadlogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CReadlog queryById(Long id) {
        return this.cReadlogDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cReadlog    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CReadlog> queryByPage(CReadlog cReadlog, PageRequest pageRequest) {
        long total = this.cReadlogDao.count(cReadlog);
        return new PageImpl<>(this.cReadlogDao.queryAllByLimit(cReadlog, pageRequest), pageRequest, total);
    }

    @Override
    public void cleanReadlog(Long userId){
        CReadlog cReadlog =new CReadlog();
        cReadlog.setIsused(0);
        cReadlog.setUserId(userId);
        List<CReadlog>cReadlogList =this.queryAll(cReadlog);
        if (!cReadlogList.isEmpty()) {
            for (CReadlog cc:cReadlogList) {
                cc.setIsused(1);
                this.update(cc);
            }
        }
    }

    @Override
    public CReadlog doReadlog(Long userId,String image,String name,Long compositionId,int type){
        CReadlog cReadlog =new CReadlog();
        cReadlog.setIsused(0);
        cReadlog.setUserId(userId);
        cReadlog.setCompositionId(compositionId);
        cReadlog.setRType(type);
        List<CReadlog> readlogList =this.queryAll(cReadlog);
        if (readlogList.isEmpty()){
            cReadlog.setCreateTime(new Date());
            cReadlog.setUpdateTime(new Date());
            cReadlog.setRImage(image);
            cReadlog.setRName(name);
            this.insert(cReadlog);
            return cReadlog;
        }else {
            CReadlog cr =readlogList.get(0);
            cr.setUpdateTime(new Date());
            this.update(cr);
            return cr;
        }
    }
    @Override
    public List<CReadlog>queryAll(CReadlog cReadlog){
        return this.cReadlogDao.queryAll(cReadlog);
    }

    /**
     * 新增数据
     *
     * @param cReadlog 实例对象
     * @return 实例对象
     */
    @Override
    public CReadlog insert(CReadlog cReadlog) {
        this.cReadlogDao.insert(cReadlog);
        return cReadlog;
    }

    /**
     * 修改数据
     *
     * @param cReadlog 实例对象
     * @return 实例对象
     */
    @Override
    public CReadlog update(CReadlog cReadlog) {
        this.cReadlogDao.update(cReadlog);
        return this.queryById(cReadlog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cReadlogDao.deleteById(id) > 0;
    }
}
