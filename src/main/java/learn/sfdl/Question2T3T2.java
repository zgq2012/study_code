package learn.sfdl;

import java.util.Arrays;

/**
 * 2.3.2：不使用哨兵进行归并排序：O(n) = n*log2(n)
 *
 * @author zgq
 */
public class Question2T3T2 {
    public static void main(String[] args) {
        int[] nums = {0, 4, 2, 7, 5, 9, 1, 6, 321, 24, 52, 11};
        System.out.println(Arrays.toString(nums));
        guiBingSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void guiBingSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        // 一堆取中值分成2堆
        int end = nums.length;
        int left = 0;
        int mid = end - end / 2 + left / 2;
        int[] leftArr = Arrays.copyOfRange(nums, 0, mid);
        int[] rightArr = Arrays.copyOfRange(nums, mid, end);

        // 各个堆继续分
        guiBingSort(leftArr);
        guiBingSort(rightArr);
        // 分完之后两两合并
        merge(nums, leftArr, rightArr);
    }

    private static void merge(int[] nums, int[] leftArr, int[] rightArr) {
        // 定义各个数组的起始坐标
        int j = 0;
        int r = 0;
        for (int i = 0; i < nums.length; i++) {
            // 若其中一个的值取完了，直接去另一个数组取就行，取完++
            if (r == rightArr.length) {
                nums[i] = leftArr[j];
                j++;
                continue;
            }
            if (j == leftArr.length) {
                nums[i] = rightArr[r];
                r++;
                continue;
            }
            // 比较2个数据最左位置的值的大小，小的就给nums赋值，并且将其坐标++
            if (leftArr[j] < rightArr[r]) {
                nums[i] = leftArr[j];
                j++;
            } else {
                nums[i] = rightArr[r];
                r++;
            }
        }
    }
}
