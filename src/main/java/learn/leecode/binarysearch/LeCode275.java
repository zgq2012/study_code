package learn.leecode.binarysearch;

/**
 * 275. H 指数 II
 *
 * @author zgq
 */
public class LeCode275 {
    public static void main(String[] args) {
        int[] citations = {0, 1, 1, 2, 5, 6};
//        int[] citations = {0, 0};
        int result = hIndex(citations);
        System.out.println("结果 : " + result);
    }


    /**
     * (275) H 指数 II
     * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。
     * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）
     * 总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）"
     * <p>
     * 示例:
     * 输入: citations = [0,1,3,5,6]
     * 输出: 3
     * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
     * 由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
     * <p>
     * 说明:
     * 如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
     * <p>
     * 进阶：
     * 这是 H 指数 的延伸题目，本题中的 citations 数组是保证有序的。
     */
    private static int hIndex(int[] citations) {

        // 二分查找，判断该位置的值是否大于等于该位置的索引+1
        int right = citations.length - 1;
        int left = 0;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            // 当前值(value)与大于当前值的个数(length - mid)比较
            if (citations[mid] >= citations.length - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            // 取中位数
            mid = left + (right - left) / 2;
        }

        return citations.length - left;
    }
}