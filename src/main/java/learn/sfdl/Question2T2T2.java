package learn.sfdl;

import java.util.Arrays;

/**
 * 2.2.2：
 * 1.写出选择排序算法：在以n个数的数组A中，
 * 首先找到A中最小的元素并将其与A[1]交换，
 * 接着找到A中第二小的元素与A[2]交换，
 * 继续对A的前n-1项执行
 * 2.标记其最坏运行时间与最段快运行时间
 *
 * @author zgq
 */
public class Question2T2T2 {
    public static void main(String[] args) {
        int[] nums = {0, 4, 2, 7, 5, 9, 1};
        System.out.println(Arrays.toString(nums));
        // 外层只需要执行n-1次，因为找到前n-1个最小值之后，剩下的第n个一定是最大值，不需要计算
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            // 循环 n - i - 1次， 因为前面的i-1次都已经排好序，只需要用i去比较i之后的（n-i-1）个数
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            // 交换i与最小值的位置
            if (min != i) {
                swapVal(nums, min, i);
            }
        }
        // 整个算法，比较稳定，最优时间与最差时间一致，因为每次寻找最小值都需要逐个比较，
        // T(n) = c[(n-1)+(n-2)+(n-3)+...+2+1]+x = c/2(n^2-n) + x => a*n^2 + b*n + c
        // 时间复杂度：O(n^2)
        System.out.println(Arrays.toString(nums));
    }

    private static void swapVal(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
