package javaD.CommandTool;

import javax.sound.midi.Soundbank;
import javax.xml.transform.sax.SAXResult;
import java.io.File;

/**
 * 接收用户的指令 和 处理命令
 */
public class CommandTool implements ICommand{
    //默认启动后操作的目录
    private static final String DESKTOP_PATH="C:\\Users\\Administrator\\Desktop";
    //记录当前操作的目录路径
    private StringBuilder currentDirPath;

    public CommandTool(){
        currentDirPath=new StringBuilder(DESKTOP_PATH);
    }
    //启动命令行工具
    public void start(){
        //创建读取指令类的对象
        CommandOperation commandOperation = new CommandOperation();
        //欢迎界面
        System.out.println("欢迎使用命令行工具");
        while(true){
            //显示目录提示
            showParent();
            //输入指令
            try {
                commandOperation.readCommand(this);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    }

    private void showParent(){
        //获取最后一个\\的索引值
        int start = currentDirPath.lastIndexOf("\\");
        //获取最后的内容
        String parent = currentDirPath.substring(start+1);
        //输出提示内容
        //parent
        System.out.print(parent+"#");
    }

    @Override
    public boolean list() {
        File[] files = fileManager.getInstance().list(currentDirPath.toString());
        for (File file : files) {
            //获取文件名
            String name=file.getName();
            //获取文件长度    字节 byte   要转成kb  1kb = 1024byte
            long size =file.length();
            long kb=size/1024;
            long by = size%1024;
            System.out.print(name);
            for(int i=0; i<30-name.length();i++){
                System.out.print(" ");
            }
            System.out.println(kb+"kb"+"."+by+"kb");
        }
        return false;
    }

    @Override
    public boolean mkdir(String path) {
        //"C:\\Users\\Administrator\\Desktop"在当前目录后拼接
        //拼接完整路径
        String dirPath= currentDirPath.toString()+"\\"+path;
        fileManager.getInstance().mkdir(dirPath);
        return false;
    }

    @Override
    public boolean copy(String src, String des) {
        //C:\\Users\\Administrator\\Desktop
        //拼接完整路径
        String srcPath= currentDirPath.toString()+"\\"+src;
        String desPath=currentDirPath.toString()+"\\"+des;
        fileManager.getInstance().copy(srcPath,desPath);
        return false;
    }

    @Override
    public boolean remove(String path) {
        //拼接完整路径
        String dirPath= currentDirPath.toString()+"\\"+path;
        fileManager.getInstance().remove(dirPath);
        return false;
    }

    @Override
    public boolean cd_to_child(String path) {
        currentDirPath.append("\\"+path);
        return false;
    }

    @Override
    public boolean cd_to_parent() {
        int start=currentDirPath.toString().lastIndexOf("\\");
        int end=currentDirPath.length();
        currentDirPath.delete(start,end);
        return false;
    }
}
