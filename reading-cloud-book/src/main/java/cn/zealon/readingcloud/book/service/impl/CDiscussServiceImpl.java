package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.account.feign.client.UserAttributeClient;
import cn.zealon.readingcloud.book.service.*;
import cn.zealon.readingcloud.book.dao.CDiscussDao;
import cn.zealon.readingcloud.book.vo.DiscussComVO;
import cn.zealon.readingcloud.book.vo.DiscussUserVO;
import cn.zealon.readingcloud.common.pojo.xzwresources.CDiscuss;
import cn.zealon.readingcloud.common.pojo.xzwresources.CNote;
import cn.zealon.readingcloud.common.pojo.xzwresources.Circle;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

/**
 * 评论表(CDiscuss)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:54
 */
@Service("cDiscussService")
public class CDiscussServiceImpl implements CDiscussService {
    @Resource
    private CDiscussDao cDiscussDao;

    @Resource
    private UserAttributeClient userAttributeClient;

    @Resource
    private CircleService circleService;

    @Resource
    private CompositionService compositionService;

    @Resource
    private CNoteService cNoteService;

    @Resource
    private CLikesService clikesService;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CDiscuss queryById(Long id) {
        return this.cDiscussDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cDiscuss    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CDiscuss> queryByPage(CDiscuss cDiscuss, PageRequest pageRequest) {
        long total = this.cDiscussDao.count(cDiscuss);
        return new PageImpl<>(this.cDiscussDao.queryAllByLimit(cDiscuss, pageRequest), pageRequest, total);
    }

    @Override
    public List<CDiscuss>queryAll(CDiscuss cDiscuss){
        cDiscuss.setIsused(0);
        return this.cDiscussDao.queryAll(cDiscuss);
    }

    @Override
    public List<DiscussComVO>queryByUserId(Long userId){
        List<DiscussComVO> diso=new ArrayList<>();
        CDiscuss cDiscuss=new CDiscuss();
        cDiscuss.setIsused(0);
        cDiscuss.setUserId(userId);
        List<CDiscuss> cDiscussList = this.cDiscussDao.queryAll(cDiscuss);
        if(cDiscussList!=null&&cDiscussList.size() > 0){
            for (CDiscuss cd: cDiscussList) {
                DiscussComVO discussComVO = new DiscussComVO();
                discussComVO.setCDiscuss(cd);
                if (cd.getRemarks().equals("0")){
                    discussComVO.setComposition(this.compositionService.queryById(cd.getCompositionId()));
                }else if (cd.getRemarks().equals("1")) {
                    discussComVO.setNote(this.cNoteService.queryById(cd.getCompositionId()));
                }else if (cd.getRemarks().equals("2")){
                    discussComVO.setCircle(this.circleService.queryById(cd.getCompositionId()));
                }
                diso.add(discussComVO);
            }
        }
        return diso;
    }
    @Override
    public List<DiscussUserVO>queryByCompositionId(Long compositionId,String remarks){
        List<DiscussUserVO>diso=new ArrayList<>();
        CDiscuss cDiscuss=new CDiscuss();
        cDiscuss.setIsused(0);
        cDiscuss.setRemarks(remarks);
        cDiscuss.setCompositionId(compositionId);
        List<CDiscuss> cDiscussList = this.cDiscussDao.queryAll(cDiscuss);
        if(cDiscussList!=null&&cDiscussList.size() > 0){
            for (CDiscuss cd: cDiscussList) {
                if (cd.getStatus()==0||cd.getStatus() == 1||cd.getStatus() == 2||cd.getStatus()==5) {
                    DiscussUserVO discussUserVO = new DiscussUserVO();
                    discussUserVO.setCDiscuss(cd);
                    discussUserVO.setUAttribute(this.userAttributeClient.queryByUserId(cd.getUserId()));
                    diso.add(discussUserVO);
                }
            }
        }
        return diso;
    }
    @Override
    public JSONObject doDiscuss(Long userId, String discuss, Long compositionId,Integer type){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            //查询用户
            UAttribute uAttribute=userAttributeClient.queryByUserId(userId);
            System.out.println(uAttribute.getId());
            CDiscuss cDiscuss=new CDiscuss();
            if (uAttribute != null) {
                cDiscuss.setIsused(0);
                cDiscuss.setStatus(0);
                cDiscuss.setUserId(uAttribute.getId());
                cDiscuss.setUserName(uAttribute.getQqnum());
                cDiscuss.setCompositionId(compositionId);
                cDiscuss.setContent(discuss);
                cDiscuss.setLikes(0);
                cDiscuss.setCreateTime(new Date());
                cDiscuss.setUpdateTime(new Date());
                cDiscuss.setRemarks(type+"");
                this.cDiscussDao.insert(cDiscuss);
                if (type==0){//如果是文章评论
                    Composition composition=this.compositionService.queryById(compositionId);
                    if (composition != null) {
                        composition.setCDiscuss(composition.getCDiscuss()+1);
                        this.compositionService.update(composition);
                    }
                    //同步发布到圈子
                    this.circleService.doCircle(uAttribute,discuss,compositionId,1);
                }else if (type == 1){//如果是随笔评论
                    CNote cnote =this.cNoteService.queryById(compositionId);
                    if (cnote!=null){
                        cnote.setDiscuss(cnote.getDiscuss()+1);
                        this.cNoteService.update(cnote);
                    }
                }else if (type == 2) {//如果是圈子评论
                    Circle circle=this.circleService.queryById(compositionId);
                    if (circle!=null){
                        circle.setDiscuss(circle.getDiscuss() + 1);
                        this.circleService.update(circle);
                    }

                }

                //redis
                this.clikesService.setRedisTask(userId,new Long(4));

                data.put("discuss",cDiscuss);
                result.put("sign",00);
                data.put("data","发布成功");
            }else {
                result.put("sign",-1);
                data.put("data","未找到用户信息");
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
     * @param cDiscuss 实例对象
     * @return 实例对象
     */
    @Override
    public CDiscuss insert(CDiscuss cDiscuss) {
        this.cDiscussDao.insert(cDiscuss);
        return cDiscuss;
    }

    /**
     * 修改数据
     *
     * @param cDiscuss 实例对象
     * @return 实例对象
     */
    @Override
    public CDiscuss update(CDiscuss cDiscuss) {
        this.cDiscussDao.update(cDiscuss);
        return this.queryById(cDiscuss.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cDiscussDao.deleteById(id) > 0;
    }
}
