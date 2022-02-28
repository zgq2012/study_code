package learn.leecode.dongtaigh;

/**
 * 62
 *
 * @author zgq
 */
public class LeCode62 {

    public static void main(String[] args) {
        int m = 7;
        int n = 3;
        int res = uniquePaths(m, n);
        System.out.println(res);
    }

    /**
     * 不同路径(62)
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 问总共有多少条不同的路径？
     * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
     * <p>
     * 示例 1:
     * 输入: m = 3, n = 2     输出: 3
     * 解释:
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     * <p>
     * 示例 2:
     * 输入: m = 7, n = 3     输出: 28
     * <p>
     * 提示：1 <= m, n <= 100     题目数据保证答案小于等于 2 * 10 ^ 9
     */
    private static int uniquePaths(int m, int n) {
        /*
        对于m*n的格子，从左上到左下，要到达第一行或第一列的格子所在位置，只可能有一条路径
        即：d[0][j] = 1; d[i][0] = 1;
        其他位置的情况：d[i][j] = d[i-1][j] + d[i][j-1];
        */
//        int[][] d = new int[m][n];
        // 优化，由二维数组转一维数组 d[n] = d[n-1] + d[n]; 每次都覆盖上一次的值
        int[] res = new int[n];
        // 初始化第一行第一列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    res[j] = 1;
//                    d[i][j] = 1;
                    continue;
                }
//                d[i][j] = d[i - 1][j] + d[i][j - 1];
                // 因为滚动数组之后， res[j]，在i=0;之后就已经存在值，每次重新赋值都时带上了上一行的数据
                res[j] = res[j - 1] + res[j];
            }
        }
        return res[n - 1];
//        return d[m - 1][n - 1];
    }
}
