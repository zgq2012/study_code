package learn.leecode.order;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author zgq
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 1, 4, 5, 3, 7, 7, 8, 10, 2, 7, 8, 0, 5, 2, 16, 12, 1, 19, 15, 5, 18, 2, 2, 22, 15, 8, 22,
                17, 6, 22, 6, 22, 26, 32, 8, 10, 11, 2, 26, 9, 12, 9, 7, 28, 33, 20, 7, 2, 17, 44, 3, 52, 27, 2, 23, 19,
                56, 56, 58, 36, 31, 1, 19, 19, 6, 65, 49, 27, 63, 29, 1, 69, 47, 56, 61, 40, 43, 10, 71, 60, 66, 42, 44,
                10, 12, 83, 69, 73, 2, 65, 93, 92, 47, 35, 39, 13, 75};
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        System.out.println(Arrays.toString(arr));
//        int[] arr = {0, 0, 0, 2, 0, 5};
        long time1 = System.currentTimeMillis();
        int[] res = quickSort(arr);
        long time2 = System.currentTimeMillis();
        System.out.println("res1->time:" + (time2 - time1));
        System.out.println("res1 : " + Arrays.toString(res));

        System.out.println(Arrays.toString(arr1));
        int[] res2 = quickSort2(arr1);
        long time3 = System.currentTimeMillis();
        System.out.println("res2->time:" + (time3 - time2));
        System.out.println("res2 : " + Arrays.toString(res2));
    }

    private static int[] quickSort2(int[] arr) {
        // 初始化，
        int left = 0;
        int right = arr.length - 1;
        select2(left, right, arr);
        return arr;
    }

    private static void select2(int left, int right, int[] arr) {
        if (left >= right) {
            return;
        }
        int index = left;
        int start = left;
        int end = right;
        boolean isRight = true;
        while (left <= right) {
            // 从右边开始，若right值小于index则交换，并刷新index，反之，则right继续--，直至遇到条件或结束
            if (isRight) {
                if (arr[right] < arr[index] && right > index) {
                    swap(index, right, arr);
                    index = right;
                    // 交换完，则右指针停止，开始左指针移动
                    isRight = false;
                }
                right--;
            } else {
                // 右边找到一个就开始左边，若left值大于index则交换，并刷新index，反之，则left继续++，直至遇到条件或结束
                if (arr[left] > arr[index] && left < index) {
                    swap(left, index, arr);
                    index = left;
                    // 交换完，则左指针停止，开始右指针移动
                    isRight = true;
                }
                left++;
            }
        }
        // 递归左右边界
        select2(start, index, arr);
        select2(index + 1, end, arr);
    }

    private static int[] quickSort(int[] arr) {
        // 初始化，
        int left = 0;
        int right = arr.length - 1;
        cycSort(left, right, arr);
        return arr;
    }

    private static void cycSort(int left, int right, int[] arr) {
        if (left < right) {
            int temp = partSort(left, right, arr);
            cycSort(left, temp - 1, arr);
            cycSort(temp + 1, right, arr);
        }
    }

    private static int partSort(int left, int right, int[] arr1) {
        int init = left + 1;
        for (int i = init; i <= right; i++) {
            if (arr1[i] < arr1[left]) {
                swap(i, init, arr1);
                init++;
            }
        }
        swap(left, init - 1, arr1);
//        System.out.println(Arrays.toString(arr1));
        return init - 1;
    }

    private static void swap(int temp, int right, int[] arr) {
        int mid = arr[temp];
        arr[temp] = arr[right];
        arr[right] = mid;
    }
}
