package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CVideos;
import cn.zealon.readingcloud.book.dao.CVideosDao;
import cn.zealon.readingcloud.book.service.CVideosService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频表(CVideos)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:55
 */
@Service("cVideosService")
public class CVideosServiceImpl implements CVideosService {
    @Resource
    private CVideosDao cVideosDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CVideos queryById(Long id) {
        return this.cVideosDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cVideos     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CVideos> queryByPage(CVideos cVideos, PageRequest pageRequest) {
        long total = this.cVideosDao.count(cVideos);
        return new PageImpl<>(this.cVideosDao.queryAllByLimit(cVideos, pageRequest), pageRequest, total);
    }

    @Override
    public List<CVideos>queryAll(CVideos cVideos){
        return this.cVideosDao.queryAll(cVideos);
    }
    /**
     * 新增数据
     *
     * @param cVideos 实例对象
     * @return 实例对象
     */
    @Override
    public CVideos insert(CVideos cVideos) {
        this.cVideosDao.insert(cVideos);
        return cVideos;
    }

    /**
     * 修改数据
     *
     * @param cVideos 实例对象
     * @return 实例对象
     */
    @Override
    public CVideos update(CVideos cVideos) {
        this.cVideosDao.update(cVideos);
        return this.queryById(cVideos.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cVideosDao.deleteById(id) > 0;
    }
}
