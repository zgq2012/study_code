package learn.leecode.huisu;

import java.util.Arrays;

/**
 * 37
 *
 * @author zgq
 */
public class LeCode37 {
    public static void main(String[] args) {
        char[][] nums4 = {
                {'.', '4', '.', '.', '.', '.', '8', '.', '.'},
                {'5', '.', '.', '.', '2', '.', '9', '.', '.'},
                {'.', '8', '6', '9', '1', '.', '.', '.', '.'},
                {'.', '.', '3', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '5', '.', '6', '.', '.', '8', '4'},
                {'.', '.', '.', '5', '.', '2', '.', '.', '7'},
                {'.', '.', '.', '2', '.', '4', '.', '.', '3'},
                {'8', '.', '.', '6', '9', '.', '7', '1', '.'},
                {'9', '.', '.', '.', '.', '.', '.', '5', '.'}
        };
        char[][] nums3 = {
                {'.', '.', '.', '.', '.', '.', '1', '2', '.'},
                {'3', '.', '.', '.', '4', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '7', '.', '4'},
                {'.', '7', '3', '1', '.', '.', '4', '.', '6'},
                {'.', '.', '6', '5', '.', '.', '.', '.', '8'},
                {'.', '.', '5', '6', '.', '7', '9', '.', '.'},
                {'.', '8', '.', '4', '7', '.', '6', '1', '.'},
                {'.', '.', '7', '9', '.', '.', '5', '.', '.'},
                {'9', '4', '.', '.', '6', '.', '.', '8', '7'}
        };
        char[][] nums2 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        for (char[] num : nums2) {
            System.out.println(Arrays.toString(num));
        }
        System.out.println("--------------------------------------");
        solveSudo(nums2);
    }

    /**
     * (37)解数独
     * 编写一个程序，通过填充空格来解决数独问题。
     * <p>
     * 一个数独的解法需遵循如下规则：
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 空白格用 '.' 表示。
     * <p>
     * 提示：
     * 给定的数独序列只包含数字 1-9 和字符 '.'。你可以假设给定的数独只有唯一解。给定数独永远是 9x9 形式的。
     */
    private static void solveSudo(char[][] board) {
        // 记录到10 -> 值可以直接放入，不用 -1转换
        // 记录3x3翻方格是否已有值  第 i 行第 j 列的格子位于第 (i/3, j/3) 个3x3宫格中，
        boolean[][][] board33 = new boolean[3][3][10];
        // 当前行填入值是否已存在
        boolean[][] line99 = new boolean[9][10];
        // 当前列填入值是否已存在
        boolean[][] column99 = new boolean[9][10];

        // 记录的值初始化
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // 不为空的才初始化
                if (board[i][j] != '.') {
                    // '0'的ASCII码10进制值为48，'1'为49，依次执行，数字的char-字符0就是其原始值,
                    int num = board[i][j] - '0';
                    // 表示第i行值为num的数字已被填充
                    line99[i][num] = true;
                    // 表示第j列值为num的数字已被填充
                    column99[j][num] = true;
                    // 表示坐标第(i / 3,j / 3)的3x3方格中值为num的数字已被填充（巧用除法来指引3x3下标）
                    board33[i / 3][j / 3][num] = true;
                }
            }
        }

        // 起始位置1开始填值,
        setValToBoard(board, board33, line99, column99, 0, 0);
        for (char[] num : board) {
            System.out.println(Arrays.toString(num));
        }
    }

    private static boolean setValToBoard(char[][] board, boolean[][][] board33,
                                         boolean[][] line99, boolean[][] column99, int col, int row) {
        // 终止界限
        if (col == board.length) {
            // 进行下一行，第一列继续开始
            col = 0;
            row++;
            // 行结束，那么表示完结
            if (row == board.length) {
                return true;
            }
        }
        // 该位置不存在值时
        if (board[row][col] == '.') {
            // 数值1-9
            for (int k = 1; k <= board.length; k++) {
                if (board33[row / 3][col / 3][k] || line99[row][k] || column99[col][k]) {
                    continue;
                }
                // 该值不在所在行，列，3x3方格中,那么就进行赋值
                board[row][col] = (char) ('0' + k);
                // 赋值之后同步3数组值
                board33[row / 3][col / 3][k] = true;
                line99[row][k] = true;
                column99[col][k] = true;
                // 继续下一列执行
                if (setValToBoard(board, board33, line99, column99, col + 1, row)) {
                    // 如果之后的正确那么直接返回、
                    return true;
                } else {
                    // 不正确表示填充的值不对，那么进行值回溯
                    board[row][col] = '.';
                    // 并且数组记录回溯
                    board33[row / 3][col / 3][k] = false;
                    line99[row][k] = false;
                    column99[col][k] = false;
                }
            }
        } else {
            // 存在值时，向右一列继续进行
            return setValToBoard(board, board33, line99, column99, col + 1, row);
        }

        return false;
    }
}
