package learn.leecode.bitcount;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * 338. 比特位计数
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/25
 **/
public class LeCode338 {
    public static void main(String[] args) {
        int n = 16;
        System.out.println("[0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1]");
        int[] res = countBits(n);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     * <p>
     * 示例 1：
     * 输入：n = 2
     * 输出：[0,1,1]
     * 解释：
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     * <p>
     * 示例 2：
     * 输入：n = 5
     * 输出：[0,1,1,2,1,2]
     * 解释：
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     * 3 --> 11
     * 4 --> 100
     * 5 --> 101
     * <p>
     * 提示：
     * 0 <= n <= 105
     * <p>
     * 进阶：
     * 很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
     * 你能不使用任何内置函数解决此问题吗？（如，C++ 中的 __builtin_popcount ）
     */
    private static int[] countBits(int n) {
        int[] dp = new int[n + 1];
        // 若dp[n]是2的幂次方 dp[n] = 1;
        // 规律：每2的幂次方子后的数都是，该次幂之前重复的个数，后面的数1的个数都+1； dp[i] = dp[i - nextTwo] + 1;
        int nextTwo = 1;

        for (int i = 1; i <= n; i++) {
            // 更新最后一次幂
            if (i == nextTwo * 2) {
                dp[i] = 1;
                nextTwo = i;
                continue;
            }

            // dp[i] = dp[i - nextTwo] + 1
            dp[i] = dp[i - nextTwo] + 1;
        }

        return dp;
    }
}
