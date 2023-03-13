package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.MColumn;
import cn.zealon.readingcloud.book.dao.MColumnDao;
import cn.zealon.readingcloud.book.service.MColumnService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 杂志栏目表(MColumn)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:14:00
 */
@Service("mColumnService")
public class MColumnServiceImpl implements MColumnService {
    @Resource
    private MColumnDao mColumnDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MColumn queryById(Long id) {
        return this.mColumnDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param mColumn     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<MColumn> queryByPage(MColumn mColumn, PageRequest pageRequest) {
        long total = this.mColumnDao.count(mColumn);
        return new PageImpl<>(this.mColumnDao.queryAllByLimit(mColumn, pageRequest), pageRequest, total);
    }

    @Override
    public List<MColumn>queryAll(MColumn mColumn){
        return this.mColumnDao.queryAll(mColumn);
    }
    /**
     * 新增数据
     *
     * @param mColumn 实例对象
     * @return 实例对象
     */
    @Override
    public MColumn insert(MColumn mColumn) {
        this.mColumnDao.insert(mColumn);
        return mColumn;
    }

    /**
     * 修改数据
     *
     * @param mColumn 实例对象
     * @return 实例对象
     */
    @Override
    public MColumn update(MColumn mColumn) {
        this.mColumnDao.update(mColumn);
        return this.queryById(mColumn.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.mColumnDao.deleteById(id) > 0;
    }
}
