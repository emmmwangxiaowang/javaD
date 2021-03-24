package javaD.CommandTool;


import javax.swing.text.StyledEditorKit;

public interface ICommand {
    /**
     * 列出当前目录的所有内容  名字+ize
     * @return
     */
    boolean list();

    /**
     * 创建一个目录
     * @param path
     * @return
     */
    boolean mkdir(String path);

    /**
     * 将src的文件复制到des位置
     * @param src
     * @param de
     * @return
     */
    boolean copy(String src,String de);

    /**
     * 删除文件
     * @param path
     * @return
     */
    boolean remove(String path);

    /**
     * 切换当前目录到子目录
     * @param path
     * @return
     */
    boolean cd_to_child(String path);

    /**
     * 切换到上一层目录
     * @return
     */
    boolean cd_to_parent();

}
