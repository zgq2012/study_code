package learn.sfdl;

import java.util.Arrays;

/**
 * 2.3.5：对排好序的数组，查找其中一个等于v的值，使用2分查找，证明：最坏情况为 O(n) = log2(n)；
 * 最优的就是直接在中间的位置O(n) = 1;
 * 在最左边或者最右边则是O(n) = log2(n);
 *
 * @author zgq
 */
public class Question2T3T5 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 6, 7, 9, 11, 24, 52, 321};
        System.out.println(Arrays.toString(nums));
        int val = 4;
        int left = 0;
        int right = nums.length;
        int i = selectVal(nums, val, left, right);
        System.out.println(i);
    }

    private static int selectVal(int[] nums, int val, int left, int right) {
        if (left == right) {
            return -1;
        }
        int mid = right - right / 2 + left / 2;
        if (nums[mid] == val) {
            return mid;
        }
        if (nums[mid] > val) {
            return selectVal(nums, val, left, mid);
        } else {
            return selectVal(nums, val, mid, right);
        }
    }
}
