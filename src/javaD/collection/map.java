package javaD.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * map接口 映射     没有顺序
 *      --HashMap           管理键值对
 *      --ConcurrentMap     线程安全
 *      --LinkedMap
 * Dictionary key - value 键值对
 * key 键
 * value 值
 */
public class map {
    public static void main(String[] args) {
        Map<String,Person> map=new HashMap<>();

        Person person1 = new Person(21, 99, "莹莹");
        Person person2 = new Person(21, 98, "墁墁");
        //添加键值对
        map.put("jack",person1);
        map.put("merry",person2);

        //获取键值对个数
        System.out.println(map.size());

        //是否包含某一个key
        System.out.println(map.containsKey("jack"));
        System.out.println(map.containsValue(person1));

        //获取某个key对应的值
        Person p=map.get("jack");
        System.out.println(p);

        //删除键值对
        //map.remove("jack");
        System.out.println(map);

        //替换
        map.replace("jack",new Person(21,97,"昌霖"));
        System.out.println(map.get("jack"));

        //清空
        /*map.clear();
        System.out.println(map);*/

        //map遍历
        Set<Map.Entry<String, Person>> entries = map.entrySet();
        for (Map.Entry<String, Person> entry : entries) {
            System.out.println(entry.getKey()+" :"+entry.getValue());
        }
    }
}
