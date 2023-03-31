package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.bean.PageBean;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * 作文表(Composition)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:14:01
 */
public interface CompositionService {



    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Composition queryById(Long id);


    PageBean<Composition> pageQuery(JSONObject jsonObject);

    /**
     * 分页查询
     *
     * @param composition 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Composition> queryByPage(Composition composition, PageRequest pageRequest);

    List<Composition> queryAll(Composition composition);

    List<Map<String,String>>queryContent();

    List<Composition>queryRandoms(int size);

    Composition compositionQRCode(Long compositionId);
    /**
     * 新增数据
     *
     * @param composition 实例对象
     * @return 实例对象
     */
    Composition insert(Composition composition);

    /**
     * 修改数据
     *
     * @param composition 实例对象
     * @return 实例对象
     */
    Composition update(Composition composition);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
