package learn.leecode.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 679
 *
 * @author zgq
 */
public class LeCode679 {

    public static void main(String[] args) {
        int[] nums = {4, 1, 8, 7};
//        int[] nums = {1, 1, 1, 1};
//        int[] nums = {1, 2, 1, 2};
        boolean res = judgePoint24(nums);
        System.out.println(res);
    }

    /**
     * (679)24点游戏
     * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
     * <p>
     * 示例 1:
     * 输入: [4, 1, 8, 7] 输出: True
     * 解释: (8-4) * (7-1) = 24
     * <p>
     * 示例 2:
     * 输入: [1, 2, 1, 2] 输出: False
     * <p>
     * 注意:
     * 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
     * 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。
     * 例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
     * 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
     */
    public static boolean judgePoint24(int[] nums) {
        /*
        采用回溯套娃的方式来解决（回溯就是一种暴力解法，穷举的形式，再一步步的优化的结果）
         */
        // int数组转换为double数组，因为涉及乘除运算，1.精度存在损失，2.除法非整除
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        // 开始套娃
        return isCanEqual(list);
    }

    private static boolean isCanEqual(List<Double> list) {
        // 小于1直接结束套娃
        int len = list.size();
        if (len < 1) {
            return false;
        }
        // 长度为1时直接判断结果值，这时已经是最后的值了，判断结束套娃
        if (len == 1) {
            return Math.abs(list.get(0) - 24) < 1e-6;
        }
        // 先取2个数来进行计算，故2层嵌套
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                // 相同的数不能取到
                if (i != j) {
                    // 采用新的数组来添加剩余的数，以及选中的2个数的计算结果
                    List<Double> nextList = new ArrayList<>();
                    for (int n = 0; n < list.size(); n++) {
                        // 非i,非j再存储
                        if (n != i && n != j) {
                            nextList.add(list.get(n));
                        }
                    }
                    // 开始遍历运算符进行计算 ,+,*,-,/
                    for (int k = 0; k < 4; k++) {
                        // 加法和乘法运算时，由于i和j时重复遍历，故i和j的值会交替出现，但是其运算结果不变，可以直接排除
                        if (k < 2 && i > j) {
                            continue;
                        }
                        double res;
                        if (k == 0) {
                            // + 算
                            res = list.get(i) + list.get(j);
                        } else if (k == 1) {
                            res = list.get(i) * list.get(j);
                        } else if (k == 2) {
                            res = list.get(i) - list.get(j);
                        } else {
                            // 除法运算时，被除数为0直接跳过
                            if (Math.abs(list.get(j)) < 1e-6) {
                                continue;
                            } else {
                                res = list.get(i) / list.get(j);
                            }
                        }
                        nextList.add(res);
                        // 用新的集合继续
                        if(isCanEqual(nextList)){
                            // 若成功直接返回true
                            return true;
                        }
                        // 若有失败则开始回溯
                        nextList.remove(nextList.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}
