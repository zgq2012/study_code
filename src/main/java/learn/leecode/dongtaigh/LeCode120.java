package learn.leecode.dongtaigh;

import java.util.ArrayList;
import java.util.List;

/**
 * 120
 *
 * @author zgq
 */
public class LeCode120 {

    public static void main(String[] args) {
        miniMumTotal();
    }

    /**
     * 三角形最小路径和(120)
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中 相邻的结点 上。
     * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     * 例如，给定三角形：
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     */
    private static void miniMumTotal() {
        List<List<Integer>> triangle = getList();
        int n = triangle.size();
        // 状态转移方程:
        //         = f[i-1][0] + c[i][0],  j=0
        // f[i][j] = f[i-1][i-1] + c[i][i],  j=i
        //         = min(f[i-1][j-1],f[i-1][j]) + c[i][j],  otherwise
        // f[i][j]只与f[i-1][...]有关，不必存储其他无关状态，所以定义二维数组，并且一维长度为2
        // -> 使用两个长度为 n的一维数组进行转移，将 i 根据奇偶性映射到其中一个一维数组，那么 i−1 就映射到了另一个一维数组
        // 这样我们使用这两个一维数组，交替地进行状态转移
        int[][] f = new int[2][n];
        // 初始化[0][0]位
        f[0][0] = triangle.get(0).get(0);
        // 遍历
        for (int i = 1; i < n; i++) {
            // 取i的模，准备当前存放记录
            int curr = i % 2;
            // 取之前的一个存放点
            int prev = 1 - curr;
            // 初始化最左边
            f[curr][0] = f[prev][0] + triangle.get(i).get(0);
            // 遍历j < i的数据记录最小值，记录了每一个位置的值
            for (int j = 1; j < i; j++) {
                // 比较大小值
                f[curr][j] = Math.min(f[prev][j - 1], f[prev][j]) + triangle.get(i).get(j);
            }
            // 更新最右边的一列
            f[curr][i] = f[prev][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[(n - 1) % 2][0];
        // 遍历比较最小和
        for (int i = 1; i < n; i++) {
            minTotal = Math.min(minTotal, f[(n - 1) % 2][i]);
        }
        System.out.println("minTotal:" + minTotal);
    }

    private static List<List<Integer>> getList() {
        // [[-1],[2,3],[1,-1,-3]]
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
//        first.add(2);
        first.add(-1);
        triangle.add(first);
        List<Integer> two = new ArrayList<>();
//        two.add(3);
//        two.add(4);
        two.add(2);
        two.add(3);
        triangle.add(two);
        List<Integer> three = new ArrayList<>();
//        three.add(6);
//        three.add(5);
//        three.add(7);
        three.add(1);
        three.add(-1);
        three.add(-3);
        triangle.add(three);
//        List<Integer> four = new ArrayList<>();
//        four.add(4);
//        four.add(1);
//        four.add(8);
//        four.add(3);
//        triangle.add(four);
        return triangle;
    }
}
