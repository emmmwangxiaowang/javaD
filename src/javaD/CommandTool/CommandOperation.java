package javaD.CommandTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 接收用户输入和解析输入类型
 */
public class CommandOperation {
    //回调对象
    private ICommand listener;
    //获取输入对象
    private Scanner mScanner;
    //保存所有指令
    private List<String>commands;
    public CommandOperation() {
        mScanner=new Scanner(System.in);
        commands= Arrays.asList(ICmd.COMMANDS);
    }

    //copy file1 file2
    //next() 以空格作为结束符
    //nextLine()读取一行数据 以\n结尾
    //接收用户输入指令
    public void readCommand(ICommand listener) throws exception.CommandNotExitException, exception.CommandArgumentException {
        this.listener=listener;
        //接收指令
        String command = mScanner.nextLine();
        //解析指令
        parseCommand(command);
    }

    //ls
    //rm file
    //copy file1 file2
    //
    public void parseCommand(String command) throws exception.CommandNotExitException, exception.CommandArgumentException {
        //将指令以空格为分割符分开
        String[] comp = command.split(" ");
        //获取用户指令
        String cmd =comp[0];


        //判断指令是否存在
        if(!commands.contains(cmd)){
            //输入的指令不存在
            //抛出异常
            throw new exception.CommandNotExitException("指令不存在");
        }

        //存在就解析是那种指令
        switch (cmd){
            case ICmd.CD:
                if(comp.length!=2){
                    throw new exception.CommandArgumentException("参数不正确");
                }
                listener.cd_to_child(comp[1]);
                break;
            case ICmd.CDP:
                //cd..
                if(comp.length!=1){
                    throw new exception.CommandArgumentException("cd..不需要参数");
                }
                listener.cd_to_parent();
                break;
            case  ICmd.MKDIR:
                //mkdir file
                if(comp.length!=2){
                    throw new exception.CommandArgumentException("mkdir需要参数");
                }
                listener.mkdir(comp[1]);
                break;
            case ICmd.REMOVE:
                //rm file
                if(comp.length!=2){
                    throw new exception.CommandArgumentException("remove需要参数");
                }
                listener.remove(comp[1]);
                break;
            case ICmd.LS:
                //ls
                if(comp.length!=1){
                    throw new exception.CommandArgumentException("ls 不需要参数");
                }
                listener.list();
            case ICmd.COPY:
                //copy file1 file2
                if(comp.length!=3){
                    throw new exception.CommandArgumentException("copy参数不正确");
                }
                listener.copy(comp[1],comp[2]);
                break;
        }
    }


}
