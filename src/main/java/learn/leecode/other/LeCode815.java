package learn.leecode.other;

import java.util.*;

/**
 * 815. 公交路线
 *
 * @author zgq
 */
public class LeCode815 {

    public static void main(String[] args) {
        int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        int source = 1;
        int target = 6;
        int res = numBusesToDestination(routes, source, target);
        System.out.println(res);
    }

    /**
     * (815) 公交路线
     * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
     * 例如，路线 routes[0] = [1, 5, 7]
     * 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
     * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
     * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
     * <p>
     * 示例 1：
     * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
     * 输出：2
     * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
     * <p>
     * 示例 2：
     * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
     * 输出：-1
     * <p>
     * 提示：
     * 1 <= routes.length <= 500.
     * 1 <= routes[i].length <= 10^5
     * routes[i] 中的所有值 互不相同
     * sum(routes[i].length) <= 10^5
     * 0 <= routes[i][j] < 10^6
     * 0 <= source, target < 10^6
     */
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        // 若起点就是终点直接返回
        if (source == target) {
            return 0;
        }

        int n = routes.length;
        // 记录某一站点是否可达另一站点，
        boolean[][] edge = new boolean[n][n];
        // 记录经过各个所有节点的公交路线，key -> 站点, value -> 包含该站点路线在routes中的索引集合
        Map<Integer, List<Integer>> rec = new HashMap<>(8);
        // 遍历所有路线
        for (int i = 0; i < n; i++) {
            // 遍历i索引路线
            for (int site : routes[i]) {
                // 记录包含该site站点路线在routes中的索引集合
                List<Integer> list = rec.getOrDefault(site, new ArrayList<>());
                for (int j : list) {
                    // 若可达，那一定是双向都可达（A->B, B->A）；
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                rec.put(site, list);
            }
        }

        // 记录所有站点, 并且填充默认值-1;
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Queue<Integer> que = new LinkedList<>();
        // 遍历包含起点的所有路线
        for (int site : rec.getOrDefault(source, new ArrayList<>())) {
            // 数组记录起点可达所在路线，并所在索引位置设为1
            dis[site] = 1;
            // 已队列记录起点可达所在路线的索引
            que.offer(site);
        }

        // 如果队列站点不为空，则继续循环查找下一站点，
        while (!que.isEmpty()) {
            // 去掉队头，并记录下队头
            int x = que.poll();
            // 遍历所有路线
            for (int y = 0; y < n; y++) {
                // 如果某一路线可达队头，并且非起点路线，
                if (edge[x][y] && dis[y] == -1) {
                    // 则在该路线记录下需乘次数+1
                    dis[y] = dis[x] + 1;
                    // 并且将该路线添加到队列尾部
                    que.offer(y);
                }
            }
        }

        // 查找可达路线中的最下值，即最小次数
        int ret = Integer.MAX_VALUE;
        for (int site : rec.getOrDefault(target, new ArrayList<>())) {
            if (dis[site] != -1) {
                ret = Math.min(ret, dis[site]);
            }
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}