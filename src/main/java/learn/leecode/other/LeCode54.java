package learn.leecode.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 54
 *
 * @author zgq
 */
public class LeCode54 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
//        int[][] matrix = {
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12}
//        };
//        int[][] matrix = {
//                {6, 9, 7}
//        };
//        int[][] matrix = {
//                {7},
//                {9},
//                {6}
//        };
//        int[][] matrix = {
//                {1, 2},
//                {3, 4}
//        };
        int[][] matrix2 = {
                {2, 5, 8},
                {4, 0, -1}
        };
        List<Integer> list = spiralOrder(matrix);
        List<Integer> list2 = spiralOrder(matrix2);
        System.out.println(list);
        System.out.println(list2);
    }

    /**
     * (54)螺旋矩阵
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     * 示例 1:
     * 输入:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * <p>
     * 示例 2:
     * 输入:
     * [
     * [1, 2, 3, 4],
     * [5, 6, 7, 8],
     * [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int right = matrix[0].length - 1;
        int down = matrix.length - 1;
        int up = 0;
        int index = 0;
        // 计算最小圈数
        int cyc = (Math.min(right + 1, down + 1) + 1) / 2;
        if (right < 1) {
            // 只有一列
            for (int[] ints : matrix) {
                list.add(ints[0]);
            }
            return list;
        }
        if (down < 1) {
            // 只有一行
            for (int val : matrix[0]) {
                list.add(val);
            }
            return list;
        }
        for (int j = 0; j < cyc; j++) {
            for (int i = index; i <= right; i++) {
                list.add(matrix[index][i]);
            }
            for (int i = index + 1; i <= down; i++) {
                list.add(matrix[i][right]);
            }
            // 如果只有一列或者一行时，不需要继续了，因为在上面已经执行完了
            if (down - index == 0 || right - index == 0) {
                break;
            }
            for (int i = right - 1; i >= index; i--) {
                list.add(matrix[down][i]);
            }
            for (int i = down - 1; i >= up + 1; i--) {
                list.add(matrix[i][index]);
            }
            right--;
            down--;
            up++;
            index++;
        }
        return list;
    }
}
