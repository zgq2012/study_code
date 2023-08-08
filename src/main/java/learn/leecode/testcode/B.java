package learn.leecode.testcode;

/**
 * TODO
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/5/9
 **/
public class B {

    /**
     * 静态先行，普通从后，构造最后, 静态与普通中，属性与代码块按排放顺序执行
     * 若引用本身，则静态属性初始化自身对象的时候只会执行本身的普通属性，
     */
    public static B b1 = new B("b1");
    public static B b2 = new B("b2");
    public static int a = 1;
    public static B b3 = new B("b3");

    private String name;
    public String age = "113";

    public B(String name) {
        System.out.println("执行构造方法" + name);
        this.name = name;
        System.out.println("---------------");
    }

    public static String c = getS();
    public String d = getS2();

    private static String getS() {
        getS3();
        System.out.println(":执行静态属性的getS方法！");
        System.out.println("ff:" + f);
        return "143";
    }

    private static void getS3() {
        System.out.println("getS3:" + f);
    }

    private String getS2() {
        System.out.println("get a:" + a + "");
        System.out.println("age:" + age);
        a++;
        System.out.println("执行普通属性的getS2方法！");
        System.out.println("get a:" + a + "");
        return "143";
    }

    {
        System.out.println("构造块:");
    }

    public static String f = "静态f";
    static {
        System.out.println("静态块" + f);
    }

    public static void main(String[] args) {
        B b3 = new B("b3");
        System.out.println(b3.name);
        System.out.println(B.a);
    }
}
