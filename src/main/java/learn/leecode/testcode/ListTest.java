package learn.leecode.testcode;

import learn.other.proxy.RealSubject;
import learn.other.proxy.Subject;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static learn.leecode.testcode.B.a;

/**
 * TODO
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/6/1
 **/
public class ListTest {
    public static void main(String[] aaa) throws Exception {
//        ms();
//        testClass();
//        proxyT();
//        test();
//        testStr();
//        countDown();
        cyclicBr();
    }

    public static final int THREAD_COUNT = 8;

    private static void cyclicBr() throws InterruptedException {
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 需要同步的线程数量
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("线程达到栅栏优先执行！"));

        for (int i = 0; i < THREAD_COUNT; i++) {
//            System.out.println("进入循环！！！！！");
            final int threadNum = i;
            Thread.sleep(1000);
            threadPool.execute(() -> {
                try {
                    test2(threadNum);
                    try {
                        /*
                         * 等待60秒，保证子线程完全执行结束
                         */
//                        System.out.println("-----await开始------");
                        // await() 当栅栏的资源数不能被完全使用时，执行会出现异常！
                        // 例如循环了7次，但是资源数为3，
                        // 最后第7次循环时，资源数制备消耗1，这时会抛出异常，并等待30秒过后才会执行await之后的代码
                        // 虽然抛出异常，但是也是达到了栅栏的效果，剩余的线程都是达到栅栏之后才会一起执行
                        cyclicBarrier.await(30, TimeUnit.SECONDS);
//                        System.out.println("-----await结束------");
                    } catch (Exception e) {
                        System.out.println("-----CyclicBarrierException------");
                    }
                    System.out.println("threadnum:" + threadNum + "is finish" + System.currentTimeMillis());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            });
        }

        System.out.println("shutdown pool!!!");
        threadPool.shutdown();
        System.out.println("shutdown pool end!!!");
    }

    public static void test2(int threadNum) {
        System.out.println("threadnum:" + threadNum + "is ready" + System.currentTimeMillis());
    }

    private static void countDown() throws Exception {
        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        // 只是测试使用，实际场景请手动赋值线程池参数
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNum = i;
            threadPool.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 表示一个请求已经被完成
                    countDownLatch.countDown();
                }

            });
        }

        System.out.println("执行count等待！");
        // await() 方法必须要让资源消耗完才会结束，不然就会一直等待，
        // 例如上面初始化了10个但是只循环使用了5个，此时await()就会一直等待，产生死锁
        countDownLatch.await();
        System.out.println("执行wait完成！");
        threadPool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadnum) throws InterruptedException {
        System.out.println("进入线程等待1秒！");
        Thread.sleep(1000);
        System.out.println("threadNum:" + threadnum);
        Thread.sleep(1000);
    }

    private static void testStr() {
        String s1 = "a" + "b"; // 编译器优化后创建ab对象，放入常量池中，并返回 "ab"的引用
        String s2 = "a" + "b"; // 编译器优化后就是ab, 常量池中已经存在，直接去池中拿对象引用
        String s11 = "a";
        String s22 = "b";
        String s33 = s11 + s22; // 运行期才知道s33的结果，创建了StringBuilder对象拼接后返回了新的String引用在堆中
        String s44 = "ab"; // 直接从常量池中取的

        System.out.println(s1 == s2);  // true
        System.out.println(s1 == s33);  // fasle
        System.out.println(s1 == s44);  // true
        System.out.println(s33 == s44);  // false
        String s3 = s1 + s2;
    }

    private static void ms() {
        Semaphore semaphore = new Semaphore(1);
//        semaphore.acquire();
        new ReentrantLock();
        new LinkedHashSet<>();
        new HashSet<>();
        new LinkedList<>();
        new PriorityQueue<>();
        new Hashtable<>();
    }

    private static void proxyT() {
        RealSubject realSubject = new RealSubject();
        Subject bPro = (Subject) Proxy.newProxyInstance(
                realSubject.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(),
                (o, method, args) -> {
                    System.out.println("执行Subject的jdk代理对像方法");
                    method.invoke(realSubject, args);
                    System.out.println("执行Subject的jdk代理对像结束");
                    return null;
                });
        bPro.work("cxy");

        System.out.println("+++++++++++++++++++++++++++");

        Enhancer eProxy = new Enhancer();
        eProxy.setClassLoader(realSubject.getClass().getClassLoader());
        eProxy.setSuperclass(realSubject.getClass());
        eProxy.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("执行Subject的cglib代理对像方法");
            methodProxy.invokeSuper(o, objects);
            System.out.println("执行Subject的cglib代理对像结束");
            return null;
        });

        RealSubject chRes = (RealSubject) eProxy.create();
        chRes.work("cglib");
    }

    private static void testClass() {
        System.out.println(a);
        new Sub().show();
        System.out.println(Sub.bb);
    }

    private static void test() {
        System.out.println(1.0 / 0);
        System.out.println("------------------");
        System.out.println(1.0 / 0.0);
        System.out.println("------------------");
        System.out.println(1.0 / -0.0);
        System.out.println("------------------");
        System.out.println(-1.0 / 0.0);
        System.out.println("------------------");
        System.out.println(1 / 0.0);
        System.out.println("------------------");
        System.out.println(0 / -0.0);
        System.out.println("------------------");
        System.out.println(-0 / 0.0);
        System.out.println("------------------");
        System.out.println(0 / 0);
        System.out.println(1 / 0);
    }

    private static class Sub extends Pa {
        static {
            // 还未初始化，但是类加载，已经在准备阶段分配了变量的内存，并赋予了默认值，此时时直接在其内存空间上修改值
            bb = 1;
        }

        // 然后，再次初始化值，
        public static int bb = 0;
        // 编译时就赋值，不需要后续初始化
        public final static int cc = 22;

        public static void fun() {
            System.out.println("执行sub的静态方法！");
        }

        private void show() {
            super.num = 10;
            super.getNumAll();

        }
    }

    private static class Pa {
        public int num;

        public static int pb;

        public void getNumAll() {
            System.out.println("Pa.num = " + num);
            System.out.println("Pa.pb = " + pb);
        }
    }
}
