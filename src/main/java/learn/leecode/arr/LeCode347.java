package learn.leecode.arr;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/25
 **/
public class LeCode347 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 2, 2};
        System.out.println(Arrays.toString(nums));
        int k = 2;
        int[] res = topKgFrequent(nums, k);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * <p>
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * <p>
     * 示例 2:
     * 输入: nums = [1], k = 1
     * 输出: [1]
     * <p>
     * 提示：
     * 1 <= nums.length <= 10^5
     * k 的取值范围是 [1, 数组中不相同的元素的个数]
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
     * <p>
     * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
     */
    private static int[] topKgFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 采用小顶堆，最上面的表示出现次数最少的，当堆满时，小于该次数的直接跳过，大于顶的，抛出堆顶，放入新的数据，重排堆顶
        PriorityQueue<int[]> smallHeap = new PriorityQueue<>(k, Comparator.comparingInt(v -> v[1]));

        map.forEach((key, value) -> {
            if (smallHeap.size() < k) {
                // 堆未满时，直接放入
                smallHeap.offer(new int[]{key, value});
            } else {
                // 堆已满，需要判定, peek取出堆顶元素
                int[] peek = smallHeap.peek();
                if (value > peek[1]) {
                    // 先抛出堆顶，再入新数据
                    smallHeap.poll();
                    smallHeap.offer(new int[]{key, value});
                }
            }
        });

        // 取出堆元素
        int[] count = new int[k];
        int index = 0;
        while (!smallHeap.isEmpty()){
            count[index] = smallHeap.poll()[0];
            index++;
        }

        return count;
    }
}
