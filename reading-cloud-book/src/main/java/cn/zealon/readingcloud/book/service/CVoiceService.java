package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CVoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 音频表(CVoice)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:14:00
 */
public interface CVoiceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CVoice queryById(Long id);

    /**
     * 分页查询
     *
     * @param cVoice      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CVoice> queryByPage(CVoice cVoice, PageRequest pageRequest);

    List<CVoice> queryAll(CVoice cVoice);
    /**
     * 新增数据
     *
     * @param cVoice 实例对象
     * @return 实例对象
     */
    CVoice insert(CVoice cVoice);

    /**
     * 修改数据
     *
     * @param cVoice 实例对象
     * @return 实例对象
     */
    CVoice update(CVoice cVoice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
