package learn.leecode.arr;

import java.util.Arrays;

/**
 * 6
 *
 * @author zgq
 */
public class LeCode6 {

    public static void main(String[] args) {
        convertZ();
    }

    /**
     * Z型变换（6）
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 4 时，排列如下：
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     */
    private static void convertZ() {
        String s = "LEETCODEISHIRINGAREREFSDFEREGERRTRWWRWEFASEFRSEFRRSEFSEFEFSEFESFSEFESFESFSEFSEFSEEGSSE";
        int numRows = 3;
        long time1 = System.currentTimeMillis();
        String convert = convert(s, numRows);
        long time2 = System.currentTimeMillis();
        String convert2 = convertByBool(s, numRows);
        long time3 = System.currentTimeMillis();
        System.out.println("convertResult:" + convert + " time : " + (time2 - time1));
        System.out.println("convert2Result:" + convert2 + " time : " + (time3 - time2));
    }

    private static String convertByBool(String s, int numRows) {
        if (s == null || s.length() < numRows || numRows < 2) {
            return s;
        }
        int curRow = 0;
        boolean goingDown = false;
        // 定义numRows长度的集合
        String[] nums = new String[numRows];
        // 初始化每个元素
        Arrays.fill(nums, "");
        for (char c : s.toCharArray()) {
            nums[curRow] += c;
            // 不在2头就反向索引添加数据！！原理：规律->走到头就反向，使用boolean控制是否转向添加
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        for (int i = 1; i < nums.length; i++) {
            nums[0] = nums[0] + nums[i];
        }
        return nums[0];
    }

    private static String convert(String s, int numRows) {
        if (s == null || s.length() < numRows || numRows < 2) {
            return s;
        }

        // 定义numRows长度的集合
        String[] nums = new String[numRows];
        // 初始化每个元素
        Arrays.fill(nums, "");
        // 查找每一行的每一个字符相间的规律 -- 得到每 2n-2 一个循环，
        for (int i = 0; i < s.length(); i++) {
            int index = i % (2 * numRows - 2);
            if (index + 1 > numRows) {
                // 当模+1大于行数的时候，需要将其放到对应的位置 2 * numRows - 2 - index
                nums[2 * numRows - 2 - index] = nums[2 * numRows - 2 - index] + s.charAt(i);
            } else {
                // 当模+1小于行数的时候，直接按模放置
                nums[index] = nums[index] + s.charAt(i);
            }
        }

        for (int i = 1; i < nums.length; i++) {
            nums[0] = nums[0] + nums[i];
        }
        return nums[0];
    }
}
