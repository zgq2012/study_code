package learn.leecode.arr;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 73. 矩阵置零
 *
 * @author zgq
 * @since 2021/10/18
 **/
public class LeCode73 {
    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes2(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 原地数组的方式
     */
    public static void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstColumn = false;
        // 使用数组第一行以及第一列的数据来记录哪些行哪些列要设置为0
        for (int i = 0; i < m; i++) {
            // 判断第一列是否应该为0
            if(matrix[i][0] == 0){
                // 若存在0的值，那第一列就为true
                firstColumn = true;
            }

            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 将对应行列设置为0,防止每一列的第一个元素被提前更新，我们需要从最后一行开始，倒序地处理矩阵元素
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }

            // 设置第一列是否为0
            if(firstColumn){
                matrix[i][0] = 0;
            }
        }
    }
}
