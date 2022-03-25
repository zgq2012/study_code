package learn.leecode.arr;

/**
 * 287. 寻找重复数
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/24
 **/
public class LeCode287 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 3, 2, 2, 5, 6};
        int res = findDuplicate(nums);
        System.out.println("res = " + res);
    }

    /**
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
     * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
     * <p>
     * 示例 1：
     * 输入：nums = [1,3,4,2,2]
     * 输出：2
     * <p>
     * 示例 2：
     * 输入：nums = [3,1,3,4,2]
     * 输出：3
     * <p>
     * 提示：
     * 1 <= n <= 10^5
     * nums.length == n + 1
     * 1 <= nums[i] <= n
     * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
     * <p>
     * 进阶：
     * 如何证明 nums 中至少存在一个重复的数字?
     * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
     */
    private static int findDuplicate(int[] nums) {
        // 由数组转化为环形链表
        // {1, 3, 4, 2, 2}
        // 0 -> 1 -> 3 -> 2 -> 4 -> 2 -> 4 -> 2 ...
        // 若一个数据组中有一个数存在重复，那么使用快慢指针，一定可以走到重复的数据
        // {1, 3, 4, 3, 2, 2, 5, 6}
        // 0 -> 1 -> 3 -> 3 -> 3 -> 3 -> 3 ...
        // 若有多个数重复，那么只会在第一个重复数成环
        // 慢指针每次走一步
        int slow = nums[0];
        // 快指针每次走2步
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // 循环完毕，那么就找到了环，此时slow == fast; 且他们处于环中

        // 那么此时,此时需要做的是，知道环的入口，也就是重复的数据，
        // 通过环形链表Ⅱ可以知道，要找到环的入口，那么可以从链表头步开始遍历，也就是数组索引0开始走 -> pre
        // 并且slow继续前行，当 slow = pre 时，此时达到环入口
        int pre = 0;
        while (slow != pre) {
            pre = nums[pre];
            slow = nums[slow];
        }

        return pre;
    }
}
