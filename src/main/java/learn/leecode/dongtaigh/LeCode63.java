package learn.leecode.dongtaigh;

/**
 * 63
 *
 * @author zgq
 */
public class LeCode63 {

    public static void main(String[] args) {
        /*int[][] arr = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}};*/
        int[][] arr = {
                {0, 0}};
        int res = uniquePathsWithObstacles(arr);
        System.out.println(res);
    }

    /**
     * 不同路径II(障碍物)(63)
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * <p>
     * 示例 1：
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]      输出：2
     * 解释：
     * 3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     * <p>
     * 示例 2：
     * 输入：obstacleGrid = [[0,1],[0,0]]      输出：1
     * <p>
     * 提示：
     * m == obstacleGrid.length     n == obstacleGrid[i].length
     * 1 <= m, n <= 100     obstacleGrid[i][j] 为 0 或 1
     */
    private static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        /*
        对于m*n的格子，从左上到左下，要到达第一行或第一列的格子所在位置，只可能有一条路径,但是遇到障碍物，那之后的位置都是0
        即：d[0][j] = 1; d[i][0] = 1;
        其他位置的情况：d[i][j] = d[i-1][j] + d[i][j-1];若遇到障碍物，则d[i][j] = 0;
        */
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // 二维转一维数组
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    res[j] = 0;
                    continue;
                }
                if (j == 0) {
                    continue;
                }
                if (i == 0) {
                    res[j] = res[j - 1];
                    continue;
                }
                res[j] = res[j - 1] + res[j];
            }
        }
        return res[n - 1];
    }
}
