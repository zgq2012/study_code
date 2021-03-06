package learn.leecode.bitcount;

/**
 * 191
 *
 * @author zgq
 */
public class LeCode191 {
    public static void main(String[] args) {
        // 521
        int n = 00000000000000000000000000001011;
        int result = hammingWeight(n);
        System.out.println(result);
    }

    /**
     * (191)二进制中1的个数
     * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
     * <p>
     * 示例 1：
     * 输入：00000000000000000000000000001011  输出：3
     * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
     * <p>
     * 示例 2：
     * 输入：00000000000000000000000010000000  输出：1
     * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
     * <p>
     * 示例 3：
     * 输入：11111111111111111111111111111101   输出：31
     * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
     *  
     * 提示：
     * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，
     * 并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
     * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
     */
    private static int hammingWeight(int n) {
        /*// 记录一的个数
        int result = 0;
        // n=0 时结束
        while (n != 0) {
            result++;
            // 位运算 n & (n - 1)，每进行一次就会消去一个1，直到都为0时 n = 0
            *//*
                      n = 00000000000000000000000000001011
                  n - 1 = 00000000000000000000000000001010
            n & (n - 1) = 00000000000000000000000000001010 -> n2 != 0 去掉一个1
                     n2 = 00000000000000000000000000001010
                 n2 - 1 = 00000000000000000000000000001001
          n2 & (n2 - 1) = 00000000000000000000000000001000 -> n3 != 0 去掉一个1
                     n3 = 00000000000000000000000000001000
                 n3 - 1 = 00000000000000000000000000000111
          n3 & (n3 - 1) = 00000000000000000000000000000000 -> n4 = 0 去掉一个1
            *//*
            n = n & (n - 1);
        }
        return result;*/

        // 通过
        return n == 0 ? 0 : hammingWeight(n & (n - 1)) + 1;
    }
}