package learn.leecode.str;

/**
 * 5. 最长回文子串
 *
 * @author zgq
 */
public class LeCode5 {

    public static void main(String[] args) {
        String s = "babad";
//        String s = "1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        System.out.println("s.length:" + s.length());
        //        String s = "acbdbca";
        long l1 = System.currentTimeMillis();
        String res = longestPalindrome(s);
        System.out.println("耗时：" + (System.currentTimeMillis() - l1));
        System.out.println("结果：" + res);
        System.out.println("res.length:" + res.length());
    }

    /**
     * 最长回文子串 (5)
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * <p>
     * 示例 1：
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * <p>
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出："bb"
     * <p>
     * 示例 3：
     * 输入：s = "a"
     * 输出："a"
     * <p>
     * 示例 4：
     * 输入：s = "ac"
     * 输出："a"
     * <p>
     * 提示：
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母（大写和/或小写）组成
     */
    private static String longestPalindrome(String s) {
        // 解法1：动态规划解题
        String res1 = dTgh(s);

        // 解法2：暴力枚举
        String res2 = blMj(s);

        // 解法3：中心扩散
        String res3 = centerKs(s);

        return res1;
    }

    private static String centerKs(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int strLen = s.length();

        // 初始化结果
        int len = 1;
        int maxStart = 0;
        int maxLen = 0;

        for (int i = 0; i < strLen; i++) {
            // 初始化左右边界
            int left = i - 1;
            int right = i + 1;

            // 向左扩散
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }

            // 向右扩散
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }

            // 左右同时扩散
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                len = len + 2;
                left--;
                right++;
            }

            // 若是回文记录值
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            // 下一循环初始化len
            len = 1;
        }
        return s.substring(maxStart + 1, maxStart + maxLen + 1);
    }

    private static String blMj(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // 回文字符串的特性，首尾字符一定是相等的，故可使用双指针
        String res = "";
        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j >= i; j--) {
                // 若2字符串相等，则截取判断是否为回文子串
                if (s.charAt(i) == s.charAt(j)) {
                    // 截取，左闭右开
                    String tempStr = s.substring(i, j + 1);

                    // 若是回文，则记录回文子串
                    if (isHw(tempStr)) {
                        if (tempStr.length() >= res.length()) {
                            res = tempStr;
                            break;
                        }
                    }
                }
            }

            if (res.length() >= len - i) {
                return res;
            }
        }

        return res;
    }

    private static String dTgh(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int k = 2; k <= len; k++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 k 和 i 可以确定右边界，即 j - i + 1 = k 得
                int j = k + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 判断字符串是否是回文字符串
     *
     * @param tempStr 字符串
     * @return 是-否
     */
    private static boolean isHw(String tempStr) {
        int length = tempStr.length();
        if (length < 2) {
            return true;
        }

        int left = 0;
        int right = length - 1;
        while (left < (length >> 1) + 1) {
            if (tempStr.charAt(left) != tempStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
