package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.account.feign.client.NoticeClient;
import cn.zealon.readingcloud.account.feign.client.UserAttributeClient;
import cn.zealon.readingcloud.book.service.CLikesService;
import cn.zealon.readingcloud.book.service.CircleService;
import cn.zealon.readingcloud.common.pojo.xzwresources.CSubmit;
import cn.zealon.readingcloud.book.dao.CSubmitDao;
import cn.zealon.readingcloud.book.service.CSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 投稿表(CSubmit)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 17:51:36
 */
@Service("cSubmitService")
public class CSubmitServiceImpl implements CSubmitService {
    @Resource
    private CSubmitDao cSubmitDao;

    @Resource
    private CLikesService  clikesService;

    @Autowired
    private NoticeClient noticeClient;

    @Resource
    private CircleService circleService;

    @Autowired
    private UserAttributeClient userAttributeClient;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CSubmit queryById(Long id) {
        return this.cSubmitDao.queryById(id);
    }

    @Override
    public ResponseEntity<CSubmit> add(Long userId, String name, String content) {
        //redis
        this.clikesService.setRedisTask(userId,new Long(7));
        CSubmit cSubmit=new CSubmit();
        cSubmit.setIsused(0);
        cSubmit.setCreateTime(new Date());
        cSubmit.setUpdateTime(new Date());
        cSubmit.setUserId(userId);
        cSubmit.setSName(name);
        cSubmit.setSContent(content);
        this.insert(cSubmit);
        //同步发布到圈子
        this.circleService.doCircle(this.userAttributeClient.queryByUserId(userId),"我刚刚发布了一篇<"+name+">的作文，欢迎捧场！",cSubmit.getId(),0);

        this.noticeClient.doNotice(userId,"新作文",0,"新作文提醒您，您的投稿已成功。");
        return ResponseEntity.ok(cSubmit);
    }

    /**
     * 分页查询
     *
     * @param cSubmit     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CSubmit> queryByPage(CSubmit cSubmit, PageRequest pageRequest) {
        long total = this.cSubmitDao.count(cSubmit);
        return new PageImpl<>(this.cSubmitDao.queryAllByLimit(cSubmit, pageRequest), pageRequest, total);
    }

    @Override
    public List<CSubmit>queryAll(CSubmit cSubmit){
        return this.cSubmitDao.queryAll(cSubmit);
    }
    /**
     * 新增数据
     *
     * @param cSubmit 实例对象
     * @return 实例对象
     */
    @Override
    public CSubmit insert(CSubmit cSubmit) {
        this.cSubmitDao.insert(cSubmit);
        return cSubmit;
    }

    /**
     * 修改数据
     *
     * @param cSubmit 实例对象
     * @return 实例对象
     */
    @Override
    public CSubmit update(CSubmit cSubmit) {
        this.cSubmitDao.update(cSubmit);
        return this.queryById(cSubmit.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cSubmitDao.deleteById(id) > 0;
    }
}
