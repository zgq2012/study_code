package learn.leecode.huisu;

import java.util.*;

/**
 * 1697. 检查边长度限制的路径是否存在
 *
 * @author zgq
 * @version v5.6.3
 * @since 2022/12/14
 **/
public class LeCode1697 {
    public static void main(String[] args) {
        // 13
        /*{{9,1,53},{3,2,66},{12,5,99},{9,7,26},{1,4,78},{11,1,62},{3,10,50},{12,1,71},{12,6,63},{1,10,63},{9,10,88},{9,11,59},{1,4,37},
         {4,2,63},{0,2,26},{6,12,98},{9,11,99},{4,5,40},{2,8,25},{4,2,35},{8,10,9},{11,9,25},{10,11,11},{7,6,89},{2,4,99},{10,4,63}}
         */
        /*{{9,7,65},{9,6,1},{4,5,34},{10,8,43},{3,7,76},{4,2,15},{7,6,52},{2,0,50},{7,6,62},{1,0,81},
        {4,5,35},{0,11,86},{12,5,50},{11,2,2},{9,5,6},{12,0,95},{10,6,9},{9,4,73},{6,10,48},
        {12,0,91},{9,10,58},{9,8,73},{2,3,44},{7,11,83},{5,3,14},{6,2,33}} */
        int n = 3;
        int[][] edgeList = {{0, 1, 2}, {1, 2, 4}, {2, 0, 8}, {1, 0, 16}};
        int[][] queries = {{0, 1, 2}, {0, 2, 5}};
//        int n = 13;
//        int[][] edgeList =
//                {{9, 1, 53}, {3, 2, 66}, {12, 5, 99}, {9, 7, 26}, {1, 4, 78}, {11, 1, 62}, {3, 10, 50},
//                        {12, 1, 71}, {12, 6, 63}, {1, 10, 63}, {9, 10, 88}, {9, 11, 59}, {1, 4, 37},
//                        {4, 2, 63}, {0, 2, 26}, {6, 12, 98}, {9, 11, 99}, {4, 5, 40}, {2, 8, 25},
//                        {4, 2, 35}, {8, 10, 9}, {11, 9, 25}, {10, 11, 11}, {7, 6, 89}, {2, 4, 99}, {10, 4, 63}};
//        int[][] queries =
//                {{9, 7, 65}, {9, 6, 1}, {4, 5, 34}, {10, 8, 43}, {3, 7, 76}, {4, 2, 15}, {7, 6, 52},
//                        {2, 0, 50}, {7, 6, 62}, {1, 0, 81}, {4, 5, 35}, {0, 11, 86}, {12, 5, 50},
//                        {11, 2, 2}, {9, 5, 6}, {12, 0, 95}, {10, 6, 9}, {9, 4, 73}, {6, 10, 48},
//                        {12, 0, 91}, {9, 10, 58}, {9, 8, 73}, {2, 3, 44}, {7, 11, 83}, {5, 3, 14}, {6, 2, 33}};
        boolean[] res = distanceLimitedPathsExist(n, edgeList, queries);
        System.out.println(Arrays.toString(res));
    }

    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        return mySolution(n, edgeList, queries);
    }

    /**
     * 手撕：
     * 看到这种顺序的寻迹，我第一时间想到的就是用 矢量图 来求解
     * 先对 原始数组 edgeList 进行处理，处理成以每一个起点位标记的map进行存储, 如所有以0开始的点，以1开始的点，等等
     * 然后 对queries进行顺序处理，但是需要注意截止条件，因为需要标记节点是否已经走过，不能重复处理
     */
    public static boolean[] mySolution(int n, int[][] edgeList, int[][] queries) {
        // 先对edgeList进行距离排序
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));

        Integer[] index = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            index[i] = i;
        }
        // 再对queries进行距离排序
        Arrays.sort(index, Comparator.comparingInt(a -> queries[a][2]));

        // 查询集合
        int[] uf = new int[n];
        for (int i = 0; i < n; i++) {
            uf[i] = i;
        }
        // 结果集
        boolean[] res = new boolean[queries.length];
        int k = 0;
        for (int i : index) {
            // 如果 k 指向的边的长度小于对应查询的 limit，则将该边加入并查集中，然后将 k 加 1，直到 k 指向的边不满足要求
            while (k < edgeList.length && edgeList[k][2] < queries[i][2]) {
                // 并查
                merge(uf, edgeList[k][0], edgeList[k][1]);
                k++;
            }
            // 根据并查集查询对应的 p 和 q 是否属于同一集合来保存查询的结果。
            res[i] = find(uf, queries[i][0]) == find(uf, queries[i][1]);
        }
        return res;
    }

    /**
     * 查询对应的点是否已经在集合
     */
    public static int find(int[] uf, int x) {
        if (uf[x] == x) {
            // 表示路径到头了，返回该路径可达终点
            return x;
        }
        return uf[x] = find(uf, uf[x]);
    }

    public static void merge(int[] uf, int x, int y) {
        x = find(uf, x);
        y = find(uf, y);
        uf[y] = x;
    }
}
