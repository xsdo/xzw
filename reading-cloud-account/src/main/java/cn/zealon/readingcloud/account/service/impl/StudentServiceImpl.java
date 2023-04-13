package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.dao.StudentDao;
import cn.zealon.readingcloud.account.service.StudentService;
import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.account.service.UTeacherService;
import cn.zealon.readingcloud.common.pojo.xzwusers.Student;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.UBinding;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

/**
 * 学生表(Student)表服务实现类
 *
 * @author makejava
 * @since 2023-04-10 15:47:46
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    @Resource
    private UAttributeService uAttributeService;

    @Resource
    private UTeacherService uTeacherService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Student queryById(Long id) {
        return this.studentDao.queryById(id);
    }

    @Override
    public List<Student>queryAll(Student student){
        return this.studentDao.queryAll(student);
    }
    /**
     * 分页查询
     *
     * @param student     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Student> queryByPage(Student student, PageRequest pageRequest) {
        long total = this.studentDao.count(student);
        return new PageImpl<>(this.studentDao.queryAllByLimit(student, pageRequest), pageRequest, total);
    }

    @Override
    public List<String> queryByTeacherId(Long teacherId){
        List<String>studentList=new ArrayList<>();
        Student student = new Student();
        student.setTeacherId(teacherId);
        List<Student>students=this.queryAll(student);
        if (students.size() > 0) {
            for (Student ss:students) {
                if (!studentList.contains(ss.getName())){
                    studentList.add(ss.getName());
                }
            }
        }
        return studentList;
    }

    @Override
    public List<Student>queryByName(Long teacherId,String name){
        Student student=new Student();
        student.setTeacherId(teacherId);
        student.setName(name);
        return this.queryAll(student);
    }

    @Override
    public JSONObject doBinding(Long userId,Long teacherId,String name, String relation,String phoneNumber,int type) {
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            UAttribute uAttribute =this.uAttributeService.queryById(userId);
            if (uAttribute != null) {
                if (uAttribute.getUType()==5) {
                    result.put("sign", -1);
                    data.put("data", "班主任不可加入其他班级");
                }else {
                    Student student = new Student();
                    student.setUserId(userId);
                    List<Student> studentListOne = this.studentDao.queryAll(student);
                    if (studentListOne.size() > 0) {
                        Student s = studentListOne.get(0);
                        if (s != null) {
                            result.put("sign", -1);
                            data.put("data", "您已加入其他班级");
                            result.put("data", data);
                            return result;
                        }
                    }
                    student.setTeacherId(teacherId);
                    List<Student> studentList = this.studentDao.queryAll(student);
                    if (studentList.size() > 0) {
                        Student s = studentList.get(0);
                        if (s != null) {
                            result.put("sign", -1);
                            data.put("data", "您已加入该班级");
                            result.put("data", data);
                            return result;
                        }
                    }
                    Student studentStudent = new Student();
                    studentStudent.setTeacherId(teacherId);
                    studentStudent.setName(name);
                    if (type == 0) {
                        List<Student> studentList2 = this.studentDao.queryAll(studentStudent);
                        if (studentList2.size() > 0) {
                            result.put("sign", -1);
                            data.put("data", "该学生已存在其他家长账号下");
                            data.put("student", studentList2);
                            result.put("data", data);
                            return result;
                        }else {
                            studentStudent.setUserId(userId);
                            studentStudent.setRelation(relation);
                            studentStudent.setPhonenumber(phoneNumber);
                            this.studentDao.insert(studentStudent);
                            UAttribute u=this.uAttributeService.queryById(userId);
                            if (u!=null){
                                u.setUpdateTime(new Date());
                                u.setTeacherid(teacherId);
                                this.uAttributeService.update(u);
                            }
                            result.put("sign", 00);
                            data.put("data", "加入班级成功");
                        }
                    } else if (type == 1) {
                        studentStudent.setRelation(relation);
                        List<Student> studentList2 = this.studentDao.queryAll(studentStudent);
                        if (studentList2.size() > 0) {
                            result.put("sign", -1);
                            data.put("data", "该学生的" + relation+"已加入班级");
                            data.put("student", studentList2);
                            result.put("data", data);
                            return result;
                        } else {
                            studentStudent.setUserId(userId);
                            studentStudent.setPhonenumber(phoneNumber);
                            this.studentDao.insert(studentStudent);
                            UAttribute u=this.uAttributeService.queryById(userId);
                            if (u!=null){
                                u.setUpdateTime(new Date());
                                u.setTeacherid(teacherId);
                                this.uAttributeService.update(u);
                            }
                            result.put("sign", 00);
                            data.put("data", "加入班级成功");
                        }
                    } else if (type == 2) {
                        studentStudent.setName(name + 1);
                        studentStudent.setRelation(relation);
                        List<Student> studentList2 = this.studentDao.queryAll(studentStudent);
                        if (studentList2.size() > 0) {
                            result.put("sign", -1);
                            data.put("data", "该学生的" + relation+"已加入班级");
                            data.put("student", studentList2);
                            result.put("data", data);
                            return result;
                        } else {
                            studentStudent.setUserId(userId);
                            studentStudent.setPhonenumber(phoneNumber);
                            this.studentDao.insert(studentStudent);
                            UAttribute u=this.uAttributeService.queryById(userId);
                            if (u!=null){
                                u.setUpdateTime(new Date());
                                u.setTeacherid(teacherId);
                                this.uAttributeService.update(u);
                            }
                            result.put("sign", 00);
                            data.put("data", "加入班级成功");
                        }
                    }

                    result.put("data", data);
                }
            }else {
                result.put("sign",-1);
                result.put("data","用户不存在");
            }
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }
    @Override
    public JSONObject conductBingding(Long userId,String name){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            UTeacher teacher = new UTeacher();
            teacher.setTeacherId(userId);
            List<UTeacher>teacherList=this.uTeacherService.queryAll(teacher);
            if (teacherList.size()>0){
            UTeacher uteacher=teacherList.get(0);
            if (uteacher != null) {
                    Student student = new Student();
                    student.setName(name);
                    student.setTeacherId(uteacher.getId());
                    List<Student> studentList = this.studentDao.queryAll(student);
                    if (studentList.size() > 0) {
                        for (Student s:studentList) {
                           this.studentDao.deleteById(s.getId());
                            UAttribute u=this.uAttributeService.queryById(s.getUserId());
                            if (u!=null){
                                u.setUpdateTime(new Date());
                                u.setTeacherid(new Long(-1));
                                this.uAttributeService.update(u);
                            }
                        }
                        result.put("sign", 00);
                        data.put("data", "已删除");
                    } else {
                        result.put("sign", -1);
                        data.put("data", "该学生不存在");
                    }

            }else {
                result.put("sign",-1);
                result.put("data","请使用教师身份操作");
            }
            result.put("data",data);
            }else {
                result.put("sign",-1);
                result.put("data","用户不存在");
            }
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }
    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @Override
    public Student insert(Student student) {
        this.studentDao.insert(student);
        return student;
    }

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @Override
    public Student update(Student student) {
        this.studentDao.update(student);
        return this.queryById(student.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.studentDao.deleteById(id) > 0;
    }
}
