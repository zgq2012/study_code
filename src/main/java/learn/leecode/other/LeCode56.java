package learn.leecode.other;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 56. 合并区间
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/2/25
 **/
public class LeCode56 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        int[][] merge = merge(intervals);

        for (int[] ints : merge) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [start.i, end.i] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * <p>
     * 示例 1：
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     * 示例 2：
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     * <p>
     * 提示：
     * 1 <= intervals.length <= 10^4
     * intervals[i].length == 2
     * 0 <= start.i <= end.i <= 10^4
     */
    private static int[][] merge(int[][] intervals) {
        // 先对左端点排序
        Arrays.sort(intervals, Comparator.comparingInt(t -> t[0]));

        int index = 0;

        int[] temp = intervals[0];
        for (int[] interval : intervals) {
            if(temp[1] >= interval[0]){
                // 表示前一段的尾部与后一段的起始相接，
                temp[1] = Math.max(interval[1], temp[1]);
            }else {
                // 表示没有重复的,放入结果
                intervals[index] = temp;
                index++;
                temp = interval;
            }
        }

        intervals[index] = temp;
        return Arrays.copyOf(intervals, index + 1);
    }
}
