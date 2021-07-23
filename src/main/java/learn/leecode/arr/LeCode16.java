package learn.leecode.arr;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 *
 * @author zgq
 */
public class LeCode16 {

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 2;
        int res = threeSumClosest(nums, target);
        System.out.println("res = " + res);
    }

    /**
     * 最接近的三数之和（16）
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
     * 返回这三个数的和。假定每组输入只存在唯一答案。
     * <p>
     * 示例：
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     * <p>
     * 提示：
     * 3 <= nums.length <= 10^3
     * -10^3 <= nums[i] <= 10^3
     * -10^4 <= target <= 10^4
     */
    private static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        // 初始化设置一个条件以内的最大值, nums[i] <= 10^3, sum < 3 * 10^4
        int closestNum = 100000;

        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 如果该位置与上一元素相同，则不需要继续，因为会得到与上一元素相同的结果
                continue;
            }
            // 初始化左右指针
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                // 记录和
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    // 表示3和与目标值相等
                    return sum;
                }

                // 比较和值与上一次和值的绝对值大小
                if (Math.abs(sum - target) < Math.abs(closestNum - target)) {
                    closestNum = sum;
                }

                if (sum > target) {
                    // 移动到不相等位置
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 表示右指针大，左移
                    right--;
                } else {
                    // 移动到不相等位置
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 表示左指针小，右移
                    left++;
                }
            }
        }

        return closestNum;
    }
}
