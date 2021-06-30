package learn.leecode.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. 连续数组
 *
 * @author zgq
 */
public class LeCode525 {

    public static void main(String[] args) {
        int[] matrix = {0, 1, 0, 0, 1};
        int res = findMaxLength(matrix);
        System.out.println("结果：" + res);
    }

    /**
     * 连续数组(525)
     * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
     * <p>
     * 示例 1:
     * 输入: nums = [0,1]
     * 输出: 2
     * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
     * <p>
     * 示例 2:
     * 输入: nums = [0,1,0]
     * 输出: 2
     * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
     * <p>
     * 提示：
     * 1 <= nums.length <= 10^5
     * nums[i] 不是 0 就是 1
     */
    private static int findMaxLength(int[] nums) {
        int len = nums.length;
        int limit = 2;
        if (len < limit) {
            return 0;
        }
        // 二进制数组中相同 0 与 1 的连续子数组
        // -> 0的数量 sum{i, j} = sum{0, j} - sum{0, i}
        // -> 1的数量 sum{i, j} = sum{0, j} - sum{0, i}
        // 若将0看作-1， 那么原问题可转换为，和为0的最长连续子数组
        // -> sum{i, j} = sum{0, j} - sum{0, i} = 0; -> sum{0,j} = sum{0,i}
        Map<Integer, Integer> zeroMap = new HashMap<>(len);
        // 初始化值
        zeroMap.put(0, -1);
        int sumPre = 0;
        int preIndex;
        int currentIndex;
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
            sumPre += nums[i];

            if (zeroMap.containsKey(sumPre)) {
                // 表示已经存在过该值，记录当前索引
                currentIndex = i;
                // 获取该值最初始出现的位置
                preIndex = zeroMap.get(sumPre);
                // 比较之前的最大值
                max = Math.max(max, currentIndex - preIndex);
            } else {
                // 若一次都没有出现则记录，其初始位置
                zeroMap.put(sumPre, i);
            }
        }
        return max;
    }
}