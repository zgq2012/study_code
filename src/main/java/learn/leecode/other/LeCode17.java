package learn.leecode.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 *
 * @author zgq
 */
public class LeCode17 {

    public static void main(String[] args) {
        String digits = "234";
        List<String> res = letterCombinations(digits);
        System.out.println("res = " + res);
    }

    /**
     * (17) 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 示例 1：
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * <p>
     * 示例 2：
     * 输入：digits = ""
     * 输出：[]
     * <p>
     * 示例 3：
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     * <p>
     * 提示：
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     */
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        // 以数组的形式存储映射,比map更节约空间
        String[] numToAz = new String[10];
        numToAz[2] = "abc";
        numToAz[3] = "def";
        numToAz[4] = "ghi";
        numToAz[5] = "jkl";
        numToAz[6] = "mno";
        numToAz[7] = "pqrs";
        numToAz[8] = "tuv";
        numToAz[9] = "wxyz";

        // 记录结果值
        List<String> res = new ArrayList<>();
        // 获取结果
        getLetterStr(res, numToAz, digits, 0, new StringBuilder());

        return res;
    }

    private static void getLetterStr(List<String> res, String[] numToAz, String digits,
                                     int index, StringBuilder builder) {
        // 如果index 与 字符串的长度相等了，表示该轮所有字符都已经遍历完了,存储数据返回
        if (index == digits.length()) {
            res.add(builder.toString());
            return;
        }

        // 获取字符串index对应的数字
        // 获取对应数字下的素有字母
        String letters = numToAz[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            // 拼接上该数字对应的各个字符 "a" -> "ae"
            builder.append(letters.charAt(i));

            // 递归继续下一个字符数字
            getLetterStr(res, numToAz, digits, index + 1, builder);

            // 完成递归后，将最后一个字符回滚，因为要进行下一个数字对应的另一个字符的拼接,
            /*
             * 例：digits = "234", 循环中的某一环，此时返回的 builder = "ae",
             * 下一次要进行的循环是 "af" 字符拼接4对应的字母, 需要 "ae" -> "a" -> "af",
             */
            builder.deleteCharAt(index);
        }
    }
}
