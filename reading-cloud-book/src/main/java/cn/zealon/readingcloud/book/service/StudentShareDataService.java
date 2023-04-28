package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.StudentShareData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 学生分享表(StudentShareData)表服务接口
 *
 * @author makejava
 * @since 2023-04-26 10:21:09
 */
public interface StudentShareDataService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StudentShareData queryById(Long id);


    int queryNumberByUserId(Long userId);


    StudentShareData queryChatNumberByUserId(Long userId);
    StudentShareData doShare(Long userId);


    void doChat(Long userId);
    /**
     * 分页查询
     *
     * @param studentShareData 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<StudentShareData> queryByPage(StudentShareData studentShareData, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param studentShareData 实例对象
     * @return 实例对象
     */
    StudentShareData insert(StudentShareData studentShareData);

    /**
     * 修改数据
     *
     * @param studentShareData 实例对象
     * @return 实例对象
     */
    StudentShareData update(StudentShareData studentShareData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
