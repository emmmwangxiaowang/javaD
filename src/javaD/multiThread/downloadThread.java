package javaD.multiThread;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/3/24 0024
 */
public class downloadThread extends Thread {
    private URL url;
    private String filePath;
    private long startPosition;
    private long size;
    public long downloadedLength;

    public downloadThread(URL url, String filePath, long startPosition, long size) {
        this.url = url;
        this.filePath = filePath;
        this.startPosition = startPosition;
        this.size = size;
    }

    @Override
    public void run() {
        try {
            //定位文件到这个线程应该写入的位置
            RandomAccessFile raf=new RandomAccessFile(filePath,"rw");
            //定位到开始写入位置
            raf.seek(startPosition);

            //开始下载
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setConnectTimeout(6*100);

            //获取输入流
            InputStream is = url.openStream();
            //设置数据读取的位置
            is.skip(startPosition);

            //开始读取数据  写入到文件
            byte[] buffer=new byte[1024];
            int len =0;
            while((len=is.read(buffer))!=-1){
                raf.write(buffer,0,len);

                //记录当前下载的长度
                downloadedLength+=len;

                //判断当前下载的范围是否结束
                if(downloadedLength>size){
                    break;
                }
            }

            is.close();
            conn.disconnect();
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
