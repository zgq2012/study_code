package learn.leecode.other;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * 1011. 在 D 天内送达包裹的能力
 *
 * @author zgq
 */
public class LeCode1011 {

    public static void main(String[] args) {
//        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        int d = 5;
//        int[] weights = {3,2,2,4,1,4};
//        int d = 3;
        int[] weights = {1, 2, 3, 1, 1};
        int d = 4;
        int res = shipWithinDays(weights, d);
        System.out.println(res);
    }

    /**
     * (1011)在 D 天内送达包裹的能力
     * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
     * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。
     * 我们装载的重量不会超过船的最大运载重量。返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
     * <p>
     * 示例 1：
     * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5   输出：15
     * 解释：
     * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
     * 第 1 天：1, 2, 3, 4, 5
     * 第 2 天：6, 7
     * 第 3 天：8
     * 第 4 天：9
     * 第 5 天：10
     * <p>
     * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
     * <p>
     * 示例 2：
     * 输入：weights = [3,2,2,4,1,4], D = 3    输出：6
     * 解释：
     * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
     * 第 1 天：3, 2
     * 第 2 天：2, 4
     * 第 3 天：1, 4
     * <p>
     * 示例 3：
     * 输入：weights = [1,2,3,1,1], D = 4      输出：3
     * 解释：
     * 第 1 天：1
     * 第 2 天：2
     * 第 3 天：3
     * 第 4 天：1, 1
     *  
     * 提示：
     * 1 <= D <= weights.length <= 50000
     * 1 <= weights[i] <= 500
     */
    public static int shipWithinDays(int[] weights, int d) {
        // 方法1: 从包裹中最大值开始递增校验，若满足天数则直接返回；需要校验很多数据
//        return typeOne(weights, d);
        // 方法2：利用二分的方式来校验，左边界->包裹最大值，右边界->所有包裹总和
        return typeTwo(weights, d);
    }

    private static int typeTwo(int[] weights, int d) {
        // 起始左边界，包裹最大值
        OptionalInt max = Arrays.stream(weights).max();
        int left = max.isPresent() ? max.getAsInt() : 1;
        // 起始右边界，包裹总和
        int sum = Arrays.stream(weights).sum();
        // 二分获取天数
        while (left < sum) {
            int mid = (left + sum) >> 1;
            // 获取该承载重量所需的天数
            int maxDay = getMaxDay(weights, mid);
            if (maxDay <= d) {
                // 若所需天数小于等于给出的天数，表示该承载重量需要变小再尝试
                sum = mid;
            } else {
                // 表示该承载重量太小，不足以在d天内完成运输，需要加大
                left = mid + 1;
            }
        }
        return left;
    }

    private static int getMaxDay(int[] weights, int d) {
        // 设为1时因为，最后一次判定需要等结束之后，但是它一定会消耗一天，
        int max = 1;
        int pre = 0;

        for (int weight : weights) {
            int temp = pre + weight;
            if (temp > d) {
                max++;
                pre = weight;
            } else {
                pre = temp;
            }
        }
        return max;
    }

    private static int typeOne(int[] weights, int d) {
        // 先排序
        int max = weights[0];
        for (int i = 1; i < weights.length; i++) {
            max = Math.max(max, weights[i]);
        }

        for (int min = max; true; min++) {
            int maxDay = getMaxDay(weights, min);
            if (maxDay <= d) {
                return min;
            }
        }
    }
}
