package learn.leecode.testcode;

/**
 * TODO
 *
 * @author zgq
 * @version v5.6.3
 * @since 2023/8/4
 **/
public class PersonClone {
    public static PersonClone one = new PersonClone();

    @Override
    public PersonClone clone() {
        return one.clone();
    }

    @Override
    public String toString() {
        return "age:" + this.age + " name:" + this.name + " p2:" + (p2 == null ? "null" : this.p2.toString());
    }

    private String name;
    private String age;
    private PersonClone2 p2;
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

    public PersonClone(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public PersonClone() {
    }

    public PersonClone2 getP2() {
        return p2;
    }

    public void setP2(PersonClone2 p2) {
        this.p2 = p2;
    }
}
