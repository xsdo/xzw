package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CNation;
import cn.zealon.readingcloud.book.dao.CNationDao;
import cn.zealon.readingcloud.book.service.CNationService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 全国作文表(CNation)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:56
 */
@Service("cNationService")
public class CNationServiceImpl implements CNationService {
    @Resource
    private CNationDao cNationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CNation queryById(Long id) {
        return this.cNationDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cNation     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CNation> queryByPage(CNation cNation, PageRequest pageRequest) {
        long total = this.cNationDao.count(cNation);
        return new PageImpl<>(this.cNationDao.queryAllByLimit(cNation, pageRequest), pageRequest, total);
    }
    @Override
    public List<CNation>queryAll(CNation cNation){
        return this.cNationDao.queryAll(cNation);
    }


    /**
     * 新增数据
     *
     * @param cNation 实例对象
     * @return 实例对象
     */
    @Override
    public CNation insert(CNation cNation) {
        this.cNationDao.insert(cNation);
        return cNation;
    }

    /**
     * 修改数据
     *
     * @param cNation 实例对象
     * @return 实例对象
     */
    @Override
    public CNation update(CNation cNation) {
        this.cNationDao.update(cNation);
        return this.queryById(cNation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cNationDao.deleteById(id) > 0;
    }
}
