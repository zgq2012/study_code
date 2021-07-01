package learn.leecode.numbercount;

/**
 * 7. 整数反转
 *
 * @author zgq
 */
public class LeCode07 {
    public static void main(String[] args) {
        int num = 432;
        int result = reverse(num);
        System.out.println("结果：" + result);
    }

    /**
     * (7)整数反转
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *  
     * 示例 1：
     * 输入：x = 123
     * 输出：321
     * <p>
     * 示例 2：
     * 输入：x = -123
     * 输出：-321
     * <p>
     * 示例 3：
     * 输入：x = 120
     * 输出：21
     * <p>
     * 示例 4：
     * 输入：x = 0
     * 输出：0
     *  
     * 提示：
     * -2^31 <= x <= 2^31 - 1
     */
    private static int reverse(int x) {
        int res = 0;

        // x 不为0则继续
        while (x != 0) {
            // 若结果值，比MAX_VALUE/10还大，表示res的结果超过int范围，同理比MIN_VALUE/10小，也超出范围
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) {
                return 0;
            }

            // 取x的模，即是取x的最后一位数
            int xMoTen = x % 10;
            // 令x = x除以10，表示x数据向右边移动一位
            x = x / 10;
            // res此时已经取到上一次循环中x的最后一位
            res = res * 10 + xMoTen;
        }

        return res;
    }
}