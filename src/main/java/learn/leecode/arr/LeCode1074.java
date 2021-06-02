package learn.leecode.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * 1074. 元素和为目标值的子矩阵数量
 *
 * @author zgq
 */
public class LeCode1074 {

    public static void main(String[] args) {
        int[][] matrix =
                {{0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}};
        int target = 0;
        int res = numSubmatrixSumTarget(matrix, target);
        System.out.println("结果：" + res);
    }

    /**
     * 元素和为目标值的子矩阵数量(1074)
     * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
     * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
     * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
     * <p>
     * 示例 1：
     * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
     * 输出：4
     * 解释：四个只含 0 的 1x1 子矩阵。
     * <p>
     * 示例 2：
     * 输入：matrix = [[1,-1],[-1,1]], target = 0
     * 输出：5
     * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
     * <p>
     * 示例 3：
     * 输入：matrix = [[904]], target = 0
     * 输出：0
     * <p>
     * 提示：
     * 1 <= matrix.length <= 100
     * 1 <= matrix[0].length <= 100
     * -1000 <= matrix[i] <= 1000
     * -10^8 <= target <= 10^8
     */
    private static int numSubmatrixSumTarget(int[][] matrix, int target) {
        // 多维数组降维 -> 一维数组
        int ans = 0;
        // 列len
        int m = matrix.length;
        // 行len
        int n = matrix[0].length;
        // 枚举上边界（i表示行索引）
        for (int i = 0; i < m; i++) {
            // sum表示该行降维之后的所有列和的元素
            int[] sum = new int[n];
            // 枚举下边界（j表示要合并的行索引,它一定是小于m的）
            for (int j = i; j < m; j++) {
                // c表示列索引
                for (int c = 0; c < n; c++) {
                    // 更新每列的元素和（行降维的列和）
                    sum[c] += matrix[j][c];
                }
                // 计算在该行有几个满足条件的矩阵
                ans += subarraySum(sum, target);
            }
        }
        return ans;
    }

    /**
     * 计算该行所在矩阵有几个满足条件的矩阵
     *
     * @param nums 该行所在矩阵降维的列和数组
     * @param k    校对值
     * @return 满足矩阵数
     */
    private static int subarraySum(int[] nums, int k) {
        // map用于记录
        Map<Integer, Integer> map = new HashMap<>();
        // 初始化
        map.put(0, 1);
        int count = 0;
        // 前值和记录
        int pre = 0;
        for (int x : nums) {
            pre += x;
            // 若map包含 和与k的差的值 则表示该位置的矩阵满足
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            // 并将数组和数据记录，并标记该值出现次数
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}