package learn.other.proxy.dynamicproxy;

import learn.other.proxy.RealSubject;
import learn.other.proxy.RunIns;
import learn.other.proxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author zgq
 * @version v1.2.0
 * @date 2021/3/24
 **/
public class DynamicProxy {
    /**
     * 代理对象未知不固定
     */
    private Object target;

    /**
     * 有参构造
     *
     * @param object 要代理的对象
     */
    public DynamicProxy(Object object) {
        this.target = object;
    }

    /**
     * 为目标对象生成代理对象
     *
     * @return 代理对象
     */
    public Object getProxyIns() {
        // 3个参数，一个代理对象的类加载器，一个是其实现的所有接口，一个是方法处理器
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                // o:代理对象, method:要代理执行的方法, args:方法参数
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                        System.out.println("object正在被代理！");
                        Object invoke = method.invoke(target, args);
                        System.out.println("object代理结束！");
                        // 返回方法的结果值
                        return invoke;
                    }
                }

//                (o, method, args) -> {
//                    System.out.println("object正在被代理！");
//                    // 执行代理方法
//                    // 用代理对象执行执行
//                    method.invoke(target, args);
//                    System.out.println("object代理结束！");
//                    return null;
//                }
        );
    }

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        System.out.println(realSubject.getClass());
        Subject subject = (Subject) new DynamicProxy(realSubject).getProxyIns();
        RunIns runIns = (RunIns) new DynamicProxy(realSubject).getProxyIns();
        System.out.println(subject.getClass());
        subject.work("zgq");
        String res = runIns.runDay("zgq");
        System.out.println("res = " + res);
    }
}
