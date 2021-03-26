package learn.leecode.arr;

/**
 * 122
 *
 * @author zgq
 */
public class LeCode122 {

    public static void main(String[] args) {
        getGuP();
    }

    /**
     * 买卖股票的最佳时机(122)
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 输入: [7,1,5,3,6,4] 输出: 7
     * <p>
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     */
    private static void getGuP() {
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        int [] prices = {1,2,3,4,5};
        int[] prices = {7, 6, 4, 3, 1};
        sumGuP(prices);
    }

    private static void sumGuP(int[] prices) {
        if (prices == null || prices.length < 2) {
            return;
        }
        // 记录一个总和
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                // 表示下一天的股票价格上涨了，买卖所有上涨段，下跌段都不买卖
                sum += prices[i] - prices[i - 1];
            }
        }
        System.out.println("sum :" + sum);
    }
}
