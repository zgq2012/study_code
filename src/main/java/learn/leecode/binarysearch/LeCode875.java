package learn.leecode.binarysearch;

/**
 * 875
 *
 * @author zgq
 */
public class LeCode875 {
    public static void main(String[] args) {
//        int[] piles = {5, 7, 12, 44, 31};
//        int h = 6;
        int[] piles = {30, 11, 23, 4, 20};
        int h = 5;
        int result = minEatingSpeed(piles, h);
        System.out.println(result);
    }

    /**
     * (875)爱吃香蕉的珂珂
     * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
     * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
     * 如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
     * <p>
     * 示例 1：
     * 输入: piles = [3,6,7,11], H = 8    输出: 4
     * <p>
     * 示例 2：
     * 输入: piles = [30,11,23,4,20], H = 5   输出: 30
     * <p>
     * 示例 3：
     * 输入: piles = [30,11,23,4,20], H = 6   输出: 23
     * <p>
     * 提示：
     * 1 <= piles.length <= 10^4
     * piles.length <= H <= 10^9
     * 1 <= piles[i] <= 10^9
     */
    private static int minEatingSpeed(int[] piles, int h) {
        if (piles.length == 1) {
            return piles[0] / h + (piles[0] % h > 0 ? 1 : 0);
        }
        // 二分查找来获取最小速度K，初始化0
        // 界定k的范围：k的最小值为1，因为最慢一次只吃一根香蕉，最大值，吃一堆只用1h,故k上限选取为最大堆，即数组中的最大值
        int min = 1;
        int max = maxArr(piles);
        // 从1开始查找，完成条件，sum(p[i]/k+mo) <= h && sum(p[i+1]/k+mo) > h; mo = p[i]%k > 0 ? 1:0;
        while (min < max) {
            int k = (max + min) / 2;
            int arrPreHour = getHour(piles, k);
            if (arrPreHour > h) {
                min = k + 1;
            }  else {
                max = k;
            }
        }
        return min;
    }

    private static int getHour(int[] piles, int k) {
        int sum = 0;
        for (int pile : piles) {
            sum = sum + pile / k + (pile % k > 0 ? 1 : 0);
        }
        return sum;
    }

    private static int maxArr(int[] piles) {
        int max = piles[0];
        for (int i = 1; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        return max;
    }
}