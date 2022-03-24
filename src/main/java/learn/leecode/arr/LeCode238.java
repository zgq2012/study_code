package learn.leecode.arr;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/24
 **/
public class LeCode238 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] res = productExceptSelf(nums);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
     * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * <p>
     * 示例 1:
     * 输入: nums = [1,2,3,4]
     * 输出: [24,12,8,6]
     * <p>
     * 示例 2:
     * 输入: nums = [-1,1,0,-3,3]
     * 输出: [0,0,9,0,0]
     * <p>
     * 提示：
     * 2 <= nums.length <= 10^5
     * -30 <= nums[i] <= 30
     * 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
     * <p>
     * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     */
    private static int[] productExceptSelf(int[] nums) {
        // solve1:采用双数组来记录左右连续的乘积，那么res[i] = left[i] * right[i];
        // solve2:采用单数组来记录左边连续的乘积，然后右边采用滚动变量，并且返回值重复覆盖单数组，那么left[i] = left[i] * r;
        int len = nums.length;
        int[] leftPro = new int[len];
        leftPro[0] = 1;
        for (int i = 1; i < len; i++) {
            // 记录位置的左边的乘积，所以数据索引是 i-1；
            leftPro[i] = nums[i - 1] * leftPro[i - 1];
        }

        // 记录右边开始的乘积
        int rightPro = 1;
        for (int i = len - 1; i >= 0; i--) {
            // 使用左边数组 来更新返回数组
            leftPro[i] = leftPro[i] * rightPro;

            // 更新右边连续乘积
            rightPro = rightPro * nums[i];
        }

        // 返回重复利用的数组
        return leftPro;
    }
}
