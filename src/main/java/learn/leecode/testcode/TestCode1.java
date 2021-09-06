package learn.leecode.testcode;

import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateDifferentiableSolver;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.math.BigDecimal;

/**
 * testCode1
 *
 * @author zgq
 */
public class TestCode1 {
    public static void main(String[] args) {
//        String equation = "能力值 / 12 - 24 = 0";
        String equation = "22 + 能力值 / 12 + 25 - 12 = 0";
        String formula = equation.split("=")[0];
        double manPower = 12.0;
        System.out.println("输入formula:" + formula + "manpower：" + manPower);
        double res = backCalculate(formula, manPower);
        System.out.println("res = " + res);
    }

    private static double backCalculate(String formula, double manPower) {
        String fix = "能力值";
        formula = formula + "-" + manPower;
        System.out.println("计算公式 : " + formula);
        String[] split = formula.split("\\+");
        String fixStr = "";
        String allStr = "";
        for (String s : split) {
            if (!s.contains(fix)) {
                continue;
            }

            if (s.contains("-")) {
                String[] split2 = s.split("-");
                for (int j = 0; j < split2.length; j++) {
                    if (split2[j].contains(fix)) {
                        allStr = split2[j];
                        fixStr = (j == 0 ? "" : "-") + split2[j].replaceAll(fix, "1.0");
                        break;
                    }
                }
            } else {
                allStr = s;
                fixStr = s.replaceAll(fix, "1.0");
            }
            break;
        }

        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");

            double modulus = getRes(engine.eval(fixStr));
            double constant = getRes(engine.eval(formula.replace(allStr, "0")));
            double[] d = {constant, modulus};

            UnivariateDifferentiableFunction f = new PolynomialFunction(d);
            UnivariateDifferentiableSolver solver = new NewtonRaphsonSolver();
            return solver.solve(10, f, 6);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("计算错误！");
            return 0.0;
        }
    }

    private static double getRes(Object eval) {
        return eval instanceof Double ? (double) eval : BigDecimal.valueOf((Integer) eval).doubleValue();
    }
}
