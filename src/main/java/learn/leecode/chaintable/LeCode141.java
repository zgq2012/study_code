package learn.leecode.chaintable;

/**
 * 141
 *
 * @author zgq
 */
public class LeCode141 {

    public static void main(String[] args) {
        cycleNode();
    }

    /**
     * 环形链表(141)
     * 给定一个链表，判断链表中是否有环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。
     * 输入：head = [3,2,0,-4], pos = 1 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     */
    private static void cycleNode() {
        ListNode test = new ListNode(1);
        test.next = new ListNode(2);
//        ListNode test = new ListNode(3);
//        ListNode twoNode = new ListNode(2);
//        test.next = twoNode;
//        test.next.next = new ListNode(0);
//        test.next.next.next = new ListNode(-4);
//        test.next.next.next.next = twoNode;

        boolean isCycle = checkCycleNode(test);
        System.out.println("isCycle:" + isCycle);
    }

    private static boolean checkCycleNode(ListNode head) {
        if (head == null) {
            return false;
        }
        // 双指针校验，first每次走一步，second每次走2步（步长不同，且不能同步，错开即可）
        ListNode pre = new ListNode(0);
        ListNode first = pre;
        ListNode second = pre;
        pre.next = head;
        // 中止条件，如果其下一个或者设置的下一个步长为null，那就表示，该链表没有环，因为存在环就不存在null元素
        while (first.next != null && second.next != null && second.next.next != null) {
            if (first.next == second.next.next) {
                return true;
            }
            first = first.next;
            second = second.next.next;
        }
        return false;
    }
}
