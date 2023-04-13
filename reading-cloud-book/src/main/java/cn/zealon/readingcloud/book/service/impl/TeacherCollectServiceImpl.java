package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.service.CompositionService;
import cn.zealon.readingcloud.book.service.DepartmentService;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.common.pojo.xzwresources.Department;
import cn.zealon.readingcloud.common.pojo.xzwresources.TeacherCollect;
import cn.zealon.readingcloud.book.dao.TeacherCollectDao;
import cn.zealon.readingcloud.book.service.TeacherCollectService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 教师收藏表(TeacherCollect)表服务实现类
 *
 * @author makejava
 * @since 2023-04-10 15:47:49
 */
@Service("teacherCollectService")
public class TeacherCollectServiceImpl implements TeacherCollectService {
    @Resource
    private TeacherCollectDao teacherCollectDao;


    @Resource
    private CompositionService compositionService;
    @Resource
    private DepartmentService departmentService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TeacherCollect queryById(Long id) {
        return this.teacherCollectDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param teacherCollect 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    @Override
    public Page<TeacherCollect> queryByPage(TeacherCollect teacherCollect, PageRequest pageRequest) {
        long total = this.teacherCollectDao.count(teacherCollect);
        return new PageImpl<>(this.teacherCollectDao.queryAllByLimit(teacherCollect, pageRequest), pageRequest, total);
    }

    @Override
    public List<TeacherCollect> addCollect(Long userId, Long compositionId, int type) {
        TeacherCollect teacherCollect = new TeacherCollect();
        teacherCollect.setIsused(0);
        teacherCollect.setUserId(userId);
        teacherCollect.setCompositionId(compositionId);
        teacherCollect.setType(type);
        List<TeacherCollect> collectCollectList = this.queryAll(teacherCollect);
        if(collectCollectList.size() == 0){
            if (type == 0){
                Composition composition=compositionService.queryById(compositionId);
                if (composition != null) {
                    teacherCollect.setCreateTime(new Date());
                    teacherCollect.setUpdateTime(new Date());
                    teacherCollect.setTitle(composition.getCTitle());
                    teacherCollect.setImage(composition.getCImageb());
                    this.teacherCollectDao.insert(teacherCollect);
                }
            }else if (type == 1){
                Department department= departmentService.queryById(compositionId);
                if (department != null) {
                    teacherCollect.setCreateTime(new Date());
                    teacherCollect.setUpdateTime(new Date());
                    teacherCollect.setTitle(department.getDTitle());
                    teacherCollect.setImage(department.getDImage());
                    this.teacherCollectDao.insert(teacherCollect);
                }
            }
        }
        TeacherCollect tt=new TeacherCollect();
        tt.setIsused(0);
        tt.setUserId(userId);
        return this.teacherCollectDao.queryAll(tt);
    }

    @Override
    public List<TeacherCollect> queryAll(TeacherCollect teacherCollect) {
        return this.teacherCollectDao.queryAll(teacherCollect);
    }
    /**
     * 新增数据
     *
     * @param teacherCollect 实例对象
     * @return 实例对象
     */
    @Override
    public TeacherCollect insert(TeacherCollect teacherCollect) {
        this.teacherCollectDao.insert(teacherCollect);
        return teacherCollect;
    }

    /**
     * 修改数据
     *
     * @param teacherCollect 实例对象
     * @return 实例对象
     */
    @Override
    public TeacherCollect update(TeacherCollect teacherCollect) {
        this.teacherCollectDao.update(teacherCollect);
        return this.queryById(teacherCollect.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.teacherCollectDao.deleteById(id) > 0;
    }
}
