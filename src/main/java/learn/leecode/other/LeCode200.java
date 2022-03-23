package learn.leecode.other;

import java.util.Arrays;

/**
 * 200. 岛屿数量
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/21
 **/
public class LeCode200 {
    public static void main(String[] args) {
//        char[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}
//        };

        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};

        int res = numIslands(grid);

        System.out.println("res = " + res);
    }

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * 示例 1：
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     *
     * 示例 2：
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     *
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] 的值为 '0' 或 '1'
     */
    private static int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 是陆地才进入小岛判定
                if (grid[i][j] == '1') {
                    // 探索到的区域标记为'2'，也可采用标记数组，探索结束表示该位置的小岛都被标记，剩下的是未标记的和海洋区域
                    dfsIslands(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private static void dfsIslands(char[][] grid, int i, int j) {
        // 超出边界直接返回
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }

        if(grid[i][j] != '1'){
            return;
        }

        grid[i][j] = '2';

        // 下面探索
        dfsIslands(grid, i + 1, j);
        // 上面探索
        dfsIslands(grid, i - 1, j);
        // 左面探索
        dfsIslands(grid, i, j - 1);
        // 右面探索
        dfsIslands(grid, i, j + 1);
    }
}
