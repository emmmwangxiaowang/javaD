package javaD.contact;

public class person implements Comparable<person> {
    String firstAlphaString;//汉字首字母组成的字符串
    String name;

    public person(String firstAlphaString, String name) {
        this.firstAlphaString = firstAlphaString;
        this.name = name;
    }


    @Override
    public int compareTo(person person) {
        return firstAlphaString.compareTo(person.firstAlphaString);
    }
}
