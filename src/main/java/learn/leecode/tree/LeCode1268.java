package learn.leecode.tree;

import java.util.*;

/**
 * TODO
 *
 * @author zgq
 * @version v5.6.3
 * @since 2023/7/10
 **/
public class LeCode1268 {
    public static void main(String[] args) {
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";
        List<List<String>> lists = suggestedProducts(products, searchWord);
        System.out.println("lists = " + lists);
    }

    private static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // 构建前缀树
        Trie root = new Trie();
        for (String product : products) {
            root.insert(product);
        }

        // 搜索前缀
        List<List<String>> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < searchWord.length(); i++) {
            // 获取搜索前缀
            sb.append(searchWord.charAt(i));
            String pre = sb.toString();

            Trie temp = root;
            PriorityQueue<String> pr = null;

            for (int j = 0; j < pre.length(); j++) {
                char c = pre.charAt(j);
                Trie next = temp.child.get(c);
                if (next == null) {
                    // 表示当前字符没有
                    System.out.println("break:" + c);
                    break;
                }

                pr = temp.preProducts.get(c);
                temp = next;
            }

            // 记录当前前缀的前3个字符串
            List<String> tempList = new ArrayList<>();
            // 将当前前缀的队列中的字符串放入结果集
            while (pr != null && !pr.isEmpty()) {
                tempList.add(0, pr.poll());
            }

            if (i == 2) {
                System.out.println("pre:" + pre);
            }
            res.add(tempList);
        }

        return res;
    }

    static class Trie {
        // 当前字符连接的所有字符
        private Map<Character, Trie> child;
        // 存放当前前缀字典序前三的字符串
        private Map<Character, PriorityQueue<String>> preProducts;

        public Trie() {
            this.child = new HashMap<>();
            // 构造大顶堆, 字典序最大的最先被移除
//            this.preProducts = new PriorityQueue<>(Collections.reverseOrder());
            this.preProducts = new HashMap<>();
        }

        // 向前缀树中添加元素
        public void insert(String product) {
            Trie cur = this;
            for (int i = 0; i < product.length(); i++) {
                char c = product.charAt(i);
                // 当前是否存有该字符
                Trie childTrie = cur.child.getOrDefault(c, new Trie());
                // 设置当前字符子节点
                cur.child.put(c, childTrie);

                // 当前字符集成该字符串
                PriorityQueue<String> childPq = cur.preProducts.getOrDefault(c,
                        new PriorityQueue<>(Collections.reverseOrder()));
                childPq.offer(product);
                if (childPq.size() > 3) {
                    // 若队列长度大于3了，则抛去队头
                    childPq.poll();
                }
                cur.preProducts.put(c, childPq);

                // 更新节点
                cur = childTrie;
            }
        }
    }
}
