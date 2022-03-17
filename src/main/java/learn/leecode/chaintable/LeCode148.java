package learn.leecode.chaintable;

/**
 * 148. 排序链表
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/8
 **/
public class LeCode148 {
    public static void main(String[] args) {
        ListNode head = new ListNode(-1);
        int[] nums = {-1, 5, 3, 4, 0};
        ListNode temp = head;
        for (int num : nums) {
            temp.next = new ListNode(num);
            temp = temp.next;
        }

        // ListNode listNode = sortList(head.next);
        // 自底向上的归并
        ListNode listNode = sortList2(head.next);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

    private static ListNode sortList2(ListNode head) {
        if (head == null) {
            return null;
        }

        // 记录链表的长度length
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        // 创建哨兵节点
        ListNode dummyHead = new ListNode(0, head);
        // 开始归并，subLength -> 每次归并的子链表长度，subLength 每次循环乘以2
        for (int subLength = 1; subLength < length; subLength <<= 1) {

            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }

                // 合并2链表
                prev.next = merge(head1, head2);

                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * 链表中节点的数目在范围 [0, 5 * 104] 内
     * -10^5 <= Node.val <= 10^5
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下
     */
    private static ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    /**
     * 时间复杂度满足，空间复杂度，每次都new了节点也为log(n)
     */
    private static ListNode sortList(ListNode head, ListNode tail) {
        // 头为null,直接返回
        if (head == null) {
            return null;
        }
        // 节点只有2个了，直接返回
        if (head.next == tail) {
            head.next = null;
            return head;
        }

        // 由于时间复杂度是n*log(n),那就需要采用归并或2分
        // 归并进行排序，寻找head的中间节点
        // 采用快慢指针来在寻找，fast走2步，low走1步，fast走到头，low就达到中间节点
        ListNode fast = head;
        ListNode low = head;
        // 快指针不等于结尾节点
        while (fast != tail) {
            // 快慢指针不同步长移动
            fast = fast.next;
            low = low.next;
            // 走到头即停止
            if (fast != tail) {
                fast = fast.next;
            }
        }

        // 开始递归,使用新的中间节点来执行
        ListNode mid = low;
        ListNode left = sortList(head, mid);
        ListNode right = sortList(mid, fast);

        // 左右到底层，开始合并
        return merge(left, right);
    }

    private static ListNode merge(ListNode head, ListNode tail) {
        ListNode root = new ListNode(-1);
        ListNode temp = root;
        ListNode temp1 = head;
        ListNode temp2 = tail;

        // 合并时，都不为空，temp依次放入小的节点，并且移动放入的原节点
        while (temp1 != null && temp2 != null) {
            if (temp1.val > temp2.val) {
                temp.next = temp2;
                temp2 = temp2.next;
            } else {
                temp.next = temp1;
                temp1 = temp1.next;
            }

            temp = temp.next;
        }

        // 节点收尾，存在不为空的直接放置到节点后面
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }

        // 返回哨兵节点的next
        return root.next;
    }
}
