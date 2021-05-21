package learn.leecode.binarysearch;

/**
 * 1482. 制作 m 束花所需的最少天数
 *
 * @author zgq
 */
public class LeCode1482 {
    public static void main(String[] args) {
        int m = 3;
        int k = 1;
        int[] bloomDay = {1, 10, 3, 10, 2};
        int result = minDays(bloomDay, m, k);
        System.out.println(result);
    }

    /**
     * (1482) 制作 m 束花所需的最少天数
     * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
     * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
     * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
     * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
     * <p>
     * 示例 1：
     * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
     * 输出：3
     * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
     * 现在需要制作 3 束花，每束只需要 1 朵。
     * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
     * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
     * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
     * <p>
     * 示例 2：
     * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
     * 输出：-1
     * 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
     * <p>
     * 示例 3：
     * 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
     * 输出：12
     * 解释：要制作 2 束花，每束需要 3 朵。
     * 花园在 7 天后和 12 天后的情况如下：
     * 7 天后：[x, x, x, x, _, x, x]
     * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
     * 12 天后：[x, x, x, x, x, x, x]
     * 显然，我们可以用不同的方式制作两束花。
     * <p>
     * 示例 4：
     * 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
     * 输出：1000000000
     * 解释：需要等 1000000000 天才能采到花来制作花束
     * <p>
     * 示例 5：
     * 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
     * 输出：9
     * <p>
     * 提示：
     * bloomDay.length == n
     * 1 <= n <= 10^5
     * 1 <= bloomDay[i] <= 10^9
     * 1 <= m <= 10^6
     * 1 <= k <= n
     */
    private static int minDays(int[] bloomDay, int m, int k) {
        // 校验花的总数是否足够，若不足直接返回
        if (m * k > bloomDay.length) {
            return -1;
        }

        // 采用二分计算天数
        // 获取初始边界
        int left = Integer.MAX_VALUE;
        int right = 0;
        for (int i : bloomDay) {
            left = Math.min(i, left);
            right = Math.max(i, right);
        }

        // 二分计算天数
        while (left < right) {
            int mod = left + ((right - left) >> 1);
            boolean isGetFlower = checkDay(mod, bloomDay, m, k);
            if (isGetFlower) {
                // 若当前天数足够，那么则将右边界左移
                right = mod;
            } else {
                // 若当前天数不足，那么则将左边界右移
                left = mod + 1;
            }
        }

        return left;
    }

    private static boolean checkDay(int mod, int[] bloomDay, int m, int k) {
        // 初始化某一花束的用花数量
        int flowers = 0;
        // 初始化可以制作的花束数量
        int quest = 0;
        // 遍历花，上限条件添加 制作花束数量大于要求数量，用于及时中止无用循环
        for (int i = 0; i < bloomDay.length && quest < m; i++) {
            if (bloomDay[i] <= mod) {
                // 若当前天数小于等于mod，表示改该天的花可以用来做花束
                flowers++;
                if (flowers == k) {
                    // 制作的花束加1
                    quest++;
                    // 重置新的花数量
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }

        // 制作的花束大于等于了要求的花束数量，表示该天可以完成花束的制作
        return quest >= m;
    }
}