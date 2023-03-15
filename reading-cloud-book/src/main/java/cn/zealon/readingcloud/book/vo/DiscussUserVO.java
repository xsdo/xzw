package cn.zealon.readingcloud.book.vo;

import cn.zealon.readingcloud.common.pojo.xzwresources.CDiscuss;
import cn.zealon.readingcloud.common.pojo.xzwresources.CNote;
import cn.zealon.readingcloud.common.pojo.xzwresources.Circle;
import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import lombok.Data;

@Data
public class DiscussUserVO {

    private CDiscuss cDiscuss;

    private UAttribute UAttribute;
}
