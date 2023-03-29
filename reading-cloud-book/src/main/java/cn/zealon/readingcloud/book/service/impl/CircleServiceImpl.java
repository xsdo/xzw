package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.account.feign.client.BindingClient;
import cn.zealon.readingcloud.account.feign.client.FollowClient;
import cn.zealon.readingcloud.account.feign.client.UserAttributeClient;
import cn.zealon.readingcloud.common.pojo.xzwresources.Circle;
import cn.zealon.readingcloud.book.dao.CircleDao;
import cn.zealon.readingcloud.book.service.CircleService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.UBinding;
import cn.zealon.readingcloud.common.pojo.xzwusers.UFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 圈子表(Circle)表服务实现类
 *
 * @author makejava
 * @since 2023-03-08 17:40:21
 */
@Service("circleService")
public class CircleServiceImpl implements CircleService {
    @Resource
    private CircleDao circleDao;

    @Autowired
    private FollowClient followClient;

    @Autowired
    private UserAttributeClient userAttributeClient;

    @Autowired
    private BindingClient bindingClient;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Circle queryById(Long id) {
        return this.circleDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param circle      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Circle> queryByPage(Circle circle, PageRequest pageRequest) {
        long total = this.circleDao.count(circle);
        return new PageImpl<>(this.circleDao.queryAllByLimit(circle, pageRequest), pageRequest, total);
    }

    @Override
    public void doCircle (UAttribute uAttribute,String content, Long compositionId,Integer type){
        Circle circle=new Circle();
        circle.setIsused(0);
        circle.setCreateTime(new Date());
        circle.setUpdateTime(new Date());
        circle.setUserId(uAttribute.getId());
        circle.setUserName(uAttribute.getQqnum());
        circle.setUserHead(uAttribute.getPortrait());
        circle.setUserTable(uAttribute.getRemarks());
        circle.setContent(content);
        circle.setCompositionId(compositionId);
        circle.setDiscuss(0);
        circle.setLikes(0);
        circle.setType(type);
        this.circleDao.insert(circle);
    }

    @Override
    public List<Circle>queryByUserId(Long userId){
        List<Circle> circleList = new ArrayList<>();
        List<UFollow>uFollowList=this.followClient.queryFollows(userId);
        List<Long>followIds=new ArrayList<>();
        followIds.add(userId);
        if (uFollowList != null && uFollowList.size() > 0) {
            for (UFollow uFollow: uFollowList) {
                followIds.add(uFollow.getFollowedUser());
            }
        }
        UAttribute uAttribute = this.userAttributeClient.queryByUserId(userId);
        if (uAttribute!=null&&uAttribute.getTeacherid()!=-1){
            List<UBinding>bindingList=this.bindingClient.queryByTeacherId(uAttribute.getTeacherid());
            if (bindingList!=null&&bindingList.size() > 0){
                for (UBinding ub:bindingList){
                    if (ub.getBStatus()==1){
                        followIds.add(ub.getUserId());
                    }
                }
            }
        }
        Circle circle=new Circle();
        circle.setIsused(0);
        List<Circle>circles=this.queryAll(circle);
        for (Circle cc:circles) {
            if (followIds.contains(cc.getUserId())) {
                circleList.add(cc);
            }
        }
        return circleList;
    }

    @Override
    public List<Circle>queryAll(Circle circle){
        return this.circleDao.queryAll(circle);
    }
    /**
     * 新增数据
     *
     * @param circle 实例对象
     * @return 实例对象
     */
    @Override
    public Circle insert(Circle circle) {
        this.circleDao.insert(circle);
        return circle;
    }

    /**
     * 修改数据
     *
     * @param circle 实例对象
     * @return 实例对象
     */
    @Override
    public Circle update(Circle circle) {
        this.circleDao.update(circle);
        return this.queryById(circle.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.circleDao.deleteById(id) > 0;
    }
}
