package javaD.IOstring;

import javax.lang.model.element.NestingKind;
import javax.management.ObjectInstance;
import java.io.*;
import java.nio.BufferOverflowException;
import java.util.Scanner;

/**
 * 文件I/O
 * 网络下载的数据 -->缓存
 * 文件:File(目录、文件)
 * 1.创建文件
 * 2.判断文件是否存在
 * 3.删除
 * 4.写入数据
 *File没有具体的写入数据的方法， 数据的读取和写入通过I/O流操作
 * I/O流:输入流  输出流
 * 方向: 相对的 相对于内存中正在运行的进程
 * 输出流: 内存->外部(硬盘 网络 外部设备)
 * 输入流: 外部 ( 硬盘 网络 外部设备) -> 内存
 *
 * 数据的存储2种
 *      1.二进制的形式保存 图片 视屏 音频
 *      2.字符的形式     文本
 * 读取方式
 *      二进制文件       ->字节流   一次读取一个字节
 *      文本            ->字符流   一次读取两个字节
 * I/O操作
 *      字节流     输入流 InputSteam 输出流  OutputSteam
 *      字符流     输入流 Reader     输出流  Writer
 * 具体实现类
 * 字节流 输入流    FileInputSteam   输出流  FileOutputSteam
 * 字符流 输入流    FileReader       输出流  FileWriter
 *
 * 注意:当使用I/O流时  不需要的资源需要自己手动释放/关闭文件
 *
 * 凡是实现了closeable接口的类都可以在try()内部创建
 * 当try代码块执行完毕或者有异常，系统自动关闭对应的对象
 * try(创建对象){
 *
 * }catch(){
 *
 * }
 *
 *
 *当以个文件存在写入数据时，会把之前的删除，从头开始写
 *RandomAccessFile
 * String String mode
 * File   String mode
 * Mode: r rw rws rwd
 */
public class myclass {
    public static void main(String[] args) {
        String des="C:\\Users\\Administrator\\Desktop\\file\\1.txt";
        String src="C:\\Users\\Administrator\\Desktop\\1.txt";
        //copyImageToFile(src,des);
        randomAccess(des);

    }

    //任意访问文件的位置
    public static void randomAccess (String src){
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(src,"rw");
            randomAccessFile.seek(9);
            byte[] buffer=new byte[50];
            randomAccessFile.read(buffer);
            System.out.println(new String(buffer));
            randomAccessFile.writeBytes("jack");
            System.out.println("----------------");
            randomAccessFile.read(buffer);
            System.out.println(new String(buffer));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读取对象
    public static void readObject(String src){
        try {
            //创建文件对象
            FileInputStream fileInputStream = new FileInputStream(src);
            //从哪儿读取？----->文件中
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            //创建接收对象保存文件中读取的对象
            person person =(person)objectInputStream.readObject();
            //输出对象
            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //保存对象
    //如果需要将自己定义的类的某个对象使用文件保存
    //呢么这个类必须是实现了serializable接口
    public static void saveObject(String des){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(des);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            person person = new person("jack",21);
            objectOutputStream.writeObject(person);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //输入重定向
    public static void redirect_input(String src){
        try {
            FileInputStream fileInputStream = new FileInputStream(src);
            Scanner s=new Scanner(fileInputStream);
            while (s.hasNext()) {
                System.out.println(s.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    //输入输出重定向 System.out -> 终端(重定向到自己指定的文件里面)
    public static void redirect_out(String des){
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(des);
            PrintStream ps=new PrintStream(fileOutputStream);
            System.setOut(ps);

            System.out.println("就是就是");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void  bufffered_IO_by(String src,String des){
        final FileInputStream fileInputStream;
        try {
            //字节缓冲输入流
            fileInputStream = new FileInputStream(src);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            //字节缓冲输出流
            FileOutputStream fileOutputStream = new FileOutputStream(des);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] buffer =new byte[1024];
            int len=0;
            while ((len=bufferedInputStream.read(buffer))!=-1){
                bufferedOutputStream.write(buffer);
            }
            bufferedOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //处理流BufferInputSteam/Reader   BufferOutputSteam/Writer
    //注意:当使用处理流输出时 需要使用flush来刷新流
    //虽然使用处理流比较快
    //1.使用节点流从磁盘将数据读取到内存的缓冲区
    //2.将内存缓冲区的数据读到处理流对应的缓冲区
    //3.处理流从处理流的缓冲区将数据读取
    public static void buffer_IO_ch(String src,String des){
        //
        try{
            //创建输入流
            FileInputStream fileInputStream = new FileInputStream(src);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader  =new BufferedReader(inputStreamReader);
            //创建输出流
            FileOutputStream fileOutputStream = new FileOutputStream(des);
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
            int ch=0;
            while ((ch=bufferedReader.read())!=-1){
                bufferedWriter.write(ch);
            }
            bufferedWriter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //一般要在这里关闭流，并且流的定义要在try语句外(作用范围!)
            //fileInputStream.close();
            //fileOutputStream.close();
        }

    }

    //字符输入输出流
    public static void copyTextToFile(String src,String des){
        try(FileReader fileReader = new FileReader(src);
            FileWriter fileWriter = new FileWriter(des);) {
            char[] buffer = new char[1024];
            while (true){
                int len = fileReader.read();
                if(len==-1){
                    break;
                }
                fileWriter.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //整个数组操作 将图片拷贝到另一个文件当中
    public static void copyImageToFile_buf(String src,String des){

        long start = System.currentTimeMillis();
        try(//创建字节输入流对象
            FileInputStream fileInputStream = new FileInputStream(src);
            //创建字节输出流对象
            FileOutputStream fileOutputStream = new FileOutputStream(des);) {
            //定义一个数组 用于保存 一次性读取的多个数据
            byte[] buffer=new byte[1024];
            int len=0;
            while(true){
                //read()一个字节一个字节的读取
                len= fileInputStream.read();
                // 判断是否读取完毕  返回值为-1
                if(len==-1){
                    break;
                }
                //write()一个字节一个字节地写入
                fileOutputStream.write(buffer);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }

    //单个字节操作 将图片拷贝到另一个文件当中
    public static void copyImageToFile(String src,String des){
        try(//创建字节输入流对象
            FileInputStream fileInputStream = new FileInputStream(src);
            //创建字节输出流对象
            FileOutputStream fileOutputStream = new FileOutputStream(des);) {
            //1.将图片读取到内存中
            //字节流(图片是二进制数据) 输入流 FileInputSteam
            //2.将内存中的图片数据写到磁盘文件中
            // 字节流 输出流 FileOutputSteam
            //创建字节输入流对象
            //FileInputStream fileInputStream = new FileInputStream(src);
            //创建字节输出流对象
            //FileOutputStream fileOutputStream = new FileOutputStream(des);
            //从输入流里面获取输入  写入到输出流
            int read =0;
            while(true){
                //read()一个字节一个字节的读取
                read=fileInputStream.read();
                //判断是否读取完毕  返回值为-1
                if(read==-1){
                    break;
                }
                //write()一个字节一个字节地写入
                fileOutputStream.write(read);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    //向文件里面写入文本
    public static void  writeToFile(String des){
        //1.准备需要写入的数据
        String txt="蒋莹莹";

        //2.选择字符流还是字节流
        FileWriter fw=null;
        try {
            //吸入的内容是文本 所以是字符流
            //把内存的地址写到磁盘  所以是输出流
            fw=new FileWriter(des);
            //开始写入数据
            fw.write(txt);


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭流
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void fileOperation() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\学习");
        //判断是文件还是目录
        if(file.isDirectory()){
            System.out.println("是目录");
        }
        if(file.isFile()){
            System.out.println("是文件");

        }

        //查看当前文件的所有内容
        /*String[] fileNameList = file.list();
        for (String name : fileNameList) {
            System.out.println(name);
        }*/

        //文件过滤
       /* String[] fileNameListFilter = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                //如果返回true 当前这个文件会被过滤出来
                //如果返回false 当前这个文件不会被选中
                File file1 = new File(file, s);
                if(file.isFile()){
                    return true;
                }
                return false;
            }
        });


        for (String name : fileNameListFilter) {
            System.out.println(name);
        }*/

        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                System.out.println(file.getPath());
            }
        }

    }

    public static void deleteFile(){
        File parent = new File("C:\\Users\\Administrator\\Desktop\\");
        File file = new File(parent,"file");
        if(file.exists()){
            file.delete();
        }

    }

    public static void createDir(){
        File file = new File("C:\\Users\\Administrator\\Desktop\\","file");
        if(file.exists()){
            return;
        }
        //创建目录
        file.mkdir();
    }

    public static void createFile(){
        //1.让File指向指定的一个文件（存在 不存在）  文件名
        //new File 并不会创建这个文件
        File file=new File("C:\\Users\\Administrator\\Desktop\\1.txt");
        //2.判断文件或目录是否存在
        if(!file.exists()){
            //3.创建文件
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
