package learn.leecode.slidwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 438
 *
 * @author zgq
 */
public class LeCode438 {
    public static void main(String[] args) {
        /*String s = "basdasdcxzsda";
        String p = "sd";*/
        String s = "";
        String p = "a";
        List<Integer> anagrams = findAnagrams(s, p);
        System.out.println(anagrams);
    }

    /**
     * (438)找到字符串中所有字母异位词
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     * <p>
     * 说明：字母异位词指字母相同，但排列不同的字符串。不考虑答案输出的顺序。
     * <p>
     * 示例 1:
     * 输入:
     * s: "cbaebabacd" p: "abc"
     * 输出:
     * [0, 6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
     * <p>
     *  示例 2:
     * 输入:
     * s: "abab" p: "ab"
     * 输出:
     * [0, 1, 2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
     */
    private static List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }
        // 滑动窗口字符计数数组
        int[] windows = new int[128];
        // 子串计数数组
        int[] pMap = new int[128];
        // 初始化子串数组和滑动数组,前p.length()个数赋值
        for (int i = 0; i < p.length(); i++) {
            pMap[p.charAt(i)]++;
            windows[s.charAt(i)]++;
        }
        // 判断前p.length()个数是否为异位词
        List<Integer> result = new ArrayList<>();
        if (isSame(windows, pMap)) {
            result.add(0);
        }

        int len = s.length();
        // 循环模式串，至目标子串之前
        for (int i = p.length(); i < len; i++) {
            // 给滑动窗口 i（即滑动窗口继续走的下一位）赋值
            windows[s.charAt(i)]++;
            // 去掉滑动窗口的刚走过的位置的值
            windows[s.charAt(i - p.length())]--;
            // 判断是否异位词
            if (isSame(windows, pMap)) {
                // 记录异位词起始索引 i-1
                result.add(i - p.length() + 1);
            }
        }
        return result;
    }

    private static boolean isSame(int[] windows, int[] pMap) {
        // 匹配每一个字符出现的次数
        for (int i = 0; i < pMap.length; i++) {
            if (windows[i] != pMap[i]) {
                return false;
            }
        }
        return true;
    }
}