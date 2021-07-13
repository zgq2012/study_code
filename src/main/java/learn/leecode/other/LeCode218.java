package learn.leecode.other;

import java.util.*;

/**
 * 218. 天际线问题
 *
 * @author zgq
 */
public class LeCode218 {

    public static void main(String[] args) {
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<List<Integer>> res = getSkyline(buildings);
        System.out.println("结果：" + res);
    }

    /**
     * (218) 天际线问题
     * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。
     * 给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
     * <p>
     * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [left_i, right_i, height_i] 表示：
     * <p>
     * left_i 是第 i 座建筑物左边缘的 x 坐标。
     * right_i 是第 i 座建筑物右边缘的 x 坐标。
     * height_i 是第 i 座建筑物的高度。
     * 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。
     * 关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。
     * 此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
     * <p>
     * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
     * 三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
     * <p>
     * 示例 1：
     * 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
     * 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
     * 解释：
     * 图 A 显示输入的所有建筑物的位置和高度，
     * 图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
     * <p>
     * 示例 2：
     * 输入：buildings = [[0,2,3],[2,5,3]]
     * 输出：[[0,3],[5,0]]
     * <p>
     * 提示：
     * 1 <= buildings.length <= 10^4
     * 0 <= left_i < right_i <= 2^31 - 1
     * 1 <= height_i <= 2^31 - 1
     * buildings 按 left_i 非递减排序
     */
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        // 建立队列
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        // 将所有横坐标组合起来
        List<Integer> boundaries = new ArrayList<>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        // 横坐标排序
        Collections.sort(boundaries);

        List<List<Integer>> ret = new ArrayList<>();
        int n = buildings.length;
        int idx = 0;
        /*
        * 首先将「包含该横坐标」的建筑加入到优先队列中，然后不断检查优先队列的队首元素是否「包含该横坐标」，
        * 如果不「包含该横坐标」，我们就将该队首元素弹出队列，直到队空或队首元素「包含该横坐标」即可。
        * 最后我们用变量 max 记录最大高度（即纵坐标的值），当优先队列为空时，max = 0，否则 max 即为队首元素。
        * 最后我们还需要再做一步检查：如果当前关键点的纵坐标大小与前一个关键点的纵坐标大小相同，
        * 则说明当前关键点无效，我们跳过该关键点即可
        */
        for (int boundary : boundaries) {
            while (idx < n && buildings[idx][0] <= boundary) {
                pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                idx++;
            }
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }

            int max = pq.isEmpty() ? 0 : pq.peek()[1];
            if (ret.size() == 0 || max != ret.get(ret.size() - 1).get(1)) {
                ret.add(Arrays.asList(boundary, max));
            }
        }
        return ret;
    }
}
