package learn.leecode.binarysearch;

import java.util.Arrays;

/**
 * 475
 *
 * @author zgq
 */
public class LeCode475 {
    public static void main(String[] args) {
//        int[] houses = {1, 2, 3, 4};
        int[] houses = {10};
//        int[] houses = {282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923};
//        int[] heaters = {1, 3};
//        int[] heaters = {2};
//        int[] heaters = {823564440, 115438165, 784484492, 74243042, 114807987,
//                137522503, 441282327, 16531729, 823378840, 143542612};
        int[] heaters = {1};
        int result = findRadius(houses, heaters);
        System.out.println(result);
    }


    /**
     * (475)供暖器
     * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
     * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
     * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
     * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
     * <p>
     * 示例 1:
     * 输入: houses = [1,2,3], heaters = [2]  输出: 1
     * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
     * <p>
     * 示例 2:
     * 输入: houses = [1,2,3,4], heaters = [1,4]  输出: 1
     * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
     * <p>
     * 示例 3：
     * 输入：houses = [1,5], heaters = [2] 输出：3
     * <p>
     * 提示：
     * 1 <= houses.length, heaters.length <= 3 * 104,  1 <= houses[i], heaters[i] <= 109
     */
    private static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        // 遍历房屋，找到离房子最近的供暖器
        // 设置最小加热半径
        int minRad = 0;
        for (int house : houses) {
            // 寻找离房子最近的供暖器的距离
            int rad = findNearRoad(house, heaters);
            // 比较留下最大值
            minRad = Math.max(rad, minRad);
            System.out.println("house:" + house + ",rad:" + minRad);
        }
        return minRad;
    }

    private static int findNearRoad(int house, int[] heaters) {
        int left = 0;
        int right = heaters.length;
        // 二分法找大于等于house的第一个值
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (house <= heaters[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // right是否减小到左边界时，否，则计算其减一距house的距离
        int dist1 = (right == 0) ? Integer.MAX_VALUE : Math.abs(house - heaters[right - 1]);
        // right是否还处于右边界时，否，则计算其距house的距离
        int dist2 = (right == heaters.length) ? Integer.MAX_VALUE : Math.abs(house - heaters[right]);
        // 比较house的左右边界的值
        return Math.min(dist1, dist2);
    }
}