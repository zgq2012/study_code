package learn.leecode.testcode;

/**
 * TODO
 *
 * @author zgq
 * @version v5.6.3
 * @since 2023/8/4
 **/
public class PersonClone2 {
    private String name;
    private String age;
    // 省略构造函数、Getter&Setter方法

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public PersonClone2(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "age:" + this.age + " name:" + this.name;
    }
}
