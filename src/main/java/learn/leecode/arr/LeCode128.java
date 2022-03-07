package learn.leecode.arr;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/3
 **/
public class LeCode128 {
    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 2, 3};
        int i = longestConsecutive(nums);
        System.out.println("i = " + i);
    }

    private static int longestConsecutive(int[] nums) {
        // 建立hash数据
        Set<Integer> numSet  = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // 定义最长值
        int longest = 0;
        for (int num : nums) {
            if(!numSet.contains(num - 1)){
                //  若set不包含num-1，表示该数据是最小的一个，从这里开始查找其是否有连续数据
                int cur = num;
                // 定义当前数的最长长度
                int curLongest = 1;
                while (numSet.contains(cur + 1)){
                    // 若set中包含cur+1表示当前数长度+1
                    curLongest++;
                    cur++;
                }
                // 比较获取最大值
                longest = Math.max(longest, curLongest);
                // 若最大长度已经大于数组的一半，那么该数一定是当前数组最长的连续数
                if(longest > nums.length / 2){
                    return longest;
                }
            }
        }

        return longest;
    }
}
