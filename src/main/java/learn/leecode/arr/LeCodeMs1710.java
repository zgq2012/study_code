package learn.leecode.arr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.10. 主要元素
 *
 * @author zgq
 */
public class LeCodeMs1710 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 9, 5, 9, 5, 5, 5};
        System.out.println("输入：" + Arrays.toString(nums));
        int ints = majorityElement(nums);
        System.out.println("结果：" + ints);
    }

    /**
     * （面试题 17.10）主要元素
     * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。
     * 若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
     * <p>
     * 示例 1：
     * 输入：[1,2,5,9,5,9,5,5,5]
     * 输出：5
     * <p>
     * 示例 2：
     * 输入：[3,2]
     * 输出：-1
     * <p>
     * 示例 3：
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     */
    private static int majorityElement(int[] nums) {
        // 不合要求的解法
        System.out.println("map:" + unValidSolve(nums));

        // 空间复杂度O(1)，时间复杂度上升的解法
        System.out.println("sort:" + sortSolve(Arrays.copyOf(nums, nums.length)));

        // 时间O(n),空间O(1),采用摩尔投票算法
        // Boyer-Moore投票算法的基本思想是：在每一轮投票过程中，从数组中删除两个不同的元素，直到投票过程无法继续，
        // 此时数组为空或者数组中剩下的元素都相等。
        // 如果数组为空，则数组中不存在主要元素；
        // 如果数组中剩下的元素都相等，则数组中剩下的元素可能为主要元素，
        return onlySolve(nums);
    }

    private static int onlySolve(int[] nums) {
        // 初始化记录值
        int candidate = -1;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        if (count == 0) {
            return -1;
        }

        int length = nums.length;
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        return count * 2 > length ? candidate : -1;
    }

    private static int sortSolve(int[] nums) {
        // 先排好序
        Arrays.sort(nums);

        int preKey = nums[0];
        int preCount = 1;
        int len = nums.length;
        int nowKey = nums[0];
        int nowCount = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] == nowKey) {
                nowCount++;
                continue;
            }
            // 如果key变了则保存原值
            if (nowCount >= preCount) {
                preKey = nowKey;
                preCount = nowCount;
            }

            nowKey = nums[i];
            nowCount = 1;
        }

        // 如果key变了则保存原值
        if (nowCount >= preCount) {
            preKey = nowKey;
            preCount = nowCount;
        }

        return preCount > (len >> 1) ? preKey : -1;
    }

    private static int unValidSolve(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>(len);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> ent : map.entrySet()) {
            Integer key = ent.getKey();
            if (ent.getValue() > (len >> 1)) {
                return key;
            }
        }

        return -1;
    }
}
