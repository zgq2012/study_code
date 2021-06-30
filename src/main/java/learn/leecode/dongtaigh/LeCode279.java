package learn.leecode.dongtaigh;

/**
 * 279. 完全平方数
 *
 * @author zgq
 */
public class LeCode279 {

    public static void main(String[] args) {
        int b = 14;
        int res = numSquares(b);
        System.out.println("结果 res : " + res);
    }

    /**
     * 完全平方数 (279)
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     * <p>
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     * <p>
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
     * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     * <p>
     * 示例 1：
     * 输入：n = 12
     * 输出：3
     * 解释：12 = 4 + 4 + 4
     * <p>
     * 示例 2：
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     *  
     * 提示：
     * 1 <= n <= 10^4
     */
    private static int numSquares(int n) {
        // 解法1：动态规划
        int count = solveOne(n);
        System.out.println("solveOne 的count : " + count);

        // 解法2：数学原理，
        count = solveTwo(n);
        System.out.println("solveTwo 的count : " + count);

        // 返回
        return count;
    }

    /**
     * 一个正整数n若是由i个数的平方和构成， 那么可知，n减去其中一个完全平方数，则 n - j^2 即转化为更小数量的 n'
     * 对于 n' ，与 n 同理可以得
     * 且 0 <= j < sqrt(n)
     */
    private static int solveOne(int n) {
        int[] dp = new int[n + 1];
        // 初始化dp[1] = 1，因为1是1的完全平方数， dp[0]值无意义
        dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            // 初始化最小值，取最大，不影响比较
            int min = Integer.MAX_VALUE;

            for (int j = 1; j * j <= i; j++) {
                // 循环比较 i 在 1 到 j² 时 dp[i - j * j] + 1 的最小值
                min = Math.min(min, dp[i - j * j] + 1);
            }
            // dp[i] =  Min(dp[i - j * j] + 1);( 1 <= j*j <= i)
            dp[i] = min;
        }
        return dp[n];
    }

    /**
     * 四平方和原理 -> 一个正整数n，一定是1到4个正整数的平方数之和
     * 若为1时，当且仅当 n = k^2;
     * 若为2时，则 n = a^2 + b^2; 即判断 1<= a*a <= n 情况下，n - a^2 是否为完全平方数即可
     * 若为4时，当且仅当 n = 4^k * (8m + 7);
     * 若为3时，情况复杂，利用排除法即可
     */
    private static int solveTwo(int n) {
        if (checkSqrt(n)) {
            // 表示n是完全平方和
            return 1;
        }

        for (int i = 1; i * i < n; i++) {
            if (checkSqrt(n - i * i)) {
                return 2;
            }
        }

        for (int i = 1; i < n; i++) {
            double temp = n / Math.pow(i, 4);
            if (Math.ceil(temp) - temp != 0) {
                // 不是整数
                continue;
            }
            double temp2 = (temp - 7) / 8;
            if (Math.ceil(temp2) - temp2 == 0) {
                // 不是整数
                return 4;
            }
        }

        return 3;
    }

    /**
     * 校验你是否是完全平方数
     *
     * @param n 参数
     * @return bool
     */
    private static boolean checkSqrt(int n) {
        double sqrt = Math.sqrt(n);
        return Math.ceil(sqrt) - sqrt == 0;
    }
}
