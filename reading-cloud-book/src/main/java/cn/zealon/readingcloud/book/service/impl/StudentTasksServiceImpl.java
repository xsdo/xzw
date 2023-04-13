package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.account.feign.client.StudentClient;
import cn.zealon.readingcloud.account.feign.client.TeacherClient;
import cn.zealon.readingcloud.book.dao.StudentTasksDao;
import cn.zealon.readingcloud.book.service.StudentTasksService;
import cn.zealon.readingcloud.book.service.TeacherTasksService;
import cn.zealon.readingcloud.book.vo.StatisticsVO;
import cn.zealon.readingcloud.book.vo.StudentTasksVO;
import cn.zealon.readingcloud.book.vo.StudentVO;
import cn.zealon.readingcloud.common.pojo.xzwresources.StudentTasks;
import cn.zealon.readingcloud.common.pojo.xzwresources.TeacherTasks;
import cn.zealon.readingcloud.common.pojo.xzwusers.Student;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 学生任务表(StudentTasks)表服务实现类
 *
 * @author makejava
 * @since 2023-04-10 15:47:48
 */
@Service("studentTasksService")
public class StudentTasksServiceImpl implements StudentTasksService {
    @Resource
    private StudentTasksDao studentTasksDao;

    @Resource
    private StudentClient studentClient;

    @Resource
    private TeacherTasksService teacherTasksService;

    @Resource
    private TeacherClient teacherClient;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public StudentTasks queryById(Long id) {
        return this.studentTasksDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param studentTasks 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<StudentTasks> queryByPage(StudentTasks studentTasks, PageRequest pageRequest) {
        long total = this.studentTasksDao.count(studentTasks);
        return new PageImpl<>(this.studentTasksDao.queryAllByLimit(studentTasks, pageRequest), pageRequest, total);
    }

    public List<StudentTasks> queryAll(StudentTasks studentTasks){
        return studentTasksDao.queryAll(studentTasks);
    }
    @Override
    public StatisticsVO queryStatistics(Long teacherId ,String taskTime){
        StatisticsVO statisticsVO=new StatisticsVO();
        //查询老师学生数量
        List<UTeacher>teacherList=teacherClient.getAllTeacher();
        if (teacherList != null && teacherList.size() > 0) {
            for (UTeacher uTeacher : teacherList) {
                if (uTeacher.getId().equals(teacherId)){
                    statisticsVO.setStudentCount(uTeacher.getTStudent());
                }
            }
        }
        //查询老师任务数量
        List<TeacherTasks>teacherTasks=teacherTasksService.queryTaskByTeacherId(teacherId,taskTime);
        if (teacherTasks.size() > 0){
            statisticsVO.setTaskCount(teacherTasks.size());
        }
        //查询老师学生完成情况
        List<StudentVO>studentVOList=this.queryAllByTime(teacherId,taskTime);
        if (studentVOList.size() > 0){
            List<String>unfinishStudentNames=new ArrayList<>();
            int finish=0;
            int unfinish=0;
            int unsend=0;
            BigDecimal finishRate=new BigDecimal(0);

            for (StudentVO studentVO : studentVOList) {
                if (studentVO.getStatus() == 2){
                    finish++;
                }else if (studentVO.getStatus() == 1){
                    unfinishStudentNames.add(studentVO.getStudentName());
                    unfinish++;
                }else {
                    unfinishStudentNames.add(studentVO.getStudentName());
                    unfinish++;
                    unsend++;
                }
            }
            finishRate=new BigDecimal(finish).divide(new BigDecimal(statisticsVO.getStudentCount()),2,BigDecimal.ROUND_HALF_UP);
            statisticsVO.setFinishCount(finish);
            statisticsVO.setUnfinishCount(unfinish);
            statisticsVO.setSendCount(finish+unfinish-unsend);
            statisticsVO.setFinishRate(finishRate);
            statisticsVO.setUnfinishStudentNames(unfinishStudentNames);
        }
        return statisticsVO;
    }

    @Override
    public List<StudentVO> queryAllByTime(Long teacherId ,String taskTime){
        List<StudentTasksVO> studentTasksVOList=new ArrayList<>();
        List<TeacherTasks>teacherTasksList=teacherTasksService.queryTaskByTeacherId(teacherId,taskTime);
        if (teacherTasksList.size() > 0){
            for (TeacherTasks teacherTasks : teacherTasksList) {
                StudentTasks studentTasks=new StudentTasks();
                studentTasks.setTaskId(teacherTasks.getId());
                List<StudentTasks> studentTasksList1=studentTasksDao.queryAll(studentTasks);
                if (studentTasksList1.size() > 0){
                    for (StudentTasks tasks : studentTasksList1) {
                        StudentTasksVO studentTasksVO=new StudentTasksVO();
                        studentTasksVO.setUserId(tasks.getUserId());
                        studentTasksVO.setStudentName(tasks.getStudentName());
                        studentTasksVO.setStatus(tasks.getStatus());
                        studentTasksVOList.add(studentTasksVO);
                    }
                }
            }
        }
        return checkMaxStatus(checkMinStatus(studentTasksVOList));
    }
    public List<StudentTasksVO> checkMinStatus(List<StudentTasksVO> studentTasksVOList){
        // 创建一个Map对象，用于存储每个userId对应的最小值
        Map<Long, Integer> minStatusMap = new HashMap<>();
        // 遍历List集合
        for (StudentTasksVO studentTasksVO : studentTasksVOList) {
            // 获取当前元素的userId
            Long userId = studentTasksVO.getUserId();
            // 获取当前元素的status
            Integer status = studentTasksVO.getStatus();
            // 判断Map中是否已经存在该userId
            if (minStatusMap.containsKey(userId)) {
                // 如果存在，获取该userId对应的最小值
                Integer minStatus = minStatusMap.get(userId);
                // 判断当前元素的status是否小于最小值
                if (status < minStatus) {
                    // 如果小于，将当前元素的status作为最小值
                    minStatusMap.put(userId, status);
                }
            } else {
                // 如果不存在，将当前元素的status作为最小值
                minStatusMap.put(userId, status);
            }
        }
        // 遍历Map
        for (Map.Entry<Long, Integer> entry : minStatusMap.entrySet()) {
            // 获取userId
            Long userId = entry.getKey();
            // 获取最小值
            Integer minStatus = entry.getValue();
            // 遍历List集合
            for (StudentTasksVO studentTasksVO : studentTasksVOList) {
                // 判断当前元素的userId是否等于最小值对应的userId
                if (studentTasksVO.getUserId().equals(userId)) {
                    // 如果相等，将最小值设置为当前元素的status
                    studentTasksVO.setStatus(minStatus);
                }
            }
        }

        return studentTasksVOList;
    }
    public List<StudentVO> checkMaxStatus(List<StudentTasksVO> studentTasksVOList){
        List<StudentVO> studentVOList=new ArrayList<>();
        // 使用 Map 存储相同姓名的学生记录
        Map<String, StudentVO> studentMap = new HashMap<>();
        // 遍历List集合
        for (StudentTasksVO studentTasksVO : studentTasksVOList) {
            // 获取当前元素的姓名
            String studentName = studentTasksVO.getStudentName();
            // 获取当前元素的status
            Integer status = studentTasksVO.getStatus();
            // 判断Map中是否已经存在该姓名
            if (studentMap.containsKey(studentName)) {
                // 如果存在，获取该姓名对应的最大值
                Integer maxStatus = studentMap.get(studentName).getStatus();
                // 判断当前元素的status是否大于最大值
                if (status > maxStatus) {
                    // 如果大于，将当前元素的status作为最大值
                    studentMap.get(studentName).setStatus(status);
                }
            } else {
                // 如果不存在，将当前元素的status作为最大值
                StudentVO studentVO=new StudentVO();
                studentVO.setStudentName(studentName);
                studentVO.setStatus(status);
                studentMap.put(studentName, studentVO);
            }
        }
        // 遍历Map 输出结果
        for (Map.Entry<String, StudentVO> entry : studentMap.entrySet()) {
            studentVOList.add(entry.getValue());
        }
        return studentVOList;
    }
    @Override
    public List<StudentTasks> queryByUserId(Long userId){
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String taskTime=today+" 00:00:00";
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(taskTime);
        } catch (ParseException e) {
            //LOGGER.error("时间转换错误, string = {}", taskTime, e);
        }
        StudentTasks studentTasks=new StudentTasks();
        studentTasks.setUserId(userId);
        List<StudentTasks> studentTasksList=studentTasksDao.queryAll(studentTasks);
        List<StudentTasks> studentTasksToday=new ArrayList<>();
        if (studentTasksList.size() > 0){
            for (StudentTasks tasks : studentTasksList) {
                TeacherTasks teacherTasks=this.teacherTasksService.queryById(tasks.getTaskId());
                if (teacherTasks != null){
                    if (teacherTasks.getTaskTime().getTime() == date.getTime()){
                        studentTasksToday.add(tasks);
                    }
                }
            }
        }
        return studentTasksToday;
    }

    @Override
    public void updateByUserId(Long userId) {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String taskTime=today+" 00:00:00";
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(taskTime);
        } catch (ParseException e) {
            //LOGGER.error("时间转换错误, string = {}", taskTime, e);
        }
        StudentTasks studentTasks=new StudentTasks();
        studentTasks.setUserId(userId);
        List<StudentTasks> studentTasksList=studentTasksDao.queryAll(studentTasks);
        if (studentTasksList.size() > 0){
            for (StudentTasks tasks : studentTasksList) {
                TeacherTasks teacherTasks=this.teacherTasksService.queryById(tasks.getTaskId());
                if (teacherTasks != null){
                    if (teacherTasks.getTaskTime().getTime() == date.getTime()){
                        tasks.setStatus(1);
                        studentTasksDao.update(tasks);
                    }
                }
            }
        }
    }
    @Override
    public void updateStatus(Long id){
        StudentTasks studentTasks=new StudentTasks();
        studentTasks.setId(id);
        studentTasks.setStatus(2);
        studentTasksDao.update(studentTasks);
    }

    @Override
    public void puskTask(){
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String taskTime=today+" 00:00:00";
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(taskTime);
        } catch (ParseException e) {
            //LOGGER.error("时间转换错误, string = {}", taskTime, e);
        }
        List<Student>studentList = studentClient.getAllStudent();
        if (studentList.size() > 0){
            for (Student student : studentList) {
                TeacherTasks teacherTasks=new TeacherTasks();
                teacherTasks.setTeacherId(student.getTeacherId());
                teacherTasks.setStatus(1);
                teacherTasks.setTaskTime(date);
                List<TeacherTasks> teacherTasksList=teacherTasksService.queryAll(teacherTasks);
                if (teacherTasksList.size() > 0){
                    for (TeacherTasks teacherTask : teacherTasksList) {
                        StudentTasks studentTasks=new StudentTasks();
                        studentTasks.setUserId(student.getUserId());
                        studentTasks.setTaskId(teacherTask.getId());
                        studentTasks.setTeacherId(teacherTask.getTeacherId());
                        studentTasks.setStudentName(student.getName());
                        List<StudentTasks> studentTasksList=studentTasksDao.queryAll(studentTasks);
                        if (studentTasksList.size() == 0){
                            studentTasks.setStatus(0);
                            studentTasksDao.insert(studentTasks);
                        }
                    }
                }
            }
        }
    }


    /**
     * 新增数据
     *
     * @param studentTasks 实例对象
     * @return 实例对象
     */
    @Override
    public StudentTasks insert(StudentTasks studentTasks) {
        this.studentTasksDao.insert(studentTasks);
        return studentTasks;
    }

    /**
     * 修改数据
     *
     * @param studentTasks 实例对象
     * @return 实例对象
     */
    @Override
    public StudentTasks update(StudentTasks studentTasks) {
        this.studentTasksDao.update(studentTasks);
        return this.queryById(studentTasks.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.studentTasksDao.deleteById(id) > 0;
    }
}
