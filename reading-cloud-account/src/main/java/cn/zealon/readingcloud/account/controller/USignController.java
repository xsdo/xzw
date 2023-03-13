package cn.zealon.readingcloud.account.controller;

import cn.zealon.readingcloud.account.service.*;
import cn.zealon.readingcloud.common.pojo.xzwusers.*;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.pkcs11.wrapper.Constants;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 签到表(USign)表控制层
 *
 * @author makejava
 * @since 2023-03-07 09:20:53
 */
@Api(description = "用户签到接口")
@RestController
@RequestMapping("account/uSign")
public class USignController {
    /**
     * 服务对象
     */
    @Resource
    private USignService uSignService;

    @Resource
    private USigndetailService uSigndetailService;

    @Resource
    private UAttributeService uAttributeService;

    @Resource
    private XzwUserService xzwUserService;

    @Resource
    private UFlowersService uFlowersService;

    @GetMapping("/sign")
    public JSONObject sign (Long userId){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            //查询今日签到
            USigndetail signToday =uSignService.todayDetail(userId);
            System.out.println(signToday);
            if (signToday == null) {//今天未签到 1:查询本月昨日签到情况
                USigndetail signDetail_yesterday = uSignService.yesterdayDetail(userId);
                USign uSign =uSignService.queryByUserId(userId);
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
                        uSignService.insert(uSign1);
                    }else {
                        uSign1.setId(uSign.getId());
                        uSign1.setUserId(userId);
                        uSign1.setContinueDays(1);
                        uSign1.setUpdateTime(new Date());
                        uSignService.update(uSign1);
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
                        uSignService.insert(uSign1);
                    }else {
                        uSign1.setId(uSign.getId());
                        uSign1.setUserId(userId);
                        uSign1.setContinueDays(uSign.getContinueDays()+1);
                        uSign1.setUpdateTime(new Date());
                        uSignService.update(uSign1);
                    }

                }
                XzwUser xzwUser=xzwUserService.queryById(userId);
                if (xzwUser != null) {
//                    System.out.println("xzwUser != null");
                    UAttribute uAttribute=uAttributeService.queryById(xzwUser.getAttributeid());
                    if (uAttribute != null){
//                        System.out.println("uAttribute != null");
                        //签到成功+2分

                        uAttribute.setIntegral(uAttribute.getIntegral()+2);
                        uAttribute.setUpdateTime(new Date());
                        uAttributeService.update(uAttribute);


                        //添加积分明细
                        UFlowers uFlowers =new UFlowers();
                        uFlowers.setIsused(0);
                        uFlowers.setUserId(userId);
                        uFlowers.setFlowers(2);
                        uFlowers.setCreateTime(new Date());
                        uFlowers.setRemarks("签到");
                        uFlowersService.insert(uFlowers);
                    }
                }

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
    @GetMapping("/getSign")
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
                USigndetail signToday =uSignService.todayDetail(userId);
                if (signToday==null){
                    data.put("signToday",0);//未签
                }else {
                    data.put("signToday",1);//已签
                }
                //查询连续签到
                USign uSign =uSignService.queryByUserId(userId);
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

    /**
     * 分页查询
     *
     * @param uSign       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<USign>> queryByPage(USign uSign, PageRequest pageRequest) {
        return ResponseEntity.ok(this.uSignService.queryByPage(uSign, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<USign> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.uSignService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param uSign 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<USign> add(USign uSign) {
        return ResponseEntity.ok(this.uSignService.insert(uSign));
    }

    /**
     * 编辑数据
     *
     * @param uSign 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<USign> edit(USign uSign) {
        return ResponseEntity.ok(this.uSignService.update(uSign));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.uSignService.deleteById(id));
    }

}

