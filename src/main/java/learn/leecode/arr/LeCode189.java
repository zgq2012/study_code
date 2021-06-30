package learn.leecode.arr;

import java.util.Arrays;

/**
 * 189
 *
 * @author zgq
 */
public class LeCode189 {

    public static void main(String[] args) {
        cycArr();
    }

    /**
     * 旋转数组(189)
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * 输入: [1,2,3,4,5,6,7] 和 k = 3 输出: [5,6,7,1,2,3,4]
     * 输入: [1,2] 和 k = 3 输出: [2,1] 旋转3次的结果
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     */
    private static void cycArr() {
//        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int[] nums = {1, 2, 3, 4, 5, 6};
//        int[] nums = {1, 2};
//        int[] nums = {-1, -100, 3, 99, 55, 66};
        int k = 4;
        long time1 = System.currentTimeMillis();
        cycArrByMove(nums, k);
        long time2 = System.currentTimeMillis();
        cycArrByDirect(nums, k);
        long time3 = System.currentTimeMillis();
        System.out.println("cycArrByMove:" + (time2 - time1));
        System.out.println("cycArrByDirect:" + (time3 - time2));
    }

    private static void cycArrByDirect(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k == 0) {
            return;
        }
        int length = nums.length;
        k = k % length;
        if (k == 0) {
            return;
        }

        // 旋转结果一定是 k % nums.length 个尾部元素被移动到最前面，直接将每一个数放到他们的最终位置
        int count = 0;
        // 外部循环length次，防止调用换的start回到原来位置，保证每个位置都交换
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            // 被挤出去的记录下来
            int prev = nums[start];
            // 进行换位置
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }

        System.out.println("cycArrByDirect" + Arrays.toString(nums));
    }

    private static void cycArrByMove(int[] nums, int k) {
        if (nums == null) {
            return;
        }
        int length = nums.length;
        int tem;
        // 每一次旋转都是将最后一个数移动到最前面,空间复杂度O(1);时间复杂度O(k*n)
        for (int j = 0; j < k; j++) {
            for (int i = 0; i < length - 1; i++) {
                tem = nums[length - 1 - i];
                nums[length - 1 - i] = nums[length - 1 - i - 1];
                nums[length - 1 - i - 1] = tem;
            }
        }

        System.out.println("cycArrByMove:" + Arrays.toString(nums));
    }
}
