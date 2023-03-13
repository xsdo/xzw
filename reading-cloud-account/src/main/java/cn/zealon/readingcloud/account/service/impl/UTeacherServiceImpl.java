package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.dao.UTeacherDao;
import cn.zealon.readingcloud.account.service.UTeacherService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师班级表(UTeacher)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
@Service("uTeacherService")
public class UTeacherServiceImpl implements UTeacherService {
    @Resource
    private UTeacherDao uTeacherDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UTeacher queryById(Long id) {
        return this.uTeacherDao.queryById(id);
    }

    @Override
    public List<UTeacher>queryAll(UTeacher uTeacher){
        return this.uTeacherDao.queryAll(uTeacher);
    }
    /**
     * 分页查询
     *
     * @param uTeacher    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UTeacher> queryByPage(UTeacher uTeacher, PageRequest pageRequest) {
        long total = this.uTeacherDao.count(uTeacher);
        return new PageImpl<>(this.uTeacherDao.queryAllByLimit(uTeacher, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param uTeacher 实例对象
     * @return 实例对象
     */
    @Override
    public UTeacher insert(UTeacher uTeacher) {
        this.uTeacherDao.insert(uTeacher);
        return uTeacher;
    }

    /**
     * 修改数据
     *
     * @param uTeacher 实例对象
     * @return 实例对象
     */
    @Override
    public UTeacher update(UTeacher uTeacher) {
        this.uTeacherDao.update(uTeacher);
        return this.queryById(uTeacher.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uTeacherDao.deleteById(id) > 0;
    }
}
