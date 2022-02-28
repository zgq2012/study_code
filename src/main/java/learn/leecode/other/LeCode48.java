package learn.leecode.other;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 48. 旋转图像
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/2/24
 **/
public class LeCode48 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] res = rotate(matrix);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }
    }

    /**
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     * <p>
     * 输入：matrix = [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]]
     * 输出：[
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]]
     * <p>
     * 输入：matrix = [
     * [5,1,9,11],
     * [2,4,8,10],
     * [13,3,6,7],
     * [15,14,12,16]]
     * 输出：[
     * [15,13,2,5],
     * [14,3,4,1],
     * [12,6,8,9],
     * [16,7,10,11]]
     * <p>
     * 提示：
     * <p>
     * n == matrix.length == matrix[i].length
     * 1 <= n <= 20
     * -1000 <= matrix[i][j] <= 1000
     */
    private static int[][] rotate(int[][] matrix) {
        // n阶矩阵 arr[i, j] 旋转90° -> arr[n - 1 - j, i]
        //              继续 旋转90° -> arr[n - 1 - i, n - 1 - j]
        //              继续 旋转90° -> arr[j, n - 1 - i]
        //              继续 旋转90° -> arr[i, j]
        // 旋转 360°回到原来的位置，那么我们可以得到1/2 * 1/2 * n 的数据旋转就得到所有数据旋转
        // n为奇数时，1/2 * n + 1的位置也需要旋转
        int n = matrix.length;
        // 奇偶判定
        int len = n % 2 == 0 ? n / 2 : (n / 2) + 1;
        for (int i = 0; i < len; i++) {
            // 若是奇数，其中一个维度需要少遍历一圈，
            for (int j = 0; j < (n % 2 == 0 ? len : len - 1); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }

        return matrix;
    }
}
