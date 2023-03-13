package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CMuted;
import cn.zealon.readingcloud.book.dao.CMutedDao;
import cn.zealon.readingcloud.book.service.CMutedService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 禁言表(CMuted)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:14:01
 */
@Service("cMutedService")
public class CMutedServiceImpl implements CMutedService {
    @Resource
    private CMutedDao cMutedDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CMuted queryById(Long id) {
        return this.cMutedDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cMuted      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CMuted> queryByPage(CMuted cMuted, PageRequest pageRequest) {
        long total = this.cMutedDao.count(cMuted);
        return new PageImpl<>(this.cMutedDao.queryAllByLimit(cMuted, pageRequest), pageRequest, total);
    }

    @Override
    public List<CMuted>queryAll(CMuted cMuted){
        return this.cMutedDao.queryAll(cMuted);
    }
    /**
     * 新增数据
     *
     * @param cMuted 实例对象
     * @return 实例对象
     */
    @Override
    public CMuted insert(CMuted cMuted) {
        this.cMutedDao.insert(cMuted);
        return cMuted;
    }

    /**
     * 修改数据
     *
     * @param cMuted 实例对象
     * @return 实例对象
     */
    @Override
    public CMuted update(CMuted cMuted) {
        this.cMutedDao.update(cMuted);
        return this.queryById(cMuted.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cMutedDao.deleteById(id) > 0;
    }
}
