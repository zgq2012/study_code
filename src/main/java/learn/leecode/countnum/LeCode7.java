package learn.leecode.countnum;

/**
 * 7. 整数反转
 *
 * @author zgq
 */
public class LeCode7 {
    public static void main(String[] args) {
        int[] nums = {1,1,2,2,3};
        int result = singleNumber(nums);
        System.out.println(result);
    }

    /**
     * (7) 整数反转
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *
     * 示例 1：
     * 输入：x = 123
     * 输出：321
     *
     * 示例 2：
     * 输入：x = -123
     * 输出：-321
     *
     * 示例 3：
     * 输入：x = 120
     * 输出：21
     *
     * 示例 4：
     * 输入：x = 0
     * 输出：0
     *  
     * 提示：
     * -231 <= x <= 231 - 1
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