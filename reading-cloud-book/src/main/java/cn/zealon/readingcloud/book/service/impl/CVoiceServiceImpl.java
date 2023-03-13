package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CVoice;
import cn.zealon.readingcloud.book.dao.CVoiceDao;
import cn.zealon.readingcloud.book.service.CVoiceService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 音频表(CVoice)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:14:00
 */
@Service("cVoiceService")
public class CVoiceServiceImpl implements CVoiceService {
    @Resource
    private CVoiceDao cVoiceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CVoice queryById(Long id) {
        return this.cVoiceDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cVoice      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CVoice> queryByPage(CVoice cVoice, PageRequest pageRequest) {
        long total = this.cVoiceDao.count(cVoice);
        return new PageImpl<>(this.cVoiceDao.queryAllByLimit(cVoice, pageRequest), pageRequest, total);
    }

    @Override
    public List<CVoice>queryAll(CVoice cVoice){
        return this.cVoiceDao.queryAll(cVoice);
    }
    /**
     * 新增数据
     *
     * @param cVoice 实例对象
     * @return 实例对象
     */
    @Override
    public CVoice insert(CVoice cVoice) {
        this.cVoiceDao.insert(cVoice);
        return cVoice;
    }

    /**
     * 修改数据
     *
     * @param cVoice 实例对象
     * @return 实例对象
     */
    @Override
    public CVoice update(CVoice cVoice) {
        this.cVoiceDao.update(cVoice);
        return this.queryById(cVoice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cVoiceDao.deleteById(id) > 0;
    }
}
