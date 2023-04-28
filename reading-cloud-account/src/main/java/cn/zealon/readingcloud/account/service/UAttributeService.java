package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 用户属性表(UAttribute)表服务接口
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
public interface UAttributeService {


//    Map<String, String> updateHead(MultipartFile multipartFile);

    Map<String, String> updateHeadImg(MultipartFile multipartFile);
    UAttribute changeHead(Long userId,String fileUrl);

    Map<String, String> updateBackGround(MultipartFile multipartFile);
    UAttribute changeBackGround(Long userId,String fileUrl);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UAttribute queryById(Long id);

    /**
     * 分页查询
     *
     * @param uAttribute  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UAttribute> queryByPage(UAttribute uAttribute, PageRequest pageRequest);

    List<UAttribute> queryAll(UAttribute uAttribute);


    List<UAttribute>queryRand(int size);
    /**
     * 新增数据
     *
     * @param uAttribute 实例对象
     * @return 实例对象
     */
    UAttribute insert(UAttribute uAttribute);

    /**
     * 修改数据
     *
     * @param uAttribute 实例对象
     * @return 实例对象
     */
    UAttribute update(UAttribute uAttribute);

    Boolean checkName(Long userId);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
