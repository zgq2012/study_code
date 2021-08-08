package learn.leecode.testcode;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 计算器基础算法
 *
 * @author zgq
 * @version v1.1.0
 * @since 2021-08-07
 */
public class CalculateBase {

    public static void main(String[] args) throws Exception {
        String formula = "(( (- 3 * 1 / 3))) + ((1.3 - 4.0) + 1)";
        double result = calculateValue(formula.trim());
        double res = BigDecimal.valueOf(result).setScale(2, RoundingMode.CEILING).doubleValue();
        System.out.println("res = " + res);
    }

    /**
     * 输入一个字符公式，其字符仅包含数字，'.'，加减乘除符号'+ - * /' 以及 英文小括号，
     * <p>
     * 要求输出该公式转换为数学形态时的结果值（保留2位小数，向上四舍五入），若无法得到结果值，返回null
     * 例1：
     * 输入： 2 * 2 + 5 - 4 + 2 / 3
     * 输出：5.67
     */
    private static double calculateValue(String formula) throws Exception {
        // 存在小括号时，（）+（（））*（）
        if (formula.contains("(")) {
            formula = removeK(formula);
        }

        // 暂未实现小括号，逻辑同
        if (formula.contains("+")) {
            return addValue(formula);
        }

        if (formula.contains("-")) {
            return div(formula);
        }

        if (formula.contains("*")) {
            return rexValue(formula);
        }

        if (formula.contains("/")) {
            return chuValue(formula);
        }

        return Double.parseDouble(formula);
    }

    private static String removeK(String formula) throws Exception {
        int lastIndexLeftK = formula.lastIndexOf('(');
        int lastIndexRightK = -1;
        for (int i = lastIndexLeftK + 1; i < formula.length(); i++) {
            // 找到一个右括号
            if (formula.charAt(i) == ')') {
                lastIndexRightK = i;
                break;
            }
        }
        // 截取括号里面的数据，得到计算结果然后回填，
        String temp = formula.substring(lastIndexLeftK + 1, lastIndexRightK);
        double v = calculateValue(temp);
        String pre = isFirstIndex(lastIndexLeftK) ? "" : formula.substring(0, lastIndexLeftK);
        String last = isLastIndex(formula, lastIndexRightK) ? "" : formula.substring(lastIndexRightK + 1);
        String res = pre + v + last;
        return res.contains("(") ? removeK(res) : res;
    }

    private static boolean isFirstIndex(int lastIndexLeftK) {
        return lastIndexLeftK == 0;
    }

    private static boolean isLastIndex(String formula, int lastIndexRightK) {
        return lastIndexRightK == formula.length() - 1;
    }

    /**
     * 加法
     *
     * @param formula 算式
     * @return double
     * @throws Exception 除数异常
     */
    private static double addValue(String formula) throws Exception {
        String[] split = formula.split("\\+");
        double res = 0.0;
        for (String s : split) {
            res += calculateValue(s);
        }

        return res;
    }

    /**
     * 减法
     *
     * @param s 算式
     * @return double
     * @throws Exception 除数异常
     */
    private static double div(String s) throws Exception {
        String[] divs = s.trim().split("-");
        int first = 0;
        if ("".equals(divs[first])) {
            // 表示为 '-' 号起步,如 '-2.5'
            first = first + 1;
        }

        double divRes = first != 0 ? 0.0 - calculateValue(divs[first]) : calculateValue(divs[first]);
        for (int i = first + 1; i < divs.length; i++) {
            divRes -= calculateValue(divs[i]);
        }
        return divRes;
    }

    /**
     * 乘法
     *
     * @param formula 算式
     * @return double
     * @throws Exception 除数异常
     */
    private static double rexValue(String formula) throws Exception {
        String[] arr1 = formula.split("\\*");
        double res = 1.0;
        for (String s : arr1) {
            res *= calculateValue(s);
        }
        return res;
    }

    /**
     * 除法
     *
     * @param formula 算式
     * @return double
     * @throws Exception 除数异常
     */
    private static double chuValue(String formula) throws Exception {
        String[] arr1 = formula.split("/");
        double res = calculateValue(arr1[0]);
        for (int i = 1; i < arr1.length; i++) {
            double v = Double.parseDouble(arr1[i]);
            if (v == 0.0) {
                // 0不可作为除数
                throw new Exception("算式中存在被除数为0的情况，请保证算式的正确性！");
            }
            res /= v;
        }
        return res;
    }
}
