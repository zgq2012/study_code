package learn.leecode.bitcount;

/**
 * 326
 *
 * @author zgq
 */
public class LeCode326 {
    public static void main(String[] args) {
        int n = 19684;
        boolean result = isPowerOfThree(n);
        System.out.println(result);
    }

    /**
     * (326)3的幂
     * 示例 1:
     * 输入: 27 输出: true
     * <p>
     * 示例 2:
     * 输入: 16 输出: false
     * <p>
     * 示例 3:
     * 输入: 9 输出: true
     */
    private static boolean isPowerOfThree(int n) {
        /*
        取巧法:
        int位数下，最大的3的幂次方是:double pow = Math.pow(3, 19) = 3^19 = 1162261467,故可取巧简化
        return n > 0 && (1162261467 % n) == 0;
        */
        /*
        运算法:
        n = 3^i -> i = log3(n) -> 换底公式 i = log10(n) / log10(3)
        本身算式无问题,但是计算器精度存在损失,需要用epsilon(e普瑟罗)来转换
        故:return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
         */
        /*
         常规解题
         */
        if (n <= 0) {
            return false;
        }
        // n % 3 != 0 表示不是3的倍数,有n/3一定是3的倍数,循环直至 n = 1结束
        while (true) {
            if (n == 1) {
                return true;
            }
            if (n % 3 != 0) {
                return false;
            }
            n = n / 3;
        }
    }
}