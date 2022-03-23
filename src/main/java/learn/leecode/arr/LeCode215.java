package learn.leecode.arr;

/**
 * 215. 数组中的第K个最大元素
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/23
 **/
public class LeCode215 {
    public static void main(String[] args) {
        int[] nums = {5, 3, 2, 6, 8, 4, 9};
        int k = 4;
        int res = findKthLargest(nums, k);
        System.out.println("res = " + res);
    }

    /**
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 示例 1:
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * <p>
     * 示例 2:
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * <p>
     * 提示：
     * 1 <= k <= nums.length <= 10^4
     * -10^4 <= nums[i] <= 10^4
     */
    private static int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        // 先创建堆
        buildHeap(len, nums);
        int count = len - 1;
        for (int i = count; i > count - k; i--) {
            // 交换，将最大值堆顶交换到最右节点，即此时的队尾，
            swap(i, 0, nums);
            // 下一次交换时，除去最大值，用剩余的堆化，然后继续交换
            len--;
            heapify(nums, len, 0);
        }

        return nums[count - k + 1];
    }

    /**
     * 建大顶堆
     */
    private static void buildHeap(int len, int[] nums) {
        for (int i = (len >> 1) - 1; i >= 0; i--) {
            // 数组堆化
            heapify(nums, len, i);
        }
    }

    /**
     * 大顶堆：arr[i] >= arr[2*i+1] && arr[i] >= arr[2*i+2]
     * 小顶堆：arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2]
     */
    private static void heapify(int[] nums, int len, int i) {
        // 采用双指针
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int cur = i;

        // 与左边比对，取最大
        if (left < len && nums[left] > nums[cur]) {
            cur = left;
        }

        // 与右边比对取最大
        if (right < len && nums[right] > nums[cur]) {
            cur = right;
        }

        // 若当前位置与初始位置不同，则表示i位置非最大，需要交换
        if (cur != i) {
            swap(cur, i, nums);
            // 继续堆化
            heapify(nums, len, cur);
        }
    }

    private static void swap(int left, int right, int[] arr) {
        int mid = arr[left];
        arr[left] = arr[right];
        arr[right] = mid;
    }
}
