package learn.leecode.bitcount;

/**
 * 231
 *
 * @author zgq
 */
public class LeCode231 {
    public static void main(String[] args) {
        int n = 1;
        int n2 = 0;
        boolean result = isPowerOfTwo(n);
        boolean result2 = isPowerOfTwo(n2);
        System.out.println(result);
        System.out.println(result2);
    }

    /**
     * (231)2的幂
     * 示例 1:
     * 输入: 1 输出: true
     * 解释: 20 = 1
     * <p>
     * 示例 2:
     * 输入: 16 输出: true
     * 解释: 24 = 16
     * <p>
     * 示例 3:
     * 输入: 218 输出: false
     */
    private static boolean isPowerOfTwo(int n) {
        // n < = 0直接返回false
        /*
        解法1：n若是2的幂次方，那么其二进制一定就只有一个1
        n若是2的幂次方，则n-1的二进制一定就是在其1的位置为0 ，后面的位置都是1 -> 即 n & (n - 1) = 0
        若n-1是2的幂次方，则n的二进制在n-1的二进制1的位置一定有1且之后还有1，则n & (n - 1) != 0
        */
        return n > 0 && (n & (n - 1)) == 0;
        /*
        解法2：若n为2的幂次方，那么存在 n == (n & (-n))
        long x = (long) n;
        return n > 0 && (x & (-x)) == x;
        */
    }
}