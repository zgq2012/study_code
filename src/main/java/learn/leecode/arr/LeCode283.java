package learn.leecode.arr;

import java.util.Arrays;

/**
 * 283. 移动零
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/24
 **/
public class LeCode283 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12, 24, 0, 32, 56};
        System.out.println(Arrays.toString(nums));
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void moveZeroes(int[] nums) {
        int zeroIndex = -1;
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            // 记录第一个0的位置
            if (nums[i] == 0) {
                if (zeroIndex == -1) {
                    zeroIndex = i;
                }
                continue;
            }
            // 前面有0时交换
            if (zeroIndex != -1) {
                nums[zeroIndex] = nums[i];
                nums[i] = 0;
                zeroIndex++;
            }
        }
    }
}
