package learn.leecode.other;

import java.util.*;

/**
 * 752. 打开转盘锁
 *
 * @author zgq
 */
public class LeCode752 {

    public static void main(String[] args) {
        // ["1002","1220","0122","0112","0121"]
        //"1200"
        String[] deadends = {"1002","1220","0122","0112","0121"};
        String target = "1200";
        int res = openLock(deadends, target);
        System.out.println(res);
    }

    /**
     * (752) 打开转盘锁
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
     * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
     * <p>
     * 示例 1:
     * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * 输出：6
     * 解释：
     * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
     * 因为当拨动到 "0102" 时这个锁就会被锁定。
     * <p>
     * 示例 2:
     * 输入: deadends = ["8888"], target = "0009"
     * 输出：1
     * 解释：
     * 把最后一位反向旋转一次即可 "0000" -> "0009"。
     * <p>
     * 示例 3:
     * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     * 输出：-1
     * 解释：
     * 无法旋转到目标数字且不被锁定。
     * <p>
     * 示例 4:
     * 输入: deadends = ["0000"], target = "8888"
     * 输出：-1
     * <p>
     * 提示：
     * 死亡列表 deadends 的长度范围为 [1, 500]。
     * 目标数字 target 不会在 deadends 之中。
     * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
     */
    private static String t;
    private static String s;
    private static Set<String> set = new HashSet<>();

    public static int openLock(String[] deadends, String target) {
        s = "0000";
        t = target;
        if (s.equals(t)) {
            return 0;
        }

        set.addAll(Arrays.asList(deadends));

        return set.contains(s) ? -1 : bfs();
    }

    private static int bfs() {
        // d1 代表从起点 s 开始搜索（正向）
        Deque<String> d1 = new ArrayDeque<>();
        d1.addLast(s);

        // d2 代表从结尾 t 开始搜索（反向）
        Deque<String> d2 = new ArrayDeque<>();
        d2.addLast(t);

        /*
         * m1 和 m2 分别记录两个方向出现的状态是经过多少次转换而来
         * e.g.s
         * m1 = {"1000":1} 代表 "1000" 由 s="0000" 替换 1 次字符而来
         * m1 = {"9999":3} 代表 "9999" 由 t="9996" 替换 3 次字符而来
         */
        Map<String, Integer> m1 = new HashMap<>(2);
        m1.put(s, 0);

        Map<String, Integer> m2 = new HashMap<>(2);
        m2.put(t, 0);

        /*
         * 只有两个队列都不空，才有必要继续往下搜索
         * 如果其中一个队列空了，说明从某个方向搜到底都搜不到该方向的目标节点
         * e.g.
         * 例如，如果 d1 为空了，说明从 s 搜索到底都搜索不到 t，反向搜索也没必要进行了
         */
        while (!d1.isEmpty() && !d2.isEmpty()) {

            int t = d1.size() <= d2.size() ? update(d1, m1, m2) : update(d2, m2, m1);

            if (t != -1) {
                return t;
            }
        }
        return -1;
    }

    static int update(Deque<String> deque, Map<String, Integer> cur, Map<String, Integer> other) {
        String poll = deque.pollFirst();
        char[] pcs = poll.toCharArray();
        int step = cur.get(poll);
        // 枚举替换哪个字符
        for (int i = 0; i < 4; i++) {
            // 能「正向转」也能「反向转」，这里直接枚举偏移量 [-1,1] 然后跳过 0
            for (int j = -1; j <= 1; j++) {
                if (j == 0) {
                    continue;
                }

                // 求得替换字符串 str
                int next = (pcs[i] - '0' + j) % 10;
                if (next == -1) {
                    next = 9;
                }

                char[] clone = pcs.clone();
                clone[i] = (char) (next + '0');
                String str = String.valueOf(clone);

                if (set.contains(str) || cur.containsKey(str)) {
                    continue;
                }

                // 如果在「另一方向」找到过，说明找到了最短路，否则加入队列
                if (other.containsKey(str)) {
                    return step + 1 + other.get(str);
                }

                deque.addLast(str);
                cur.put(str, step + 1);
            }
        }
        return -1;
    }
}
