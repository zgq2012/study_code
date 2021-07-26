package learn.leecode.dongtaigh;

import java.util.*;

/**
 * 1713. 得到子序列的最少操作次数
 *
 * @author zgq
 */
public class LeCode1713 {

    public static void main(String[] args) {
        // [6,4,8,1,3,2]
        //[4,7,6,2,3,8,6,1]
        int[] target = {6, 4, 8, 1, 3, 2};
        int[] arr = {4, 7, 6, 2, 3, 8, 6, 1};
//        int[] target = {5, 1, 3};
//        int[] arr = {9, 4, 2, 3, 4};
//        int[] target = {5, 1, 3};
//        int[] arr = {9, 4, 2, 3, 4};
        int res = minOperations(target, arr);
        System.out.println("res = " + res);
    }

    /**
     * (1713) 得到子序列的最少操作次数
     * 给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
     * 每一次操作中，你可以在 arr 的任意位置插入任一整数。
     * 比方说，如果arr = [1,4,1,2]，那么你可以在中间添加 3 得到 [1,4,3,1,2] 。你可以在数组最开始或最后面添加整数。
     * 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
     * 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。
     * 比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
     * <p>
     * 示例 1：
     * 输入：target = [5,1,3], arr = [9,4,2,3,4]
     * 输出：2
     * 解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
     * <p>
     * 示例 2：
     * 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
     * 输出：3
     * <p>
     * 提示：
     * 1 <= target.length, arr.length <= 10^5
     * 1 <= target[i], arr[i] <= 10^9
     * target 不包含任何重复元素。
     */
    public static int minOperations(int[] target, int[] arr) {
        // 官解。
        int res = solveMin(target, arr);
        System.out.println("官解 res = " + res);

        // 超时解 -> 获取最长递增子序列时，遍历超时，而官解使用的二分查找
        Map<Integer, Integer> tarMap = new HashMap<>(target.length);
        for (int i = 0; i < target.length; i++) {
            tarMap.put(target[i], i);
        }

        // 获取arr元素对应的target元素下标
        int[] indexArr = new int[arr.length];
        Arrays.fill(indexArr, -1);
        int index = 0;
        for (int i : arr) {
            if (tarMap.get(i) != null) {
                indexArr[index] = tarMap.get(i);
                index++;
            }
        }

        // 返回长度差值, 如果target所有元素在arr中的都不存在，则判定
        // 获取indexArr的最长递增公共子序列
        return indexArr[0] == -1 ? target.length : target.length - lengthOfLis(indexArr);
    }

    private static int solveMin(int[] target, int[] arr) {
        int n = target.length;
        // target值与其索引的映射
        Map<Integer, Integer> pos = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            pos.put(target[i], i);
        }

        // 记录arr中元素在target中对应的索引
        List<Integer> indexArr = new ArrayList<>();
        for (int val : arr) {
            // 如果arr的元素不在target中，则 不需要记录
            if (!pos.containsKey(val)) {
                continue;
            }

            // arr元素在target中的索引
            int idx = pos.get(val);
            // 采用二分获取记录的索引
            int it = binarySearch(indexArr, idx);

            if (it != indexArr.size()) {
                indexArr.set(it, idx);
            } else {
                indexArr.add(idx);
            }
        }

        return n - indexArr.size();
    }

    private static int binarySearch(List<Integer> indexArr, int target) {
        int size = indexArr.size();
        if (size == 0 || indexArr.get(size - 1) < target) {
            return size;
        }

        int low = 0, high = size - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (indexArr.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private static int lengthOfLis(int[] indexArr) {
        // 定义新数组，记录每一个索引位置所在的最大上升序列长度
        int[] dp = new int[indexArr.length];
        Arrays.fill(dp, 1);
        // 初始化最大值
        int max = 1;
        // 初始化0节点
        dp[0] = 1;
        for (int i = 1; i < indexArr.length; i++) {
            // 初始化每一个索引所在长度为1，因为最次的情况就是最大长度为其本身 -> maxLength = 1
            dp[i] = 1;
            int a = indexArr[i];
            // 将i所在的值与前面的依次比较
            for (int j = 0; j < i; j++) {
                // 如果i的值大于了前面的值
                // -> 则需要比对i的最大值与（j的最大值+1）的大小
                // -> 因为i的最大值只可能在本身以及j的最大值+1中产生，
                // -> 并更新i索引所在的最大值
                int b = indexArr[j];
                if (a > b) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 更新最大值，最大值只可能在上一个max和当前索引所在最大长度产生
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
