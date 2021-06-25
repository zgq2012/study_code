package learn.leecode.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. 直线上最多的点数
 *
 * @author zgq
 */
public class LeCode149 {

    public static void main(String[] args) {
        int[][] height = {{1, 8}, {6, 2}, {5, 4}, {8, 3}};
        int res = maxPoints(height);
        System.out.println(res);
    }

    /**
     * (149) 直线上最多的点数
     * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
     * <p>
     * 提示：
     * 1 <= points.length <= 300
     * points[i].length == 2
     * -10^4 <= xi, yi <= 10^4
     * points 中的所有点 互不相同
     */
    public static int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (ret >= n - i || ret > n / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcdXy = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXy;
                    y /= gcdXy;
                }
                int key = y + x * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxN = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int num = entry.getValue();
                maxN = Math.max(maxN, num + 1);
            }
            ret = Math.max(ret, maxN);
        }
        return ret;
    }

    public static int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
