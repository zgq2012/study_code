package learn.leecode.other;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * J40
 *
 * @author zgq
 */
public class LeCodeJ40 {

    public static void main(String[] args) {
        int k = 75;
//        int[] arr = {1, 5, 7, 9, 45, 2, 3, 4, 5, 0, 1};
        int[] arr = {0, 1, 1, 1, 4, 5, 3, 7, 7, 8, 10, 2, 7, 8, 0, 5, 2, 16, 12, 1, 19, 15, 5, 18, 2, 2, 22, 15, 8, 22,
                17, 6, 22, 6, 22, 26, 32, 8, 10, 11, 2, 26, 9, 12, 9, 7, 28, 33, 20, 7, 2, 17, 44, 3, 52, 27, 2, 23, 19,
                56, 56, 58, 36, 31, 1, 19, 19, 6, 65, 49, 27, 63, 29, 1, 69, 47, 56, 61, 40, 43, 10, 71, 60, 66, 42, 44,
                10, 12, 83, 69, 73, 2, 65, 93, 92, 47, 35, 39, 13, 75};
//        int[] arr = {0, 1, 1, 2, 4, 4, 1, 3, 3, 2, 6};
//        int[] arr = {0, 0, 0, 2, 0, 5};
        int[] res = getLeastNumbers(arr, k);
        System.out.println("getLeastNumbers : " + Arrays.toString(res));
        int[] res2 = getLeastNumbers2(arr, k);
        System.out.println("getLeastNumbers2 : " + Arrays.toString(res2));
    }

    /**
     * (J40)最小的k个数
     * 输入整数数组 arr ，找出其中最小的 k 个数。
     * 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     * <p>
     * 示例 1：
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     * <p>
     * 示例 2：
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     *  
     * 限制：
     * 0 <= k <= arr.length <= 10000
     * 0 <= arr[i] <= 10000
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        if (k < 1) {
            return res;
        }
        /*
        1.通过建小顶堆(最优先队列)的操作来获取前k个最小数
        */
        // 创建一个长度为k的队列，并且以小顶堆的形式储值
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (t1, t2) -> t2 - t1);
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                // 初始化值
                // queue.offer(Object c)获取队列头部元素，队列为空时，返回null;
                queue.offer(arr[i]);
                continue;
            }
            // 若超过长度，
            // queue.peek()获取队列头部元素，队列为空时，返回null;
            // queue.element()获取队列头部元素，队列为空时，抛出NoSuchElementException异常;
            if (queue.peek() > arr[i]) {
                // 若插入的值<队列最大值，则插入并去掉原最大值，反之，则不插入
                // queue.poll()删除头元素，并且返回他,队列为空时，返回null;
                // queue.delete()删除头元素，并且返回他，队列为空时，抛出NoSuchElementException异常;
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; ++i) {
            res[i] = queue.poll();
        }
        return res;
    }

    public static int[] getLeastNumbers2(int[] arr, int k) {
        int[] res = new int[k];
        if (k < 1) {
            return res;
        }
        /*
        1.通过快速排序的思想，进行快速选择来获取前k最小数
        */
        // 初始化，
        int left = 0;
        int right = arr.length - 1;
        selectSolve(left, right, arr, k);
        System.out.println("val:" + arr[k] + ", arr:" + Arrays.toString(arr));
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }

    private static void selectSolve(int left, int right, int[] arr, int k) {
        if (left >= right) {
            return;
        }
        int index = left;
        int start = left;
        int end = right;
        boolean isRight = true;
        while (left <= right) {
            if (isRight) {
                // 从右边开始，若right值小于index则交换，并刷新index，反之，则right继续--，直至遇到条件或结束
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

        // 若大于k,则表示，k个数在index里面，只需要对左边进行排序
        if (index > k) {
            System.out.println("index:" + index + ",val:" + arr[index] + ", arr:" + Arrays.toString(arr));
            selectSolve(start, index, arr, k);
        } else if (index < k) {
            System.out.println("index:" + index + ",val:" + arr[index] + ", arr:" + Arrays.toString(arr));
            // 若小于k,则表示k个数在index右边，只需要对右边进行排序
            selectSolve(index + 1, end, arr, k);
        }
        // 等于直接返回结果
    }

    private static void swap(int temp, int right, int[] arr) {
        int mid = arr[temp];
        arr[temp] = arr[right];
        arr[right] = mid;
    }
}
