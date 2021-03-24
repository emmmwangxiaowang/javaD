package javaD.multiThread;

import javax.naming.event.NamingExceptionEvent;
import java.util.Map;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/3/24 0024
 */
public class DownloadManager {
    private Map<String,String>[] source;
    private static  DownloadManager manager;
    private  static Object obj=new Object();
    private DownloadManager(){

    }

    public static DownloadManager getInstance(){
        if(manager==null){
            synchronized (obj){
                if(manager==null){
                    manager=new DownloadManager();
                }
            }
        }
        return manager;
    }

    public void loadData(String urlString,String filPath){
        DownloadOperation downloader=new DownloadOperation(urlString,filPath,3);
        downloader.download();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    float rate = downloader.currentRate();
                    if (rate < 1.00000000000001) {
                        System.out.println("当前下载比例"+rate);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        break;
                    }
                }
            }
        }).start();
    }



    public void loadData(Map<String,String>[] datas){

    }
}
