package learn.leecode.other;

/**
 * 221. 最大正方形
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/23
 **/
public class LeCode221 {
    public static void main(String[] args) {
        char[][] matrix = {{'0', '1'}};
        int res = maximalSquare(matrix);
        System.out.println("res = " + res);
    }

    /**
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     *
     * 示例 1：
     * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
     * 输出：4
     *
     * 示例 2：
     * 输入：matrix = [["0","1"],["1","0"]]
     * 输出：1
     *
     * 示例 3：
     * 输入：matrix = [["0"]]
     * 输出：0
     *
     * 提示：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 300
     * matrix[i][j] 为 '0' 或 '1'
     */
    private static int maximalSquare(char[][] matrix) {
        // 采用动态规划，dp[i][j] 标志以(i,j)为右下角的正方形的最大边长
        // dp[i][j] 相关 的是，其上方，其左方，其左上方的正方形大小，
        // -> dp[i][j] = min(dp[i-1][j], dp[i-1][j-1],dp[i][j-1]) + 1
        // 且 i==0 || j==0 时，dp[i][j] = 1 || 0;
        // 也可以用元素组记录，看需求是否可以变更原来的数组
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        // 记录最大边长
        int maxSide = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 不等于'0'才进入判定; 等于'0'，直接跳过，因为默认dp[i][j] = 0
                if (matrix[i][j] != '0') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = matrix[i][j] - '0';
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                    }
                }

                maxSide = Math.max(dp[i][j], maxSide);
            }
        }

        return maxSide * maxSide;
    }
}
