package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CCollect;
import cn.zealon.readingcloud.book.dao.CCollectDao;
import cn.zealon.readingcloud.book.service.CCollectService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 收藏表(CCollect)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 17:51:37
 */
@Service("cCollectService")
public class CCollectServiceImpl implements CCollectService {
    @Resource
    private CCollectDao cCollectDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CCollect queryById(Long id) {
        return this.cCollectDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cCollect    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CCollect> queryByPage(CCollect cCollect, PageRequest pageRequest) {
        long total = this.cCollectDao.count(cCollect);
        return new PageImpl<>(this.cCollectDao.queryAllByLimit(cCollect, pageRequest), pageRequest, total);
    }

    @Override
    public List<CCollect>queryAll(CCollect cCollect){
        return this.cCollectDao.queryAll(cCollect);
    }
    @Override
    public List<CCollect>queryByUserId(Long userId){
        CCollect cCollect =new CCollect();
        cCollect.setIsused(0);
        cCollect.setUserId(userId);
        List<CCollect> list = this.queryAll(cCollect);
        if (list.isEmpty()) {
            cCollect.setCreateTime(new Date());
            cCollect.setUpdateTime(new Date());
            cCollect.setCName("默认收藏夹");
            this.insert(cCollect);
        }
        CCollect cc =new CCollect();
        cc.setIsused(0);
        cc.setUserId(userId);
        return this.queryAll(cc);
    }

    /**
     * 新增数据
     *
     * @param cCollect 实例对象
     * @return 实例对象
     */
    @Override
    public CCollect insert(CCollect cCollect) {
        this.cCollectDao.insert(cCollect);
        return cCollect;
    }

    /**
     * 修改数据
     *
     * @param cCollect 实例对象
     * @return 实例对象
     */
    @Override
    public CCollect update(CCollect cCollect) {
        this.cCollectDao.update(cCollect);
        return this.queryById(cCollect.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cCollectDao.deleteById(id) > 0;
    }
}
