package learn.leecode.dongtaigh;

/**
 * 322. 零钱兑换
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/25
 **/
public class LeCode322 {
    public static void main(String[] args) {
        int[] coins = {474, 83, 404, 3};
        int amount = 264;
        int res = coinChange(coins, amount);
        System.out.println("res = " + res);
    }

    /**
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * 示例 1：
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * <p>
     * 示例 2：
     * 输入：coins = [2], amount = 3
     * 输出：-1
     * <p>
     * 示例 3：
     * 输入：coins = [1], amount = 0
     * 输出：0
     * <p>
     * 提示：
     * 1 <= coins.length <= 12
     * 1 <= coins[i] <= 2^31 - 1
     * 0 <= amount <= 10^4
     */
    private static int coinChange(int[] coins, int amount) {
        // 试用动态规划解决, 同 完全平方数 的思想
        // dp[n] 表示总金额为n时，所需要的硬币最小数
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = amount + 1;
            // 比较i位置所有的 i - coins[j], (0 <= j < coins.length - 1) 的最小值
            for (int coin : coins) {
                if (coin <= i) {
                    int minJ = dp[i - coin] + 1;
                    min = Math.min(minJ, min);
                }
            }

            dp[i] = min;
        }

        // 若最后 d[n] > n 表示没有合适的硬币书数足
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
