package learn.leecode.other;

/**
 * 633. 平方数之和
 *
 * @author zgq
 */
public class LeCode633 {

    public static void main(String[] args) {
//        int c = 5;
//        int c = 4;
        int c = 999999999;
        boolean res = judgeSquareSum(c);
        System.out.println(res);
    }

    /**
     * (633)平方数之和
     * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
     * <p>
     * 示例 1：
     * 输入：c = 5 输出：true
     * 解释：1 * 1 + 2 * 2 = 5
     * <p>
     * 示例 2：
     * 输入：c = 3 输出：false
     * <p>
     * 示例 3：
     * 输入：c = 4 输出：true
     * <p>
     * 示例 4：
     * 输入：c = 2 输出：true
     * <p>
     * 示例 5：
     * 输入：c = 1 输出：true
     * <p>
     * 提示：
     * 0 <= c <= 2^31 - 1
     */
    public static boolean judgeSquareSum(int c) {
        // method1 : 取上界，使用开方为整数的原理
//       return method1(c);
        // method2 : 双指针
//        return method2(c);
        // method3 : 数学方法，费马平方和定理
        return method3(c);
    }

    private static boolean method3(int c) {
        // 一个非负整数 c 如果能够表示为两个整数的平方和，当且仅当 c 的所有形如 4k+3 的质因子的幂均为偶数。
        for (int base = 2; base * base <= c; base++) {
            // 如果不是因子，枚举下一个
            if (c % base != 0) {
                continue;
            }

            // 计算 base 的幂
            int exp = 0;
            while (c % base == 0) {
                c /= base;
                exp++;
            }

            // 根据 Sum of two squares theorem 验证
            if (base % 4 == 3 && exp % 2 != 0) {
                return false;
            }
        }

        // 例如 11 这样的用例，由于上面的 for 循环里 base * base <= c ，base == 11 的时候不会进入循环体
        // 因此在退出循环以后需要再做一次判断
        return c % 4 != 3;
    }

    private static boolean method2(int c) {
        // 由题可以得到 0<= a与b <= c的开方向上取整;
        // 那么让a从0开始，b从 c的开方开始向中间靠拢，若当 a=b 时都没有解，则表示无解；
        int a = 0;
        int b = (int) Math.ceil(Math.sqrt(c));
        while (a <= b) {
            // 这里需要转化为long，因为 sum 可能会溢出
            int sum = a * a + b * b;
            if (sum < c) {
                // 表示值小了，需要左边界收拢
                a++;
            } else if (sum == c) {
                // 表示得到正解
                return true;
            } else {
                // 表示值大了，右边界需要收拢
                b++;
            }
        }
        return false;
    }

    private static boolean method1(int c) {
        // 用勾股定理， a^2 + b^2 = c^2 的原理，
        // 获取该数的开方向上取整，
        int sqrt = (int) Math.ceil(Math.sqrt(c));
        System.out.println(sqrt);
        // 遍历,x且  c - a^2 开根号一定是一个整数
        for (int a = 0; a <= sqrt; a++) {
            double b = Math.sqrt(c - (a * a));
            if (b - Math.ceil(b) == 0) {
                // 表示b存在小数部分；
                return true;
            }
        }

        return false;
    }
}
