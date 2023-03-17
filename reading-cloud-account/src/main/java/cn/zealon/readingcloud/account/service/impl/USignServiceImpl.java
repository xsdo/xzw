package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.dao.USigndetailDao;
import cn.zealon.readingcloud.account.service.*;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.USign;
import cn.zealon.readingcloud.account.dao.USignDao;
import cn.zealon.readingcloud.common.pojo.xzwusers.USigndetail;
import cn.zealon.readingcloud.common.pojo.xzwusers.XzwUser;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.beans.SimpleBeanInfo;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 签到表(USign)表服务实现类
 *
 * @author makejava
 * @since 2023-03-07 09:20:54
 */
@Service("uSignService")
public class USignServiceImpl implements USignService {
    @Resource
    private USignDao uSignDao;
    @Resource
    private USigndetailService uSigndetailService;
    @Resource
    private UAttributeService uAttributeService;

    @Resource
    private XzwUserService xzwUserService;

    @Resource
    private UFlowersService uFlowersService;



    @Override
    public JSONObject sign (Long userId){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            //查询今日签到
            USigndetail signToday =todayDetail(userId);
            System.out.println(signToday);
            if (signToday == null) {//今天未签到 1:查询本月昨日签到情况
                USigndetail signDetail_yesterday = yesterdayDetail(userId);
                USign uSign =queryByUserId(userId);
                if (signDetail_yesterday==null){
                    USigndetail signdetail = new USigndetail();
                    USign uSign1 = new USign();
                    signdetail.setUserId(userId);
                    signdetail.setSignDate(new Date());
                    signdetail.setIsused(0);
                    uSigndetailService.insert(signdetail);
                    if (uSign==null){
                        uSign1.setUserId(userId);
                        uSign1.setContinueDays(1);
                        uSign1.setIsused(0);
                        uSign1.setUpdateTime(new Date());
                        insert(uSign1);
                    }else {
                        uSign1.setId(uSign.getId());
                        uSign1.setUserId(userId);
                        uSign1.setContinueDays(1);
                        uSign1.setUpdateTime(new Date());
                        update(uSign1);
                    }

                } else {//昨日已签到
                    USigndetail usigndetail =new USigndetail();
                    USign uSign1=new USign();
                    usigndetail.setUserId(userId);
                    usigndetail.setIsused(0);
                    usigndetail.setSignDate(new Date());
                    usigndetail.setUpdateTime(new Date());
                    uSigndetailService.insert(usigndetail);
                    //首次签到
                    if (uSign==null) {
                        uSign1.setUserId(userId);
                        uSign1.setContinueDays(1);
                        uSign1.setUpdateTime(new Date());
                        uSign1.setIsused(0);
                        insert(uSign1);
                    }else {
                        uSign1.setId(uSign.getId());
                        uSign1.setUserId(userId);
                        uSign1.setContinueDays(uSign.getContinueDays()+1);
                        uSign1.setUpdateTime(new Date());
                        update(uSign1);
                    }

                }
                //签到成功+添加积分
                uFlowersService.addFlowers(userId,2,"签到");

                result.put("sign",00);
                data.put("data","签到成功");
            }else {
                result.put("sign",-1);
                data.put("data","当日已签到");
            }

            result.put("data", data);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }



    @Override
    public Map<String,Object> getSign(Long userId) {
        HashMap result = new HashMap();
        JSONObject data = new JSONObject();
        String signDetail = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            //查询用户积分
            XzwUser xzwUser=xzwUserService.queryById(userId);
            if (xzwUser != null) {
                UAttribute uAttribute=uAttributeService.queryById(xzwUser.getAttributeid());
                if (uAttribute != null){
                    data.put("integral",uAttribute.getIntegral());
                }
                //查询今日签到
                USigndetail signToday =todayDetail(userId);
                if (signToday==null){
                    data.put("signToday",0);//未签
                }else {
                    data.put("signToday",1);//已签
                }
                //查询连续签到
                USign uSign =queryByUserId(userId);
                if (uSign!=null){
                    data.put("continueDays",uSign.getContinueDays());
                }else {
                    data.put("continueDays",0);
                }
                //查询累计签到
                List<USigndetail> uSigndetailList=uSigndetailService.queryAllByUserId(userId);
                data.put("countSign",uSigndetailList.size());
                result.put("sign",00);
                result.put("data",data);
            }else {
                result.put("sign",-1);
                result.put("data","未发现积分数据");
            }
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }

    @Override
    public USigndetail todayDetail(Long userId){
        USigndetail uSigndetail = null;
        List<USigndetail> uSigndetailList = uSigndetailService.queryAllByUserId(userId);
        if (uSigndetailList!=null) {
            LocalDate localDate =LocalDate.now();
            for (int i = 0; i < uSigndetailList.size(); i++) {
                DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String datestr= dateTimeFormatter.format(localDate);
                String dateSign=formatter.format(uSigndetailList.get(i).getSignDate());
                System.out.println(datestr);
                System.out.println(dateSign);
                if (dateSign.equals(datestr)) {
                    uSigndetail=uSigndetailList.get(i);
                }
            }
            return uSigndetail;
        }else {return null;}
    }

    @Override
    public USigndetail yesterdayDetail(Long userId){
        USigndetail uSigndetail = null;
        List<USigndetail> uSigndetailList = uSigndetailService.queryAllByUserId(userId);
        if (uSigndetailList!=null) {
            LocalDate localDate =LocalDate.now();
            LocalDate preDay=localDate.plusDays(-1);
            for (int i = 0; i < uSigndetailList.size(); i++) {
                DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String datestr= dateTimeFormatter.format(preDay);
                String dateSign=formatter.format(uSigndetailList.get(i).getSignDate());
                if (dateSign.equals(datestr)) {
                    uSigndetail=uSigndetailList.get(i);
                }
            }
            return uSigndetail;
        }else {return null;}
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public USign queryById(Long id) {
        return this.uSignDao.queryById(id);
    }

    @Override
    public USign queryByUserId(Long userId){
        USign uSign=new USign();
        uSign.setUserId(userId);
        List<USign>list=this.uSignDao.queryAll(uSign);
        if (list == null || list.size() == 0){
            return null;
        }else {
            return list.get(0);
        }

    }

    /**
     * 分页查询
     *
     * @param uSign       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<USign> queryByPage(USign uSign, PageRequest pageRequest) {
        long total = this.uSignDao.count(uSign);
        return new PageImpl<>(this.uSignDao.queryAllByLimit(uSign, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param uSign 实例对象
     * @return 实例对象
     */
    @Override
    public USign insert(USign uSign) {
        this.uSignDao.insert(uSign);
        return uSign;
    }

    /**
     * 修改数据
     *
     * @param uSign 实例对象
     * @return 实例对象
     */
    @Override
    public USign update(USign uSign) {
        this.uSignDao.update(uSign);
        return this.queryById(uSign.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uSignDao.deleteById(id) > 0;
    }
}
