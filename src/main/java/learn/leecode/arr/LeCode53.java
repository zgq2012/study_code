package learn.leecode.arr;

/**
 * 53
 *
 * @author zgq
 */
public class LeCode53 {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = maxSubArray(nums);
        System.out.println(res);
    }

    /**
     * 最大子序和（53）
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * <p>
     * 进阶:
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     */
    private static int maxSubArray(int[] nums) {
        // Solve1:采用动态规划的思想
        /*
        设第i个位置时其子序和f(i),
        则，f(i)与f(i-1)以及nums[i]的和有关，若和减小了，表示nums[i]为负数，
        可知 -> f(i) = max(f(i-1)+nums[i], nums[i]);
        时间->空间：O(n)->O(n)
         */
        // return solveOne(nums);
        /*
        优化空间dp[], 使用常量来滚动计算最大值;
        时间->空间：O(n)->O(1)
        */
        // return solveTwo(nums);
        // Solve2：采用分治策略
        /*
        分治思想：将整个数组分为若干部分，每一个分延续整体的计算方式；
        分解 -> 解决 -> 合并
        时间->空间：O(n)->O(log(n))
        -> 特殊的优势：在指定区间的时候不需要改代码，且复杂度不改变，
        */
        return solveThree(nums);
    }

    public static class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            // [l,r] 的区间和
            this.iSum = iSum;
            // 表示 [l,r] 内以 l 为左端点的最大子段和
            this.lSum = lSum;
            // 表示 [l,r] 内以 r 为右端点的最大子段和
            this.rSum = rSum;
            // [l,r] 内的最大子段和
            this.mSum = mSum;
        }
    }

    private static Status mergeMax(Status leftMax, Status rightMax) {
        // 左右2段总的区间和
        int iSum = leftMax.iSum + rightMax.iSum;
        // 左子区间的最大值 = Max(左端点的最大值，左边的区间和与右区间的左端点的和)
        int lSum = Math.max(leftMax.lSum, leftMax.iSum + rightMax.lSum);
        // 右子区间的最大值 = Max(右端点的最大值，右边的区间和与左区间的右端点的和)
        int rSum = Math.max(rightMax.rSum, rightMax.iSum + leftMax.rSum);
        // 最大字段和 = Max(左区间的的最大字段和，右区间的最大字段和，含中点的左区间右子段与右区间左子段的和)
        int mSum = Math.max(Math.max(leftMax.mSum, rightMax.mSum), leftMax.rSum + rightMax.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    private static int solveThree(int[] nums) {
        // 中点分界，左右最大，中间包含叠加，3分比较
        return getMax(nums, 0, nums.length - 1).mSum;
    }

    private static Status getMax(int[] nums, int left, int right) {
        if (left == right) {
            // 递归终结者
            return new Status(nums[left], nums[left], nums[left], nums[left]);
        }
        // 中值
        int mid = left + (right - left) / 2;
        // 分段递归
        Status leftMax = getMax(nums, left, mid);
        Status rightMax = getMax(nums, mid + 1, right);
        // 合并
        return mergeMax(leftMax, rightMax);
    }

    private static int solveTwo(int[] nums) {
        int pre = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 计算当前i位置的最大和并记录
            pre = Math.max(pre + nums[i], nums[i]);
            // 比较上一个最大值
            max = Math.max(max, pre);
        }
        return max;
    }

    private static int solveOne(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }

        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
