package learn.leecode.huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51
 *
 * @author zgq
 */
public class LeCode51 {
    public static void main(String[] args) {
        int num = 8;
        List<List<String>> result = solveNumQueens(num);
        result.forEach(str -> {
            str.forEach(System.out::println);
            System.out.println("-----------------");
        });
    }

    /**
     * (51)N皇后
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 上图为 8 皇后问题的一种解法。
     * <p>
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '-' 分别代表了皇后和空位。
     * <p>
     * 示例：
     * <p>
     * 输入：4
     * 输出：[
     * [".Q..",  // 解法 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * <p>
     * ["..Q.",  // 解法 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     * 解释: 4 皇后问题存在两个不同的解法。
     * <p>
     * 提示：皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
     */
    private static List<List<String>> solveNumQueens(int n) {
        // 记录所用情况
        List<List<String>> result = new ArrayList<>();
        // 初始化图形
        char[][] arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = '-';
            }
        }
        // 寻找皇后queen位置,默认从第一位索引0开始
        selectIndex(arr, result, 0);
        return result;
    }

    private static void selectIndex(char[][] arr, List<List<String>> result, int index) {
        if (index == arr.length) {
            List<String> list = new ArrayList<>();
            for (char[] chars : arr) {
                list.add(Arrays.toString(chars));
            }
            result.add(list);
            return;
        }

        boolean isExist;
        // 按列给皇后占位赋值
        for (int i = 0; i < arr.length; i++) {
            isExist = checkQueen(arr, i, index);
            if (!isExist) {
                // 不存在时，继续向下走，
                arr[index][i] = 'Q';
                selectIndex(arr, result, index + 1);
                // 复原，因为可能有多种解法
                arr[index][i] = '-';
            }
        }
    }

    private static boolean checkQueen(char[][] arr, int i, int index) {
        // 判断该位置所在行或列是否已存在queen
        for (int k = 0; k < index; k++) {
            if (arr[k][i] == 'Q') {
                return true;
            }
        }

        // 判断行以上的斜线列（行以下的还没开始，没必要判断）是否已存在queen
        //判断当前坐标的右上角有没有皇后
        for (int k = index - 1, j = i + 1; k >= 0 && j < arr.length; k--, j++) {
            if (arr[k][j] == 'Q') {
                return true;
            }
        }
        //判断当前坐标的左上角有没有皇后
        for (int k = index - 1, j = i - 1; k >= 0 && j >= 0; k--, j--) {
            if (arr[k][j] == 'Q') {
                return true;
            }
        }
        return false;
    }
}
