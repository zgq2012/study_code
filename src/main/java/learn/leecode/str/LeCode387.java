package learn.leecode.str;

import java.util.LinkedHashMap;

/**
 * 387
 *
 * @author zgq
 */
public class LeCode387 {

    public static void main(String[] args) {
        firstUniqChar();
    }

    /**
     * 字符串中的第一个唯一字符(344)
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     * <p>
     * 示例：
     * s = "leetcode"
     * 返回 0
     * s = "loveleetcode"
     * 返回 2
     *  
     * 提示：你可以假定该字符串只包含小写字母。
     */
    private static void firstUniqChar() {
//        String s = "leetcode";
        String s = "loveleetcode";
        // 记录每个字母出现次数
        char[] chars = s.toCharArray();
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (char aChar : chars) {
            map.put(aChar, map.containsKey(aChar) ? map.get(aChar) + 1 : 1);
        }
        // 找出第一个为1的字母，没有就返回-1
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}
