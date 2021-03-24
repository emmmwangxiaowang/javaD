package javaD.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/3/24 0024
 */
public class downLoad {
    public static void main(String[] args) throws Exception {
        //下载视频
        //1.url
        URL url =new URL("http://localhost/upload/video/3.mp4");

        //2.连接
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();

        //3.获取输入流
        InputStream inputStream = url.openStream();
        BufferedInputStream bis=new BufferedInputStream(inputStream);

        //4.创建输出流
        String des="C:\\Users\\Administrator\\Desktop\\test.mp4";
        FileOutputStream fos=new FileOutputStream(des);
        BufferedOutputStream bos=new BufferedOutputStream(fos);

        //5.将数据写入磁盘
        byte[] buffer = new byte[1024];
        int len =0;
        while ((len=bis.read(buffer))!=-1){
            bos.write(buffer,0,len);
        }

        bos.close();
        fos.close();
        bis.close();
        inputStream.close();
    }
}
