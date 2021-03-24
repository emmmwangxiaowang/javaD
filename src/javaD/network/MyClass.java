package javaD.network;

import java.io.InputStream;
import java.net.*;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/3/24 0024
 */
public class MyClass {
    public static void main(String[] args) throws Exception {
        //想网络中发起请求
        //1.获取对应的url地址
        String str="http://localhost/login.php?user_name=jack&user_password=123&%E7%99%BB%E9%99%86=%E6%8F%90%E4%BA%A4";
        URL url=new URL(str);
        /*//编码
        String world= URLDecoder.decode("%E7%99%BB%E9%99%86=%E6%8F%90%E4%BA%A4","utf-8");
        //解码
        String code= URLEncoder.encode("登陆","utf-8");*/

        //2.使用URLConnection发起连接
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");//默认get
        conn.setDoInput(true);//设置是否打开接受的流，默认打开
        conn.setDoOutput(true);//设置是否打开输出流 post方式必须打开
        conn.setConnectTimeout(5*100);//请求时间
        conn.setUseCaches(false);//是否需要缓存

        //3.接收发送和接收都是用输入输出流
        //openStream   从服务器端获取数据
        //conn.getOutputStream() 向服务器端发送数据
        InputStream is = url.openStream();

        //4.处理输入流的数据 ->byte[] ->String
        byte[] buffer = new byte[1024];
        int len =0;
        while ((len=is.read(buffer))!=-1){
            String content=new String(buffer);
            System.out.println(content);
        }

        //5.关闭流
        is.close();
    }
}
