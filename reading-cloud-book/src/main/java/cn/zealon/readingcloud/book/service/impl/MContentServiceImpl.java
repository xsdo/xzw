package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.service.CompositionService;
import cn.zealon.readingcloud.book.vo.MagazinesVO;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.common.pojo.xzwresources.MContent;
import cn.zealon.readingcloud.book.dao.MContentDao;
import cn.zealon.readingcloud.book.service.MContentService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

/**
 * 杂志内容表(MContent)表服务实现类
 *
 * @author makejava
 * @since 2023-03-15 17:17:09
 */
@Service("mContentService")
public class MContentServiceImpl implements MContentService {
    @Resource
    private MContentDao mContentDao;

    @Resource
    private CompositionService compositionService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MContent queryById(Long id) {
        return this.mContentDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param mContent    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<MContent> queryByPage(MContent mContent, PageRequest pageRequest) {
        long total = this.mContentDao.count(mContent);
        return new PageImpl<>(this.mContentDao.queryAllByLimit(mContent, pageRequest), pageRequest, total);
    }

    @Override
    public List<MContent>queryAll(MContent mContent){
        return this.mContentDao.queryAll(mContent);
    }
    @Override
    public List<MagazinesVO>queryByMagazinesId(Long magazinesId){
        List<MagazinesVO>magazinesVOList=new ArrayList<>();
        MContent mContent=new MContent();
        mContent.setIsused(0);
        mContent.setMagazinesId(magazinesId);
        List<MContent> mContentList = this.mContentDao.queryAll(mContent);
        if (mContentList!=null&&mContentList.size()>0){
            for (MContent m:mContentList){
                MagazinesVO mavo=new MagazinesVO();
                mavo.setMContent(m);
                Composition c=this.compositionService.queryById(m.getCompositionId());
                if (c != null) {
                    mavo.setComposition(c);
                }
                magazinesVOList.add(mavo);
            }
        }
        return magazinesVOList;
    }
    @Override
    public void randMagazines(Long magazinesId,int size){
        List<Composition>compositionList=this.compositionService.queryRandoms(size);
        if (compositionList!=null){
            for (Composition composition: compositionList) {
                if (composition != null) {
                    if (composition.getCVoice()!=null){
                        MContent mContent=new MContent();
                        mContent.setIsused(0);
                        mContent.setCompositionId(composition.getId());
                        mContent.setMagazinesId(magazinesId);
                        mContent.setCreateTime( new Date());
                        mContent.setUpdateTime(new Date());
                        mContent.setTitles(composition.getCTitle());
                        mContent.setContent(composition.getCArticle());
                        mContent.setTryvip(1);
                        this.mContentDao.insert(mContent);
                    }
                }
            }
        }
    }

    @Override
    public JSONObject toMagazines(Long magazinesId,Long compositionId){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            Composition composition=this.compositionService.queryById(compositionId);
            if (composition != null) {
                if (composition.getCVoice()!=null){
                    MContent mContent=new MContent();
                    mContent.setIsused(0);
                    mContent.setCompositionId(compositionId);
                    mContent.setMagazinesId(magazinesId);
                    mContent.setCreateTime( new Date());
                    mContent.setUpdateTime(new Date());
                    mContent.setTitles(composition.getCTitle());
                    mContent.setContent(composition.getCArticle());
                    mContent.setTryvip(1);
                    this.mContentDao.insert(mContent);
                    result.put("sign",00);
                    data.put("data","成功添加");
                }
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
     * 新增数据
     *
     * @param mContent 实例对象
     * @return 实例对象
     */
    @Override
    public MContent insert(MContent mContent) {
        this.mContentDao.insert(mContent);
        return mContent;
    }

    /**
     * 修改数据
     *
     * @param mContent 实例对象
     * @return 实例对象
     */
    @Override
    public MContent update(MContent mContent) {
        this.mContentDao.update(mContent);
        return this.queryById(mContent.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.mContentDao.deleteById(id) > 0;
    }
}
