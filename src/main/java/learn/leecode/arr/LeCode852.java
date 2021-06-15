package learn.leecode.arr;

/**
 * 852. 山脉数组的峰顶索引
 *
 * @author zgq
 */
public class LeCode852 {

    public static void main(String[] args) {
        int[] matrix = {23, 2, 4, 6, 5};
        int res = peakIndexInMountainArray(matrix);
        System.out.println("结果：" + res);
    }

    /**
     * 山脉数组的峰顶索引 (852)
     * 符合下列属性的数组 arr 称为 山脉数组 ：
     * arr.length >= 3
     * 存在 i（0 < i < arr.length - 1）使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i] && arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * <p>
     * 给你由整数组成的山脉数组 arr ，
     * 返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
     * <p>
     * 示例 1：
     * 输入：arr = [0,1,0]
     * 输出：1
     * <p>
     * 示例 2：
     * 输入：arr = [0,2,1,0]
     * 输出：1
     * <p>
     * 示例 3：
     * 输入：arr = [0,10,5,2]
     * 输出：1
     * <p>
     * 示例 4：
     * 输入：arr = [3,4,5,1]
     * 输出：2
     * <p>
     * 示例 5：
     * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
     * 输出：2
     * <p>
     * 提示：
     * 3 <= arr.length <= 104
     * 0 <= arr[i] <= 106
     * 题目数据保证 arr 是一个山脉数组
     */
    private static int peakIndexInMountainArray(int[] arr) {
        int res1 = solveOne(arr);
        System.out.println("res1: " + res1);

        int res2 = solveTwo(arr);
        System.out.println("res2: " + res2);

        return res1;
    }

    /**
     * 二分来查找峰顶
     *
     * @param arr 数组
     * @return 任意峰顶位置
     */
    private static int solveTwo(int[] arr) {
        int n = arr.length;
        int left = 1;
        // 确定右边界
        int right = n - 2;
        // 记录结果值
        int ans = 0;
        // 二分条件选择，
        while (left <= right) {
            // 获取中间索引
            int mid = (left + right) / 2;
            // 根据中间值判定是否为下峰，题目固定len >= 3 && 一定是封顶数组，否则二分不生效
            if (arr[mid] > arr[mid + 1]) {
                // 若当前为下峰，记录值，收缩右边界，因为峰顶一定是在左边
                ans = mid;
                right = mid - 1;
            } else {
                // 非下峰则收缩左边界
                left = mid + 1;
            }
        }
        return ans;
    }

    private static int solveOne(int[] arr) {
        int res = -1;
        if (arr.length < 3) {
            return res;
        }
        for (int i = 1; i < arr.length - 1; i++) {
            // 题目固定len >= 3 && 一定是封顶数组
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return res;
    }
}