package learn.leecode.huisu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 22. 括号生成
 *
 * @author zgq
 */
public class LeCode22 {
    public static void main(String[] args) {
        int n = 3;
        List<String> result = generateParenthesis(n);
        result.forEach(System.out::println);
    }

    /**
     * (22) 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 有效括号组合需满足：左括号必须以正确的顺序闭合。
     *
     * 示例 1：
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：["()"]
     *
     * 提示：
     * 1 <= n <= 8
     */
    private static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    private static void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        // 左右括号都达到要求则开始存储数据
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        // 左括号小于n时 进行添加左括号
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            // 左括号回溯
            cur.deleteCharAt(cur.length() - 1);
            // 左括号回溯完继续校验右括号
        }

        // 右括号小于n时 添加右括号
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            // 右括号回溯
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
