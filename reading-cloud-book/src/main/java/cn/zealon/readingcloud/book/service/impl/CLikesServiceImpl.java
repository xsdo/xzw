package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.service.*;
import cn.zealon.readingcloud.common.pojo.xzwresources.*;
import cn.zealon.readingcloud.book.dao.CLikesDao;
import cn.zealon.readingcloud.common.pojo.xzwusers.UFollow;
import com.alibaba.fastjson.JSONObject;
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
 * 用户点赞表(CLikes)表服务实现类
 *
 * @author makejava
 * @since 2023-03-09 10:51:23
 */
@Service("cLikesService")
public class CLikesServiceImpl implements CLikesService {
    @Resource
    private CLikesDao cLikesDao;

    @Resource
    private CircleService circleService;

    @Resource
    private CompositionService compositionService;

    @Resource
    private CNoteService cNoteService;

    @Resource
    private CDiscussService cDiscussService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CLikes queryById(Long id) {
        return this.cLikesDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cLikes      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CLikes> queryByPage(CLikes cLikes, PageRequest pageRequest) {
        long total = this.cLikesDao.count(cLikes);
        return new PageImpl<>(this.cLikesDao.queryAllByLimit(cLikes, pageRequest), pageRequest, total);
    }

    public CLikes queryByUserId(Long userId,Long likesId){
        CLikes likes =new CLikes();
        likes.setIsused(0);
        likes.setUserId(userId);
        likes.setLikesId(likesId);
        UFollow uFollow=new UFollow();
        List<CLikes>likesList=this.cLikesDao.queryAll(likes);
        if (likesList.size() > 0) {
            return likesList.get(0);
        }else {
            return null;
        }
    }

    //查询是否关注
    @Override
    public Integer checkLikes (Long userId ,Long likesId){
        Integer checkLike =0;
        CLikes clikes =this.queryByUserId(userId, likesId);
        if (clikes!=null){
            if (clikes.getStatus()==0){
                checkLike=1;
            }else {checkLike=2;}
        }else {checkLike=2;}
        return checkLike;
    }
    //点赞 type 文章0 随笔1 圈子2 评论的评3
    @Override
    public JSONObject toLikes(Long userId ,Long likesId,Integer type){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            //查询是否点赞
            CLikes clikes=this.queryByUserId(userId, likesId);
            if (clikes != null) {
                if (clikes.getStatus()==0){
                    clikes.setStatus(1);
                    clikes.setUpdateTime(new Date());
                    this.update(clikes);
                    this.likesCount(likesId,type,-1);
                    result.put("sign",00);
                    data.put("data","取消点赞");
                }else if (clikes.getStatus()==1){
                    clikes.setStatus(0);
                    clikes.setUpdateTime(new Date());
                    this.update(clikes);
                    this.likesCount(likesId, type, 1);
                    result.put("sign",00);
                    data.put("data","点赞成功");
                }
            }else {
                CLikes c=new CLikes();
                c.setIsused(0);
                c.setUserId(userId);
                c.setLikesId(likesId);
                c.setStatus(0);
                c.setType(type);
                c.setCreateTime(new Date());
                c.setUpdateTime(new Date());
                this.insert(c);
                this.likesCount(likesId, type, 1);
                result.put("sign",00);
                data.put("data","点赞成功");
            }
            result.put("data",data);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }

    //点赞 type 文章0 随笔1 圈子2 评论的评3
    public void likesCount(Long likesId,Integer type,Integer count){
        if(type == 0){
            Composition composition=this.compositionService.queryById(likesId);
            if (composition != null) {
                composition.setCLikes(composition.getCLikes()+count);
                this.compositionService.update(composition);
            }
        }else if(type == 1){
            CNote cnote =this.cNoteService.queryById(likesId);
            if (cnote!=null){
                cnote.setLikes(cnote.getLikes()+count);
                this.cNoteService.update(cnote);
            }
        }else if (type == 2) {
            Circle circle=this.circleService.queryById(likesId);
            if (circle!=null){
                circle.setLikes(circle.getLikes() + count);
                this.circleService.update(circle);
            }
        }else if (type == 3) {
            CDiscuss cDiscuss =this.cDiscussService.queryById(likesId);
            if (cDiscuss!=null){
                cDiscuss.setLikes(cDiscuss.getLikes() + count);
                this.cDiscussService.update(cDiscuss);
            }
        }



    }

    /**
     * 新增数据
     *
     * @param cLikes 实例对象
     * @return 实例对象
     */
    @Override
    public CLikes insert(CLikes cLikes) {
        this.cLikesDao.insert(cLikes);
        return cLikes;
    }

    /**
     * 修改数据
     *
     * @param cLikes 实例对象
     * @return 实例对象
     */
    @Override
    public CLikes update(CLikes cLikes) {
        this.cLikesDao.update(cLikes);
        return this.queryById(cLikes.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cLikesDao.deleteById(id) > 0;
    }
}
