package learn.leecode.chaintable;

/**
 * 203. 移除链表元素
 *
 * @author zgq
 */
public class LeCode203 {

    public static void main(String[] args) {
        mergeTwoNode();
    }

    /**
     * 203. 移除链表元素
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     */
    private static void mergeTwoNode() {
        ListNode l2 = new ListNode(7);
        l2.next = new ListNode(7);
        l2.next.next = new ListNode(7);
        l2.next.next.next = new ListNode(7);
        ListNode listNode1 = removeElements(l2, 7);
        while (null != listNode1) {
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }

        System.out.println("=======================");
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(6);
        l1.next.next.next = new ListNode(3);
        l1.next.next.next.next = new ListNode(4);
        l1.next.next.next.next.next = new ListNode(5);
        l1.next.next.next.next.next.next = new ListNode(6);
        ListNode listNode = removeElements2(l1, 6);
        while (null != listNode) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    private static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode pre = new ListNode(-1);

        while (head != null) {
            if (head.val != val) {
                pre.next = new ListNode(head.val);
                pre = pre.next;
            }
            head = head.next;
        }

        return pre.next;
    }

    private static ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null) {
            // 若值相等，则后位变前位， 反之前进一位
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }

        return pre.next;
    }
}
