package learn.leecode.slidwindow;

import java.util.*;

/**
 * J57
 *
 * @author zgq
 */
public class LeCodeJ57 {
    public static void main(String[] args) {
        int target = 94527;
        int[][] result = findContinuousSequence(target);
        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * (J57)和为s的连续正数序列
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     * <p>
     * 示例 1：
     * 输入：target = 9  输出：[[2,3,4],[4,5]]
     * <p>
     * 示例 2：
     * 输入：target = 15  输出：[[1,2,3,4,5],[4,5,6],[7,8]]
     *  
     * 限制：1 <= target <= 10^5
     */
    private static int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();
        /*
        连续序列 -> 遍历只需要 target / 2
        利用等差数列和特性：
        Sn = a1*n + (n*(n-1))*d/2
        ->公式： n^2 + (2*a1 - 1) * n -2*Sn = 0
        -> Δ = 4*a1^2 - 4*a1 + 1 + 8*Sn
        遍历起点是a1,默认等差值d=1，Sn=target，那么公式n存在正整数解，即是以起点到n的值即是所求的数组，重复遍历解决
        公式存在正整数解，即是Δ=b^2 - 4ac >=0 ; n若2个解 n1 = (-1 + 根号下Δ) / 2  或 n2 = (-1 - 根号下Δ) / 2
        */
        int limit = target / 2;
        for (int i = 1; i <= limit; i++) {
            // 使用long类型转换，使用int在大数据时会超出int最大值
            long Δ = 4 * (long) i * i - 4 * i + 1 + 8 * target;
            if (Δ < 0) {
                // 表示无解
                continue;
            }
            // Δ 开方，需要加上0.5，不然可能存在精度损失
            int sqrtΔ = (int) Math.sqrt(Δ + 0.5);
            // 这里必须使用long类型转换check，使用int在大数据时会超出int最大值
            long check = (long) sqrtΔ * sqrtΔ;
            // 若开方正确，同时 sqrtΔ - 1 为偶数 -> 即有整数解才记录
            if (check == Δ && (sqrtΔ - 1) % 2 == 0) {
                // 表示其中的正数解,另外一解为负数，不用考虑
                int n1 = (sqrtΔ - 1) / 2;
                // n1 - i + 1 个元素，否则会有默认值0输出
                int[] resultChild = new int[n1 - i + 1];
                if (n1 > i) {
                    for (int j = i; j <= n1; j++) {
                        resultChild[j - i] = j;
                    }
                    result.add(resultChild);
                }
            }
        }

        // list转数组输出
        return result.toArray(new int[result.size()][]);
    }
}