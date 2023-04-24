package cn.zealon.readingcloud.book.service;

import org.springframework.web.multipart.MultipartFile;

public interface WxService {


    Boolean checkText(String msg);

    Boolean checkImg(MultipartFile multipartFile);

}
