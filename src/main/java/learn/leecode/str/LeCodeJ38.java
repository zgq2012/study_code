package learn.leecode.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * J38. 字符串的排列
 *
 * @author zgq
 */
public class LeCodeJ38 {

    public static void main(String[] args) {
        String s = "suvyls";
        String[] permutation = permutation(s);
        System.out.println("结果：" + Arrays.toString(permutation));
    }

    /**
     * 字符串的排列(J38)
     * 输入一个字符串，打印出该字符串中字符的所有排列。你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     * <p>
     * 示例:
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     * <p>
     * 限制： 1 <= s 的长度 <= 8
     */
    public static List<String> res;
    public static boolean[] vis;

    private static String[] permutation(String s) {
        int len = s.length();
        res = new ArrayList<>();
        vis = new boolean[len];
        char[] chars = s.toCharArray();
        // 字符排序，将重复字符放一起
        Arrays.sort(chars);
        StringBuilder builder = new StringBuilder();

        // 回溯
        getCycleStr(chars, 0, len, builder);

        // 结果集返回
        String[] strArr = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            strArr[i] = res.get(i);
        }

        return strArr;
    }

    private static void getCycleStr(char[] chars, int i, int len, StringBuilder builder) {
        // 若已经填完，则放入结果集中
        if (i == len) {
            res.add(builder.toString());
            return;
        }

        for (int j = 0; j < len; j++) {
            // 如果当前位置已经被添加 或者 该字符已经在上一个位置被添加过，即重复字符，则不放数据
            boolean isContinue = vis[j] || (j > 0 && !vis[j - 1] && chars[j - 1] == chars[j]);
            if (isContinue) {
                continue;
            }

            // 当前位置可填，记录值并设置填置状态
            vis[j] = true;
            builder.append(chars[j]);
            // 继续填位
            getCycleStr(chars, i + 1, len, builder);
            // 触发回溯
            builder.deleteCharAt(builder.length() - 1);
            vis[j] = false;
        }
    }
}
