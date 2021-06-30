package learn.leecode.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15
 *
 * @author zgq
 */
public class LeCode15 {

    public static void main(String[] args) {
        threeNumSum();
    }

    /**
     * 三数之和（15）
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
     * 请你找出所有满足条件且不重复的三元组。注意：答案中不可以包含重复的三元组
     */
    private static void threeNumSum() {
        int[] nums = {-1, 0, 1, 2, -1, 2};
        Arrays.sort(nums);
        int sum = 0;
        // 使用固定指针+双指针
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // i循环固定++，设置左指针，右指针
            int left = i + 1;
            int right = nums.length - 1;
            // 如果nums[i]已经大于0，那么则不可能找到题目的诉求
            if (nums[i] > 0) {
                System.out.println(result);
                return;
            }
            // 如果当前值与上一步值相等，表示重复了，没必要继续记录，直接跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 定义双指针所在数的和
            int doubleSum = sum - nums[i];
            while (left < right) {
                int rlSum = nums[left] + nums[right];
                if (doubleSum == rlSum) {
                    // 如果相等则记录下该组
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 并且使右指针--，继续完成该次循环找值,如果右指针的下一步值还是一样的直接跳过
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    right--;
                } else if (doubleSum > rlSum) {
                    // 如果和大于2数之和，表示需要左指针进一位，即++；
                    left++;
                } else {
                    // 如果和小于2数之和，表示需要右指针退一位，即--；
                    right--;
                }
            }
        }
        System.out.println("result:" + result);
    }
}
