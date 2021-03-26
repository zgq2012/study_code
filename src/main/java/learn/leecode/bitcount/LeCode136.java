package learn.leecode.bitcount;

/**
 * 136
 *
 * @author zgq
 */
public class LeCode136 {
    public static void main(String[] args) {
        int[] nums = {1,1,2,2,3};
        int result = singleNumber(nums);
        System.out.println(result);
    }

    /**
     * (136)只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * <p>
     * 示例 1:
     * 输入: [2,2,1]  输出: 1
     *
     * 示例 2:
     * 输入: [4,1,2,1,2]  输出: 4
     */
    private static int singleNumber(int[] nums) {
        /*
        位运算：
        a^a = 0; 0^b = b; a^b^a = b; a^a^b = b
        故若一个数出现2次，被另一个数异或之后，结果一定是另一个数本身
         */
        int tem = nums[0];
        for (int i = 1; i < nums.length; i++) {
            tem = tem^nums[i];
        }
        return tem;
    }
}