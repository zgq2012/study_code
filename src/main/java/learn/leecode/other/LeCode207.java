package learn.leecode.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 207. 课程表
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/22
 **/
public class LeCode207 {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        boolean res = canFinish(numCourses, prerequisites);
        System.out.println("res = " + res);
    }

    /**
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，
     * 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     * <p>
     * 示例 1：
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：true
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     * <p>
     * 示例 2：
     * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
     * 输出：false
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     * <p>
     * 提示：
     * 1 <= numCourses <= 10^5
     * 0 <= prerequisites.length <= 5000
     * prerequisites[i].length == 2
     * 0 <= ai, bi < numCourses
     * prerequisites[i] 中的所有课程对 互不相同
     */
    private static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 用于存储每个课程之后必须学的所有课程
        List<List<Integer>> preList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            // 初始化
            preList.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            preList.get(prerequisite[1]).add(prerequisite[0]);
        }

        // 用于记录单向走过的路
        int[] flags = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            // 遍历课程，并且给每个课程去走单向图，有环则表示失败
            if (!dfsCur(flags, preList, i)) {
                return false;
            }
        }

        return true;
    }
    
    private static boolean dfsCur(int[] flags, List<List<Integer>> preList, int index) {
        if (flags[index] == 1) {
            // 已经走过，却再次来到这里，表示有环，返回false
            return false;
        }

        if (flags[index] == -1) {
            // 表示该课程之前右多个课程被完成，是允许存在的
            return true;
        }

        // 进入这里设置为进入此路单向标志
        flags[index] = 1;
        // 继续index的下面分支
        for (int i = 0; i < preList.get(index).size(); i++) {
            // 重复操作
            if (!dfsCur(flags, preList, preList.get(index).get(i))) {
                return false;
            }
        }

        // 走过的并正确的路置为-1
        flags[index] = -1;

        return true;
    }
}
