package learn.leecode.tree;

/**
 * 208. 实现 Trie (前缀树)
 *
 * @author zgq
 */
public class LeCode208 {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("work");
        trie.insert("hard");
        trie.insert("love");
        trie.insert("you");
    }

    /**
     * (208)实现 Trie (前缀树)
     * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
     * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
     * <p>
     * 请你实现 Trie 类：
     * Trie() 初始化前缀树对象。
     * void insert(String word) 向前缀树中插入字符串 word 。
     * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
     * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
     * <p>
     * 示例：
     * 输入
     * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
     * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
     * 输出
     * [null, null, true, false, true, null, true]
     * <p>
     * 解释
     * Trie trie = new Trie();
     * trie.insert("apple");
     * trie.search("apple");   // 返回 True
     * trie.search("app");     // 返回 False
     * trie.startsWith("app"); // 返回 True
     * trie.insert("app");
     * trie.search("app");     // 返回 True
     * <p>
     * 提示：
     * 1 <= word.length, prefix.length <= 2000
     * word 和 prefix 仅由小写英文字母组成
     * insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次
     */
    private static class Trie {
        private Trie[] children;
        private boolean isEnd;

        /**
         * 一棵有根树，其每个节点包含以下字段：
         * 指向子节点的指针数组 children。对于本题而言，数组长度为 26，即小写英文字母的数量。
         * 此时children[0] 对应小写字母 a，children[1] 对应小写字母 b，…，children[25] 对应小写字母 z。
         * 布尔字段isEnd，表示该节点是否为字符串的结尾
         */
        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        /**
         * 插入字符串。对于当前字符对应的子节点，有两种情况：
         * 1.子节点存在。沿着指针移动到子节点，继续处理下一个字符。
         * 2.子节点不存在。创建一个新的子节点，记录在children 数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。
         * 重复以上步骤，直到处理字符串的最后一个字符，然后将当前节点标记为字符串的结尾。
         *
         * @param word 插入词
         */
        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        /**
         * 从字典树的根开始，查找前缀。对于当前字符对应的子节点，有两种情况：
         * 1.子节点存在。沿着指针移动到子节点，继续搜索下一个字符。
         * 2.子节点不存在。说明字典树中不包含该前缀，返回空指针。
         * 重复以上步骤，直到返回空指针或搜索完前缀的最后一个字符。
         *
         * @param word 查找词
         * @return boolean
         */
        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }
}
