package javaD.thread;

import javax.swing.text.TabableView;
import java.io.ObjectOutputStream;

/**
 * 多线程
 * 程序  静态代码
 * 进程   正在运行，执行的一个程序QQ idea
 *        进程用于管理所有的资源，不进行实际的任务
 * 线程: 完成具体的任务(一个进程里面可以有多个线程)
 * QQ运行起来-> 进程-> 1.聊天  2.视频 3.刷空间
 *
 *
 * 主线程:     java（main 方法 里面的代码  就在主线程中跑)
 *            Android/iOS (启动程序  开到UI界面-》 UI主线程)
 *
 * 子线程: 除了主线程之外的线程
 *
 * 为什么要使用多线程:
 *      在主线程里面 任务的执行顺序是从上至下的，如果其中一个任务需要花费大量时间（下载数据)
 *      呢么这个任务后面的任务就会被阻塞,必须等这个任务结束才能被执行，用户的体验效果不好
 *      这个时候就需要将耗时的任务放在另外一个不在主线程里面执行的路径(子线程)
 * 主线程/子线程  都有自己独立的内存空间(执行路径/声明周期)
 *
 * Thread
 * Thread.currentThread()   获取当前线程信息
 *
 * 线程是通过抢占事件片来货去运行机会的(谁抢到时间片 谁就可以运行)
 * 时间片是由操作系统来分配的，所以每一次执行结果基本不一致
 *
 * 当调用Start方法是，这个线程会自动扔到操作系统的任务队列中(线程池)
 * 治愈这个任务什么时候被执行，我们无法确定，由操作系统来决定
 * 如何开启一个线程
 *      1.写一个类及承诺与Thread
 *      a.创建类继承与Thread
 *        重写父类的run方法，具体执行的任务放在run方法
 *      b.创建类的对象
 *      c.调用start方法   开始执行
 *        系统会自动将这个任务放到队列中，等待调度
 *      2.写一个类实现Runnable接口
 *          a.创建一个类实现Runnable接口  (这个类知识一个任务，并不能创建线程)
 *          b.创建Thread类的对象      (Thread类可以创建线程  我们只需要将自己的任务和这个Thread关联
 *          c.调用start启动线程
 *
 *  两种启动线程方式的对比
 *      灵活性: 方式2更好(更容易扩展)
 *      松耦合
 *
 *  不管子线程还是主线程  都有自己的执行路径(独立内存空间)
 *  线程的生命周期(创建到结束)
 *  创建状态: new Thread()
 *  就绪状态: 1.新的线程调用start
 *          2.阻塞条件结束
 *          3.正在运行当代线程时间片被其他行政村抢走
 *  运行状态: 从就绪状态到运行状态  是由操作系统来实现的，外部无法敢于
 *  死亡状态: 1.run方法结束
 *          2.手动让线程暂停  不建议使用stop
 *            通过其他方式让线程暂停
 * @author 王航
 * @QQ 954544828
 * @since 2021/3/20 0020
 */
public class MyClass {
    public static void main(String[] args) {

    }

    public static void testThread(){
        //2.创建具体的对象
        TestThread testThread1=new TestThread("子线程1");
        TestThread testThread2=new TestThread("子线程2");
        System.out.println(Thread.currentThread()+"main");

        //3.启动线程
        testThread1.start();
        testThread2.start();
    }
    public  static void testRunnable(){
        //2.创建具体对象
        //这个类不能直接开启线程  必须依赖于Thread类
        TestRunnable testRunnable = new TestRunnable();

        //3.创建一个Thread对象  让这个线程去执行testRunnable的任务  这个线程名字为: 子线程1
        Thread thread1 = new Thread(testRunnable,"子线程1");
        Thread thread2 = new Thread(testRunnable,"子线程2");

        //4.启动线程
        thread1.start();
        thread2.start();
    }
}

//1.创建一个类实现Runnable接口
class TestRunnable implements Runnable{
    @Override
    public void run() {
        //这个线程需要执行的任务
        for (int i=0;i<100;i++){
            System.out.print(i);
            System.out.println(Thread.currentThread().getName());
        }
    }
}

//1.创建类继承与Thread
class TestThread extends Thread{
    public TestThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        //这个线程需要执行的任务
        for (int i=0;i<100;i++){
            System.out.println(i);
            System.out.println(getName());
        }
    }
}