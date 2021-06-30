package learn.leecode.slidwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * 3
 *
 * @author zgq
 */
public class LeCode3 {
    public static void main(String[] args) {
        String s = "au";
        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    /**
     * (3)无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * 输入: "abcabcbb"  输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 示例 2:
     * 输入: "bbbbb"  输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * <p>
     * 示例 3:
     * 输入: "pwwkew" 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * <p>
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     */
    private static int lengthOfLongestSubstring(String s) {
        // 滑动窗口解法
        if("".equals(s)){
            return 0;
        }
        int len = s.length();
        if(len == 1){
            return len;
        }
        // 采用滑动窗口
        int right = 0;
        int left = 0;
        // 初始化最大值为1
        int max = 1;
        for (int j = 1; j < len; j++) {
            for(int i = left; i<= right; i++){
                // 在滑动窗口left-right的范围内查找与当前位置相等的字符
                if(s.charAt(i) == s.charAt(j)){
                    // 若找到，则收缩左边界返回，
                    left = i + 1;
                    break;
                }
            }
            // 该窗口内没有重复值，右边界扩张
            right = j;
            // 查找完成，记录max，max取值为 查找前的max 与 当前右边界与左边界的距离的最大值
            max = Math.max(max, right - left + 1);
        }
        return max;


        // 哈希集合，记录每个字符是否出现过
        /*Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int right = -1;
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                char c = s.charAt(i - 1);
                occ.remove(c);
            }
            // right是不断更新的，如果当前right+1已经存在那么，直接返回，然后前一位被移除，直到重复的字符被移除才继续
            while (right + 1 < n && !occ.contains(s.charAt(right + 1))) {
                // 不断地移动右指针
                char c = s.charAt(right + 1);
                // 不重复的添加进去
                occ.add(c);
                // 然后指针继续移位
                right++;
            }
            // 第 i 到 right 个字符是一个极长的无重复字符子串，以result和当前位比较大小
            result = Math.max(result, right - i + 1);
        }
        return result;*/
    }
}