package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwusers.UFlowers;
import cn.zealon.readingcloud.account.dao.UFlowersDao;
import cn.zealon.readingcloud.account.service.UFlowersService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.util.List;

/**
 * 鲜花表(UFlowers)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 09:23:23
 */
@Service("uFlowersService")
public class UFlowersServiceImpl implements UFlowersService {
    @Resource
    private UFlowersDao uFlowersDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UFlowers queryById(Long id) {
        return this.uFlowersDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uFlowers    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UFlowers> queryByPage(UFlowers uFlowers, PageRequest pageRequest) {
        long total = this.uFlowersDao.count(uFlowers);
        return new PageImpl<>(this.uFlowersDao.queryAllByLimit(uFlowers, pageRequest), pageRequest, total);
    }

    @Override
    public List<UFlowers>queryAll(UFlowers uFlowers){
        return this.uFlowersDao.queryAll(uFlowers);
    }

    @Override
    public List<UFlowers>queryByUserId(Long userId){
        UFlowers uFlowers=new UFlowers();
        uFlowers.setIsused(0);
        uFlowers.setUserId(userId);
        return this.uFlowersDao.queryAll(uFlowers);
    }

    @Override
    public List<UFlowers>queryByTeacherId(Long teacherId) {
        UFlowers uFlowers = new UFlowers();
        uFlowers.setIsused(0);
        uFlowers.setTeacherId(teacherId);
        return this.uFlowersDao.queryAll(uFlowers);
    }

    /**
     * 新增数据
     *
     * @param uFlowers 实例对象
     * @return 实例对象
     */
    @Override
    public UFlowers insert(UFlowers uFlowers) {
        this.uFlowersDao.insert(uFlowers);
        return uFlowers;
    }

    /**
     * 修改数据
     *
     * @param uFlowers 实例对象
     * @return 实例对象
     */
    @Override
    public UFlowers update(UFlowers uFlowers) {
        this.uFlowersDao.update(uFlowers);
        return this.queryById(uFlowers.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uFlowersDao.deleteById(id) > 0;
    }
}
