package learn.leecode.huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 46
 *
 * @author zgq
 */
public class LeCode46 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute(nums);
        result.forEach(System.out::println);
    }

    /**
     * (46)全排列
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     * 示例:
     * 输入: [1,2,3]
     * 输出:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     * 通过回溯的方式来解决，一次列出所有可能的情况，失败时回溯到上一版本，继续执行
     */
    private static List<List<Integer>> permute(int[] nums) {
        // 记录所用情况
        List<List<Integer>> result = new ArrayList<>();
        // 用于记录当前解决方式
        List<Integer> indexList = new ArrayList<>();
        for (int num : nums) {
            indexList.add(num);
        }

        int length = nums.length;
        // 回溯填值,0 表示起始索引
        backWay(length, indexList, result, 0);
        return result;
    }

    private static void backWay(int length, List<Integer> indexList, List<List<Integer>> result, int index) {
        if (index == length) {
            // 表示索引已经达到length长度，即数组的所有值都走过一遍,也是该方法的结束条件
            // 采用copy的方式记录，因为indexList会一直变，直到最后
            result.add(new ArrayList<>(indexList));
            return;
        }

        for (int i = index; i < length; i++) {
            // 以index为起始，交换index与i的值
            Collections.swap(indexList, index, i);
            // 依次递归，继续传递执行
            backWay(length, indexList, result, index + 1);
            // indexList固定index一次循环完之后，将其恢复原状
            Collections.swap(indexList, index, i);
        }
    }
}
