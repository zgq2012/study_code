package learn.leecode.arr;

import java.util.Arrays;

/**
 * 494. 目标和
 *
 * @author zgq
 */
public class LeCode494 {

    public static void main(String[] args) {
        int[] nums = {1};
        int target = 1;
        int res = findTargetSumWays(nums, target);
        System.out.println("结果：" + res);
    }

    /**
     * 目标和(494)
     * 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *  
     * 示例 1：
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * <p>
     * 示例 2：
     * 输入：nums = [1], target = 1
     * 输出：1
     * <p>
     * 提示：
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 1000
     * 0 <= sum(nums[i]) <= 1000
     * -1000 <= target <= 100
     */
    private static int findTargetSumWays(int[] nums, int target) {
        // 解法一：回溯，遍历所有情况，满足则计数

        // 解法二：动态规划，该题情况很容易得到，nums的所有元素之和 sumArr,
        // 我们假设所有添加负号的元素之和为 neg, 那么剩下的所有 +号的元素之和 为 (sumArr - neg)
        // 可知，那表达式target是所有+号和所有-号的元素的结果
        // -> (sumArr - neg) - neg = target
        // -> sumArr - 2neg = target
        // -> neg = (sumArr - target)/2， 且 neg >= 0
        // 那么问题转化为，随机从数组中抽取i个元素，使得其和 = (sumArr - target)/2，找到一种就表示满足条件+1

        // 定义dp[i][j] 表示在数组前i个数中选取元素，使得元素之和等于j得方案数

        // 当没有任何元素可以选取时，元素和只能是 0，对应的方案数是 1
        // dp[0][j] = 1 (j = 0) 或者  dp[0][j] = 0(j >= 1)

        // 若 nums[n] > j 表示当前值大于目标值，则不取它，-> dp[i][j] = dp[i-1][j];
        // 若 nums[n] <= j 表示当前值小于等于目标值，
        // 若不取它，-> dp[i][j] = dp[i-1][j];
        // 若取它则，-> dp[i][j] = dp[i][j-nums[n]]，
        // 则 dp[i][j] = dp[i-1][j] + dp[i][j-nums[n]];
        // 最终结果为dp[n][neg].
        int len = nums.length;
        // 获取总和
        int sum = Arrays.stream(nums).sum();
        // 剔除限制条件 neg >= 0 且 neg 应该为偶数
        if (sum - target < 0 || (sum - target) % 2 != 0) {
            return 0;
        }

        // 计算neg
        int neg = (sum - target) >> 1;
        // 初始化数组
        int[][] dp = new int[len + 1][neg + 1];
        // 初始化边界值
        dp[0][0] = 1;
        // 遍历从1开始,len结束
        for (int i = 1; i <= len; i++) {
            // 获取得时 i - 1 的值
            int value = nums[i - 1];
            // 枚举j的值，从 0 -> neg 的所有情况
            for (int j = 0; j <= neg; j++) {
                // 根据nums[i-1]的值与目标值的比较觉得dp[i][j]的取值结果
                if (value > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - value];
                }
            }
        }

        return dp[len][neg];
    }
}