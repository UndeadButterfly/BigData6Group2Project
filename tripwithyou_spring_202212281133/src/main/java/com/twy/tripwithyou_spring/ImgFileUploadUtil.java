package com.twy.tripwithyou_spring;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ImgFileUploadUtil {
    // 이미지 저장 (이미지 파일,파일경로명,분류할 이름(ex.upload,reply,course,matching...))
    public static String saveImg(MultipartFile imgFile, String imgPath, String storeRoot) throws IOException {
        String fileName=null;
        if (imgFile!=null && !imgFile.isEmpty()){
            String[] contentType=imgFile.getContentType().split("/");
            if(contentType[0].equals("image")){
                fileName=storeRoot+"_"+System.currentTimeMillis()+"_"+(int)(Math.random()*10000)+"."+contentType[1];
                Path path= Paths.get(imgPath+"/"+fileName);
                imgFile.transferTo(path);
            }
        }
        return fileName;
    }
    public static int remove(String imgPath, String fileName){
        int remove=0;
        if (fileName != null) {
            File originFile = new File(imgPath + "/" + fileName);
            remove += (originFile.delete()) ? 1 : 0;
        }
        return remove;
    }
    public static int remove(String imgPath, List<String> fileNames){
        int remove=0;
        if(fileNames!=null){
            for(String fileName : fileNames){
                File originFile=new File(imgPath+"/"+fileName);
                remove+=(originFile.delete())?1:0;
            }
        }
        return remove;
    }
}
