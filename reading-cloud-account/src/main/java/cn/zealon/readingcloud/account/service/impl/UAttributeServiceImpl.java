package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.dao.UTableDao;
import cn.zealon.readingcloud.common.config.FileProperties;
import cn.zealon.readingcloud.common.exception.BadRequestException;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import cn.zealon.readingcloud.account.dao.UAttributeDao;
import cn.zealon.readingcloud.account.service.UAttributeService;
import cn.zealon.readingcloud.common.pojo.xzwusers.UTable;
import cn.zealon.readingcloud.common.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户属性表(UAttribute)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
@Service("uAttributeService")
public class UAttributeServiceImpl implements UAttributeService {
    @Resource
    private UAttributeDao uAttributeDao;

    @Resource
    private FileProperties properties;

    @Resource
    private UTableDao uTableDao;



    /*@Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> updateHead(MultipartFile multipartFile) {
        // 文件大小验证
        FileUtil.checkSize(properties.getAvatarMaxSize(), multipartFile.getSize());
        // 验证文件上传的格式
        String image = "gif jpg png jpeg";
        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        if(fileType != null && !image.contains(fileType)){
            throw new BadRequestException("文件格式错误！, 仅支持 " + image +" 格式");
        }
        File file = FileUtil.upload(multipartFile, properties.getPath().getAvatar());
        return new HashMap<String, String>(1) {{
            put("portrait", Objects.requireNonNull(file).getPath());
            put("avatar", file.getName());
        }};
    }
*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> updateHeadImg(MultipartFile multipartFile) {
        // 文件大小验证
        FileUtil.checkSize(properties.getAvatarMaxSize(), multipartFile.getSize());
        // 验证文件上传的格式
        String image = "gif jpg png jpeg";
        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        if(fileType != null && !image.contains(fileType)){
            throw new BadRequestException("文件格式错误！, 仅支持 " + image +" 格式");
        }
        try {
            String fileUrl = FileUtil.uploadFile(multipartFile, properties.getPath().getAvatar());

            return new HashMap<String, String>(1) {{
                put("fileUrl", "/Resource/avatar/"+fileUrl);
            }};
        }catch (Exception e) {
            return null;
        }
    }
    @Override
    public UAttribute changeHead(Long userId,String fileUrl){
        UAttribute uAttribute=this.queryById(userId);
        if (uAttribute!=null){
            uAttribute.setPortrait(fileUrl);
            this.update(uAttribute);
        }
        return this.queryById(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> updateBackGround(MultipartFile multipartFile) {
        // 文件大小验证
        FileUtil.checkSize(properties.getAvatarMaxSize(), multipartFile.getSize());
        // 验证文件上传的格式
        String image = "gif jpg png jpeg";
        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        if(fileType != null && !image.contains(fileType)){
            throw new BadRequestException("文件格式错误！, 仅支持 " + image +" 格式");
        }
        try {
            String fileUrl = FileUtil.uploadFile(multipartFile, properties.getPath().getPath());

            return new HashMap<String, String>(1) {{
                put("fileUrl", "/Resource/News/"+fileUrl);
            }};
        }catch (Exception e) {
            return null;
        }
    }
    @Override
    public UAttribute changeBackGround(Long userId,String fileUrl){
        UAttribute uAttribute=this.queryById(userId);
        if (uAttribute!=null){
            uAttribute.setBackground(fileUrl);
            this.update(uAttribute);
        }
        return this.queryById(userId);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UAttribute queryById(Long id) {
        UAttribute uAttribute = this.uAttributeDao.queryById(id);
        if (uAttribute == null) {
            return null;
        }
        if (uAttribute.getUTableuse()!=null){
            UTable uTable=uTableDao.queryById(uAttribute.getUTableuse().longValue());
            if (uTable != null) {
                uAttribute.setRemarks(uTable.getUTableName());
            }
        }

        return uAttribute;
    }

    /**
     * 分页查询
     *
     * @param uAttribute  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UAttribute> queryByPage(UAttribute uAttribute, PageRequest pageRequest) {
        long total = this.uAttributeDao.count(uAttribute);
        return new PageImpl<>(this.uAttributeDao.queryAllByLimit(uAttribute, pageRequest), pageRequest, total);
    }

    @Override
    public List<UAttribute>queryAll(UAttribute uAttribute){
        return this.uAttributeDao.queryAll(uAttribute);
    }

    @Override
    public List<UAttribute>queryRand(int size){
        return this.uAttributeDao.queryRand(size);
    }

    /**
     * 新增数据
     *
     * @param uAttribute 实例对象
     * @return 实例对象
     */
    @Override
    public UAttribute insert(UAttribute uAttribute) {
        this.uAttributeDao.insert(uAttribute);
        return uAttribute;
    }

    /**
     * 修改数据
     *
     * @param uAttribute 实例对象
     * @return 实例对象
     */
    @Override
    public UAttribute update(UAttribute uAttribute) {
        this.uAttributeDao.update(uAttribute);
        return this.queryById(uAttribute.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uAttributeDao.deleteById(id) > 0;
    }
}
