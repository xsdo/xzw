package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CAuthors;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 作者表(CAuthors)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:14:02
 */
public interface CAuthorsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CAuthors queryById(Long id);

    /**
     * 分页查询
     *
     * @param cAuthors    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CAuthors> queryByPage(CAuthors cAuthors, PageRequest pageRequest);

    List<CAuthors> queryAll(CAuthors cAuthors);

    JSONObject toAuthors(String title, String name, String province, String city, String area, String school );
    /**
     * 新增数据
     *
     * @param cAuthors 实例对象
     * @return 实例对象
     */
    CAuthors insert(CAuthors cAuthors);

    /**
     * 修改数据
     *
     * @param cAuthors 实例对象
     * @return 实例对象
     */
    CAuthors update(CAuthors cAuthors);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
