package javaD.multiThread;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/3/24 0024
 */
public class MyClass {
    public static void main(String[] args) {
        String url="http://localhost/upload/video/2.mp4";
        String path="C:\\Users\\Administrator\\Desktop\\copy.mp4";
        DownloadManager.getInstance().loadData(url,path);
    }
}
