package learn.leecode.bitcount;

/**
 * 324
 *
 * @author zgq
 */
public class LeCode324 {
    public static void main(String[] args) {
        int n = 1;
        int n2 = 0;
        boolean result = isPowerOfFour(n);
        boolean result2 = isPowerOfFour(n2);
        System.out.println(result);
        System.out.println(result2);
    }

    /**
     * (324)4的幂
     * 示例 1:
     * 输入: 1 输出: true
     * <p>
     * 示例 2:
     * 输入: 16 输出: true
     * <p>
     * 示例 3:
     * 输入: 218 输出: false
     */
    private static boolean isPowerOfFour(int n) {
        /*
        位运算:
        1.4的幂次方一定是2的的幂次方 -> n 一定满足 n & (n - 1)
        2.n对3取模结果一定是1(即 n % 3 == 1)
            x = 2^2k % 3
        ->  x = (3+1)^k % 3
        ->  x = (3+1)*(3+1)^(k-1) % 3
        ->  x = (3*(3+1)^(k-1)+(3+1)^(k-1)) % 3
        ->  x = (3+1)^(k-1) % 3
        -> ...
        ->  x = (3+1)^1 % 3
        ->  x = 1
        */
        return ((n & (n - 1)) == 0) && (n % 3 == 1);
        /*
        运算法:
        4的幂次方:n = 4^i -> i = log4(n) -> i = (1/2)log2(n) -> i = (1/2)log10(n)/log10(2)
        结果i一定是整数,那么log10(n)/log10(2)一定是偶数 -> (log10(n)/log10(2)) % 2 == 0
        -> return n > 0 && (Math.log10(n)/Math.log10(2)) % 2 == 0;
        */
    }
}