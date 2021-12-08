package learn.other.proxy;

/**
 * 被代理的真实实现类
 *
 * @author zgq
 * @version v1.2.0
 * @date 2021/3/24
 **/
public class RealSubject implements Subject, RunIns {

    @Override
    public void work(String name) {
        System.out.println("工作者是：" + name);
    }

    @Override
    public String runDay(String name) {
        System.out.println("奔跑者是：" + name);
        return name;
    }

    public void drink(String water){
        System.out.println("喝：" + water);
    }
}
