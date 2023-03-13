package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwusers.UAddress;
import cn.zealon.readingcloud.account.dao.UAddressDao;
import cn.zealon.readingcloud.account.service.UAddressService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户地址表(UAddress)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 17:54:34
 */
@Service("uAddressService")
public class UAddressServiceImpl implements UAddressService {
    @Resource
    private UAddressDao uAddressDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UAddress queryById(Long id) {
        return this.uAddressDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uAddress    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UAddress> queryByPage(UAddress uAddress, PageRequest pageRequest) {
        long total = this.uAddressDao.count(uAddress);
        return new PageImpl<>(this.uAddressDao.queryAllByLimit(uAddress, pageRequest), pageRequest, total);
    }

    @Override
    public List<UAddress>queryAll(UAddress uAddress){
        return this.uAddressDao.queryAll(uAddress);
    }

    @Override
    public UAddress queryByUserId(Long userId){
        UAddress uAddress =new UAddress();
        uAddress.setIsused(0);
        uAddress.setUserId(userId);
        List<UAddress> uAddressList = this.uAddressDao.queryAll(uAddress);
        if (uAddressList.size()>0&&uAddressList!=null){
            return uAddressList.get(0);
        }else {
            return null;
        }
    }
    /**
     * 新增数据
     *
     * @param uAddress 实例对象
     * @return 实例对象
     */
    @Override
    public UAddress insert(UAddress uAddress) {
        this.uAddressDao.insert(uAddress);
        return uAddress;
    }

    /**
     * 修改数据
     *
     * @param uAddress 实例对象
     * @return 实例对象
     */
    @Override
    public UAddress update(UAddress uAddress) {
        this.uAddressDao.update(uAddress);
        return this.queryById(uAddress.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uAddressDao.deleteById(id) > 0;
    }
}
