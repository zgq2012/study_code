package learn.leecode.arr;

/**
 * 27
 *
 * @author zgq
 */
public class LeCode27 {

    public static void main(String[] args) {
        deleteVal();
    }

    /**
     * 原地删除(27)
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     */
    private static void deleteVal() {
        int[] arr = {3, 2, 2, 3};
        int length = arr.length;
        int val = 3;

        int n = 0;
        while (n < length) {
            if (arr[n] == val) {
                // 相等就和最后一个交换
                arr[n] = arr[length - 1];
                // 且让条件前移一步，可以当作是将数组最后一个元素扔掉
                length--;
            } else {
                // 不相等就++,表示留下一个元素
                n++;
            }
        }
        System.out.println("n = " + n);
    }
}
