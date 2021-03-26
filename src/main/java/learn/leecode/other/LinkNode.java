package learn.leecode.other;

/**
 * 链表key-value类
 *
 * @author zgq
 */
public class LinkNode {
    int key;
    int val;
    LinkNode next;
    LinkNode pre;

    public LinkNode() {
    }

    public LinkNode(int key, int val) {
        this.val = val;
        this.key = key;
    }
}
