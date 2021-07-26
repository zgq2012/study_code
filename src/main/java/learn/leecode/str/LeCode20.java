package learn.leecode.str;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 *
 * @author zgq
 */
public class LeCode20 {

    public static void main(String[] args) {
//        String s = "{{}}{}[]()";
//        String s = "{[]}";
        String s = "{{";
        boolean valid = isValid(s);
        System.out.println("valid = " + valid);
    }


    /**
     * 有效的括号 (20)
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * <p>
     * 示例 1：
     * 输入：s = "()"
     * 输出：true
     * <p>
     * 示例 2：
     * 输入：s = "()[]{}"
     * 输出：true
     * <p>
     * 示例 3：
     * 输入：s = "(]"
     * 输出：false
     * <p>
     * 示例 4：
     * 输入：s = "([)]"
     * 输出：false
     * <p>
     * 示例 5：
     * 输入：s = "{[]}"
     * 输出：true
     * <p>
     * 提示：
     * 1 <= s.length <= 10^4
     * s 仅由括号 '()[]{}' 组成
     */
    private static boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }

        Map<Character, Character> khMap = new HashMap<>(4);
        khMap.put('(', ')');
        khMap.put('[', ']');
        khMap.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Character other = khMap.get(c);
            if(i == 0 && other == null){
                return false;
            }

            if(stack.size() == 0){
                stack.push(other);
                continue;
            }

            Character s1 = stack.lastElement();
            if (s1 != null && c == s1) {
                // 如果c 与栈的第一个元素相等，表示括号正确
                stack.pop();
            } else {
                // 如果c 与栈的第一个元素不相等,那么，若map存在key就直接添加, 不存在直接返回false
                if (other == null) {
                    return false;
                }
                stack.push(khMap.get(c));
            }
        }

        return stack.isEmpty();
    }
}
