package learn.leecode.order;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author zgq
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 1, 4, 5, 3, 7, 7, 8, 10, 2, 7, 8, 0, 5, 2, 16, 12, 1, 19, 15, 5, 18, 2, 2, 22, 15, 8, 22,
                17, 6, 22, 6, 22, 26, 32, 8, 10, 11, 2, 26, 9, 12, 9, 7, 28, 33, 20, 7, 2, 17, 44, 3, 52, 27, 2, 23, 19,
                56, 56, 58, 36, 31, 1, 19, 19, 6, 65, 49, 27, 63, 29, 1, 69, 47, 56, 61, 40, 43, 10, 71, 60, 66, 42, 44,
                10, 12, 83, 69, 73, 2, 65, 93, 92, 47, 35, 39, 13, 75};
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        int[] arr2 = Arrays.copyOf(arr, arr.length);

        System.out.println("大顶堆升序------------------------------------");
        int[] res = heapSort(arr1);
        System.out.println(Arrays.toString(res));
        System.out.println("小顶堆降序------------------------------------");
        int[] res2 = heapSort2(arr2);
        System.out.println(Arrays.toString(res2));
    }

    /**
     * 大顶堆升序
     */
    private static int[] heapSort(int[] arr1) {
        int len = arr1.length;
        // 先创建堆
        buildHeap(len, arr1);
        System.out.println(Arrays.toString(arr1));
        for (int i = len - 1; i > 0; i--) {
            // 交换，将最大值堆顶交换到最右节点，即此时的队尾，
            swap(i, 0, arr1);
            // 下一次交换时，除去最大值，用剩余的堆化，然后继续交换
            heapify(arr1, i, 0);
        }

        return arr1;
    }

    /**
     * 小顶堆降序
     */
    private static int[] heapSort2(int[] arr1) {
        int len = arr1.length;
        // 先创建堆
        buildSmallHeap(len, arr1);

        System.out.println(Arrays.toString(arr1));
        for (int i = len - 1; i > 0; i--) {
            // 队头的是最小值，给他交换放到队尾，然后以 len--，继续
            swap(i, 0, arr1);
            // 下一次交换时，除去最小值，用剩余的堆化，然后继续交换
            heapifySmall(arr1, i, 0);
        }

        return arr1;
    }

    /**
     * 建小顶堆
     */
    private static void buildSmallHeap(int len, int[] arr1) {
        for (int i = (len >> 1) - 1; i >= 0; i--) {
            // 数组堆化
            heapifySmall(arr1, len, i);
        }
    }

    private static void heapifySmall(int[] arr1, int len, int i) {
        // 采用双指针
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int cur = i;

        if (left < len && arr1[left] < arr1[cur]) {
            cur = left;
        }

        if (right < len && arr1[right] < arr1[cur]) {
            cur = right;
        }

        if (cur != i) {
            swap(cur, i, arr1);
            heapifySmall(arr1, len, cur);
        }
    }

    /**
     * 建大顶堆
     */
    private static void buildHeap(int len, int[] arr1) {
        for (int i = (len >> 1) - 1; i >= 0; i--) {
            // 数组堆化
            heapify(arr1, len, i);
        }
    }

    /**
     * 大顶堆：arr[i] >= arr[2*i+1] && arr[i] >= arr[2*i+2]
     * 小顶堆：arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2]
     */
    private static void heapify(int[] arr1, int len, int i) {
        // 采用双指针
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int cur = i;

        // 与左边比对，取最大
        if (left < len && arr1[left] > arr1[cur]) {
            cur = left;
        }

        // 与右边比对取最大
        if (right < len && arr1[right] > arr1[cur]) {
            cur = right;
        }

        // 若当前位置与初始位置不同，则表示i位置非最大，需要交换
        if (cur != i) {
            swap(cur, i, arr1);
            // 继续堆化
            heapify(arr1, len, cur);
        }
    }

    private static void swap(int left, int right, int[] arr) {
        int mid = arr[left];
        arr[left] = arr[right];
        arr[right] = mid;
    }
}
