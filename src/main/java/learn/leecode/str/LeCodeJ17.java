package learn.leecode.str;

/**
 * 剑17
 *
 * @author zgq
 */
public class LeCodeJ17 {

    public static void main(String[] args) {
        printNumbers();
    }

    /**
     * 打印从1到最大的n位数(剑17)
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     * <p>
     * 示例 1:
     * 输入: n = 1
     * 输出: [1,2,3,4,5,6,7,8,9]
     * <p>
     * 说明（不考虑大数越界）：用返回一个整数列表来代替打印 n 为正整数
     */
    private static StringBuilder res;
    private static int nine = 0, start, n = 3;
    private static char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static void printNumbers() {
        // 以字符串的形式输出
        res = new StringBuilder();
        // 标志当前值
        num = new char[n];
        // 标志起始位数
        start = n - 1;
        // 递归记录输出值
        dfs(0);
        // 去除最后一个逗号
        res.deleteCharAt(res.length() - 1);
        System.out.println(res.toString());
    }

    private static void dfs(int x) {
        if (x == n) {
            // 截取num的值（去除前面的0）
            String s = String.valueOf(num).substring(start);
            if (!"0".equals(s)) {
                // 拼接逗号
                res.append(s).append(",");
            }
            if (n - start == nine) {
                // 重置start
                start--;
            }
            return;
        }
        for (char i : loop) {
            if (i == '9') {
                // 进位
                nine++;
            }
            num[x] = i;
            dfs(x + 1);
        }
        nine--;
    }
}
