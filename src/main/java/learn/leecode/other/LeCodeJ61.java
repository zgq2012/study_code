package learn.leecode.other;

import java.util.Arrays;

/**
 * J61
 *
 * @author zgq
 */
public class LeCodeJ61 {

    public static void main(String[] args) {
//        int[] nums = {1, 8, 6, 2, 5};
//        int[] nums = {1, 2, 4, 3, 0};
//        int[] nums = {0, 0, 1, 2, 5};
        int[] nums = {11, 0, 9, 0, 0};
        boolean res = isStraight(nums);
        System.out.println(res);
    }

    /**
     * (J61)扑克牌中的顺子
     * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2～10为数字本身，A为1，J为11，Q为12，K为13，
     * 而大、小王为 0 ，可以看成任意数字。
     * A 不能视为 14。
     * <p>
     * 示例 1:
     * 输入: [1,2,3,4,5]   输出: True
     * <p>
     * 示例 2:
     * 输入: [0,0,1,2,5]   输出: True
     *  
     * 限制：数组长度为 5   数组的数取值为 [0, 13] .
     */
    public static boolean isStraight(int[] nums) {
        // 先排序，倒序排列的时需要数组定义为封装类型才好用comparable
        Arrays.sort(nums);
        // 判定max - min < 5 ? true : false;0除外，
        // 同时判定 是否重复 ? true : false;0除外，
        // return 条件1 && 条件2;
        int min = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                min = nums[i + 1];
                break;
            }
            if (nums[i] == nums[i + 1]) {
                return false;
            }
        }
        min = min == 0 ? nums[0] : min;
        return nums[nums.length - 1] - min < 5;
    }
}
