package javaD.collection;


import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Predicate;

class Person  implements Comparable<Person>{
    int age;
    int score;
    String name;

    public Person(int age, int score, String name) {
        this.age = age;
        this.score = score;
        this.name = name;
    }

    @Override
    public String   toString() {
        return "Person{" +
                "age=" + age +
                ", score=" + score +
                ", name='" + name + '\'' +
                '}';
    }


    public static void main(String[] args) {
        //数组的创建
        //List<Person> people=new ArrayList<>();
        //集合对象里面只能保存一种数据类型
        ArrayList<Person> personList = new ArrayList<>();

        //添加对象
        Person xw = new Person(32, 8, "小王");
        //在数组末尾添加
        personList.add(new Person(34,78,"小李"));
        personList.add(new Person(12,98,"小杨"));
        personList.add(new Person(23,78,"小蒋"));
        //在某个位置插入数据
       // personList.add(0,new Person(23,78,"小蒋"));
        System.out.println(personList);

        //获取一个对象
        Person person = personList.get(0);
        person=new Person(34,78,"小李");
        //比较的是对象，不是对象里面的内容
        if(personList.contains(person)){
            System.out.println("有这个人");
        }else{
            System.out.println("没有这个人");
        }
        //获取对象的索引值
        System.out.println(personList.indexOf(person));
        System.out.println(person);

        //重新改变某个值
        personList.set(0,person);
        System.out.println(personList.get(0));

        //清空
        //personList.clear();
        //System.out.println(personList);

        /*for (int i = 0; i < personList.size(); i++) {
            System.out.println(personList.get(i));
            //删除i对应的对象
            personList.remove(i);
            //删除某个对象
            personList.remove(person);
        }*/
        System.out.println("-------------------");

        //遍历这个数组
        //1.使用for循环遍历
      /*  for (Person person1 : personList) {
            System.out.println(person1);
            personList.remove(person1);
        }*/

        System.out.println("----------");

        //2.使用Iterate遍历器遍历
        //主要的区别是在遍历的同时需要删除对象
        //获取数组对应的遍历器对象
        Iterator<Person> iterator = personList.iterator();
        //使用hasNext()判断是否还有下一个
        //和next（）获取下一个对象
        while ((iterator.hasNext())){
            Person p=iterator.next();
            //iterator.remove();
        }
        System.out.println("=======================");

        //数据的删除
        //1.按照一定的条件删除
        //MyPredicate predicate=new MyPredicate();
        //personList.removeIf(predicate);
        System.out.println(personList);

        //2.使用匿名内部类
        /*personList.removeIf(new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                if(person.age>30){
                    //如果返回值为true，则这个对象会被删除
                    return true;
                }
                return false;
            }
        });*/

        //3.排序
        //1.自己定义的对象必须实现comparable接口和compareTo方法
        //Collections.sort(personList);
        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person person, Person t1) {
                //指定比较的规则
                return person.name.compareTo(t1.name);
            }
        });
        System.out.println(personList);
    }

    //升序
    //降序
    @Override
    public int compareTo(Person person) {
        if(age>person.age){
            return 1;
        }else if(age==person.age){
            return 0;
        }else
            return -1;
    }

    //创建一个类实现predicate接口的方法
    static class MyPredicate implements Predicate<Person>{
        @Override
        public boolean test(Person person) {
            if(person.age>30){
                return true;
            }
            return false;
        }
    }

}

