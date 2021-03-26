package learn.leecode.slidwindow;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 239
 *
 * @author zgq
 */
public class LeCode239 {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, 2, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result));
    }

    /**
     * (239)滑动窗口最大值
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * <p>
     * 返回滑动窗口中的最大值。
     * <p>
     * 进阶：你能在线性时间复杂度内解决此题吗？
     * <p>
     * 示例:
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3  输出: [3,3,5,5,6,7]
     * <p>
     * 解释:
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     * <p>
     * 提示：
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     * 1 <= k <= nums.length
     */
    private static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        // 如果数组长度为0 或者 k=0 直接返回 或者 k =1 直接返回原数组
        if (len * k == 0 || k == 1) {
            return nums;
        }
        // 双向队列
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        // 记录最大值索引
        int maxIdx = 0;
        // 采用双向队列来处理
        // 初始化 k动态队列
        for (int i = 0; i < k; i++) {
            cleanDeque(i, k, nums, deq);
            // 从队列后添加值
            deq.addLast(i);
            // 更新最大值
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        // 输出的数组长度 len-k+1
        int[] output = new int[len - k + 1];
        // 初始化output[0]的值
        output[0] = nums[maxIdx];

        // 开始计算输出数组
        for (int i = k; i < len; i++) {
            cleanDeque(i, k, nums, deq);
            deq.addLast(i);
            // 直接将最大值赋给输出数组，deq.getFirst()一定是队列最大值索引
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }

    private static void cleanDeque(int i, int k, int[] nums, ArrayDeque<Integer> deq) {
        // 移除滑动窗口未移动之前的值
        if (!deq.isEmpty() && deq.getFirst() == i - k) {
            deq.removeFirst();
        }
        // 若新进队列的值大于原队列的值，就舍弃原队列值
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
            deq.removeLast();
        }
    }
}