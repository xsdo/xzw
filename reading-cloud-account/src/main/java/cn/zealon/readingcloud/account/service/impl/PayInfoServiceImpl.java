package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwusers.PayInfo;
import cn.zealon.readingcloud.account.dao.PayInfoDao;
import cn.zealon.readingcloud.account.service.PayInfoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 支付信息表(PayInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-04-26 15:58:15
 */
@Service("payInfoService")
public class PayInfoServiceImpl implements PayInfoService {
    @Resource
    private PayInfoDao payInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PayInfo queryById(Long id) {
        return this.payInfoDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param payInfo     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<PayInfo> queryByPage(PayInfo payInfo, PageRequest pageRequest) {
        long total = this.payInfoDao.count(payInfo);
        return new PageImpl<>(this.payInfoDao.queryAllByLimit(payInfo, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param payInfo 实例对象
     * @return 实例对象
     */
    @Override
    public PayInfo insert(PayInfo payInfo) {
        this.payInfoDao.insert(payInfo);
        return payInfo;
    }

    @Override
    public List<PayInfo> queryAll(PayInfo payInfo){
        return this.payInfoDao.queryAll(payInfo);
    }

    /**
     * 修改数据
     *
     * @param payInfo 实例对象
     * @return 实例对象
     */
    @Override
    public PayInfo update(PayInfo payInfo) {
        this.payInfoDao.update(payInfo);
        return this.queryById(payInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.payInfoDao.deleteById(id) > 0;
    }
}
