package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.TeacherCollect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 教师收藏表(TeacherCollect)表服务接口
 *
 * @author makejava
 * @since 2023-04-10 15:47:49
 */
public interface TeacherCollectService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TeacherCollect queryById(Long id);

    /**
     * 分页查询
     *
     * @param teacherCollect 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    Page<TeacherCollect> queryByPage(TeacherCollect teacherCollect, PageRequest pageRequest);

    /**
     * 收藏
     *
     * @param userId
     * @param compositionId
     * @param type
     * @return
     */
    List<TeacherCollect> addCollect(Long userId,Long compositionId,int type);

    /**
     * 查询所有数据
     *
     * @param teacherCollect 筛选条件
     * @return 对象列表
     */
    List<TeacherCollect> queryAll(TeacherCollect teacherCollect);
    /**
     * 新增数据
     *
     * @param teacherCollect 实例对象
     * @return 实例对象
     */
    TeacherCollect insert(TeacherCollect teacherCollect);

    /**
     * 修改数据
     *
     * @param teacherCollect 实例对象
     * @return 实例对象
     */
    TeacherCollect update(TeacherCollect teacherCollect);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
