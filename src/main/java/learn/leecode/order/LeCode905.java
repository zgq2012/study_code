package learn.leecode.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 905
 *
 * @author zgq
 */
public class LeCode905 {
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        int[] result = sortArrayByParity(arr);
        System.out.println(Arrays.toString(result));
    }

    /**
     * (905)按奇偶排序数组
     * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
     * 你可以返回满足此条件的任何数组作为答案。
     * <p>
     * 示例：
     * 输入：[3,1,2,4]
     * 输出：[2,4,3,1] 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
     * <p>
     * 提示：1 <= A.length <= 5000   0 <= A[i] <= 5000
     */
    private static int[] sortArrayByParity(int[] arr) {
        int i = 0, j = arr.length - 1;
        // 双指针
        while (i < j) {
            // i为奇数，且j为偶数时交换
            if (arr[i] % 2 > arr[j] % 2) {
                // 交换
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
            // i为偶数时，i++，指针前移
            if (arr[i] % 2 == 0) {
                i++;
            }
            // j为奇数时，j-- ，即当前位置不变
            if (arr[j] % 2 == 1) {
                j--;
            }
        }
        return arr;
    }
}