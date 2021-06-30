package learn.sfdl;

/**
 * 1-1：算法所需时间为f(n)毫秒，求1秒，1分钟，1小时，1天，1月，1年时，n的最大值？
 *
 * @author zgq
 */
public class QuestionSk1T1 {
    public static void main(String[] args) {
        int n = 2;
        count(n);
        System.out.println();
    }

    private static void count(int n) {
        // 1s以内时，n的最大值 = 10^1000,
        int y = n;
        while (method1(y) < 1000) {
            y++;
            System.out.println(y);
            // 计算机无法解析
            break;
        }
    }

    private static double method1(int n) {
        return Math.log10(n);
    }
}
