package learn.sfdl;

/**
 * 1.2.2：n的最小值为何值时，运行时间100*n*n的一个算法比在相同机器上快于运行时间为2^n的另一个算法？
 *
 * @author zgq
 */
public class Question1T2T3 {
    public static void main(String[] args) {
        int n = 1;
        while ((100 * n * n) > (Math.pow(2, n))) {
            n++;
        }
        System.out.println("n取：" + n);
        // res:n = 15
    }
}
