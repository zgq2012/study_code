package learn.leecode.dongtaigh;

/**
 * 53
 *
 * @author zgq
 */
public class LeCode53 {

    public static void main(String[] args) {
        maxSumArr();
    }

    /**
     * 最大子序和(53)
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 示例:输入: [-2,1,-3,4,-1,2,1,-5,4]  输出: 6  解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     */
    private static void maxSumArr() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int max = 0;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            // 第一位为起始值
            if (i == 0) {
                max = nums[i];
                temp = max;
                continue;
            }
            // 依次，如果之前的和小于0 时，那么当前的数值必然就是连续数组最大值（因为 -> 负数+i < i）
            // 如果之前的大于0，那么继续累加，并且更新最大值
            max = Math.max(max + nums[i], nums[i]);
            temp = Math.max(temp, max);
        }
        System.out.println(temp);
    }
}
