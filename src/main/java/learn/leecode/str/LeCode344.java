package learn.leecode.str;

import java.util.Arrays;

/**
 * 344
 *
 * @author zgq
 */
public class LeCode344 {

    public static void main(String[] args) {
        reverseString();
    }

    /**
     * 反转字符串(344)
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     * <p>
     * 示例 1：
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * <p>
     * 示例 2：
     * 输入：["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     */
    private static void reverseString() {
//        char[] s = {'h', 'e', 'l', 'l', 'o'};
        char[] s = {'H', 'a', 'n', 'n', 'a', 'h'};
        int length = s.length;
        if (length < 2) {
            System.out.println(Arrays.toString(s));
            return;
        }
        char temp;
        for (int i = 0; i < length / 2; i++) {
            temp = s[i];
            s[i] = s[length - i - 1];
            s[length - i - 1] = temp;
        }
        System.out.println(Arrays.toString(s));
    }
}
