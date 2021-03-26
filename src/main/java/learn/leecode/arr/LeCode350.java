package learn.leecode.arr;


import java.util.Arrays;
import java.util.HashMap;

/**
 * 350
 *
 * @author zgq
 */
public class LeCode350 {
    public static void main(String[] args) {
        getArr();
    }

    /**
     * 350题：两个数组的交集
     * 给定两个数组，编写一个函数来计算它们的交集。输入：nums1 = [1,2,2,1], nums2 = [2,2] ; 输出：[2,2]
     * 说明：输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。我们可以不考虑输出结果的顺序。
     */
    private static void getArr() {
        int[] nums2 = {2, 2, 3};
        int[] arr3 = {1, 2, 3, 1, 2, 2};

        long time1 = System.currentTimeMillis();
        mapArr(nums2, arr3);
        long time2 = System.currentTimeMillis();
        System.out.println("mapArr-time:" + (time2 - time1));
        // 最优解
        sortedNoArr(arr3, nums2);
        long time3 = System.currentTimeMillis();
        System.out.println("sortedNoArr-time:" + (time3 - time2));
    }

    private static int[] mapArr(int[] nums1, int[] nums2) {
        // 比较出长度短的数组，重新执行
        if (nums1.length > nums2.length) {
            return mapArr(nums2, nums1);
        }

        // 记录短数组个元素的出现次数
        HashMap<Object, Integer> map = new HashMap<>(nums1.length);
        for (int value : nums1) {
            map.merge(value, 1, Integer::sum);
        }

        // 遍历长数组
        int k = 0;
        for (int value : nums2) {
            Integer count = map.get(value);
            if (count != null && count > 0) {
                // 如果存在且次数大于1，记录值,并减少次数
                nums1[k] = value;
                k++;
                map.put(value, count - 1);
            }
        }
        int[] ints = Arrays.copyOfRange(nums1, 0, k);
        System.out.println("mapArr:" + Arrays.toString(ints));
        return ints;
    }

    private static void sortedNoArr(int[] arr3, int[] nums2) {
        Arrays.sort(nums2);
        Arrays.sort(arr3);
        sortedArr(arr3, nums2);
    }

    private static void sortedArr(int[] nums1, int[] nums2) {
        int k = 0;
        // 有序数组使用双指针法，谁大，指针就往后移
        for (int i = 0, j = 0; i < nums1.length & j < nums2.length; ) {
            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                // 相等时，记录值，并且指针都后移
                nums1[k] = nums1[i];
                k++;
                i++;
                j++;
            }
        }

        System.out.println("sortedNoArr:" + Arrays.toString(Arrays.copyOfRange(nums1, 0, k)));
    }
}
