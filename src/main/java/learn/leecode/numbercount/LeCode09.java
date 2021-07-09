package learn.leecode.numbercount;

/**
 * 9. 回文数
 *
 * @author zgq
 */
public class LeCode09 {
    public static void main(String[] args) {
        int num = 121;
        boolean result = isPalindrome(num);
        System.out.println("结果：" + result);
    }

    /**
     * (9) 回文数
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     * <p>
     * 示例 1：
     * 输入：x = 121
     * 输出：true
     * <p>
     * 示例 2：
     * 输入：x = -121
     * 输出：false
     * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * <p>
     * 示例 3：
     * 输入：x = 10
     * 输出：false
     * 解释：从右向左读, 为 01 。因此它不是一个回文数。
     * <p>
     * 示例 4：
     * 输入：x = -101
     * 输出：false
     *  
     * 提示：
     * -2^31 <= x <= 2^31 - 1
     * 进阶：你能不将整数转为字符串来解决这个问题吗？
     */
    private static boolean isPalindrome(int x) {
        // 官方解法：反转数的后一半比较
        boolean res = solveByOffice(x);
        System.out.println("官方解法：" + res);

        // 负数不可能为回文，10的倍数的值不可能为回文
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }

        // 0-9都看作是回文
        if (x < 10) {
            return true;
        }

        // 获取x的最高位数
        int maxBit = 1;
        int temp = x;
        while (temp > 0) {
            temp = temp / 10;
            if (temp > 0) {
                maxBit++;
            }
        }

        int left = 1;
        int right = maxBit;
        while (left < right) {
            // 获取left与right位所在的num
            if (getBitNum(x, left) != getBitNum(x, right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    private static boolean solveByOffice(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static int getBitNum(int x, int bit) {
        int temp = 1;
        for (int i = 0; i < bit - 1; i++) {
            temp *= 10;
        }

        return (x / temp) % 10;
    }
}