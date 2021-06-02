package learn.leecode.dongtaigh;

/**
 * 664. 奇怪的打印机
 *
 * @author zgq
 */
public class LeCode664 {

    public static void main(String[] args) {
        String text1 = "a4321435fadaasfqba";

        int res = strangePrinter(text1);
        System.out.println(res);
    }

    /**
     * (664)奇怪的打印机
     * 有台奇怪的打印机有以下两个特殊要求：
     * 打印机每次只能打印由 同一个字符 组成的序列。
     * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
     * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
     * <p>
     * 示例 1：
     * 输入：s = "aaabbb"
     * 输出：2
     * 解释：首先打印 "aaa" 然后打印 "bbb"。
     * <p>
     * 示例 2：
     * 输入：s = "aba"
     * 输出：2
     * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
     * <p>
     * 提示：
     * 1 <= s.length <= 100
     * s 由小写英文字母组成
     */
    public static int strangePrinter(String s) {
        int len = s.length();
        // 采用动态规划,
        // 分析dp, 打印机一次可打印重复字符，则字符串，在长度[i,j]的区间中时，
        // dp[i][j] = 最少需要1次，
        // 若s[i] = s[j], 表示字符串首位字符相等，在一次打印中，可以直接将首位都打印出来，
        // 那么可知 dp[i][j] 与 dp[i][j-1]需要的打印次数相等 --> dp[i][j] = dp[i][j-1]
        // 若s[i] ≠ s[j], 那么，字符串可以在进行拆分，得到新的2个字符数组,区间 [i, k],与 [k+1, j].
        // 在这2个的基础上，继续分，最后最差的情况就会分解为，每一个单独的字符就需要打印一次,
        // 可得：dp[i][j] = dp[i][k] + dp[k+1][j];
        int[][] dp = new int[len][len];
        // 倒叙遍历，
        for (int i = len - 1; i >= 0; i--) {
            // 初始化ii区间打印次数
            dp[i][i] = 1;
            // 遍历区间
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    int minn = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        minn = Math.min(minn, dp[i][k] + dp[k + 1][j]);
                    }
                    dp[i][j] = minn;
                }
            }
        }

        return dp[0][len - 1];

    }
}
