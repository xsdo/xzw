package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CCollectlog;
import cn.zealon.readingcloud.book.dao.CCollectlogDao;
import cn.zealon.readingcloud.book.service.CCollectlogService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收藏夹文件表(CCollectlog)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 17:51:36
 */
@Service("cCollectlogService")
public class CCollectlogServiceImpl implements CCollectlogService {
    @Resource
    private CCollectlogDao cCollectlogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CCollectlog queryById(Long id) {
        return this.cCollectlogDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cCollectlog 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CCollectlog> queryByPage(CCollectlog cCollectlog, PageRequest pageRequest) {
        long total = this.cCollectlogDao.count(cCollectlog);
        return new PageImpl<>(this.cCollectlogDao.queryAllByLimit(cCollectlog, pageRequest), pageRequest, total);
    }

    @Override
    public List<CCollectlog>queryAll(CCollectlog cCollectlog){
        return this.cCollectlogDao.queryAll(cCollectlog);
    }
    /**
     * 新增数据
     *
     * @param cCollectlog 实例对象
     * @return 实例对象
     */
    @Override
    public CCollectlog insert(CCollectlog cCollectlog) {
        this.cCollectlogDao.insert(cCollectlog);
        return cCollectlog;
    }


    @Override
    public JSONObject addCollectlog (Long collectId,String cName ,String cImage, Long compositionId, int type){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try{
            //查询是否已收藏
            CCollectlog cCollectlog = new CCollectlog();
            cCollectlog.setCollectId(collectId);
            cCollectlog.setCompositionId(compositionId);
            List<CCollectlog>collectlogList =this.queryAll(cCollectlog);
            if (collectlogList.size() > 0){
                CCollectlog cc=collectlogList.get(0);
                if (cc != null) {
                    if (cc.getIsused()==0) {
                        cc.setUpdateTime(new Date());
                        cc.setIsused(1);
                        this.update(cc);
                        result.put("sign",00);
                        data.put("data","取消收藏成功");
                    }else if (cc.getIsused() == 1) {
                        cc.setUpdateTime(new Date());
                        cc.setIsused(0);
                        this.update(cc);
                        result.put("sign",00);
                        data.put("data","收藏成功");
                    }
                }
            }else {
                cCollectlog.setIsused(0);
                cCollectlog.setCreateTime(new Date());
                cCollectlog.setUpdateTime(new Date());
                cCollectlog.setCName(cName);
                cCollectlog.setCImage(cImage);
                cCollectlog.setCType(type);
                this.insert(cCollectlog);
                result.put("sign",00);
                data.put("data","收藏成功");
            }
                result.put("data",data);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }

        return result;
    }

    /**
     * 修改数据
     *
     * @param cCollectlog 实例对象
     * @return 实例对象
     */
    @Override
    public CCollectlog update(CCollectlog cCollectlog) {
        this.cCollectlogDao.update(cCollectlog);
        return this.queryById(cCollectlog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cCollectlogDao.deleteById(id) > 0;
    }
}
