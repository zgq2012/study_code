package learn.other.proxy.cglib;

import learn.other.proxy.RealSubject;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLlb的代理:通过继承的方式实现
 * 可执行代理委托类所有非final修饰的方法
 *
 * @author zgq
 * @version v1.2.0
 * @date 2021/3/24
 **/
public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("开始执行cglib代理");
        // 执行代理
        Object object = proxy.invokeSuper(obj, args);
        System.out.println("cglib代理结束");
        return object;
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 设置代理类
        enhancer.setSuperclass(RealSubject.class);
        // 设置回调代理
        enhancer.setCallback(new CglibProxy());
        // 创建代理类
        RealSubject proxy = (RealSubject) enhancer.create();
        System.out.println(proxy.getClass());
        proxy.runDay("zgq");
        proxy.work("zgq");
        proxy.drink("海之言");
    }
}
