package learn.other.proxy.staticproxy;

import learn.other.proxy.RealSubject;
import learn.other.proxy.Subject;

/**
 * 静态代理
 *
 * @author zgq
 * @version v1.2.0
 * @date 2021/3/24
 **/
public class StaticProxy implements Subject {

    private RealSubject realSubject;

    public StaticProxy(RealSubject subject) {
        this.realSubject = subject;
    }

    @Override
    public void work(String name) {
        System.out.println("开始执行代理方法！");
        realSubject.work(name);
        System.out.println("执行代理方法结束！");
    }

    public static void main(String[] args) {
        // 被代理对象
        RealSubject realSubject = new RealSubject();
        // 开始代理对象
        StaticProxy staticProxy = new StaticProxy(realSubject);
        // 执行代理方法
        staticProxy.work("zgq");
    }
}
