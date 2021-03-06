package learn.leecode.other;

/**
 * 1227
 *
 * @author zgq
 */
public class LeCode1227 {

    public static void main(String[] args) {
        int n = 5;
        double res = nthPersonGetsNthSeat(n);
        System.out.println(res);
    }

    /**
     * (1227)飞机座位分配概率
     * 有 n 位乘客即将登机，飞机正好有 n 个座位。第一位乘客的票丢了，他随便选了一个座位坐下。
     * 剩下的乘客将会：
     * 如果他们自己的座位还空着，就坐到自己的座位上，
     * 当他们自己的座位被占用时，随机选择其他座位
     * 第 n 位乘客坐在自己的座位上的概率是多少？
     * <p>
     * 示例 1：
     * 输入：n = 1 输出：1.00000
     * 解释：第一个人只会坐在自己的位置上。
     * <p>
     * 示例 2：
     * 输入: n = 2    输出: 0.50000
     * 解释：在第一个人选好座位坐下后，第二个人坐在自己的座位上的概率是 0.5。
     * <p>
     * 提示：
     * 1 <= n <= 10^5
     */
    public static double nthPersonGetsNthSeat(int n) {
        /*
        n个人时，第一个人是随机坐的，那么每次做的概率都是(1/n)，同时坐的位置有3种情况：
        1.坐在自己的位置上，那么后面的所有人都坐在正确的位置上， f(n) = (1/n) * 1.0;
        2.坐在第n个人的位置上，那么第n个人没有正确的位置坐， f(n) = (1/n) * 0 = 0;
        3.坐在第2到第(n-1)的位置上，那么第2到(n-1)个人坐位置时又决定了第n个人的位置，那么与n的情况也相同
        即，2 <= i <= n-1时，第i个人的概率为(1/n) * f(i);
        那么总的概率和为：
        f(n) = (1/n) * 1.0 + (1/n) * 0 + (1/n) * f(2) + (1/n) * f(3) +...+ (1/n) * f(n-1);
        通过裂项相消
        -> f(n-1) = (1/(n-1)) * (1.0 + 0 + f(2) + f(3) +...+ f(n-2));
        -> n*f(n) - (n-1)*f(n-1) = f(n-1);
        -> n*f(n) - n*f(n-1) = 0;
        -> f(n) = f(n-1);
        -> f(n) = f(n-1) = f(n-2) = ... = f(3) = f(2);
        -> f(2) = 1/2;
        -> f(n) = 1/2;
        那么可以得到：
               1.0; n=1;
        f(n) = 0.5; n >= 2;
        */
        return n >= 2 ? 0.5 : 1.0;
    }
}
