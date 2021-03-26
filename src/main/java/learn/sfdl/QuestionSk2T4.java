package learn.sfdl;

import java.util.Arrays;

/**
 * 2-4：计算一个任意序列的数组中的逆序对数量 -> i<j && nums[i] > nums[j];
 *
 * @author zgq
 */
public class QuestionSk2T4 {
    public static void main(String[] args) {
//        int[] nums = {0, 4, 2, 7, 5, 9, 1, 6, 321, 24, 52, 11};
        int[] nums = {8, 7, 6, 5, 4, 3, 2, 1};
        // 借鉴归并的思想来计算每一次分组时，左右2组中的逆序对，右组中的值小于左组，则表示有一对
        int num = getReverseNum(nums);
        System.out.println(num);
        System.out.println(Arrays.toString(nums));
    }

    private static int getReverseNum(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        // 一堆取中值分成2堆
        int left = 0;
        int end = nums.length;
        int mid = end - end / 2 + left / 2;
        int[] leftArr = Arrays.copyOfRange(nums, 0, mid);
        int[] rightArr = Arrays.copyOfRange(nums, mid, end);
        int leftNum = getReverseNum(leftArr);
        int rightNum = getReverseNum(rightArr);
        // 合并
        int mergeNum = merge(nums, leftArr, rightArr);
        return leftNum + rightNum + mergeNum;
    }

    private static int merge(int[] nums, int[] leftArr, int[] rightArr) {
        int num = 0;
        // 定义各个数组的起始坐标
        int r = 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // 若其中一个的值取完了，直接去另一个数组取就行，取完++
            if (j == leftArr.length) {
                nums[i] = rightArr[r];
                r++;
                continue;
            }
            if (r == rightArr.length) {
                nums[i] = leftArr[j];
                j++;
                continue;
            }
            // 比较2个数据最左位置的值的大小，小的就给nums赋值，并且将其坐标++
            if (leftArr[j] <= rightArr[r]) {
                nums[i] = leftArr[j];
                j++;
            } else {
                nums[i] = rightArr[r];
                // 右边小于左边的话，那左边剩余的所有数相对于右边的该值都是逆序对
                num = num + leftArr.length - j;
                r++;
            }
        }
        return num;
    }
}
