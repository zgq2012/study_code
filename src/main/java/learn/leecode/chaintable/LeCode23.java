package learn.leecode.chaintable;

/**
 * 21
 *
 * @author zgq
 */
public class LeCode23 {

    public static void main(String[] args) {
        mergeTwoNode();
    }

    /**
     * 合并K个有序链表(23)
     * 将K个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    private static void mergeTwoNode() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode listNode = mergeTwoLists(l1, l2);
        System.out.println("val:" + listNode.val);
    }

    private static ListNode mergeKgLists(ListNode[] lists) {
        int len = lists.length;

        // 开始合并
        return meg(0, len, lists);
    }

    private static ListNode meg(int left, int right, ListNode[] lists) {
        if(left == right){
            return lists[left];
        }
        if(left > right){
            return null;
        }

        int mid = left / 2 + (right - left) / 2;
//        return mergeTwoLists(meg())
        return null;
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 定义一个哨兵头部
        ListNode preHead = new ListNode(-1);
        // 这里导致了一个问题，新建的prev变量只是指向了preHead的地址，也就是preHead与prev同用一个地址
        ListNode prev = preHead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                // 这里prev指向的地址空间中的值变化，preHead也同步变化
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            // 这里开始prev的指向地址就开始变化了，不再是preHead的地址，而是其next的，也就导致preHead.next开始变化
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;
        // preHead指向的还是原地址空间，preHead的值一直没变，prev的值一直在向next迭代，最终只需要返回链表的头部即可，
        return preHead.next;
    }
}
