package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.common.config.OssProperties;
import cn.zealon.readingcloud.account.common.utils.OssUtil;
import cn.zealon.readingcloud.account.common.utils.QRCodeUtils;
import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.common.config.FileProperties;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.common.pojo.xzwusers.USchool;
import cn.zealon.readingcloud.common.pojo.xzwusers.WebLogin;
import cn.zealon.readingcloud.account.dao.WebLoginDao;
import cn.zealon.readingcloud.account.service.WebLoginService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 扫码登录表(WebLogin)表服务实现类
 *
 * @author makejava
 * @since 2023-04-18 13:38:38
 */
@Service("webLoginService")
public class WebLoginServiceImpl implements WebLoginService {
    @Resource
    private WebLoginDao webLoginDao;

    @Resource
    private FileProperties properties;

    @Resource
    private OssProperties ossProperties;

    @Resource
    private UAttributeService uAttributeService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WebLogin queryById(Long id) {
        return this.webLoginDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param webLogin    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<WebLogin> queryByPage(WebLogin webLogin, PageRequest pageRequest) {
        long total = this.webLoginDao.count(webLogin);
        return new PageImpl<>(this.webLoginDao.queryAllByLimit(webLogin, pageRequest), pageRequest, total);
    }

    @Override
    public void changeLoginStatus(String uuid){
        WebLogin webLogin=this.queryByUuid(uuid);
        if (webLogin!=null){
            webLogin.setStatus(1);
            this.update(webLogin);
        }
    }

    @Override
    public String loginWeb(String uuid,Long userId){
        WebLogin webLogin=this.queryByUuid(uuid);
        String userName="";
        if (webLogin!=null){
            UAttribute uAttribute=this.uAttributeService.queryById(userId);
            if (uAttribute != null) {
                webLogin.setUserId(userId);
                webLogin.setUserMessage(JSON.toJSONString(uAttribute));
                webLogin.setStatus(2);
                this.update(webLogin);
                userName=uAttribute.getQqnum();
            }
        }
        return userName;
    }



    @Override
    public WebLogin queryByUuid(String uuid){
        WebLogin webLogin = new WebLogin();
        webLogin.setIsused(0);
        webLogin.setUuid(uuid);
        List<WebLogin>webLoginList=this.webLoginDao.queryAll(webLogin);
        if (webLoginList!=null&&webLoginList.size() > 0){
            return webLoginList.get(0);
        }
        return null;
    }

    @Override
    public WebLogin queryQrCodeStatus(String uuid){
        WebLogin webLogin=this.queryByUuid(uuid);
        if (webLogin!=null){
            return webLogin;
        }else {
            webLogin =new WebLogin();
            webLogin.setIsused(0);
            webLogin.setUuid(uuid);
            webLogin.setStatus(0);
            webLogin.setQrCode(this.loginQcCode(uuid));
            this.insert(webLogin);
        }
        return this.queryByUuid(uuid);
    }

    public String loginQcCode(String uuid) {
        String codePath="";
        /**
         * 获取oss的属性
         */
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getKeyId();
        String accessKeySecret = ossProperties.getKeySecret();
        String bucketName = ossProperties.getBucketName();

        if (uuid != null) {
            try{
                // 存放在二维码中的内容
                BufferedImage image = null;
                File uploadFile = null;
                // 存放在二维码中的内容
                // 二维码中的内容可以是文字，可以是链接等
                String text = "https://xzw.aace.com.cn/weblogin/?uuid="+uuid;
                image = QRCodeUtils.createQRCode(text);
                image=QRCodeUtils.pressText(image,null, Color.BLACK,"打开微信，扫一扫");
                String imgPath =properties.getPath().getPath()+"/"+"logo.png";
                image= QRCodeUtils.insertLogo(image,imgPath,true);
                // 生成的二维码的路径及名称
                String name=System.currentTimeMillis()+"";
                String destPath =properties.getPath().getPath() + name + ".jpg";

                //生成二维码
//                QRCodeUtil.encode(text, null, destPath, true);
                //上传oss
                File file = new File(name + ".jpg");
                ImageIO.write(image, "jpg",file);
                Map<String, String> map = new HashMap<>();
                map= OssUtil.uploadOssFile(endpoint, accessKeyId ,accessKeySecret,bucketName,file,"Resource/News/",name + ".jpg");
                String fileUrlOss=map.get("fileUrl");
                System.out.println(fileUrlOss);
                file.delete();
                //上传服务器
                QRCodeUtils.writeToLocalByPath(image, "jpg", destPath);
                // 解析二维码 部分二维码错误 略去解析步骤
//                String str = QRCodeUtil.decode(destPath);
//                System.out.println(str);

                codePath="/Resource/News/"+name + ".jpg";
            }catch (Exception e) {

            }
        }
        return codePath;
    }
    /**
     * 新增数据
     *
     * @param webLogin 实例对象
     * @return 实例对象
     */
    @Override
    public WebLogin insert(WebLogin webLogin) {
        this.webLoginDao.insert(webLogin);
        return webLogin;
    }

    /**
     * 修改数据
     *
     * @param webLogin 实例对象
     * @return 实例对象
     */
    @Override
    public WebLogin update(WebLogin webLogin) {
        this.webLoginDao.update(webLogin);
        return this.queryById(webLogin.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.webLoginDao.deleteById(id) > 0;
    }
}
