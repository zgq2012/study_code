package learn.leecode.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. 连续的子数组和
 *
 * @author zgq
 */
public class LeCode523 {

    public static void main(String[] args) {
        int[] matrix = {23, 2, 4, 6, 6};
        int k = 7;
        boolean res = checkSubarraySum(matrix, k);
        System.out.println("结果：" + res);
    }

    /**
     * 连续的子数组和(523)
     * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
     * 子数组大小至少为 2 ，且子数组元素总和为 k 的倍数。
     * 如果存在，返回 true ；否则，返回 false 。
     * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
     *  
     * 示例 1：
     * 输入：nums = [23,2,4,6,7], k = 6
     * 输出：true
     * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
     * <p>
     * 示例 2：
     * 输入：nums = [23,2,6,4,7], k = 6
     * 输出：true
     * 解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
     * 42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
     * <p>
     * 示例 3：
     * 输入：nums = [23,2,6,4,7], k = 13
     * 输出：false
     * <p>
     * 提示：
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * 0 <= sum(nums[i]) <= 2^31 - 1
     * 1 <= k <= 2^31 - 1
     */
    private static boolean checkSubarraySum(int[] nums, int k) {
        int m = nums.length;
        // 数组长度小于2，一定不能完成
        int minLen = 2;
        if (m < minLen) {
            return false;
        }

        // 记录余数出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        // 初始化
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            // 获取余数，remainder + nums[i] 相当于i以及i之前的元素和 对k取余的结果，
            remainder = (remainder + nums[i]) % k;
            // 若余数出现过，校验数组长度是否符合条件
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                // 若未出现，直接放入
                map.put(remainder, i);
            }
        }
        return false;
    }
}