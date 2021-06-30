package learn.leecode.dongtaigh;

import java.util.Arrays;

/**
 * 300
 *
 * @author zgq
 */
public class LeCode300 {

    public static void main(String[] args) {
        lengthOfLis();
    }

    /**
     * 最长上升子序列(300)
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * 示例:输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。你算法的时间复杂度应该为 O(n2)
     */
    private static void lengthOfLis() {
        int[] nums = {10, 9, 2, 5, 3, 4, 7, 101, 18};
        // 定义新数组，记录每一个索引位置所在的最大上升序列长度
        int[] dp = new int[nums.length];
        Arrays.fill(nums, 1);
        // 初始化0节点
        dp[0] = 1;
        // 初始化最大值
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            // 初始化每一个索引所在长度为1，因为最次的情况就是最大长度为其本身 -> maxLength = 1
            int a = nums[i];
            dp[i] = 1;
            // 将i所在的值与前面的依次比较
            for (int j = 0; j < i; j++) {
                int b = nums[j];
                // 如果i的值大于了前面的值
                // -> 则需要比对i的最大值与（j的最大值+1）的大小
                // -> 因为i的最大值只可能在本身以及j的最大值+1中产生，
                // -> 并更新i索引所在的最大值
                if (a > b) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 更新最大值，最大值只可能在上一个max和当前索引所在最大长度产生
            max = Math.max(max, dp[i]);
        }
        System.out.println("max:" + max);
    }
}
