package learn.leecode.other;

import java.util.TreeSet;

/**
 * 363. 矩形区域不超过 K 的最大数值和
 *
 * @author zgq
 */
public class LeCode363 {

    public static void main(String[] args) {
        int[][] height = {{1, 8, 6, 2}, {5, 4, 8, 3,}};
        int k = 16;
        int res = maxSumSubmatrix(height, k);
        System.out.println(res);
    }

    /**
     * (363)矩形区域不超过 K 的最大数值和
     * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
     * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
     * <p>
     * 示例 1：
     * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
     * 输出：2
     * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
     * <p>
     * 示例 2：
     * 输入：matrix = [[2,2,-1]], k = 3
     * 输出：3
     * <p>
     * 提示：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 100
     * -100 <= matrix[i][j] <= 100
     * -105 <= k <= 105
     */
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        /*
        我们枚举矩形的上下边界，并计算出该边界内每列的元素和，则原问题转换成了如下一维问题：
        给定一个整数数组和一个整数 k，计算该数组的最大区间和，要求区间和不超过 k。
        定义长度为 n 的数组 a 的前缀和
        i=0时， Si = 0； 1<= i <= n 时， Si = a0+a1+a2+...+ai-1。
        则区间 [l,r)的区间和a(l)+a(l+1)+...+a(r-1),可以表示为Sr-Sl;
        枚举 r ,上述问题的约束 Sr-Sl <= k,可以转换为 Sl >= Sr - k .
        要使 Sr-Sl尽可能大，则需要寻找最小的满足 Sl >= Sr - k的 Sl，
        我们可以在枚举 r 的同时维护一个存储Si(i<l)的有序集合，则可以在O(logn)的时间内二分找到符合要求的 Sl。
        */
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        // 枚举上边界
        for (int i = 0; i < m; ++i) {
            int[] sum = new int[n];
            // 枚举下边界
            for (int j = i; j < m; ++j) {
                for (int c = 0; c < n; ++c) {
                    // 更新每列的元素和
                    sum[c] += matrix[j][c];
                }
                TreeSet<Integer> sumSet = new TreeSet<Integer>();
                sumSet.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    Integer ceil = sumSet.ceiling(s - k);
                    if (ceil != null) {
                        ans = Math.max(ans, s - ceil);
                    }
                    sumSet.add(s);
                }
            }
        }
        return ans;
    }
}
