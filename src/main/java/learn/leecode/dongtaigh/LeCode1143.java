package learn.leecode.dongtaigh;

/**
 * 1143. 最长公共子序列
 *
 * @author zgq
 */
public class LeCode1143 {

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";

        int res = longestCommonSubsequence(text1, text2);
        System.out.println(res);
    }

    /**
     * (1143)最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：
     * 它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * <p>
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * <p>
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     * <p>
     * 示例 1：
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     * <p>
     * 示例 2：
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
     * <p>
     * 示例 3：
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0 。
     *  
     * 提示：
     * 1 <= text1.length, text2.length <= 1000
     * text1 和 text2 仅由小写英文字符组成。
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        // 定义二位数组dp[i][j]记录每一个字符串的相同子序列数量
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        // 在i = 0,或者j = 0时，表示的就是其中一个字符串为空，可得-> dp[i][0] = 0; dp[0][j] = 0;无需设置，数组默认值就是0
        // 字符串皆从1开始遍历，整个流程相当于一个 m*n 的方格，其中每一个格子的计数，
        for (int i = 1; i <= text1.length(); i++) {
            // 获取从0开始索引的字符
            char charI = text1.charAt(i - 1);
            for (int j = 1; j <= text2.length(); j++) {
                char charJ = text2.charAt(j - 1);
                // 若2个字符相等则，dp[i][j] = dp[i - 1][j - 1] + 1,
                if (charI == charJ) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 若2个字符不相等则，dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]),
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }
}
