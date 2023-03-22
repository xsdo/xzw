package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.Images;
import cn.zealon.readingcloud.book.dao.ImagesDao;
import cn.zealon.readingcloud.book.service.ImagesService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 随机图表(Images)表服务实现类
 *
 * @author makejava
 * @since 2023-03-22 18:22:35
 */
@Service("imagesService")
public class ImagesServiceImpl implements ImagesService {
    @Resource
    private ImagesDao imagesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Images queryById(Long id) {
        return this.imagesDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param images      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Images> queryByPage(Images images, PageRequest pageRequest) {
        long total = this.imagesDao.count(images);
        return new PageImpl<>(this.imagesDao.queryAllByLimit(images, pageRequest), pageRequest, total);
    }

    @Override
    public Images queryRand(){
        List<Images> images = this.imagesDao.queryRand(1);
        if (images.size() > 0){
            return images.get(0);
        }else {
            return null;
        }
    }
    /**
     * 新增数据
     *
     * @param images 实例对象
     * @return 实例对象
     */
    @Override
    public Images insert(Images images) {
        this.imagesDao.insert(images);
        return images;
    }

    /**
     * 修改数据
     *
     * @param images 实例对象
     * @return 实例对象
     */
    @Override
    public Images update(Images images) {
        this.imagesDao.update(images);
        return this.queryById(images.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.imagesDao.deleteById(id) > 0;
    }
}
