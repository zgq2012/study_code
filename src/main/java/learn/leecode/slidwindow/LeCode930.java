package learn.leecode.slidwindow;

/**
 * 930. 和相同的二元子数组
 *
 * @author zgq
 */
public class LeCode930 {
    public static void main(String[] args) {
//        int goal = 2;
//        int[] nums = {1, 0, 1, 0, 1};
        int goal = 0;
        int[] nums = {0, 0, 0, 0, 0};
        int res = numSubarraysWithSum(nums, goal);
        System.out.println("结果：" + res);
    }

    /**
     * (930) 和相同的二元子数组
     * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
     * 子数组 是数组的一段连续部分。
     * <p>
     * 示例 1：
     * 输入：nums = [1,0,1,0,1], goal = 2
     * 输出：4
     * 解释：
     * 如下面黑体所示，有 4 个满足题目要求的子数组：
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * <p>
     * 示例 2：
     * 输入：nums = [0,0,0,0,0], goal = 0
     * 输出：15
     * <p>
     * 提示：
     * 1 <= nums.length <= 3 * 104
     * nums[i] 不是 0 就是 1
     * 0 <= goal <= nums.length
     */
    private static int numSubarraysWithSum(int[] nums, int goal) {
        /*
         * 从左至右遍历，sum+nums[right]，
         * 若和小于goal，表示left与right区间的子数组都不可能是结果，right++
         * 若和大于goal，表示结果集可能存在在left与right之间，
         * left++，一步一步判定，直至left与right之间和小于goal，此时继续right++
         * 重复以上步骤，直接达到边界结束
         */

        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int ret = 0;
        // 终止条件 右边界达到数组右边界后, 0<= right <= n-1
        while (right < n) {
            // 记录sum > goal 的情况
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                // 减去左边界，并且让左边界向右移动，寻找等值
                sum1 -= nums[left1];
                left1++;
            }
            // 记录sum >= goal 的清理
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            // 若2个left同时移位表示，此时sum还是大于goal，没找到对应数组,
            // 反之有一个left满足条件，即得到差值
            ret += left2 - left1;
            right++;
        }
        return ret;
    }
}