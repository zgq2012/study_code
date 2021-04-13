package learn.leecode.day;

import java.util.*;

/**
 * 179. 最大数
 *
 * @author zgq
 * @version v1.2.0
 * @date 2021/4/12
 **/
public class LeCode179 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 11, 3, 55};
        String result = largestNumber(nums);
        System.out.println(result);
    }

    /**
     * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
     * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     * <p>
     * 示例 1：
     * 输入：nums = [10,2]
     * 输出："210"
     * <p>
     * 示例 2：
     * 输入：nums = [3,30,34,5,9]
     * 输出："9534330"
     * <p>
     * 示例 3：
     * 输入：nums = [1]
     * 输出："1"
     * <p>
     * 示例 4：
     * 输入：nums = [10]
     * 输出："10"
     * <p>
     * 提示：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 10^9
     */
    public static String largestNumber(int[] nums) {
        /*
        // 未知蛇皮写法，耗时15ms
        List<String> strArr = new ArrayList<>(nums.length);
        StringBuilder result = new StringBuilder();
        for (int num : nums) {
            strArr.add(num + "");
        }
        strArr.sort((o1, o2) -> {
            long num = Long.parseLong(o1 + o2) - Long.parseLong(o2 + o1);
            return num >= 0L ? 1 : -1;
        });
        if ("0".equals(strArr.get(0))) {
            return "0";
        }
        for (String s : strArr) {
            result.append(s);
        }
        return result.toString();
        */


        // 官方题解，耗时4ms
        // int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
         /*
         Integer[] numsArr = new Integer[n];
         for (int i = 0; i < n; i++) {
             numsArr[i] = nums[i];
         }
         */

        // 此处若使用快速排序，时间效率能继续提高，耗时1ms左右。
        quickSort(nums, 0, nums.length - 1);

        // 此规律具有传递性
         /*
         Arrays.sort(numsArr, (x, y) -> {
             long sx = 10, sy = 10;
             while (x >= sx) {
                 sx *= 10;
             }
             while (y >= sy) {
                 sy *= 10;
             }
             return (int) (-sy * x - y + sx * y + x);
         });

         if (numsArr[0] == 0) {
             return "0";
         }
         */
        if (nums[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : nums) {
            ret.append(num);
        }
        return ret.toString();
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = nums[start];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            long x = 10;
            long y = 10;
            // 将数转换为前置的高位， 如，12 & 45, 可以组合为 1200+45，或者4500+12，下面while就是用来添加0的处理
            while (nums[i] >= x) {
                x *= 10;
            }
            while (pivot >= y) {
                y *= 10;
            }
            if (nums[i] * y + pivot > nums[i] + pivot * x) {
                index += 1;
                swap(nums, index, i);
            }
        }
        swap(nums, index, start);
        quickSort(nums, start, index - 1);
        quickSort(nums, index + 1, end);
    }

    private static void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
