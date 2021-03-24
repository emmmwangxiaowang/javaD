package javaD.multiThread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/3/24 0024
 */
public class DownloadOperation {
    private URL url;
    private String filePath;
    private int threadCount;
    private downloadThread[] tasks;
    private long size;

    public DownloadOperation(String urlString, String filePath, int threadCount) {
        try {
            this.url=new URL(urlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.filePath = filePath;
        this.threadCount = threadCount;
        tasks=new downloadThread[threadCount];
    }

    public void download(){
        //获取文件大小
        getFileSize();
        //创建文件   用于保存下载的数据
        createFile();

        //分配线程下载数据
        disPatch();
    }

    private void disPatch(){
        //计算每个线程下载的平均大小
        long avg=size/threadCount;

        long start=0;
        long downloadSize=avg;
        for(int i=0;i<threadCount;i++){
            start=i*avg;

            //最后一个线程可能下载的数量要大于平均值
            if(i==threadCount-1){
                downloadSize=size-start;
            }

            //创建线程  执行对应的模块进行下载
            downloadThread dt=new downloadThread(url,filePath,start,downloadSize);
            //保存这个线程对象
            tasks[i]=dt;
            //启动下载
            dt.start();
        }
    }

    public float currentRate(){
        long len =0;
        for(downloadThread dt:tasks){
            len+= dt.downloadedLength;

        }
        return (float)len/size;
    }

    private  void createFile(){
        File file=new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //设置文件大小
        RandomAccessFile rac = null;
        try {
            rac=new RandomAccessFile(file,"rw");
            rac.setLength(size);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rac.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getFileSize(){
        //url
        //获取连接
        HttpURLConnection conn=null;
        try {
            conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            //获取资源大小
            size=conn.getContentLengthLong();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            conn.disconnect();
        }
    }
}
