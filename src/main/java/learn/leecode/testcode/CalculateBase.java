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
        String formula = " 3 * 1 / 3 + 1.3 - 4.0";
        double result = calculateValue(formula);
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
        String[] divs = s.split("-");
        double divRes = calculateValue(divs[0]);
        for (int i = 1; i < divs.length; i++) {
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
                throw new Exception("算式中包含了0作为除数，请保证算式的正确性！");
            }
            res /= v;
        }
        return res;
    }
}
