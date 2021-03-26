package learn.leecode.chaintable;

/**
 * 19
 *
 * @author zgq
 */
public class LeCode19 {

    public static void main(String[] args) {
        removeNthFromEnd();
    }

    /**
     * 删除链表倒数第N个节点(19)
     * <p>
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     */
    private static void removeNthFromEnd() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int n = 2;
        ListNode val = removeNode(head, n);
        System.out.println("head :" + val.val);
    }

    private static ListNode removeNode(ListNode head, int n) {
        // 在链表头部定义一个哨兵节点
        ListNode start = new ListNode(0);
        start.next = head;
        // 定义2个初始指针
        ListNode first = start;
        ListNode second = start;
        // 双指针找需要删除的节点，first节点先行，与second间隔 n 时，second 开始行进，直至first遍历结束
        while (first.next != null) {
            // 1 2 |3 4 5
            first = first.next;
            // 1 0 |-1 -2 -3
            n--;
            if (n < 0) {
                // |1 2 3
                second = second.next;
            }
        }
        // 删除倒数第n节点
        assert second.next != null;
        second.next = second.next.next;
        return start.next;
    }
}
