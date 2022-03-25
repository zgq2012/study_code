package learn.leecode.arr;

/**
 * 309. 最佳买卖股票时机含冷冻期
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/25
 **/
public class LeCode309 {
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        int res = maxProfit(prices);
        System.out.println("res = " + res);
    }

    /**
     * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1:
     * 输入: prices = [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     * <p>
     * 示例 2:
     * 输入: prices = [1]
     * 输出: 0
     * <p>
     * 提示：
     * 1 <= prices.length <= 5000
     * 0 <= prices[i] <= 1000
     */
    private static int maxProfit(int[] prices) {
        // 采用动态规划，当天获取的股票增益 与 昨天的收益有关，且每天手里股票的状态有多种，需要多样分析
        // 股票状态：0 -> 当天结束持有股票，1 -> 当天结束没有股票，2 -> 当天是冷冻期
        // 设 f[n][s] 表示第n天，第s种状态的最大收益
         /*
         若第n天有股票，那么可能的情况是，
         第n-1天就持有；
         或者第n天才买入,若第n天才买入，那么第n-1天一定是冷冻期，总收益就要减去买入的股票
         f[n][0] = max(f[n-1][0], f[n-1][2] - prices[n])
         */
         /*
         若第n天处于冷冻期，那么第n-1天一定是在卖出状态，
         f[n][2] = f[n-1][1]
         */
         /*
         若第n天处于非冷冻期，但是手上也没有持有股票，可能情况是，
         当天不买入，此时收益与昨天不买入相当，
         或者昨天是冷冻期
         或者今天刚卖
         f[n][1] = max(f[n-1][1], f[n-1][0] + prices[n], f[n-1][2])
         */
        int len = prices.length;
        // 可以采用滚动变量代替数组，因为第n天以前的数据只有n-1有效，其他的都不需要了
        int[][] f = new int[len][3];
        f[0][0] = -prices[0];
        f[0][1] = 0;
        f[0][2] = 0;
        for (int n = 1; n < len; n++) {
            f[n][0] = Math.max(f[n - 1][0], f[n - 1][2] - prices[n]);
            f[n][2] = f[n - 1][1];
            f[n][1] = Math.max(Math.max(f[n - 1][2], f[n - 1][1]), f[n - 1][0] + prices[n]);
        }

        return Math.max(Math.max(f[len - 1][0], f[len - 1][1]), f[len - 1][2]);
    }
}
