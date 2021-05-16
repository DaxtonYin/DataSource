package com.util;

import java.io.*;

public class FileUtils {
    public String separator = System.getProperty("file.separator");

    public void saveFile(String content, String fileName){
         saveFile(content,"data",fileName);
    }

    public void saveFile(String content,String directory, String fileName){
        String filePath = this.getClass().getResource("/").getPath();
        filePath = filePath+separator+"com"+separator+directory+separator+fileName;
        try {
            //写入的txt文档的路径
            PrintWriter pw = new PrintWriter(filePath, "utf-8");
            //写入的内容
            pw.write(content);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFileContent(String fileName) {
       return getFileContent("data",fileName);
    }

    public String getFileContent(String directory,String fileName) {
        String absolutePath = this.getClass().getResource("/").getPath();
        absolutePath = absolutePath+separator+"com"+separator+directory+separator+fileName;
        String str = "";
        try {
            // 创建一个FileInputStream对象
            File file = new File(absolutePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bs = new byte[1000000];
            // 将文件的内容读入 数组中
            fileInputStream.read(bs);
            // 将byte数组转成String
            str = new String(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.trim();
    }
}
