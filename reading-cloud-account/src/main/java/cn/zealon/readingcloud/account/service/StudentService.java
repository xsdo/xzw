package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.Student;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * 学生表(Student)表服务接口
 *
 * @author makejava
 * @since 2023-04-10 15:47:46
 */
public interface StudentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Student queryById(Long id);

    List<Student> queryAll(Student student);

    /**
     * 分页查询
     *
     * @param student     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Student> queryByPage(Student student, PageRequest pageRequest);

    List<String> queryByTeacherId(Long teacherId);

    Map<String,String> queryHeadByTeacherId(Long teacherId);

    List<Student>queryByName(Long teacherId,String name);

    JSONObject doBinding(Long userId, Long teacherId, String name, String relation, String phoneNumber, int type);

    JSONObject conductBingding(Long userId,String name);
    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    Student insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    Student update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
