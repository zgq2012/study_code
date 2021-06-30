package learn.sfdl;

import java.util.Arrays;

/**
 * 2.1.4：
 * 输入：长度均为 n 的数组 A、B，分别存储一个 n 位的二进制整数。（最高位必须是 1，其它位为 0 或者 1）
 * 输出：长度为 n+1 的数组 C，存储 A 与 B 存储的二进制整数之和。
 *
 * @author zgq
 */
public class Question2T1T4 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 0, 1, 0, 1};
        StringBuilder s = new StringBuilder();
        for (int i : arr) {
            s.append(i);
        }

        int[] arr2 = {1, 1, 0, 0, 1, 1, 1};
        int[] arr3 = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            arr3[i] = arr[i] + arr2[i] + arr3[i];
            if (arr3[i] >= 2) {
                arr3[i + 1] = arr3[i + 1] + 1;
                arr3[i] = arr3[i] - 2;
            }
        }
        System.out.println(Arrays.toString(arr3));
    }
}
