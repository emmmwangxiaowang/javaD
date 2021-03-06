package javaD.network;

import javax.imageio.stream.FileImageOutputStream;
import javax.xml.crypto.Data;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/3/24 0024
 */

//通过url直接登录
public class upload {
    public static void main(String[] args) throws Exception {
        postFile();
    }

    //post 上传文件
    public  static void postFile() throws Exception{
        URL url = new URL("http://localhost/upload.php");

        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-type","multipart/form-data,boundary=AaB03x");

        //发送请求体数据
        OutputStream os=conn.getOutputStream();
        DataOutputStream out =new DataOutputStream(os);

        //开头
        out.writeBytes("--AaB03x\r\n");
        out.writeBytes("content-disposition: form-data; name=\"file\"; filename=\"1.png\"\r\n");
        out.writeBytes("Content-Type: image/png\r\n\r\n");

        //图片的具体内容
        //创建文件的输入流对象
        FileInputStream fis=new FileInputStream("C:\\Users\\Administrator\\Desktop\\1.png");
        BufferedInputStream bis=new BufferedInputStream(fis);
        byte[] buffer =new byte[1024];
        int len =0;
        while ((len=bis.read(buffer))!=-1){
            out.write(buffer,0,len);
        }
        out.writeBytes("\r\n");
        //结尾   结尾前面也有换行---->\r\n
        out.writeBytes("--AaB03x--\r\n");

        out.flush();
        out.close();
        bis.close();

        InputStream is = conn.getInputStream();
        DataInputStream in=new DataInputStream(is);
        len=in.read(buffer);
        String content = new String(buffer,0,len);
        System.out.println(content);
    }

    //post 底层方式提交普通数据
     public static void  postData2() throws Exception {
         //1.url
         URL url=new URL("http://localhost/login.php");

        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        //设置请求方式为POST
        conn.setRequestMethod("POST");
        //设置输出流
        conn.setDoOutput(true);
         //设置请求头部信息
         conn.setRequestProperty("Content-type","multipart/form-data,boundary=AaB03x");


         //发送请求体数据
         OutputStream os = conn.getOutputStream();
         DataOutputStream out=new DataOutputStream(os);

         out.writeBytes("--AaB03x\r\n");
         out.writeBytes("content-disposition: form-data; name=\"user_name\"\r\n");
         out.writeBytes("\r\n");
         out.writeBytes("jack\r\n");
         out.writeBytes("--AaB03x\r\n");
         out.writeBytes("content-disposition: form-data; name=\"user_password\"\r\n");
         out.writeBytes("\r\n");
         out.writeBytes("123\r\n");
         out.writeBytes("--AaB03x--\r\n");
         out.flush();
         out.close();

         InputStream is = conn.getInputStream();
         DataInputStream in=new DataInputStream(is);
         byte[] buffer = new byte[1024];
         int len=in.read(buffer);
         String content = new String(buffer,0,len);
         System.out.println(content);
    }

    //post 提交普通数据
    public static void postData() throws Exception {
        //1.url
        URL url=new URL("http://localhost/login.php");

        //2.获取连接对象
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setConnectTimeout(5*100);

        //3.拼接上传的普通数据
        String params="user_name=jack&user_password=123";

        //4.获取输出流对象
        OutputStream os = conn.getOutputStream();
        //使用DataOutputStream来包裹字节输出流
        //平台无关的   内存->网络中
        DataOutputStream out = new DataOutputStream(os);

        //5.将自己的数据写入到输出流中
        out.writeBytes(params);
        out.flush();
        out.close();

        //获取服务器的响应信息
        InputStream is = conn.getInputStream();
        DataInputStream in=new DataInputStream(is);
        byte[] buffer = new byte[1024];
        int len=in.read(buffer);
        String content = new String(buffer,0,len);
        System.out.println(content);
    }
}
