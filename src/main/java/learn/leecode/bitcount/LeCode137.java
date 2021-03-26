package learn.leecode.bitcount;

/**
 * 137
 *
 * @author zgq
 */
public class LeCode137 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 2, 3, 3, 4, 3};
        int result = singleNumber(nums);
        System.out.println(result);
    }

    /**
     * (137)只出现一次的数字II
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现三次。找出那个只出现了一次的元素。
     * <p>
     * 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * <p>
     * 示例 1:
     * 输入: [2,2,2,1]  输出: 1
     * <p>
     * 示例 2:
     * 输入: [4,1,1,2,2,1,2]  输出: 4
     */
    private static int singleNumber(int[] nums) {
        /*
        位运算：
        a^a = 0; 0^b = b; a^b^a = b; a^a^b = b;
        通过 a^b只能检测出出现一次或者3次的数，
        故：变化计算方式，通过掩码0计算,初始x^temp1 = x;
        temp1 = ~temp2 & (x^temp1);
        temp2 = ~temp1 & (temp2^num);
        3次过后掩码恢复到原来的数据
        若，有一个数只出现一次，那么掩码temp1就是单独出现的那个数
         */
        int temp1 = 0;
        int temp2 = 0;
        for (int num : nums) {
            temp1 = ~temp2 & (temp1 ^ num);
            temp2 = ~temp1 & (temp2 ^ num);
        }
        // 3此过后
        return temp1;
    }
}