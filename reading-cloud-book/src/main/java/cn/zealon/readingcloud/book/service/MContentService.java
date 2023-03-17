package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.book.vo.MagazinesVO;
import cn.zealon.readingcloud.common.pojo.xzwresources.MContent;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 杂志内容表(MContent)表服务接口
 *
 * @author makejava
 * @since 2023-03-15 17:17:09
 */
public interface MContentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MContent queryById(Long id);

    /**
     * 分页查询
     *
     * @param mContent    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<MContent> queryByPage(MContent mContent, PageRequest pageRequest);

    List<MContent> queryAll(MContent mContent);

    List<MagazinesVO>queryByMagazinesId(Long magazinesId);


    JSONObject toMagazines(Long magazinesId, Long compositionId);

    void randMagazines(Long magazinesId,int size);
    /**
     * 新增数据
     *
     * @param mContent 实例对象
     * @return 实例对象
     */
    MContent insert(MContent mContent);

    /**
     * 修改数据
     *
     * @param mContent 实例对象
     * @return 实例对象
     */
    MContent update(MContent mContent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
