package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.account.feign.client.TeacherClient;
import cn.zealon.readingcloud.account.feign.client.UserAttributeClient;
import cn.zealon.readingcloud.book.service.CompositionService;
import cn.zealon.readingcloud.book.service.DepartmentService;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.common.pojo.xzwresources.Department;
import cn.zealon.readingcloud.common.pojo.xzwresources.TeacherTasks;
import cn.zealon.readingcloud.book.dao.TeacherTasksDao;
import cn.zealon.readingcloud.book.service.TeacherTasksService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 教师任务表(TeacherTasks)表服务实现类
 *
 * @author makejava
 * @since 2023-04-10 15:47:51
 */
@Service("teacherTasksService")
public class TeacherTasksServiceImpl implements TeacherTasksService {
    @Resource
    private TeacherTasksDao teacherTasksDao;

    @Resource
    private CompositionService compositionService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private UserAttributeClient userAttributeClient;
    @Resource
    private TeacherClient teacherClient;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TeacherTasks queryById(Long id) {
        return this.teacherTasksDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param teacherTasks 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<TeacherTasks> queryByPage(TeacherTasks teacherTasks, PageRequest pageRequest) {
        long total = this.teacherTasksDao.count(teacherTasks);
        return new PageImpl<>(this.teacherTasksDao.queryAllByLimit(teacherTasks, pageRequest), pageRequest, total);
    }

    @Override
    public List<TeacherTasks> queryAll(TeacherTasks teacherTasks){
        return this.teacherTasksDao.queryAll(teacherTasks);
    }

    @Override
    public void pushTask(){
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String taskTime=today+" 00:00:00";
        List<UTeacher>teacherList= teacherClient.getAllTeacher();
        if (teacherList.size() > 0) {
            for (UTeacher uTeacher : teacherList) {
                List<TeacherTasks> teacherTasksList = this.queryTaskByTeacherId(uTeacher.getId(), taskTime);
                if (teacherTasksList.size() > 0) {
                    System.out.println("教师添加任务班级"+uTeacher.getId());
                    for (TeacherTasks teacherTasks : teacherTasksList) {
                        teacherTasks.setStatus(1);
                        this.update(teacherTasks);
                    }
                }else {
                    System.out.println("自动推送任务班级"+uTeacher.getId());
                    List<Composition>compositionList=this.compositionService.queryRandoms(3);
                    System.out.println(compositionList);
                    for (Composition composition: compositionList) {
                        this.addTask0(uTeacher.getTeacherId(), composition.getId(), 0, taskTime,1);
                    }
                }
            }
        }

    }
    /**
     * 新增数据
     *
     */
    @Override
    public List<TeacherTasks> addTask(Long userId, Long compositionId,int type, String taskTime) {
        return this.addTask0(userId,compositionId,type,taskTime,0);
    }
    public List<TeacherTasks> addTask0(Long userId, Long compositionId,int type, String taskTime,int status) {
        System.out.println(userId);
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(taskTime);
        } catch (ParseException e) {
            //LOGGER.error("时间转换错误, string = {}", taskTime, e);
        }
        TeacherTasks teacherTasks = new TeacherTasks();
        teacherTasks.setIsused(0);
        teacherTasks.setUserId(userId);
        teacherTasks.setTaskTime(date);
        List<TeacherTasks> list = this.queryAll(teacherTasks);
        if (list.size() < 3) {
            teacherTasks.setCompositionId(compositionId);
            teacherTasks.setType(type);
            List<TeacherTasks> teacherTaskList = this.queryAll(teacherTasks);
            if (teacherTaskList.size() == 0) {
                teacherTasks.setCreateTime(new Date());
                teacherTasks.setUpdateTime(new Date());
                teacherTasks.setStatus(status);
                UAttribute uAttribute = userAttributeClient.queryByUserId(userId);
                if (uAttribute != null) {
                    if (uAttribute.getUType() == 5) {
                        teacherTasks.setTeacherId(uAttribute.getTeacherid());
                        if (type == 0) {
                            Composition composition = compositionService.queryById(compositionId);
                            if (composition != null) {
                                teacherTasks.setTitle(composition.getCTitle());
                                this.insert(teacherTasks);
                            }
                        } else if (type == 1) {
                            Department department = departmentService.queryById(compositionId);
                            if (department != null) {
                                teacherTasks.setTitle(department.getDTitle());
                                this.insert(teacherTasks);
                            }
                        }
                    }
                }

            }

        }
        TeacherTasks tt = new TeacherTasks();
        tt.setIsused(0);
        tt.setUserId(userId);
        tt.setTaskTime(date);
        return this.queryAll(tt);
    }
    @Override
    public List<TeacherTasks> queryTask(Long userId, String taskTime){
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(taskTime);
        } catch (ParseException e) {
            //LOGGER.error("时间转换错误, string = {}", taskTime, e);
        }
        TeacherTasks teacherTasks = new TeacherTasks();
        teacherTasks.setUserId(userId);
        teacherTasks.setIsused(0);
        teacherTasks.setTaskTime(date);
        return this.queryAll(teacherTasks);
    }
    @Override
    public List<TeacherTasks> queryTaskByTeacherId(Long teacherId, String taskTime){
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(taskTime);
        } catch (ParseException e) {
            //LOGGER.error("时间转换错误, string = {}", taskTime, e);
        }
        TeacherTasks teacherTasks = new TeacherTasks();
        teacherTasks.setTeacherId(teacherId);
        teacherTasks.setIsused(0);
        teacherTasks.setTaskTime(date);
        return this.queryAll(teacherTasks);
    }

    /**
     * 新增数据
     *
     * @param teacherTasks 实例对象
     * @return 实例对象
     */
    @Override
    public TeacherTasks insert(TeacherTasks teacherTasks) {
        this.teacherTasksDao.insert(teacherTasks);
        return teacherTasks;
    }

    /**
     * 修改数据
     *
     * @param teacherTasks 实例对象
     * @return 实例对象
     */
    @Override
    public TeacherTasks update(TeacherTasks teacherTasks) {
        this.teacherTasksDao.update(teacherTasks);
        return this.queryById(teacherTasks.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.teacherTasksDao.deleteById(id) > 0;
    }
}
