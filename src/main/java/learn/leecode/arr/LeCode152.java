package learn.leecode.arr;

/**
 * 152. 乘积最大子数组
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/17
 **/
public class LeCode152 {
    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        int res = maxProduct(nums);
        System.out.println("res = " + res);
    }

    /**
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），
     * 并返回该子数组所对应的乘积。
     * 测试用例的答案是一个 32-位 整数。子数组 是数组的连续子序列。
     *
     * 示例 1:
     * 输入: nums = [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     *
     * 示例 2:
     * 输入: nums = [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     *  
     * 提示:
     * 1 <= nums.length <= 2 * 10^4
     * -10 <= nums[i] <= 10
     * nums 的任何前缀或后缀的乘积都 保证 是一个 32-位整数
     */
    private static int maxProduct(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }

        // 记录总体最大值
        int max = nums[0];
        // 记录当前索引之前的连续最大值
        int maxPre = nums[0];
        // 记录当前索引之前的连续最小值
        int minPre = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int mx = maxPre;
            int mn = minPre;
            // 更新当前索引之前的连续最大值，mx * nums[i] 之前与现在的乘积，nums[i], mn * nums[i] 主要解决负数的问题
            maxPre = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            // 更新当前索引之前的连续最小值
            minPre = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            // 用maxPre与之前最大值比较
            max = Math.max(maxPre, max);
        }

        return max;
    }
}
