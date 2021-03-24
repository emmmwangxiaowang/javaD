package javaD.CommandTool;

/**
 * 定义指令
 */
public interface ICmd {
    String LS="ls";             //列出当前目录
    String MKDIR="mkdir";       //创建目录
    String COPY="copy";         //拷贝
    String REMOVE="rm";     //删除
    String CD="cd";             //切换目录
    String CDP="cd..";          //进入上一层

    String[]  COMMANDS=new String[]{LS,MKDIR,COPY,REMOVE,CD,CDP};
}
