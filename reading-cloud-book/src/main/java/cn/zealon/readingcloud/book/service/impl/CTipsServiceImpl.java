package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CTips;
import cn.zealon.readingcloud.book.dao.CTipsDao;
import cn.zealon.readingcloud.book.service.CTipsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 举报表(CTips)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:59
 */
@Service("cTipsService")
public class CTipsServiceImpl implements CTipsService {
    @Resource
    private CTipsDao cTipsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CTips queryById(Long id) {
        return this.cTipsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cTips       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CTips> queryByPage(CTips cTips, PageRequest pageRequest) {
        long total = this.cTipsDao.count(cTips);
        return new PageImpl<>(this.cTipsDao.queryAllByLimit(cTips, pageRequest), pageRequest, total);
    }

    @Override
    public List<CTips>queryAll(CTips cTips){
        return this.cTipsDao.queryAll(cTips);
    }
    /**
     * 新增数据
     *
     * @param cTips 实例对象
     * @return 实例对象
     */
    @Override
    public CTips insert(CTips cTips) {
        this.cTipsDao.insert(cTips);
        return cTips;
    }

    /**
     * 修改数据
     *
     * @param cTips 实例对象
     * @return 实例对象
     */
    @Override
    public CTips update(CTips cTips) {
        this.cTipsDao.update(cTips);
        return this.queryById(cTips.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cTipsDao.deleteById(id) > 0;
    }
}
