package com.bjsxt.service.Impl;

import com.bjsxt.commons.pojo.BaizhanResult;
import com.bjsxt.service.FileUploadService;
import com.bjsxt.utils.fastDFSUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${baizhan.fastdfs.nginx}")
    private String nginxServer;
    @Override
    public BaizhanResult fileUpload(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String[] result = fastDFSUtils.uploadFile(inputStream, fileName);
            if(result != null){
                System.out.println("存储地址为:"+nginxServer+result[0]+"/"+result[1]);
                return BaizhanResult.ok(nginxServer+result[0]+"/"+result[1]);
            }else{
                throw new Exception("上传失败");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return BaizhanResult.error(e.getMessage());
        }
    }


}
