package learn.leecode.binarysearch;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/2/24
 **/
public class LeCode34 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 5, 5, 5, 6, 7, 8, 8};
        int target = -1;
        int[] res = getIndex(nums, target);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 进阶：
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     *
     * 示例 1：
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     *
     * 示例 2：
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     *
     * 示例 3：
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     */
    private static int[] getIndex(int[] nums, int target) {
        // 获取左边的第一个索引
        int left = getTargetIndex(nums, target, true);
        System.out.println("left = " + left);
        // 获取右边的第一个索引
        int right = getTargetIndex(nums, target, false);
        System.out.println("right = " + right);

        return left <= right ? new int[]{left, right} : new int[]{-1, -1};
    }

    private static int getTargetIndex(int[] nums, int target, boolean isLeft) {
        int left = 0;
        int right = nums.length -1;
        while (left <= right){
            int mid = (left + right) / 2;
            if(isLeft ? nums[mid] >= target : target < nums[mid]){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return isLeft ? left : right;
    }
}
