package javaD.CommandTool;

import javax.swing.text.StyledEditorKit;
import java.io.*;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 王航
 * @QQ 954544828
 * @since 2021/3/19 0019
 */
public class fileManager {
    private static fileManager manager;

    public fileManager() {
    }

    //单例方法
    public static fileManager getInstance(){
        if(manager==null){
            synchronized (fileManager.class){
                if(manager==null){
                    manager=new fileManager();
                }
            }
        }
        return  manager;
    }

    /**
     * 创建目录
     */

    public boolean mkdir(String path){
        File file=new File(path);
        if (file.exists()){
            return false;
        }
        //mkdir要求路径中的目录都存在
        //mkdirs如果路径中的目录不存在 也会自动创建
        return file.mkdir();
    }

    /**
     *删除文件
     * @param path
     * @return
     */
    public boolean remove(String path){
        File file=new File(path);
        if(!file.exists()){
            return false;
        }
        return file.delete();
    }

    /**
     * 列出文件信息
     * @param path
     * @return
     */
    public File[] list(String path){
        File file=new File(path);
        if(!file.exists()){
            return null;
        }
        return file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                if(s.startsWith(".")){
                    return false;
                }
                return true;
            }
        });
    }

    /**
     * 文件拷贝
     * @param src
     * @param des
     * @return
     */
    public boolean copy(String src,String des){
        File srcFile=new File(src);
        File desFile=new File(des);
        //判断文件是否存在
        if(!srcFile.exists()||desFile.exists()){
            return false;
        }
        //判断是否是文件
        if(srcFile.isFile()){
            //直接拷贝文件
            copyFile(src,des);
        }else{
            //是目录
            //创建当前的目录
            desFile.mkdir();
            //需要将原目录的所有内容拷贝到des目录
            for (File file : srcFile.listFiles()) {
                copy(file.getAbsolutePath(),des+"\\"+file.getName());
            }
        }
        return true;
    }

    private void copyFile(String src,String des){
        FileInputStream fis=null;
        BufferedInputStream bis=null;
        FileOutputStream fos=null;
        BufferedOutputStream bos=null;
        try {
            //创建输入流
            fis=new FileInputStream(src);
            bis=new BufferedInputStream(fis);

            //创建输出流
            fos=new FileOutputStream(des);
            bos=new BufferedOutputStream(fos);

            //创建buffer
            byte[] buffer= new byte[1024];
            int len=0;
            while((len=bis.read(buffer))!=-1){
                bos.write(buffer);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bis.close();
                fis.close();
                bos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
