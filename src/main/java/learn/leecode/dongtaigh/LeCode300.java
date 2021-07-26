package learn.leecode.dongtaigh;

import java.util.Arrays;

/**
 * 300
 *
 * @author zgq
 */
public class LeCode300 {

    public static void main(String[] args) {
        int res = lengthOfLis();
        System.out.println("res = " + res);
    }

    /**
     * 最长上升子序列(300)
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * 示例:输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。你算法的时间复杂度应该为 O(n2)
     */
    private static int lengthOfLis() {
        int[] nums = {10, 9, 2, 5, 3, 4, 7, 101, 18};
        int len = nums.length;
        // 初始化dp数组
        int[] dp = new int[len];
        // 填充dp数组默认值 1
        Arrays.fill(dp, 1);
        // 直接从索引1开始，因为0的位置只可能是1，已经被赋值了
        // dp[i] = Math.max(dp[i], Arrays.max(dp[j])+1); 0<j<i;
        int max = 1;
        for(int i  = 1; i< len; i++){
            for(int j = 0; j< i; j++){
                // 若nums[i]>nums[j]，则表示在该i的位置的最大上升序列长度需要变化
                if(nums[i] > nums[j]){
                    // 在内循环中，dp[i]与所有的dp[j]比完之后，就是最大的
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
