package learn.leecode.str;

import java.util.Arrays;

/**
 * 28
 *
 * @author zgq
 */
public class LeCode28 {

    public static void main(String[] args) {
        strStr();
    }

    /**
     * 实现 strStr()(28)
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
     * 如果不存在，则返回  -1。
     * <p>
     * 示例 1:
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 示例 2:
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * <p>
     * 说明:当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0
     */
    private static void strStr() {
//        String haystack = "hello";
//        String haystack = "llhello";
        String haystack = "heollqweqws";
//        String haystack = "heoll";
        String needle = "ll";
        if ("".equals(needle)) {
            System.out.println(0);
            return;
        }
        if ("".equals(haystack)) {
            System.out.println(-1);
            return;
        }
        if (haystack.startsWith(needle)) {
            System.out.println(0);
            return;
        }

        String[] split = haystack.split(needle);
        int length = split.length;
        if (haystack.endsWith(needle) && length == 1) {
            System.out.println(haystack.length() - needle.length());
            return;
        }
        if (length <= 1) {
            System.out.println(-1);
            return;
        }
        int index = split[0].length();
        System.out.println(index);
        haystack.indexOf(needle);
        // 优解->解法2：滑动匹配方式
        int indexStr = strStr2(haystack, needle);
        System.out.println(indexStr);
    }

    private static int strStr2(String haystack, String needle) {
        int len = needle.length();
        int n = haystack.length();
        // 滑动匹配
        for (int start = 0; start < n - len + 1; ++start) {
            // 当前字符与字符串第一个字符匹配时才比较
            if (haystack.charAt(start) == needle.charAt(0)
                    && haystack.substring(start, start + len).equals(needle)) {
                return start;
            }
        }
        return -1;
    }
}
