package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.Magazines;
import cn.zealon.readingcloud.book.dao.MagazinesDao;
import cn.zealon.readingcloud.book.service.MagazinesService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 杂志表(Magazines)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:55
 */
@Service("magazinesService")
public class MagazinesServiceImpl implements MagazinesService {
    @Resource
    private MagazinesDao magazinesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Magazines queryById(Long id) {
        return this.magazinesDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param magazines   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Magazines> queryByPage(Magazines magazines, PageRequest pageRequest) {
        long total = this.magazinesDao.count(magazines);
        return new PageImpl<>(this.magazinesDao.queryAllByLimit(magazines, pageRequest), pageRequest, total);
    }

    @Override
    public List<Magazines>queryAll(Magazines magazines){
        return this.magazinesDao.queryAll(magazines);
    }

    /**
     * 新增数据
     *
     * @param magazines 实例对象
     * @return 实例对象
     */
    @Override
    public Magazines insert(Magazines magazines) {
        this.magazinesDao.insert(magazines);
        return magazines;
    }

    /**
     * 修改数据
     *
     * @param magazines 实例对象
     * @return 实例对象
     */
    @Override
    public Magazines update(Magazines magazines) {
        this.magazinesDao.update(magazines);
        return this.queryById(magazines.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.magazinesDao.deleteById(id) > 0;
    }
}
