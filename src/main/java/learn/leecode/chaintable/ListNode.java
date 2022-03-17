package learn.leecode.chaintable;

/**
 * 链表类
 *
 * @author zgq
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int x, ListNode nextNode) {
        val = x;
        next = nextNode;
    }
}
