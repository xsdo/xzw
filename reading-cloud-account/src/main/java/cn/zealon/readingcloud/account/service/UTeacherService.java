package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.USchool;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTeacher;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 教师班级表(UTeacher)表服务接口
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
public interface UTeacherService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UTeacher queryById(Long id);

    Map<String, String> updateHeadImg(MultipartFile multipartFile);
    UTeacher changeHead(Long teacherId, String fileUrl);
    UTeacher changeTeacherHead(Long teacherId, String fileUrl);


    UTeacher teacherQRCode(Long teacherId);

    UTeacher teacherQRCodePress(Long teacherId);

    JSONObject doBinding(Long userId, Long schoolId , String grade, String term, int student);
    /**
     * 分页查询
     *
     * @param uTeacher    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UTeacher> queryByPage(UTeacher uTeacher, PageRequest pageRequest);

    List<UTeacher> queryAll(UTeacher uTeacher);
    /**
     * 新增数据
     *
     * @param uTeacher 实例对象
     * @return 实例对象
     */
    UTeacher insert(UTeacher uTeacher);

    /**
     * 修改数据
     *
     * @param uTeacher 实例对象
     * @return 实例对象
     */
    UTeacher update(UTeacher uTeacher);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
