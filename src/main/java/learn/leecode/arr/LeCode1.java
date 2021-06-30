package learn.leecode.arr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *
 * @author zgq
 */
public class LeCode1 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int k = 9;
        int[] ints = twoSum(nums, k);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * （1）两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     *
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     *
     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     *
     * 示例 3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     *
     * 提示：
     * 2 <= nums.length <= 10^3
     * -10^9 <= nums[i] <= 10^9
     * -10^9 <= target <= 10^9
     * 只会存在一个有效答案
     */
    private static int[] twoSum(int[] nums, int target) {
        // 使用map映射走过的值的另一半，
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        int[] res = new int[2];
        for(int i = 0; i < nums.length; i++){
            Integer index = map.get(nums[i]);
            // 在map中寻找是否右本身值已经被存储了
            if(index == null){
                // 若失败，则无配对值，存进新的值
                Integer key = target - nums[i];
                map.put(key, i);
            }else{
                // 若存储则表示配对成功
                res[0] = index;
                res[1] = i;
                return res;
            }
        }
        return res;
    }
}
