package learn.leecode.chaintable;

/**
 * 1669
 *
 * @author zhuguangqain
 */
public class LeCode1669 {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(0);
        list1.next = new ListNode(1);
        list1.next.next = new ListNode(2);
        list1.next.next.next = new ListNode(3);
        list1.next.next.next.next = new ListNode(4);
        list1.next.next.next.next.next = new ListNode(5);

        int a = 3;
        int b = 4;
        ListNode list2 = new ListNode(100000);
        list2.next = new ListNode(100001);
        list2.next.next = new ListNode(100002);
        ListNode res = mergeInBetween(list1, a, b, list2);
        while (res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    /**
     * 合并两个链表（1669）
     * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
     * 请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置
     */
    private static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // 记录下标
        int idx = 0;
        // 作为头节点
        ListNode head = new ListNode(-1);
        // 作为temp节点
        ListNode pre = head;

        // 拼接[0, a)
        while (idx < a && list1 != null) {
            pre.next = list1;
            pre = pre.next;
            list1 = list1.next;
            idx++;
        }

        // 拼接 list2
        while (list2 != null) {
            pre.next = list2;
            pre = pre.next;
            list2 = list2.next;
        }

        // 删除[a, b]
        while (idx <= b && list1 != null) {
            list1 = list1.next;
            idx++;
        }
        // 拼接(b, +∞]
        pre.next = list1;

        return head.next;
    }

}
