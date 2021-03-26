package learn.leecode.binarysearch;

/**
 * 69
 *
 * @author zgq
 */
public class LeCode69 {
    public static void main(String[] args) {
        int x = 2147483647;
        int result = mySqrt(x);
        System.out.println(result);
        int result2 = mySqrt2(x);
        System.out.println(result2);
    }

    private static int mySqrt2(int x) {
        // 1以下的平方根去整都是0
        if (x < 1) {
            return 0;
        }
        int left = 1;
        // 1以上的平方根取整一定小于等于x/2
        int right = x / 2;
        // 因为直接返回的是左边界left,所以定义left<right
        while (left < right) {
            // 取中值时，扩大了1个范围，（便于不用纠结中值的位置）
            int mid = ((left + right) >>> 1) + 1;
            // 条件判定
            if ((long) mid * mid <= x) {
                // 左边界赋值，因为要输出，所以左边界会一直以最接近返回值更新
                left = mid;
            } else {
                // 右边界降1，重新计算
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * (69)x 的平方根
     * 实现 int sqrt(int x) 函数。
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * <p>
     * 示例 1:
     * 输入: 4 输出: 2
     * <p>
     * 示例 2:
     * 输入: 8 输出: 2
     * <p>
     * 说明: 8 的平方根是 2.82842...,  由于返回类型是整数，小数部分将被舍去。
     */
    private static int mySqrt(int x) {
        // 二分直接查找值，
        // x是非负整数，上限为其本身，下限为0，终止条件为 sqrt*sqrt <= x &&(sqrt+1)*(sqrt+1) > x
        int min = 0;
        int max = x;
        // 用于记录结果值，保证min和max的动态变换不受影响
        int sqrt = 0;
        while (min <= max) {
            // 获取中值,
            // 隐藏坑点：(max + min)/2 可能会超出int范围，因为(max + min)超出,
            // 故官方解答 int mid = min + (max - min) / 2;
            // 或者直接 (max + min) >>> 1
            // >>> 表示无符号右移，忽略符号位，空位都以0补齐
            // >>> 与 >> 在正数运算时无差异，
            // 例：16>>>2 = 4,例：16>>2 = 4, 但：-16>>>2 = 1073741820, -16>>2 = -4
            int mid = (max + min) >>> 1;
            // 判断中值变化条件
            if ((long) mid * mid <= x) {
                // 记录满足条件的值
                sqrt = mid;
                // 满足条件，则将下限提升，不能使用min = mid, 因为mid已经不可能为值
                // 因为可能还有其他值也满足条件，需要找到最接近不满足条件的值
                min = mid + 1;
            } else {
                // 满足条件，则将上限降低，不能使用max = mid, 因为mid已经不可能为值
                max = mid - 1;
            }
        }
        return sqrt;
    }
}