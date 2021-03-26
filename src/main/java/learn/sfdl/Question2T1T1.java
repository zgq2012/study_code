package learn.sfdl;

import java.util.Arrays;

/**
 * 2.1.1：插入排序之升序排列以及降序排列
 *
 * @author zgq
 */
public class Question2T1T1 {
    public static void main(String[] args) {
        int[] arr = {31, 41, 59, 26, 41, 58};
        int[] res1 = insertSortUp(arr);
        System.out.println(Arrays.toString(res1));
    }

    private static int[] insertSortUp(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            /*
            // 升序
            for (int j = i; j >= 1; j--) {
                if (arr[j] >= arr[j - 1]) {
                    break;
                } else {
                    swapVal(arr, j, j - 1);
                }
            }
            */
            // 降序
            int j = i;
            while (j > 0 && arr[j] > arr[j - 1]) {
                swapVal(arr, j, j - 1);
                j--;
            }
        }
        return arr;
    }

    private static void swapVal(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
