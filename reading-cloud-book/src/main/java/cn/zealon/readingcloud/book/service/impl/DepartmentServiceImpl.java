package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.Department;
import cn.zealon.readingcloud.book.dao.DepartmentDao;
import cn.zealon.readingcloud.book.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部编同步作文(Department)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:56
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentDao departmentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Department queryById(Long id) {
        return this.departmentDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param department  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Department> queryByPage(Department department, PageRequest pageRequest) {
        long total = this.departmentDao.count(department);
        return new PageImpl<>(this.departmentDao.queryAllByLimit(department, pageRequest), pageRequest, total);
    }

    @Override
    public List<Department>queryAll(Department department){
        return this.departmentDao.queryAll(department);
    }
    /**
     * 新增数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    @Override
    public Department insert(Department department) {
        this.departmentDao.insert(department);
        return department;
    }

    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    @Override
    public Department update(Department department) {
        this.departmentDao.update(department);
        return this.queryById(department.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.departmentDao.deleteById(id) > 0;
    }
}
