package learn.leecode.bitcount;

import java.util.Arrays;

/**
 * 260
 *
 * @author zgq
 */
public class LeCode260 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3, 4};
        int[] result = singleNumber(nums);
        System.out.println(Arrays.toString(result));
    }

    /**
     * (260)只出现一次的数字III
     * 给定一个非空整数数组，其中恰好有两个元素只出现一次，其余每个元素均出现两次。找出那个只出现了一次的那两个元素。
     * <p>
     * 示例 1:
     * 输入: [1,2,1,3,2,5]  输出: [3,5]
     * <p>
     * 说明：
     * 1.你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * 2.结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
     */
    private static int[] singleNumber(int[] nums) {
        // 通过位运算得到只出现一次的2个数的异或值
        // bitmask 会保留只出现一次的两个数字（x 和 y）之间的差异
        int bitmask = 0;
        for (int num : nums) {
            bitmask ^= num;
        }

        // 找到差异结果中的最右边一位1
        int diff = bitmask & (-bitmask);

        int x = 0;
        // 通过最右边一位1，来找到相应的只出现一次的数x,
        for (int num : nums) {
            // 不为0则表示该数存在最右边一位1，
            if ((num & diff) != 0) {
                // 不断循环，当x与num异或之后，只会剩下只出现一次，并且最右一边一位1的数
                x ^= num;
            }
        }

        // bitmask^x 就是另一位只出现一次的数
        return new int[]{x, bitmask^x};
    }
}