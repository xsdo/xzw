package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.Images;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 随机图表(Images)表服务接口
 *
 * @author makejava
 * @since 2023-03-22 18:22:35
 */
public interface ImagesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Images queryById(Long id);

    /**
     * 分页查询
     *
     * @param images      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Images> queryByPage(Images images, PageRequest pageRequest);

    Map<String, String> updateImage(MultipartFile multipartFile, MultipartFile multipartFile2 );

    Images queryRand();
    /**
     * 新增数据
     *
     * @param images 实例对象
     * @return 实例对象
     */
    Images insert(Images images);

    /**
     * 修改数据
     *
     * @param images 实例对象
     * @return 实例对象
     */
    Images update(Images images);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
