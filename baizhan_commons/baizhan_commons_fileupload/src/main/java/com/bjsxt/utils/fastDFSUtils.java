package com.bjsxt.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class fastDFSUtils {
        private static final Properties PROPERTIES  = new Properties();
        private static final StorageClient STORAGE_CLIENT ;
    static{
        try {
            PROPERTIES.load(fastDFSUtils.class.getClassLoader().getResourceAsStream("fdfs_client.properties"));
            ClientGlobal.initByProperties(PROPERTIES);
            //创建Tracker客户端
            TrackerClient trackerClient = new TrackerClient();
            //创建Tracker服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取storage服务端
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            STORAGE_CLIENT = new StorageClient(trackerServer,storeStorage);
        } catch (IOException | MyException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    //获取文件后缀名
    public static String getExtName(String fileName){
        return fileName.substring(fileName.indexOf(".")+1);
    }
    //上传文件
    public static String[] uploadFile(InputStream is,String fileName){
        try {
            //获取文件字节长度
            int length = is.available();
            //输入流写入文件
            byte[] fileBytes = new byte[length];
            is.read(fileBytes,0,length);
            //编写元信息
            Properties meta = new Properties();
            meta.put("文件名",fileName);
            NameValuePair[] nvps = new NameValuePair[]{
                    new NameValuePair("文件名",fileName)
            };
            //获取文件名后缀
            String extName = getExtName(fileName);
            //上传文件
            String[] result = STORAGE_CLIENT.upload_file(fileBytes, extName, nvps);
            System.out.println(Arrays.toString(result));
            return result;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static StorageClient getStorageClient(){
        return STORAGE_CLIENT;
    }

}
