package learn.leecode.huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/2/25
 **/
public class LeCode78 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }

    private static List<List<Integer>> res = new ArrayList<>();
    private static List<Integer> temp = new ArrayList<>();

    private static List<List<Integer>> subsets(int[] nums) {
        // 回溯操作
        res.clear();
        temp.clear();

        subset(nums, 0);

        return res;
    }

    private static void subset(int[] nums, int index) {
        // index 达到要求时，把记录存起来
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        // 记录当前值
        temp.add(nums[index]);
        subset(nums, index + 1);
        // 回滚数据
        temp.remove(temp.size() - 1);
        // 跳过当前index, 进行下一个 index+1
        subset(nums, index + 1);
    }
}
