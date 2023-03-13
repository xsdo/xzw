package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.DGoodsentence;
import cn.zealon.readingcloud.book.dao.DGoodsentenceDao;
import cn.zealon.readingcloud.book.service.DGoodsentenceService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部编好句表(DGoodsentence)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:53
 */
@Service("dGoodsentenceService")
public class DGoodsentenceServiceImpl implements DGoodsentenceService {
    @Resource
    private DGoodsentenceDao dGoodsentenceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DGoodsentence queryById(Long id) {
        return this.dGoodsentenceDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param dGoodsentence 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @Override
    public Page<DGoodsentence> queryByPage(DGoodsentence dGoodsentence, PageRequest pageRequest) {
        long total = this.dGoodsentenceDao.count(dGoodsentence);
        return new PageImpl<>(this.dGoodsentenceDao.queryAllByLimit(dGoodsentence, pageRequest), pageRequest, total);
    }

    @Override
    public List<DGoodsentence>queryAll(DGoodsentence dGoodsentence){
        return this.dGoodsentenceDao.queryAll(dGoodsentence);
    }
    /**
     * 新增数据
     *
     * @param dGoodsentence 实例对象
     * @return 实例对象
     */
    @Override
    public DGoodsentence insert(DGoodsentence dGoodsentence) {
        this.dGoodsentenceDao.insert(dGoodsentence);
        return dGoodsentence;
    }

    /**
     * 修改数据
     *
     * @param dGoodsentence 实例对象
     * @return 实例对象
     */
    @Override
    public DGoodsentence update(DGoodsentence dGoodsentence) {
        this.dGoodsentenceDao.update(dGoodsentence);
        return this.queryById(dGoodsentence.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.dGoodsentenceDao.deleteById(id) > 0;
    }
}
