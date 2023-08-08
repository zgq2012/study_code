package learn.leecode.testcode;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.NamedThreadFactory;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * test
 *
 * @author zgq
 * @version v4.2.0
 * @since 2021/12/1
 **/
public class Test222 {

    public static void main(String[] args) {
        PersonClone clone1 = PersonClone.one.clone();
        PersonClone clone2 = PersonClone.one.clone();

        System.out.println(clone1.toString());
        System.out.println(clone2.toString());

        // watchdog
        redisTest();

        // post调用表单接口
        post();

        // dynamicThreadPool
        try {
            dynamicThreadPool();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // concurrentSkipListMap 跳表
        concurrentSkipMap();

        // threadLocal
        threadLocal();

        // jdk8的list用法
        listJdk8();

        // test jdk8
        testMap();

        // count,bigDecimal处理
        countVal();

        // timePort
        timePort();

        // linkCheck
        valCheck();

        // completeFuture
        asyncMo();

        // sortByEnCn
        sortByEnCn();
    }

    private static void redisTest() {
//    RedissonClient redissonClient = Redisson.create();
//        RLock anyLock = redissonClient.getLock("any");
//        anyLock.lock(10, TimeUnit.SECONDS);
//
//        try {
//            anyLock.tryLock(60, 10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
    }

    private static void post() {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("username", "18328704393");
//        map.put("password", "VMLoNeVf+DFRDMkJTpbvJ+BTKOKwAuWHbFyBBTBW8pPqkXS+fapWYsgwmr2JVmiQ5axfnLvESsVbjGB0usKqKu+aDfhoKL" +
//                "DKGBPilBnSLY3RNDbQFB07BR7b9DgzCIbutbh24o810mWXN0wU8jPSEcu43dG1OUiP86uNSVH0lByeV++cioYw70Rylru8" +
//                "+Xqz127LaxYYVZegrkWRHMZSnj6dVUZLREyc72vJ5Hfev3fFxAIXv37dB52AbwEvP52kr5Bbq/Zk2CPmFiPrU+35SWXPEl7" +
//                "V8FrL0SjeGn9GX611pI2EABfy5knOH04ZlSZp4Ea8fOXCiT9WIlMKf46rWg==");
//        map.put("authCod", "null");
//        map.put("grant_type", "password");
//        map.put("appTypeEnum", "WEB");
//        map.put("authKey", "null");
//        map.put("device", "Win32");
//        map.put("browser", "Chrome/)97.0.4692.99");
//        map.put("address", "四川省成都市");
//
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put("authorization", "Basic Y2hhb3MtdWFhOmxyd3p5dlpGUEpvWEN5enc=");
//
//        String post = HttpUtil.createPost("https://uaa-test.cdcdn.cn:22666/backend/auth/oauth/token")
//                .addHeaders(headers)
//                .form(map)
//                .execute()
//                .body();
//        System.out.println("post = " + post);
    }

    private static void dynamicThreadPool() throws Exception {
        // 线程池
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(
//                // 核心线程数,
//                // CPU 密集型任务(N+1或+2),让每一个cpu都一直运行，+1/2 用于防止线程偶发的缺页中断从而导致cpu空闲
//                // I/O 密集型任务(2N)，I/O过程不占用cpu,此时可以让其去干其他的，减少cpu空闲
//                5,
//                // 最大线程数
//                10,
//                // 线程存活时间
//                60,
//                TimeUnit.SECONDS,
//                // 阻塞队列，以及队列长度
//                new LinkedBlockingQueue<>(20),
//                // 线程工厂
//                new NamedThreadFactory("zgq", false),
//                // 拒绝策略
//                new ThreadPoolExecutor.CallerRunsPolicy());
//
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            try {
//                System.out.println("开始执行：-100");
////                    TimeUnit.SECONDS.sleep(temp);
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "-100";
//        }, pool);
//
////        List<CompletableFuture<String>> list = CollectionUtil.newArrayList();
//        for (int i = 0; i < 100; i++) {
//            int temp = i;
//            future = future.thenCombineAsync(CompletableFuture.supplyAsync(() -> {
//                try {
//                    System.out.println("开始执行：" + temp);
////                    TimeUnit.SECONDS.sleep(temp);
//                    TimeUnit.MINUTES.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return "" + temp;
//            }, pool), String::concat);
////            list.add(future);
//        }
//
//        System.out.println(future.get());
//
//        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(list.toArray(new CompletableFuture[0]));
//        completableFuture.join();
//
//        List<String> collect = list.stream().map(v -> {
//            try {
//                return v.get();
//            } catch (InterruptedException | ExecutionException e) {
//                return "-1";
//            }
//        }).collect(Collectors.toList());
//        System.out.println(collect);


//        for (int i = 0; i < 40; i++) {
//            int temp = i;
//            pool.execute(() -> {
//                getThreadPoolStatus(pool, "创建任务" + temp);
//
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//
//        getThreadPoolStatus(pool, "修改前");
//        TimeUnit.SECONDS.sleep(1);
//
//        pool.setCorePoolSize(10);
//        pool.setCorePoolSize(10);
//        LinkedBlockingQueue<Runnable> queue = (LinkedBlockingQueue) pool.getQueue();
//        queue.setCapacity(50);
//        pool.allowCoreThreadTimeOut(true);
//
//        getThreadPoolStatus(pool, "修改后");

    }

    private static void getThreadPoolStatus(ThreadPoolExecutor pool, String msg) {
        LinkedBlockingQueue<Runnable> queue = (LinkedBlockingQueue) pool.getQueue();
        int corePoolSize = pool.getCorePoolSize();
        int maximumPoolSize = pool.getMaximumPoolSize();
        int activeCount = pool.getActiveCount();
        long completedTaskCount = pool.getCompletedTaskCount();
        int qSize = queue.size();
        int reCa = queue.remainingCapacity();
        System.out.println(Thread.currentThread().getName() + "-" + msg + "-:" +
                " 核心线程数：" + corePoolSize +
                " 最大线程数：" + maximumPoolSize +
                " 线程池活跃度：" + activeCount * 100 / maximumPoolSize + "%" +
                " 任务完成数：" + completedTaskCount +
                " 队列大小：" + (qSize + reCa) +
                " 当前排队线程数：" + qSize +
                " 队列剩余大小：" + reCa +
                " 队列使用度：" + qSize * 100 / (qSize + reCa) + "%"
        );
    }

    private static void concurrentSkipMap() {
//        ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();
//        map.put("2022-05-01", "32");
//        map.put("2022-07-01", "32");
//        map.put("2022-06-01", "32");
//        map.put("2021-07-01", "32");
//        System.out.println(map.keySet());
    }

    private static void threadLocal() {
//        Thread thread = Thread.currentThread();
//        ThreadLocal<Object> threadLocal = ThreadLocal.withInitial(() -> "5425");
//
//        Thread thread1 = new Thread(() -> {
//            ThreadLocal<Object> stringThreadLocal = threadLocal;
//            System.out.println(stringThreadLocal.get());
//            System.out.println(threadLocal.get());
//        });
//
//        thread1.start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(threadLocal.get());

//        ThreadLocal<Test123.Person> zgq = ThreadLocal.withInitial(() -> new Test123.Person("zgq", "123"));
//        System.out.println("zgq.get().getAge() = " + zgq.get().getAge());
//        zgq.set(null);
//        String age = Optional.ofNullable(zgq.get()).orElse(new Test123.Person("214", "23")).getAge();
//        System.out.println("age = " + age);
//
//        ThreadGroup threadGroup = thread.getThreadGroup();


    }

    private static void listJdk8() {
       /* List<String> one1 = CollectionUtil.newArrayList("11", "12", "10");
        List<String> one2 = CollectionUtil.newArrayList("13", "14", "15");
        List<List<String>> two = CollectionUtil.newArrayList(one1, one2);
        List<List<String>> two2 = CollectionUtil.newArrayList(one1, one2);
        List<List<List<String>>> th = CollectionUtil.newArrayList();
        th.add(two);
        th.add(two2);

        System.out.println(one1.stream().reduce(String::concat).orElse("666"));

        System.out.println("th = " + th);
        System.out.println(th.stream().flatMap(Collection::stream).collect(Collectors.toList()));
        System.out.println(th.stream()
                .flatMap(v -> v.stream()
                        .flatMap(Collection::stream))
                .sorted()
                .distinct()
                .collect(Collectors.toList()));


        List<Test123.Person> perList = CollectionUtil.newArrayList(new Test123.Person("zgq", "93"),
                new Test123.Person("zs", null));


        // 返回一个定义的集合
        System.out.println(perList.stream().reduce(
                // 每次sink执行的一个返回的对象
                CollectionUtil.newArrayList(),
                // 定义Function, s1 -> 定义的返回对象， s2 -> 流执行的入口参数，即该次执行的主体，即集合的某一元素
                // 并且返回一个定义的返回类型
                (s1, s2) -> {
                    s1.add(s2.getAge());
                    s1.add(s2.getName());
                    return s1;
                },
                // 定义Function, s1 -> 返回对象1， s2 -> 返回对象2， 即流对象当此执行结果与上从执行结果的处理方式
                // 并且返回一个定义的返回类型
                (s1, s2) -> {
                    s1.addAll(s2);
                    return s1;
                }));

        // 返回一个集合
        System.out.println("" + perList.stream()
                .collect(
                        // 每次sink执行的一个返回的对象
                        ArrayList::new,
                        // 定义Consumer, s1 -> 定义的返回对象， s2 -> 流执行的入口参数，即该次执行的主体，即集合的某一元素
                        (v1, v2) -> v1.add(v2.getAge()),
                        // 定义Consumer, s1 -> 返回对象1， s2 -> 返回对象2， 即流对象当此执行结果与上从执行结果的处理方式
                        ArrayList::addAll)
        );

        // 返回一个字符串
        System.out.println(perList.stream()
                .collect(StringBuilder::new,
                        (v1, v2) -> v1.append(v2.getName()),
                        StringBuilder::append)
                .toString()
        );*/
    }

    private static void sortByEnCn() {
//        String[] fileNames = {"你的组别1", "你的组别2", "你的组别6", "你的组别11",
//                "你的组别7", "你的组别8", "你的组别9", "你的组别10",
//                "我的组别7", "我的组别8", "我的组别9", "我的组别10",
//                "你的组别3", "你的组别4", "你的组别5", "你的组别12"};
//
//        char[][] list = getList(fileNames);
//        Arrays.sort(list, new ChineseSortCp());
//
//        List<String> res = new ArrayList<>();
//
//        for (int i = 0; i < fileNames.length; i++) {
//            res.add(String.valueOf(list[i]));
//        }
//
//        System.out.println(res);
    }

    private static void testMap() {
//        Map<Object, Object> map = CollectionUtil.newHashMap();
//        // key, defaultValue, 获取不到返回默认值
//        System.out.println(map.getOrDefault(13, "a"));
//        System.out.println(map.get(13));
//        // key存在 -> 计算后覆盖value, 不存在 -> 计算后添加 key-value
        // (k, v) -> k 是 map的key, v key对应的value
//        System.out.println(map.compute(12, (k, v) -> v == null ? 1 : Integer.parseInt(v.toString()) + 1));
//        System.out.println(map.get(12));
//        System.out.println(map.compute(12, (k, v) -> v == null ? 1 : Integer.parseInt(v.toString()) + 1));
//        System.out.println(map.get(12));
//        String s = "aaabbc34c";
        // 计算每个字符出现的次数
//        for (char c : s.toCharArray()) {
//            map.compute(c, (k, v) -> v == null ? 1 : Integer.parseInt(v.toString()) + 1);
//            map.merge(c, 1, (oldVal, newVal) -> Integer.parseInt(oldVal.toString()) + Integer.parseInt(newVal.toString()));
//        }
//        System.out.println(map);
//
//        Map<Character, Integer> collect = Arrays.stream(ArrayUtil.wrap(s.toCharArray()))
//                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
//        System.out.println(collect);
//        // key存在 -> 计算后覆盖value, 不存在 -> 不计算不添加
//        System.out.println(map.computeIfPresent(12, (v1, v2)-> Integer.parseInt(v1.toString()) + 3));
//        System.out.println(map.get(12));
//        System.out.println(map.computeIfPresent(24, (v1, v2)-> Integer.parseInt(v1.toString()) + 3));
//        System.out.println(map.get(24));
//        // key存在 -> 不计算也不覆盖value, 不存在 -> 计算后添加
//        System.out.println(map.computeIfAbsent(24, v-> Integer.parseInt(v.toString()) + 3));
//        System.out.println(map.computeIfAbsent(12, v-> Integer.parseInt(v.toString()) + 4));
//        // 执行计算并添加
//        System.out.println(map.merge(36, "123", (v1, v2) ->  v1.toString().concat(v2.toString())));
//        System.out.println(map.get(36));
//        String test = "aa_bb_cc_aa_sss_sa_asd_fff_ss";
//        // 统计字符串里面各个字符的出现次数
//        System.out.println(Arrays.stream(ArrayUtil.wrap(test.toCharArray())).parallel().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum)));
//        String tes2t = "aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss" +
//                "_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss" +
//                "_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss" +
//                "_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss" +
//                "_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss" +
//                "_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss" +
//                "_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss" +
//                "_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss" +
//                "_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss" +
//                "_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss" +
//                "_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss" +
//                "_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss_aa_bb_cc_aa_sss_sa_asd_fff_ss";
//        // 统计字符串里面各个字符的出现次数
//        // 使用 parallel() 后，在collect或reduce中操作是线程安全的 {a=948, b=316, s=1106, c=316, d=158, f=474, _=1421}
//        System.out.println(Arrays.stream(ArrayUtil.wrap(tes2t.toCharArray())).parallel().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum)));
//
//        // 使用 parallel() 后，在正常的foreach中操作是线程不安全的 {a=801, b=309, c=310, d=156, f=463, s=180, _=1268}
//        HashMap<Character, Integer> newMap = CollectionUtil.newHashMap();
//        Arrays.stream(ArrayUtil.wrap(tes2t.toCharArray())).parallel().forEach(v -> newMap.compute(v, (v1 ,v2)-> v2 == null ? 1 : v2 + 1));
//        System.out.println(newMap);
    }

    private static void countVal() {
//        System.out.println(BigDecimal.valueOf(4.59).setScale(0, BigDecimal.ROUND_HALF_EVEN).intValue());
//        int real = 15;
//        int hisPredict = 25;
//        System.out.println(BigDecimal.valueOf((1 - (double) Math.abs(hisPredict - real) / real))
//                .multiply(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP) + "%");
    }

    private static void timePort() {

//        List<String> timeList = new ArrayList<>();
//
//        long timeInterval = 30;
//        LocalTime startTime  = LocalTime.of(7,0);
//        LocalTime endTime = LocalTime.of(22,0);
//        do {
//            timeList.add(startTime.toString());
//            startTime = startTime.plusMinutes(timeInterval);
//        }
//        while (startTime.compareTo(endTime) != 0);
//
//        timeList.add(endTime.toString());
//
//        System.out.println(timeList);

//        long timeInterval = Long.parseLong("15");
//        LocalTime zero = LocalTime.MIN;
//        LocalTime temp = LocalTime.MIN;
//        List<String> timeList = CollectionUtil.newArrayList();
//
//        do {
//            timeList.add(temp.toString());
//            temp = temp.plusMinutes(timeInterval);
//        } while (temp.compareTo(zero) != 0);
//        timeList.add(timeList.get(0));
//
//        List<String> timeIntervalList = CollectionUtil.newArrayList();
//        for (int i = 1; i < timeList.size(); i++) {
//            timeIntervalList.add(timeList.get(i-1) + "-" + timeList.get(i));
//        }
//
//        System.out.println(timeList);
//        System.out.println(timeIntervalList);
    }

    private static void valCheck() {
//        LinkVad<Object> linkVad = new LinkVad<>();
//        linkVad.next(123, v -> Integer.parseInt(v.toString()) > 100, "参数必须大于100");
//        linkVad.next("zxj123432352545", v -> v.toString().startsWith("zxj"), "姓名必须是zxj开头");
//        linkVad.next(v -> StrUtil.isNotEmpty(v.toString()), "姓名不能为空");
//        linkVad.next(v -> v.toString().length() <= 10, "姓名不能大于10个字节");
//
//        String validate = linkVad.validate();
//
//        System.out.println("validate = " + validate);
    }

    private static void asyncMo() {
//        List<Integer> list = CollectionUtil.newArrayList(1, 2, 3, 4, 5, 6);
//
//        // 线程池
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(
//                // 核心线程数,
//                // CPU 密集型任务(N+1或+2),让每一个cpu都一直运行，+1/2 用于防止线程偶发的缺页中断从而导致cpu空闲
//                // I/O 密集型任务(2N)，I/O过程不占用cpu,此时可以让其去干其他的，减少cpu空闲
//                5,
//                // 最大线程数
//                10,
//                // 线程存活时间
//                5,
//                TimeUnit.MINUTES,
//                // 阻塞队列，以及队列长度
//                new ArrayBlockingQueue<>(300),
//                // 拒绝策略
//                new ThreadPoolExecutor.CallerRunsPolicy());
//
//        List<CompletableFuture<Integer>> cs = list.stream()
//                .map(v -> CompletableFuture.supplyAsync(
//                        // 线程执行操作
//                        () -> {
//                            System.out.println("执行任务：" + v);
//                            return v;
//                        },
//                        // 自定义线程池
//                        pool
//                )).collect(Collectors.toList());
//
//        CompletableFuture
//                // 所有的线程组合
//                .allOf(cs.toArray(new CompletableFuture[0]))
//                // 等待所有线程执行完
//                .join();
//
//        // 得到每一个线程执行的结果
//        System.out.println(cs.stream().map(v -> {
//            try {
//                return v.get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//                return 0;
//            }
//        }).collect(Collectors.toList()));
//
//        // 关闭线程池
//        pool.shutdown();

//        List<Test123.Person> perList = CollectionUtil.newArrayList(new Test123.Person("zgq", "93"),
//                new Test123.Person("zs", null));
//        List<String> suList = perList.stream().map(v -> {
//            try {
//                return CompletableFuture.supplyAsync(v::getName).get();
//            } catch (InterruptedException | ExecutionException e) {
//                return "ec";
//            }
//        }).collect(Collectors.toList());
//
//        System.out.println(suList);
//
//
//        CompletableFuture<ArrayList<String>> listCompletableFuture = CompletableFuture
//                .supplyAsync(() -> CollectionUtil.newArrayList("123"))
//                .thenCombine(CompletableFuture.supplyAsync(() -> CollectionUtil.newArrayList("456")),
//                        (v1, v2) -> {
//                            v1.addAll(v2);
//                            return v1;
//                        })
//                .thenCompose(s -> CompletableFuture.supplyAsync(() -> {
//                            s.addAll(CollectionUtil.newArrayList("789"));
//                            return s;
//                        })
//                );
//
//        CompletableFuture.runAsync(() -> System.out.println("away!!!"));
//        try {
//
//            System.out.println(listCompletableFuture.get(1, TimeUnit.SECONDS));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private static char[][] getList(String[] fileNames) {
        char[][] chFileNames = new char[fileNames.length][];
        for (int i = 0; i < fileNames.length; i++) {
            chFileNames[i] = fileNames[i].toCharArray();
        }
        return chFileNames;
    }

    public static class InnerClassTest {
        private String name;
        private String realName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }
    }
}
