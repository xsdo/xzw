package cn.zealon.readingcloud.common.pojo.xzwresources;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * 随机图表(Images)实体类
 *
 * @author makejava
 * @since 2023-03-22 18:22:34
 */
public class Images implements Serializable {
    private static final long serialVersionUID = 284996847235569189L;
    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    /**
     * 大图地址
     */
    private String imageb;
    /**
     * 小图地址
     */
    private String images;
    /**
     * 类型
     */
    private Integer type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageb() {
        return imageb;
    }

    public void setImageb(String imageb) {
        this.imageb = imageb;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}

