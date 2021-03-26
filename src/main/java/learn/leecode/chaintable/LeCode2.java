package learn.leecode.chaintable;

/**
 * 2
 *
 * @author zhuguangqain
 */
public class LeCode2 {
    public static void main(String[] args) {
        twoNumAdd();
    }

    /**
     * 两数相加（2）
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
     * 并且它们的每个节点只能存储一位数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    private static void twoNumAdd() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode sumNode = addTwoNumbers(l1, l2);

        StringBuilder sum = new StringBuilder();
        while (sumNode != null) {
            sum.append(sumNode.val);
            sumNode = sumNode.next;
        }
        System.out.println("sum:" + sum);
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        // 进位值
        int carry = 0;
        // 在l1或l2不为空时，或者有进位值时继续执行
        while (l1 != null || l2 != null || carry != 0) {
            // 在l1 或者 l2 为null 时，计算的位数赋值为0
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            // 获取进位值
            carry = sumVal / 10;
            // 当前位数赋值
            ListNode sumNode = new ListNode(sumVal % 10);
            // 指定哨兵节点下一节点
            cursor.next = sumNode;
            // 哨兵节点继续往下走
            cursor = sumNode;
            // 不为空时，2数 继续往下走
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return root.next;
    }
}
