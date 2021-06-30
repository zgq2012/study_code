package learn.leecode.chaintable;

/**
 * 160. 相交链表
 *
 * @author zgq
 */
public class LeCode160 {

    public static void main(String[] args) {
        ListNode equalNode = new ListNode(8);
        equalNode.next = new ListNode(4);
        equalNode.next.next = new ListNode(5);

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = equalNode;

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(0);
        headB.next.next = new ListNode(1);
        headB.next.next.next = equalNode;


        ListNode resNode = getIntersectionNode(headA, headB);
        System.out.println(resNode == null ? 0 : resNode.val);
    }

    /**
     * 相交链表(160)
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
     * 图示两个链表在节点 c1 开始相交：
     * 题目数据 保证 整个链式结构中不存在环。
     * 注意，函数返回结果后，链表必须 保持其原始结构 。
     * <p>
     * 示例 1：
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Intersected at '8'
     * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
     * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     * <p>
     * 示例 2：
     * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Intersected at '2'
     * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
     * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     * <p>
     * 示例 3：
     * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * 输出：null
     * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
     * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
     * 这两个链表不相交，因此返回 null 。
     * <p>
     * 提示：
     * listA 中节点数目为 m
     * listB 中节点数目为 n
     * 0 <= m, n <= 3 * 10^4
     * 1 <= Node.val <= 10^5
     * 0 <= skipA <= m
     * 0 <= skipB <= n
     * 如果 listA 和 listB 没有交点，intersectVal 为 0
     * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
     */
    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // 相交节点比较的是节点是否是同一个，即 -> 地址值是否相等，而不是val相等
        ListNode tempA = headA;
        ListNode tempB = headB;
        // 一直循环，直至tempA与tempB相等，或者都为null
        while (tempA != tempB) {
            // 若tempA已经走到头了，则使用headB来继续交替比较，循环次数为 A + B ，
            // 到最后，若没找到新相等，则都会为null = null结束循环
            tempA = tempA == null ? headB : tempA.next;
            tempB = tempB == null ? headA : tempB.next;
        }

        return tempA;
    }
}
