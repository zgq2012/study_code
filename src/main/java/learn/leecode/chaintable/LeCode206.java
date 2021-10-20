package learn.leecode.chaintable;

/**
 * 206. 反转链表
 *
 * @author zgq
 */
public class LeCode206 {

    public static void main(String[] args) {
        mergeTwoNode();
    }

    /**
     * 206. 反转链表
     *
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
     */
    private static void mergeTwoNode() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        ListNode listNode = reverseList(l1);
        while (null != listNode) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }


    private static ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode pre = new ListNode(-1);
        ListNode temp = pre;

        dg(head, temp);
        temp.next = head;
        temp = temp.next;
        temp.next = null;

        return pre.next;
    }

    public static ListNode dg(ListNode head, ListNode temp) {
        if (head.next != null) {
             temp.next = dg(head.next, temp);
             temp = temp.next;
             return temp;
        }else {
            return head;
        }
    }
}
