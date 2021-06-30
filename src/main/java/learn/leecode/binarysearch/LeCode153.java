package learn.leecode.binarysearch;

/**
 * 153
 *
 * @author zgq
 */
public class LeCode153 {
    public static void main(String[] args) {
//        int[] nums = {2, 1};
//        int[] nums = {11, 13, 15, 17};
//        int[] nums = {3, 4, 5, 1, 2};
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
//        int[] nums = {23, 1, 2};
        int result = findMin(nums);
        System.out.println(result);
        int result2 = findMin2(nums);
        System.out.println(result2);
    }

    private static int findMin2(int[] nums) {
        // 精简
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            // 中值与右边界比较，因为正常情况，最小值时右边界一定大于等于中值
            // 反之，若中值大于右边界，那么最小值一定在中值右侧
            if (nums[middle] < nums[right]) {
                // middle可能是最小值，
                right = middle;
            } else {
                // middle肯定不是最小值
                left = middle + 1;
            }
        }
        return nums[left];
    }


    /**
     * (153)寻找旋转排序数组中的最小值
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。
     * 请找出其中最小的元素。
     * <p>
     * 示例 1：
     * 输入：nums = [3,4,5,1,2]    输出：1
     * <p>
     * 示例 2：
     * 输入：nums = [4,5,6,7,0,1,2]    输出：0
     * <p>
     * 示例 3：
     * 输入：nums = [1]    输出：1
     * <p>
     * 提示：
     * 1 <= nums.length <= 5000  ,  -5000 <= nums[i] <= 5000
     * nums 中的所有整数都是唯一的, nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转
     */
    private static int findMin(int[] nums) {
        // 最小值的特点：最小值的右边大于它，其左边的首位值也大于它
        int min = 1;
        int max = nums.length;
        if (nums[max - 1] >= nums[min - 1]) {
            return nums[min - 1];
        }
        if (max == 2) {
            return Math.min(nums[min - 1], nums[max - 1]);
        }
        int k = 0;
        while (min <= max) {
            int mid = (min + max) >>> 1;
            // 若满足 中值>左边的首位值,min = mid;
            if (nums[mid] > nums[0]) {
                min = mid + 1;
            } else {
                k = mid;
                max = mid - 1;
            }
        }
        return nums[k];
    }
}