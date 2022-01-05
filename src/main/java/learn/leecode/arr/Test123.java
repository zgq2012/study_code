package learn.leecode.arr;

/**
 * TODO
 *
 * @author zgq
 * @version v3.1.0
 * @since 2021/7/20
 **/
public class Test123 {


    public static class Person {
        private String name;
        // 省略构造函数、Getter&Setter方法

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Person(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Person xiaoZhang = new Person("小张");
        Person xiaoLi = new Person("小李");
        swap(xiaoZhang, xiaoLi);
        System.out.println("xiaoZhang:" + xiaoZhang.getName());
        System.out.println("xiaoLi:" + xiaoLi.getName());

        Test222.InnerClassTest test = new Test222.InnerClassTest();
        test.setName("asd");
        System.out.println(test);
    }

    public static void swap(Person person1, Person person2) {
        Person temp = person1;
        person1 = person2;
        person2 = temp;
        System.out.println("person1:" + person1.getName());
        System.out.println("person2:" + person2.getName());
        person1.setName("person1");
        person2.setName("person2");
    }
}
