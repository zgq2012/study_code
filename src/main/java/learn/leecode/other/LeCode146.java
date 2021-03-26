package learn.leecode.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 146
 *
 * @author zgq
 */
public class LeCode146 {
    public static void main(String[] args) {
        /*
        LruCache lruCache = new LruCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
        */
        // [[1],[2,1],[2],[3,2],[2],[3]]
        /*LruCache lruCache = new LruCache(1);
        lruCache.put(2, 1);
        System.out.println(lruCache.get(2));
        lruCache.put(3, 2);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));*/
        // [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
        /*LruCache lruCache = new LruCache(2);
        lruCache.put(2, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(2));
        lruCache.put(1, 1);
        lruCache.put(4, 1);
        System.out.println(lruCache.get(2));*/
        // [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        /*LruCache lruCache = new LruCache(2);
        System.out.println(lruCache.get(2));
        lruCache.put(2, 6);
        System.out.println(lruCache.get(1));
        lruCache.put(1, 5);
        lruCache.put(1, 2);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));*/
        // [[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
        LruCache lruCache = new LruCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(1));
        lruCache.put(5, 5);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.get(5));
    }

    /**
     * (146)LRU 缓存机制
     * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
     * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     * <p>
     * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
     * <p>
     * 示例：
     * 输入:
     * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
     * 输出:
     * [null, null, null, 1, null, -1, null, -1, 3, 4]
     * 示例2：
     * 输入:
     * ["LRUCache","put","get","put","get","get"]
     * [[1],[2,1],[2],[3,2],[2],[3]]
     * 输出:
     * [null, null, null, 1, null, -1, null, -1, 3, 4]
     * 解释:
     * LRUCache lRUCache = new LRUCache(2);
     * lRUCache.put(1, 1); // 缓存是 {1=1}
     * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
     * lRUCache.get(1);    // 返回 1
     * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
     * lRUCache.get(2);    // 返回 -1 (未找到)
     * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
     * lRUCache.get(1);    // 返回 -1 (未找到)
     * lRUCache.get(3);    // 返回 3
     * lRUCache.get(4);    // 返回 4
     * <p>
     * 提示：
     * 1 <= capacity <= 3000    0 <= key <= 3000    0 <= value <= 104
     * 最多调用 3 * 104 次 get 和 put
     */
    static class LruCache {
        int capacity;
        int size;
        LinkNode start;
        LinkNode end;

        Map<Integer, LinkNode> cache = new HashMap<>();

        public LruCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            start = new LinkNode();
            end = new LinkNode();
            // 使用伪头部和伪尾部节点
            start.next = end;
            end.pre = start;
        }

        public int get(int key) {
            LinkNode valNode = cache.get(key);
            if (valNode == null) {
                // 没有key直接返回
                return -1;
            }
            // 移动置头部
            this.reSetDict(valNode);
            return valNode.val;
        }

        public void put(int key, int val) {
            LinkNode valNode = cache.get(key);
            if (valNode == null) {
                // 表示不存在，进行直接添加操作
                valNode = new LinkNode(key, val);
                size++;
                cache.put(key, valNode);
                this.addToHead(valNode);
                if (size > capacity) {
                    // 超出长度，去掉尾部的节点,并且返回移除的节点！！不然不知道cache将要移除谁
                    LinkNode linkNode = this.deleteEndNode();
                    // map也去除
                    cache.remove(linkNode.key);
                    size--;
                }
            } else {
                // 表示存在，那么进行更新值，然后位置调整
                valNode.val = val;
                this.reSetDict(valNode);
            }
        }

        private LinkNode deleteEndNode() {
            LinkNode res = end.pre;
            removeNode(res);
            return res;
        }

        private void reSetDict(LinkNode valNode) {
            // 先删除该节点
            this.removeNode(valNode);
            // 然后在头部添加
            this.addToHead(valNode);
        }

        private void removeNode(LinkNode valNode) {
            valNode.pre.next = valNode.next;
            valNode.next.pre = valNode.pre;
        }

        private void addToHead(LinkNode valNode) {
            valNode.next = start.next;
            valNode.pre = start;
            start.next.pre = valNode;
            start.next = valNode;
        }
    }
}