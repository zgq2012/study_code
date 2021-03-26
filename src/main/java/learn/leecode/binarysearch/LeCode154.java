package learn.leecode.binarysearch;

/**
 * 154
 *
 * @author zgq
 */
public class LeCode154 {
    public static void main(String[] args) {
//        int[] nums = {2, 2, 2, 0};
//        int[] nums = {1, 2, 2};
        int[] nums = {3, 3, 1, 3};
        int result = findMin(nums);
        System.out.println(result);
    }


    /**
     * (154)寻找旋转排序数组中的最小值II
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。
     * 请找出其中最小的元素。注意数组中可能存在重复的元素
     * <p>
     * 示例 1：
     * 输入：nums = [3,4,5,1,2]    输出：1
     * <p>
     * 示例 2：
     * 输入：nums = [2,2,2,0,1]    输出：0
     * <p>
     * 提示：
     * 1 <= nums.length <= 5000  ,  -5000 <= nums[i] <= 5000
     */
    private static int findMin(int[] nums) {
        int min = 0;
        int max = nums.length - 1;
        while (min < max) {
            int mid = (min + max) >>> 1;
            // 判断方式：中值与右边界比较，如果中值小于右边界，则移动右边界至中值，
            if (nums[mid] < nums[max]) {
                max = mid;
            } else if (nums[mid] > nums[max]) {
                // 反之大于时，移动左边界到中值+1，因为中值直接被排除了
                min = mid + 1;
            } else {
                // 特殊情况=时，将右边界降一位一次比较，因为无法判定最小值在哪里
                max--;
            }
        }
        return nums[min];
    }
}