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
