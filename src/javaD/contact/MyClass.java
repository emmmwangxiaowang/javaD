package javaD.contact;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.Scanner;

public class MyClass {
    public static void main(String[] args) {
        //创建联系人对象
        Contact contact = new Contact();

        Scanner s = new Scanner(System.in);
        while(true){
            //提示添加联系人
            System.out.println("输入联系人姓名:");

            String name = s.next();
            System.out.println(name);
            //将汉字姓名转换为拼音（每个汉字首字母大写）
            String firstAlpha = util.getFirstAlpha(name);
            System.out.println(firstAlpha);

            //创建person
            person person=new person(firstAlpha,name);

            //添加联系人
            contact.addPerson(person);
            //是否继续
            if(!util.isContinue()){
                break;
            }
        }
        //显示内容
        contact.showList();
    }
}
