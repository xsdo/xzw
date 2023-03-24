package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.service.CompositionService;
import cn.zealon.readingcloud.common.config.FileProperties;
import cn.zealon.readingcloud.common.exception.BadRequestException;
import cn.zealon.readingcloud.common.pojo.xzwresources.CAuthors;
import cn.zealon.readingcloud.book.dao.CAuthorsDao;
import cn.zealon.readingcloud.book.service.CAuthorsService;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.common.pojo.xzwresources.Images;
import cn.zealon.readingcloud.common.pojo.xzwtasks.ActiveTasklog;
import cn.zealon.readingcloud.common.utils.FileUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者表(CAuthors)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:14:02
 */
@Service("cAuthorsService")
public class CAuthorsServiceImpl implements CAuthorsService {
    @Resource
    private CAuthorsDao cAuthorsDao;

    @Resource
    private CompositionService compositionService;

    @Resource
    private FileProperties properties;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CAuthors queryById(Long id) {
        return this.cAuthorsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cAuthors    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CAuthors> queryByPage(CAuthors cAuthors, PageRequest pageRequest) {
        long total = this.cAuthorsDao.count(cAuthors);
        return new PageImpl<>(this.cAuthorsDao.queryAllByLimit(cAuthors, pageRequest), pageRequest, total);
    }

    @Override
    public List<CAuthors>queryAll(CAuthors cAuthors){
        return this.cAuthorsDao.queryAll(cAuthors);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject toAuthors(String title, String name, String province, String city, String area, String school  ){
        JSONObject result = new JSONObject();
        Map<String, Object> data = new HashMap<>();
        try {
            //查询是否存在作文
            Composition composition=new Composition();
            composition.setCTitle(title);
            List<Composition> compositions =this.compositionService.queryAll(composition);
            if (!compositions.isEmpty()) {

                CAuthors cAuthors =new CAuthors();
                cAuthors.setIsused(0);
                cAuthors.setCreateTime(new Date());
                cAuthors.setUpdateTime(new Date());
                cAuthors.setAName(name);
                cAuthors.setAType(0);
                cAuthors.setProvince(province);
                cAuthors.setCity(city);
                cAuthors.setArea(area);
                cAuthors.setSchool(school);
//                cAuthors.setAHead(this.updateImage(multipartFile));
                this.cAuthorsDao.add(cAuthors);
                Composition cc=compositions.get(0);
                cc.setCAuthorid(cAuthors.getId());
                this.compositionService.update(cc);

                result.put("sign",00);
                data.put("data","录入成功");
            }else {
                result.put("sign",-1);
                data.put("data","作文标题错误或不存在该作文");
            }
            result.put("data",data);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("sign",-1);
            result.put("data","服务器错误");
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public String updateImage(MultipartFile multipartFile) {
        // 文件大小验证
        FileUtil.checkSize(properties.getAvatarMaxSize(), multipartFile.getSize());
        // 验证文件上传的格式
        String imageUrl = "";
        String image = "gif jpg png jpeg";
        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        if(fileType != null && !image.contains(fileType)){
            throw new BadRequestException("文件格式错误！, 仅支持 " + image +" 格式");
        }
        try {
            String fileUrl = FileUtil.uploadFile(multipartFile, properties.getPath().getPath());
            imageUrl="/Resource/News/"+fileUrl;

        }catch (Exception e) {

        }
        return imageUrl;
    }
    /**
     * 新增数据
     *
     * @param cAuthors 实例对象
     * @return 实例对象
     */
    @Override
    public CAuthors insert(CAuthors cAuthors) {
        this.cAuthorsDao.insert(cAuthors);
        return cAuthors;
    }

    /**
     * 修改数据
     *
     * @param cAuthors 实例对象
     * @return 实例对象
     */
    @Override
    public CAuthors update(CAuthors cAuthors) {
        this.cAuthorsDao.update(cAuthors);
        return this.queryById(cAuthors.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cAuthorsDao.deleteById(id) > 0;
    }
}
