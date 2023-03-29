package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.dao.UNoticeDao;
import cn.zealon.readingcloud.account.service.UNoticeService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UNotice;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 消息通知表(UNotice)表服务实现类
 *
 * @author makejava
 * @since 2023-03-09 17:33:38
 */
@Service("uNoticeService")
public class UNoticeServiceImpl implements UNoticeService {
    @Resource
    private UNoticeDao uNoticeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UNotice queryById(Long id) {
        return this.uNoticeDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uNotice     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UNotice> queryByPage(UNotice uNotice, PageRequest pageRequest) {
        long total = this.uNoticeDao.count(uNotice);
        return new PageImpl<>(this.uNoticeDao.queryAllByLimit(uNotice, pageRequest), pageRequest, total);
    }

    @Override
    public List<UNotice> queryAll(UNotice uNotice){
        return this.uNoticeDao.queryAll(uNotice);
    }

    @Override
    public void doNotice(Long userId,String name,int type,String coment){
        UNotice unotice =new UNotice();
        unotice.setIsused(0);
        unotice.setUserId(userId);
        unotice.setCreateTime(new Date());
        unotice.setUpdateTime(new Date());
        unotice.setReadoff(0);
        unotice.setNName(name);
        unotice.setNType(type);
        unotice.setComent(coment);
        this.insert(unotice);
    }

    @Override
    public long countNotice (Long userId){
        UNotice uNotice =new UNotice();
        uNotice.setUserId(userId);
        uNotice.setIsused(0);
        uNotice.setReadoff(0);
        return this.uNoticeDao.count(uNotice);
    }

    @Override
    public List<UNotice> readNotice(Long userId){
        UNotice uNotice =new UNotice();
        uNotice.setUserId(userId);
        uNotice.setIsused(0);
        //设置为已读
        this.updateByUserId(userId);

        return this.uNoticeDao.queryAll(uNotice);
    }

    public int updateByUserId(Long userId){
        UNotice uNotice =new UNotice();
        uNotice.setUserId(userId);
        uNotice.setReadoff(1);
        return this.uNoticeDao.updateByUserId(uNotice);
    }
    /**
     * 新增数据
     *
     * @param uNotice 实例对象
     * @return 实例对象
     */
    @Override
    public UNotice insert(UNotice uNotice) {
        this.uNoticeDao.insert(uNotice);
        return uNotice;
    }

    /**
     * 修改数据
     *
     * @param uNotice 实例对象
     * @return 实例对象
     */
    @Override
    public UNotice update(UNotice uNotice) {
        this.uNoticeDao.update(uNotice);
        return this.queryById(uNotice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uNoticeDao.deleteById(id) > 0;
    }
}
