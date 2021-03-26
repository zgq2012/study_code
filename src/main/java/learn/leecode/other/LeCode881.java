package learn.leecode.other;

import java.util.Arrays;

/**
 * 881
 *
 * @author zgq
 */
public class LeCode881 {

    public static void main(String[] args) {
        int limit = 5;
//        int[] people = {1, 2, 5, 8, 2};
//        int[] people = {3, 2, 2, 1};
        int[] people = {3, 5, 3, 4};
        int res = numRescueBoats(people, limit);
        System.out.println(res);
    }

    /**
     * (881)救生艇
     * 第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
     * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
     * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
     * <p>
     * 示例 1：
     * 输入：people = [1,2], limit = 3     输出：1
     * 解释：1 艘船载 (1, 2)
     * <p>
     * 示例 2：
     * 输入：people = [3,2,2,1], limit = 3     输出：3
     * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
     * <p>
     * 示例 3：
     * 输入：people = [3,5,3,4], limit = 5     输出：4
     * 解释：4 艘船分别载 (3), (3), (4), (5)
     * <p>
     * 提示：
     * 1 <= people.length <= 50000
     * 1 <= people[i] <= limit <= 30000
     */
    public static int numRescueBoats(int[] people, int limit) {
        // 在限制了船的人数时，第一选择是：将最重的人放上去，然后再将最轻的放上去看是否超载，
        // 超载则不放，不超载则放，直至结束
        Arrays.sort(people);
        // 且在排序之后，一定有：(people[i]+people[j]) >= (people[i--]+people[j++])
        int count = 0;
        int j = 0;
        for (int i = people.length - 1; i >= j; i--) {
            // 超重的只能单独坐
            if (people[i] >= limit) {
                count++;
                continue;
            }
            if (i == j) {
                count++;
                continue;
            }
            // 最重的带最轻的走，反之则单独走
            if (people[i] + people[j] <= limit) {
                count++;
                j++;
            } else {
                count++;
            }
        }
        return count;
    }
}
