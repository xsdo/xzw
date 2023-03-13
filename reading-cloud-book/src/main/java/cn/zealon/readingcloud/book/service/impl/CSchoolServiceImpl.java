package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CSchool;
import cn.zealon.readingcloud.book.dao.CSchoolDao;
import cn.zealon.readingcloud.book.service.CSchoolService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 校园作文表(CSchool)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:57
 */
@Service("cSchoolService")
public class CSchoolServiceImpl implements CSchoolService {
    @Resource
    private CSchoolDao cSchoolDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CSchool queryById(Long id) {
        return this.cSchoolDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cSchool     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CSchool> queryByPage(CSchool cSchool, PageRequest pageRequest) {
        long total = this.cSchoolDao.count(cSchool);
        return new PageImpl<>(this.cSchoolDao.queryAllByLimit(cSchool, pageRequest), pageRequest, total);
    }

    @Override
    public List<CSchool>queryAll(CSchool cSchool){
        return this.cSchoolDao.queryAll(cSchool);
    }
    /**
     * 新增数据
     *
     * @param cSchool 实例对象
     * @return 实例对象
     */
    @Override
    public CSchool insert(CSchool cSchool) {
        this.cSchoolDao.insert(cSchool);
        return cSchool;
    }

    /**
     * 修改数据
     *
     * @param cSchool 实例对象
     * @return 实例对象
     */
    @Override
    public CSchool update(CSchool cSchool) {
        this.cSchoolDao.update(cSchool);
        return this.queryById(cSchool.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cSchoolDao.deleteById(id) > 0;
    }
}
