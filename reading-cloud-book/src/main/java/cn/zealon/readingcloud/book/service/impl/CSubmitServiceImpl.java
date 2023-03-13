package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CSubmit;
import cn.zealon.readingcloud.book.dao.CSubmitDao;
import cn.zealon.readingcloud.book.service.CSubmitService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 投稿表(CSubmit)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 17:51:36
 */
@Service("cSubmitService")
public class CSubmitServiceImpl implements CSubmitService {
    @Resource
    private CSubmitDao cSubmitDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CSubmit queryById(Long id) {
        return this.cSubmitDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cSubmit     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CSubmit> queryByPage(CSubmit cSubmit, PageRequest pageRequest) {
        long total = this.cSubmitDao.count(cSubmit);
        return new PageImpl<>(this.cSubmitDao.queryAllByLimit(cSubmit, pageRequest), pageRequest, total);
    }

    @Override
    public List<CSubmit>queryAll(CSubmit cSubmit){
        return this.cSubmitDao.queryAll(cSubmit);
    }
    /**
     * 新增数据
     *
     * @param cSubmit 实例对象
     * @return 实例对象
     */
    @Override
    public CSubmit insert(CSubmit cSubmit) {
        this.cSubmitDao.insert(cSubmit);
        return cSubmit;
    }

    /**
     * 修改数据
     *
     * @param cSubmit 实例对象
     * @return 实例对象
     */
    @Override
    public CSubmit update(CSubmit cSubmit) {
        this.cSubmitDao.update(cSubmit);
        return this.queryById(cSubmit.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cSubmitDao.deleteById(id) > 0;
    }
}
