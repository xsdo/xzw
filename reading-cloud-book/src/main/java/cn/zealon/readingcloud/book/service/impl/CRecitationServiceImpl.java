package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CRecitation;
import cn.zealon.readingcloud.book.dao.CRecitationDao;
import cn.zealon.readingcloud.book.service.CRecitationService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 朗读者表(CRecitation)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:58
 */
@Service("cRecitationService")
public class CRecitationServiceImpl implements CRecitationService {
    @Resource
    private CRecitationDao cRecitationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CRecitation queryById(Long id) {
        return this.cRecitationDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cRecitation 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CRecitation> queryByPage(CRecitation cRecitation, PageRequest pageRequest) {
        long total = this.cRecitationDao.count(cRecitation);
        return new PageImpl<>(this.cRecitationDao.queryAllByLimit(cRecitation, pageRequest), pageRequest, total);
    }
    @Override
    public List<CRecitation> queryAll(CRecitation cRecitation) {
        return this.cRecitationDao.queryAll(cRecitation);
    }

    /**
     * 新增数据
     *
     * @param cRecitation 实例对象
     * @return 实例对象
     */
    @Override
    public CRecitation insert(CRecitation cRecitation) {
        this.cRecitationDao.insert(cRecitation);
        return cRecitation;
    }

    /**
     * 修改数据
     *
     * @param cRecitation 实例对象
     * @return 实例对象
     */
    @Override
    public CRecitation update(CRecitation cRecitation) {
        this.cRecitationDao.update(cRecitation);
        return this.queryById(cRecitation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cRecitationDao.deleteById(id) > 0;
    }
}
