package learn.leecode.dongtaigh;

import java.util.Arrays;

/**
 * 70
 *
 * @author zgq
 */
public class LeCode70 {

    public static void main(String[] args) {
        climbStairs();
    }

    /**
     * 爬楼梯(27) - 动态规划
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。注意：给定 n 是一个正整数。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 示例 1：
     * 输入： 2 输出： 2 解释： 有两种方法可以爬到楼顶。1.  1 阶 + 1 阶； 2.  2 阶
     * <p>
     * 示例 2：输入： 3 输出： 3 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     */
    private static void climbStairs() {
        int n = 100;
        // 解决问题的方式适配斐波那契数列，即，result(n) = result(n-1) + result(n-2)
        // 可以用「滚动数组思想」把空间复杂度优化成 O(1)
        long[] result = new long[n];
        int index = 0;
        while (index < n) {
            if (index == 0) {
                result[index] = 1L;
            } else if (index == 1) {
                result[index] = 2L;
            } else {
                result[index] = result[index - 1] + result[index - 2];
            }
            index++;
        }
        System.out.println(Arrays.toString(result));
        System.out.println(result[n - 1]);
    }
}
