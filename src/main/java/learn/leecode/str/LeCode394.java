package learn.leecode.str;

import java.util.Stack;

/**
 * 394. 字符串解码
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/4/12
 **/
public class LeCode394 {
    public static void main(String[] args) {
//        String s = "3[a]2[bc]";
        String s = "3[a2[c]]";
        System.out.println("s = " + s);
        String res = decodeString(s);
        System.out.println("res = " + res);
    }

    /**
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_String]，表示其中方括号内部的 encoded_String 正好重复 k 次。注意 k 保证为正整数。
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     * <p>
     * 示例 1：
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     * <p>
     * 示例 2：
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     * <p>
     * 示例 3：
     * 输入：s = "2[abc]3[cd]ef"
     * 输出："abcabccdcdcdef"
     * <p>
     * 示例 4：
     * 输入：s = "abc3[cd]xyz"
     * 输出："abccdcdcdxyz"
     * <p>
     * 提示：
     * 1 <= s.length <= 30
     * s 由小写英文字母、数字和方括号 '[]' 组成
     * s 保证是一个 有效 的输入。
     * s 中所有整数的取值范围为 [1, 300] 
     */
    private static String decodeString(String s) {
        // 数字栈
        Stack<Integer> numStk = new Stack<>();
        // 字符串栈
        Stack<String> strStk = new Stack<>();
        // 当前正在累积的字符串
        String str = "";
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                // 遇到数字
                int n = 0;
                while (Character.isDigit(c)) {
                    // 数字可能有多位
                    n = 10 * n + c - '0';
                    i++;
                    c = s.charAt(i);
                }
                // 加入数字栈
                numStk.push(n);
                // 往后退一步(for循环处有自增操作)
                --i;
            } else if (c == '[') {
                // 遇到左括号
                // 将当前累积的字符串入栈
                strStk.push(str);
                // 开始记录新的一段字符串
                str = "";
            } else if (c == ']') {
                // 遇到右括号
                StringBuilder tmp = new StringBuilder();
                // 将当前字符串按数字栈栈顶元素为倍数进行扩展
                for (int j = 0; j < numStk.peek(); ++j) {
                    tmp.append(str);
                }
                str = tmp.toString();
                // 数字栈栈顶元素弹出
                numStk.pop();
                // 字符串栈栈顶元素弹出来并与当前字符串拼接，作为新的当前正在累积的字符串
                str = strStk.pop() + str;
            } else {
                // 当前字符串继续累积
                str += c;
            }
        }
        return str;
    }
}
