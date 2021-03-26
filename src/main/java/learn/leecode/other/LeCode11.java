package learn.leecode.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 11
 *
 * @author zgq
 */
public class LeCode11 {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int[] height = {1, 2, 4, 3};
        int res = maxArea(height);
        System.out.println(res);
    }

    /**
     * (11)盛最多水的容器
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器。
     * 输入：[1,8,6,2,5,4,8,3,7]   输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * <p>
     * 示例 2：
     * 输入：height = [1,1]    输出：1
     * <p>
     * 示例 3：
     * 输入：height = [4,3,2,1,4]  输出：16
     * <p>
     * 示例 4：
     * 输入：height = [1,2,1]  输出：2
     * <p>
     * 提示：
     * n = height.length    2 <= n <= 3 * 104   0 <= height[i] <= 3 * 104
     */
    public static int maxArea(int[] height) {
        // 1.暴力解法，枚举所有的匹配方法，比较得出最大值
        /*
        2.双指针 -> 要使2线段之间装的水最大，那么取决于2线段最短的一端，
          从2端同时向中间靠拢，若下一位置长于当前位，则移动，反之移动另一指针
        */
        int j = height.length - 1;
        int i = 0;
        int max = 0;
        while (i <= j) {
            // 精简写法，i++（i在参与公式计算之后，立马运算）
            max = height[i] <= height[j] ?
                    Math.max(max, (j - i) * height[i++]):
                    Math.max(max, (j - i) * height[j--]);
            // 计算当前位置和之前的比的最大值
//            max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            // 若左指针的值大于了右指针的值，则将右指针减一，反之左指针加一
//            if (height[i] <= height[j]) {
//                i++;
//            } else {
//                j--;
//            }
        }

        return max;
    }
}
