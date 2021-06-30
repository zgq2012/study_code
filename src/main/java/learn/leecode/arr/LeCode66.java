package learn.leecode.arr;

import java.util.Arrays;

/**
 * 66
 *
 * @author zgq
 */
public class LeCode66 {

    public static void main(String[] args) {
        addOne();
    }

    /**
     * 加一（66）
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头
     */
    private static void addOne() {
        int[] nums = {9, 9, 9};
        for (int i = nums.length - 1; i >= 0; i--) {
            // 尾部执行加一
            nums[i]++;
            // 利用模巧妙赋值
            nums[i] = nums[i] % 10;
            // 判断模是否为0，不为0表示不需要进位，直接return, 为0表示进位，进行下一位加一，依次循环
            if (nums[i] != 0) {
                System.out.println("nums :" + Arrays.toString(nums));
                return;
            }
        }
        // 若循环完了，还是需要加一，则表示该数为 99,999,9999...类型，直接新起数组
        nums = new int[nums.length + 1];
        // 首位复制，其余为默认值
        nums[0] = 1;
        System.out.println("nums :" + Arrays.toString(nums));
    }
}
