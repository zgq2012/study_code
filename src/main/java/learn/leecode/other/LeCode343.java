package learn.leecode.other;

/**
 * 343
 *
 * @author zgq
 */
public class LeCode343 {

    public static void main(String[] args) {
        int n = 4;
        System.out.println("n:" + n);
        int res = integerBreak(n);
        System.out.println(res);
    }

    /**
     * (343)整数拆分
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     * <p>
     * 示例 1:
     * 输入: 2    输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     * <p>
     * 示例 2:
     * 输入: 10    输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     * <p>
     * 说明: 你可以假设 n 不小于 2 且不大于 58。
     */
    public static int integerBreak(int n) {
        /*
        解法2：
        动态规划:dp[i]=max(2×(i−2),2×dp[i−2],3×(i−3),3×dp[i−3]);( i>=3 )
         */
         /*
         解法1：
         一个整数拆分为多个整数（个数大于等于2）之和
         那么其乘积的最大值，必然是尽可能的拆分为大于1的最小部分
         例：...Max(8) = 3*3*2; Max(10) = 3*3*2*2; Max(12) = 3*3*3*3;...
         根据部分枚举我们可以得到基本规律（或者归纳法、反证法证明）：
         将一个整数尽可能的拆分为含3的组合，
         那么得到 -> n/3 的余数 n%3 可能为0，1，2
         为0表示都是3的组合，不管；
         为1表示最后一次拆分得到 3+1，但是3*1<2*2，故将最后的3+1拆分为2+2；
         为2表示最后不可再拆，直接乘积
         */
        if (n < 2 || n > 58) {
            return 0;
        }
        if (n == 2 || n == 3) {
            return n - 1;
        }
        int count = n / 3;
        System.out.println("3的个数：" + count);
        int mo = n % 3;
        System.out.println("n的余数:" + mo);
        double max = Math.pow(3, count);
        if (mo == 1) {
            max = max / 3 * 2 * 2;
        } else if (mo == 2) {
            max = max * mo;
        }
        return (int) max;
    }
}