package org.example.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadUtils {
    //定义目标路径，即图片上传到的位置
    private static final String BASE_PATH = "M:\\Gitee\\PROJECT_UPLOAD\\";

    //定义图片访问路径
    private static final String SREVER_PATH = "http://localhost:8282/pic/";

    //定义上传方法
    public static String upload(MultipartFile file){
        //改写文件名，令其唯一
        GetOneId getOneId = new GetOneId("pic",11);
        String filename = getOneId.GetOnlyId();
        //创建一个文件实例
        File image = new File(BASE_PATH,filename);
        //对这个文件进行上传的操作
        try {
            file.transferTo(image);
        } catch (IOException e) {
            return null;
//            e.printStackTrace();
        }

        return SREVER_PATH+filename;
    }

}
