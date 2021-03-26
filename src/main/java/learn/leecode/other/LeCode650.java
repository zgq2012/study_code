package learn.leecode.other;

import java.util.Arrays;

/**
 * 650
 *
 * @author zgq
 */
public class LeCode650 {

    public static void main(String[] args) {
        int n = 15;
        int res = minSteps(n);
        System.out.println(res);
        int res2 = minSteps2(n);
        System.out.println(res2);
    }

    private static int minSteps2(int n) {
        /*
        采用数学技巧
        n为素数的时候，操作一定是n,
        n为合数的时候，操作一定是：n的所有公因数之和(去除1) 例：n = i*j*k*m, res = i + j + k +m
         */
        int res = 0;
        int d = 2;
        // n大于1才进行
        while (n > 1) {
            // 因数分解，素数时，d直接循环到n的值返回
            while (n % d == 0) {
                // 每分解一次，值记录，并且n取公因数
                res += d;
                n = n / d;
            }
            d++;
        }
        return res;
    }

    /**
     * (650)只有两个键的键盘
     * 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
     * Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
     * Paste (粘贴) : 你可以粘贴你上一次复制的字符。
     * 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。
     * 输出能够打印出 n 个 'A' 的最少操作次数。
     * <p>
     * 示例 1:
     * 输入: 3     输出: 3
     * 解释:
     * 最初, 我们只有一个字符 'A'。
     * 第 1 步, 我们使用 Copy All 操作。
     * 第 2 步, 我们使用 Paste 操作来获得 'AA'。
     * 第 3 步, 我们使用 Paste 操作来获得 'AAA'。
     * 说明:
     * n 的取值范围是 [1, 1000] 。
     */
    public static int minSteps(int n) {
        // 通过动态规划分析最优解
        /*
        1.当n为素数的时候(n = 1*n)，
            ->操作一定是：C-P-P-P-P-P-...-P,(总共n次)
            -> dp[n] = n;
        2.当n为合数时(n = i*j)，
            ->操作一定时：C-P..P- C-P..P- C...P,(先复制粘贴i个A，再将复制的i个A复制，粘贴j次，)
            -> dp[n] = dp[i]+dp[n/i];(i<=n,j<=n)
         */
        if (n == 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i <= 3) {
                dp[i] = i;
            }
            // 判断i是否为素数；
            int isSs = isHeData(i);
            if (i == isSs) {
                // 表示为素数
                dp[i] = i;
            } else {
                dp[i] = dp[isSs] + dp[i / isSs];
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    private static int isHeData(int i) {
        for (int n = 2; n < i; n++) {
            if (i % n == 0) {
                // 表示i被n所整除；表示为合数
                return i / n;
            }
        }
        return i;
    }
}
