package learn.leecode.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 *
 * @author zgq
 */
public class LeCode18 {

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> res = fourSum(nums, target);
        System.out.println("res = " + res);
    }

    /**
     * 四数之和（18）
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
     * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
     * 找出所有满足条件且不重复的四元组。
     * 注意：答案中不可以包含重复的四元组。
     * <p>
     * 示例 1：
     * 输入：nums = [1,0,-1,0,-2,2], target = 0
     * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     * <p>
     * 示例 2：
     * 输入：nums = [], target = 0
     * 输出：[]
     * <p>
     * 提示：
     * 0 <= nums.length <= 200
     * -10^9 <= nums[i] <= 10^9
     * -10^9 <= target <= 10^9
     */
    private static List<List<Integer>> fourSum(int[] nums, int target) {
        // 先排序
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 一层循环固定
        for (int i = 0; i < len - 3; i++) {
            // 前几个数都大于target 那么一定不存在，后几位都小于也不存在
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[len - 1] + nums[len - 2] + nums[len - 3] + nums[len - 4] < target) {
                break;
            }

            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 二层循环固定
            for (int j = i + 1; j < len; j++) {
                // 去重， 也可添加前4与后4的和比较判定，减少边界情况
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 初始化左右指针
                int left = j + 1;
                int right = len - 1;
                // 循环left -> right
                while (left < right) {
                    // 记录和
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];

                    // 判定和
                    if (sum == target) {
                        // 存储值
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        res.add(list);
                        // 并且使左指针++，继续完成该次循环找值,如果左指针的下一步值还是一样的直接跳过
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        // 并且使右指针--，继续完成该次循环找值,如果右指针的下一步值还是一样的直接跳过
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum > target) {
                        // 和大于target时，right需要--，
                        right--;
                    } else {
                        // 和小于target时，left需要++，
                        left++;
                    }
                }
            }
        }

        return res;
    }
}
