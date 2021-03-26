package learn.leecode.dongtaigh;

import java.util.Arrays;

/**
 * 64
 *
 * @author zgq
 */
public class LeCode64 {

    public static void main(String[] args) {
        minPathSum();
    }

    /**
     * 最小路径和(64)
     * 给定一个包含非负整数的 m*n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * 输入:
     * [
     * [1,3,1],
     * [1,5,1],
     * [4,2,1]
     * ]
     * 输出: 7  解释: 因为路径 1→3→1→1→1 的总和最小。
     */
    private static void minPathSum() {
        int[][] grid = getArr();
        // 只有一行时
        if (grid.length == 1) {
            int sum = 0;
            for (int i = 0; i < grid[0].length; i++) {
                sum += grid[0][i];
            }
            System.out.println(sum);
            return;
        }
        // 只有一列时
        if (grid[0].length == 1) {
            int sum = 0;
            for (int[] ints : grid) {
                sum += ints[0];
            }
            System.out.println(sum);
            return;
        }
        // 最优子解整合得到最优解
        // 获取状态转移方程
        //           f[i-1][0] + c[i][0], 最左边的一列只能由上边下来
        // f[i][j] = min(f[i-1][j],f[i][j-1]) + c[i][j], f[i][j]只能由左边或者上边获取到，
        //           f[0][j-1] + c[0][j], 最上边的只能由左边来
        int[][] f = new int[grid.length][grid[0].length];
        // 初始化第一位
        f[0][0] = grid[0][0];
        // 超过2列则通用遍历
        for (int i = 1; i < grid.length; i++) {
            // 最左边列取值
            f[i][0] = f[i - 1][0] + grid[i][0];
            for (int j = 1; j < grid[i].length; j++) {
                // 最上边列
                f[0][j] = f[0][j - 1] + grid[0][j];
                // 其他通用列取值
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
            }
        }

        System.out.println(Arrays.toString(f[f.length - 1]));
        System.out.println(f[f.length - 1][grid[0].length - 1]);
    }

    private static int[][] getArr() {
        // [[1,3,1],[1,5,1],[4,2,1]]
        int[][] grid = new int[1][3];
        grid[0][0] = 1;
        grid[0][1] = 3;
        grid[0][2] = 1;
//        grid[1][0] = 1;
//        grid[1][1] = 5;
//        grid[1][2] = 1;
//        grid[2][0] = 4;
//        grid[2][1] = 2;
//        grid[2][2] = 1;
        for (int[] ints : grid) {
            System.out.println(Arrays.toString(ints));
        }
        return grid;
    }
}
