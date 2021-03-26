package learn.sfdl;

/**
 * 1.2.2：n取何值时，插入排序优于归并排序，插入排序步骤8*n*n，归并步骤64*n*lgn
 * lgn -> 算法导论中表示log2(n)
 *
 * @author zgq
 */
public class Question1T2T2 {
    public static void main(String[] args) {
        // n=1时 -> lg1=0，无意义的计算
        int n = 2;
        // Math.log(n) 表示以e为底数的对数
        while ((8 * n * n) < (64 * n * Math.log(n) / Math.log(2))) {
            n++;
        }
        System.out.println("n取：" + n);
        // res:n = 44
    }
}
