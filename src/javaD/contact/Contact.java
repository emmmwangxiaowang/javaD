package javaD.contact;

import java.util.*;

public class Contact {
    ArrayList<String>  titles;//保存栏目标题
    Map<String, List<person>> contacts;//保存每个栏目的名字和内容

    public Contact() {
        //初始化数组
        titles=new ArrayList<>();
        contacts=new HashMap<>();
    }

    //显示所有人信息
    public void showList(){
        for (String title : titles) {
            //打印这个栏目的标题
            System.out.println(title);
            //输出这个栏目对应的所有人信息
            //获取这个title对应的联系人的信息
            List<person>list=contacts.get(title);
            for (person person : list) {
                System.out.println(person.name);
            }
        }
    }

    public void addPerson(person person){
        //获取person对应的首字母
        String title = person.name.substring(0, 1);
        //判断当前栏目中是否有这个person对应的首字母
        if(titles.contains(title)) {
            //已经有这个栏目
            List<person> lists = contacts.get(title);
            //添加到数组中
            lists.add(person);

            //对数组进行排序
            Collections.sort(lists);
        }
        else{
            //没有这个栏目
            //现在标题数组中添加这个栏目
            titles.add(title);
            //对标题数组进行排序
            Collections.sort(titles);

            //添加键值对
            List<person>list=new ArrayList<>();
            list.add(person);
            contacts.put(title,list);
        }
    }
}
