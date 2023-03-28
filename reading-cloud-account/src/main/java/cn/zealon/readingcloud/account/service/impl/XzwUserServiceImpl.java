package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.common.config.SmsConfig;
import cn.zealon.readingcloud.account.common.utils.HttpRequest;
import cn.zealon.readingcloud.account.common.utils.JwtUtil;
import cn.zealon.readingcloud.account.common.utils.SmsCodeUtil;
import cn.zealon.readingcloud.account.dao.UAttributeDao;
import cn.zealon.readingcloud.account.service.UVipService;
import cn.zealon.readingcloud.account.vo.AuthXzwVO;
import cn.zealon.readingcloud.account.vo.XzwUserVO;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.config.FileProperties;
import cn.zealon.readingcloud.common.exception.BadRequestException;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.XzwUser;
import cn.zealon.readingcloud.account.dao.XzwUserDao;
import cn.zealon.readingcloud.account.service.XzwUserService;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import cn.zealon.readingcloud.common.utils.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.*;

import static cn.zealon.readingcloud.account.common.utils.SmsCodeUtil.codeFen;
import static cn.zealon.readingcloud.common.cache.RedisExpire.MINUTE_THIRTY;
import static cn.zealon.readingcloud.common.constant.JwtConstant.EXPIRE_DAY;

/**
 * 用户表(XzwUser)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 10:43:57
 */
@Service("xzwUserService")
public class XzwUserServiceImpl implements XzwUserService {
    @Resource
    private XzwUserDao xzwUserDao;

    @Resource
    private RedisService redisService;

    @Resource
    private UAttributeDao uAttributeDao;

    @Resource
    private SmsConfig smsConfig;

    @Resource
    private UVipService uVipService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public XzwUser queryById(Long id) {
        return this.xzwUserDao.queryById(id);
    }


    @Override
    public XzwUser queryByPhoneNumber(String phoneNumber) {
        List<XzwUser>xzwUserList=this.xzwUserDao.queryByPhoneNumber(phoneNumber);
        if (xzwUserList.size() == 0||xzwUserList==null){
            return null;
        }else {
            return xzwUserList.get(0);
        }
    }

    @Override
    public XzwUser queryByOpenId(String openId) {
        List<XzwUser>xzwUserList=this.xzwUserDao.queryByOpenId(openId);
        if (xzwUserList.size() == 0||xzwUserList==null){
            return null;
        }else {
            return xzwUserList.get(0);
        }
    }

    @Override
    public Result send4Login(String phoneNumber){
        try {
            // 1、随机生成4位数的验证码
//            Integer code = ValidateCodeUtils.generateValidateCode(6);
            String code=codeFen();
            // 2、发送短信验证码
            sendSms(phoneNumber,code);
            // 3、将验证码保存到redis，存活时间5分钟，同时调用不同场景的后缀进行区分
            this.redisService.setExpireCache(phoneNumber,code,MINUTE_THIRTY);
            // 4、将验证码返回给页面提示
            return new Result(200, "验证码发送成功，请在三十分钟内进行操作！",code);
        } catch (Exception e) {
            e.printStackTrace();
            // 2、将验证码返回给页面提示
            return new Result(500, "验证码发送失败，请重新发送");
        }
    }

    public ResponseEntity<String> sendSms(String phoneNumber, String code){
        String result="";
        try {
            SmsCodeUtil.sendSmsCode(phoneNumber,code,smsConfig.getSmsKey(),smsConfig.getSmsSecret());
            result=phoneNumber+"发送成功，验证码："+code;
        }catch (Exception e) {
            result = phoneNumber+"验证码发送失败";
        }
        return ResponseEntity.ok(result);
    }

    @Override
    public Result loginByPhoneNumber(String phoneNumber,String validateCode){
        // 获取redis的验证码
        String code =this.redisService.getCache(phoneNumber);
        // 判断三项信息是否为空
        if(phoneNumber != null && code != null && validateCode != null && code.equals(validateCode)){
            // 验证码正确
            XzwUser xzwUser =this.queryByPhoneNumber(phoneNumber);
            if (xzwUser==null){
                xzwUser = new XzwUser();
                xzwUser.setPhoneNumber(phoneNumber);
                xzwUser.setCreateTime(new Date());
                xzwUser.setUpdateTime(new Date());
                xzwUser.setIsused(0);
                // 不是会员，自动完成会员注册
                xzwUserDao.insert(xzwUser);
                XzwUser xzwUser1=this.queryByPhoneNumber(phoneNumber);
                if (xzwUser1 != null) {
                    //自动注册会员信息
                    UAttribute uAttribute= new UAttribute();
                    uAttribute.setId(xzwUser1.getId());
                    uAttribute.setIsused(0);
                    uAttribute.setSex(0);
                    uAttribute.setCreateTime(new Date());
                    uAttribute.setUpdateTime(new Date());
                    uAttribute.setPortrait("/Resource/avatar/2023/3/28/2023032814230165d209f083-d588-454b-8712-908f5a7a653c.jpg");
                    uAttribute.setUType(0);
                    uAttribute.setBackground("/Resource/News/2023/3/16/20230316165055682569b7a0b-51c1-42c3-9862-c7ce3bc1acd0.png");
                    uAttribute.setQqnum("用户"+phoneNumber);
                    uAttribute.setIsAuth(0);
                    uAttribute.setSign("个性签名");
                    uAttribute.setTeacherid(new Long(-1));
                    uAttribute.setIsgoodcommons(0);
                    uAttribute.setIntegral(0);
                    uAttributeDao.insert(uAttribute);

                    //新用户自动充值一个月会员
                    uVipService.toVipFirst(xzwUser1.getId());

                    xzwUser1.setAttributeid(uAttribute.getId());
                    xzwUserDao.update(xzwUser1);
                }
            }
            XzwUser xzwUser2 =this.queryByPhoneNumber(phoneNumber);
            //保存会员信息到Redis中
            // Redis是不能直接保存java对象的的，需要转换对象
            String json = JSON.toJSON(xzwUser2).toString();
            redisService.setExpireCache(phoneNumber,json,MINUTE_THIRTY);
            // 登录成功，返回用户信息
            AuthXzwVO vo=new AuthXzwVO();
            XzwUserVO xzwUserVO = new XzwUserVO();
            BeanUtils.copyProperties(xzwUser2,xzwUserVO);
            vo.setToken(JwtUtil.buildJwtXzw(this.getLoginExpre(),xzwUserVO));
            vo.setXzwUser(xzwUserVO);
            return ResultUtil.success(vo);
        }else if (!code.equals(validateCode)){
            return ResultUtil.fail().buildMessage("验证码错误！请重新验证。 ");
        }else {
            return ResultUtil.fail().buildMessage("登录失败！请重试。 ");
        }
    }

    @Override
    public String getOpeId(JSONObject js_code) {
        String code = js_code.getString("js_code");
        // 小程序唯一标识 (在微信小程序管理后台获取)
        String appid = smsConfig.getWechatAppId();
        // 小程序的 app secret (在微信小程序管理后台获取)
        String secret = smsConfig.getWechatSecret();
        // 授权（必填）
        String grant_type = "authorization_code";
        // 向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type;
        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        // 解析相应内容（转换成json对象）
        System.out.println(sr);
        JSONObject json = JSONObject.parseObject(sr);
        // 获取会话密钥（session_key）
//        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        return openid;
    }

    @Override
    public Result loginByOpenId(String openId ,String nickName, String avatarUrl ){
        //判断openId是否为空
        if (openId!=null) {
            XzwUser xzwUser =this.queryByOpenId(openId);
            if (xzwUser == null) {
                xzwUser = new XzwUser();
                xzwUser.setOpenId(openId);
                xzwUser.setCreateTime(new Date());
                xzwUser.setUpdateTime(new Date());
                xzwUser.setIsused(0);
                // 不是会员，自动完成会员注册
                xzwUserDao.insert(xzwUser);
                XzwUser xzwUser1=this.queryByOpenId(openId);
                if (xzwUser1 != null) {
                    //自动注册会员信息
                    UAttribute uAttribute = new UAttribute();
                    uAttribute.setId(xzwUser1.getId());
                    uAttribute.setIsused(0);
                    uAttribute.setSex(0);
                    uAttribute.setCreateTime(new Date());
                    uAttribute.setUpdateTime(new Date());
                    uAttribute.setQqnum(nickName);
                    uAttribute.setPortrait("/Resource/avatar/2023/3/28/2023032814230165d209f083-d588-454b-8712-908f5a7a653c.jpg");
                    uAttribute.setUType(0);
                    uAttribute.setBackground("/Resource/News/2023/3/16/20230316165055682569b7a0b-51c1-42c3-9862-c7ce3bc1acd0.png");
                    uAttribute.setIsAuth(0);
                    uAttribute.setSign("个性签名");
                    uAttribute.setTeacherid(new Long(-1));
                    uAttribute.setIsgoodcommons(0);
                    uAttribute.setIntegral(0);
                    uAttributeDao.insert(uAttribute);

                    //新用户自动充值一个月会员
                    uVipService.toVipFirst(xzwUser1.getId());

                    xzwUser1.setAttributeid(uAttribute.getId());
                    xzwUserDao.update(xzwUser1);
                }
            }
            XzwUser xzwUser2 =this.queryByOpenId(openId);
            //保存会员信息到Redis中
            // Redis是不能直接保存java对象的的，需要转换对象
            String json = JSON.toJSON(xzwUser2).toString();
            redisService.setExpireCache(openId,json,MINUTE_THIRTY);
            // 登录成功，返回用户信息
            AuthXzwVO vo=new AuthXzwVO();
            XzwUserVO xzwUserVO = new XzwUserVO();
            BeanUtils.copyProperties(xzwUser2,xzwUserVO);
            vo.setToken(JwtUtil.buildJwtXzw(this.getLoginExpre(),xzwUserVO));
            vo.setXzwUser(xzwUserVO);
            return ResultUtil.success(vo);
        }else {
            return ResultUtil.fail().buildMessage("登录失败！请重试。 ");
        }
    }



    @Override
    public Result login(String phoneNumber){

        // 判断信息是否为空
        if(phoneNumber != null ){
            // 验证码正确
            XzwUser xzwUser =this.queryByPhoneNumber(phoneNumber);
            if (xzwUser==null){
                xzwUser = new XzwUser();
                xzwUser.setPhoneNumber(phoneNumber);
                xzwUser.setCreateTime(new Date());
                xzwUser.setUpdateTime(new Date());
                xzwUser.setIsused(0);
                // 不是会员，自动完成会员注册
                xzwUserDao.insert(xzwUser);
                XzwUser xzwUser1=this.queryByPhoneNumber(phoneNumber);
                if (xzwUser1 != null) {
                    //自动注册会员信息
                    UAttribute uAttribute;
                    uAttribute = new UAttribute();
                    uAttribute.setIsused(0);
                    uAttribute.setCreateTime(new Date());
                    uAttribute.setUpdateTime(new Date());
                    uAttribute.setId(xzwUser1.getId());
                    uAttribute.setQqnum("用户"+phoneNumber);
                    uAttributeDao.insert(uAttribute);
                    xzwUser1.setAttributeid(uAttribute.getId());
                    xzwUserDao.update(xzwUser1);
                }
            }
            XzwUser xzwUser2 =this.queryByOpenId(phoneNumber);
            //保存会员信息到Redis中
            // Redis是不能直接保存java对象的的，需要转换对象
            String json = JSON.toJSON(xzwUser2).toString();
            redisService.setExpireCache(phoneNumber,json,MINUTE_THIRTY);
            // 登录成功，返回用户信息
            AuthXzwVO vo=new AuthXzwVO();
            XzwUserVO xzwUserVO = new XzwUserVO();
            BeanUtils.copyProperties(xzwUser2,xzwUserVO);
            vo.setToken(JwtUtil.buildJwtXzw(this.getLoginExpre(),xzwUserVO));
            vo.setXzwUser(xzwUserVO);
            return ResultUtil.success(vo);
        }else {
            return ResultUtil.fail().buildMessage("登录失败！请重试。 ");
        }
    }


    /**
     * 获取登陆过期时间
     * @return
     */
    private Date getLoginExpre(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, EXPIRE_DAY);
        return calendar.getTime();
    }


    /**
     * 分页查询
     *
     * @param xzwUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<XzwUser> queryByPage(XzwUser xzwUser, PageRequest pageRequest) {
        long total = this.xzwUserDao.count(xzwUser);
        return new PageImpl<>(this.xzwUserDao.queryAllByLimit(xzwUser, pageRequest), pageRequest, total);
    }



    /**
     * 新增数据
     *
     * @param xzwUser 实例对象
     * @return 实例对象
     */
    @Override
    public XzwUser insert(XzwUser xzwUser) {
        this.xzwUserDao.insert(xzwUser);
        return xzwUser;
    }

    /**
     * 修改数据
     *
     * @param xzwUser 实例对象
     * @return 实例对象
     */
    @Override
    public XzwUser update(XzwUser xzwUser) {
        this.xzwUserDao.update(xzwUser);
        return this.queryById(xzwUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.xzwUserDao.deleteById(id) > 0;
    }
}
