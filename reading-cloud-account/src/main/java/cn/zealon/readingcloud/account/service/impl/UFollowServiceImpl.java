package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.UFollow;
import cn.zealon.readingcloud.account.dao.UFollowDao;
import cn.zealon.readingcloud.account.service.UFollowService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户关注表(UFollow)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 10:43:54
 */
@Service("uFollowService")
public class UFollowServiceImpl implements UFollowService {
    @Resource
    private UFollowDao uFollowDao;

    @Resource
    private UAttributeService uAttributeService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UFollow queryById(Long id) {
        return this.uFollowDao.queryById(id);
    }

    //查询是否关注
    @Override
    public Integer checkFollow(Long userId, Long followId){
        Integer checkFollow=0;
        Integer fo=0;
        Integer fa=0;
        UFollow follow = this.queryByUserId(userId, followId);
        UFollow fans = this.queryByUserId(followId, userId);
        if (follow !=null){
            if (follow.getStatus()==0){
                fo=1;
            }else {
                fo=2;
            }
        }else {fo=2;}
        if(fans!=null){
            if (fans.getStatus()==0){
                fa=1;
            }else {
                fa=2;
            }
        }else {fa=2;}

        if (fo==1){
            if (fa==1){
                checkFollow=4;//互相关注
            }else {
                checkFollow=1;//已关注
            }
        }else {
            if (fa==1){
                checkFollow=3;//回关
            }else {
                checkFollow=2;//未关注
            }
        }
        return checkFollow;
    }

    //关注
    @Override
    public JSONObject doFollow(Long userId, Long followId){

        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();

        if (userId==followId) {
            result.put("sign",-1);
            result.put("data","不可以关注自己");
            return result;
        }

        try {
            //查询是否关注
            UFollow u = this.queryByUserId(userId, followId);
            if (u != null) {
                if (u.getStatus()==0){
                    u.setStatus(1);
                    u.setUpdateTime(new Date());
                    this.update(u);
                    data.put("followId",followId);
                    result.put("sign",00);
                    data.put("data","取消关注");
                }else if (u.getStatus() == 1) {
                    u.setStatus(0);
                    u.setUpdateTime(new Date());
                    this.update(u);
                    data.put("followId",followId);
                    result.put("sign",00);
                    data.put("data","关注成功");
                }
            }else {
                UFollow uFollow=new UFollow();
                uFollow.setIsused(0);
                uFollow.setUserId(userId);
                uFollow.setFollowedUser(followId);
                uFollow.setStatus(0);
                uFollow.setCreateTime(new Date());
                uFollow.setUpdateTime(new Date());
                this.insert(uFollow);
                data.put("followId",followId);
                result.put("sign",00);
                data.put("data","关注成功");
            }
            result.put("data",data);
        }catch (Exception e) {
            e.printStackTrace();
            data.put("followId",followId);
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }

    //查询所有关注
    @Override
    public JSONObject queryFollowAll(Long userId){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            List<UFollow>uFollowList=this.queryFollow(userId);
            if (uFollowList!=null){
                List<UAttribute>u=new ArrayList<>();
                for (UFollow uFollow: uFollowList) {
                    UAttribute uAttribute=this.uAttributeService.queryById(uFollow.getFollowedUser());
                    u.add(uAttribute);
                }
                result.put("sign",00);
                result.put("count",uFollowList.size());
                result.put("follower",u);
                data.put("data","查询成功");
            }else {
                result.put("sign",00);
                result.put("count",0);
                data.put("data","查询成功");
            }
            result.put("data",data);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }

    //查询所有粉丝
    @Override
    public JSONObject queryFansAll(Long followId){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            List<UFollow>uFollowList=this.queryFans(followId);
            if (uFollowList!=null){
                List<UAttribute>u=new ArrayList<>();
                for (UFollow uFollow: uFollowList) {
                    UAttribute uAttribute=this.uAttributeService.queryById(uFollow.getUserId());
                    u.add(uAttribute);
                }
                result.put("sign",00);
                result.put("count",uFollowList.size());
                result.put("follower",u);
                data.put("data","查询成功");
            }else {
                result.put("sign",00);
                result.put("count",0);
                data.put("data","查询成功");
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
     * 分页查询
     *
     * @param uFollow     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UFollow> queryByPage(UFollow uFollow, PageRequest pageRequest) {
        long total = this.uFollowDao.count(uFollow);
        return new PageImpl<>(this.uFollowDao.queryAllByLimit(uFollow, pageRequest), pageRequest, total);
    }

    @Override
    public UFollow queryByUserId(Long userId,Long followId){
        UFollow uFollow=new UFollow();
        uFollow.setIsused(0);
        uFollow.setUserId(userId);
        uFollow.setFollowedUser(followId);
        List<UFollow> uFollowList = this.uFollowDao.queryAll(uFollow);
        if (uFollowList.size() > 0) {
            return uFollowList.get(0);
        }else {
            return null;
        }
    }
    @Override
    public List<UFollow> queryFollow(Long userId){
        UFollow uFollow=new UFollow();
        uFollow.setIsused(0);
        uFollow.setUserId(userId);
        uFollow.setStatus(0);
        List<UFollow> uFollowList = this.uFollowDao.queryAll(uFollow);
        if (uFollowList.size() > 0) {
            return uFollowList;
        }else {
            return null;
        }
    }
    @Override
    public List<UFollow> queryFans(Long followId){
        UFollow uFollow=new UFollow();
        uFollow.setIsused(0);
        uFollow.setStatus(0);
        uFollow.setFollowedUser(followId);
        List<UFollow> uFollowList = this.uFollowDao.queryAll(uFollow);
        if (uFollowList.size() > 0) {
            return uFollowList;
        }else {
            return null;
        }
    }
    /**
     * 新增数据
     *
     * @param uFollow 实例对象
     * @return 实例对象
     */
    @Override
    public UFollow insert(UFollow uFollow) {
        this.uFollowDao.insert(uFollow);
        return uFollow;
    }

    /**
     * 修改数据
     *
     * @param uFollow 实例对象
     * @return 实例对象
     */
    @Override
    public UFollow update(UFollow uFollow) {
        this.uFollowDao.update(uFollow);
        return this.queryById(uFollow.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uFollowDao.deleteById(id) > 0;
    }
}
