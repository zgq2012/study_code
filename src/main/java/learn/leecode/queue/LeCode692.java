package learn.leecode.queue;

import cn.hutool.core.collection.CollectionUtil;

import java.util.*;

/**
 * 692. 前K个高频单词
 *
 * @author zgq
 */
public class LeCode692 {
    public static void main(String[] args) {
        int k = 2;
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> strings = topKcFrequent(words, k);
        System.out.println(strings);
    }

    /**
     * (692)前K个高频单词
     * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
     * <p>
     * 示例 1：
     * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * 输出: ["i", "love"]
     * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
     * 注意，按字母顺序 "i" 在 "love" 之前。
     *  
     * 示例 2：
     * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
     * 输出: ["the", "is", "sunny", "day"]
     * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
     * 出现次数依次为 4, 3, 2 和 1 次。
     *  
     * 注意：
     * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
     * 输入的单词均由小写字母组成。
     * <p>
     * 扩展练习：
     * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
     */
    private static List<String> topKcFrequent(String[] words, int k) {
        // 1.采用记忆化方式，map+sort排序
        List<String> list;
//        list =  firstSolve(words, k);
        // 采用优先队列的形式，设置k个对象的队列，将条件最次的放在头，每一次进来一个，就会剔除一个最小的
        list = twoSolve(words, k);
        return list;
    }

    private static List<String> twoSolve(String[] words, int k) {
        Map<String, Integer> cMap = new HashMap<>(words.length);
        for (String word : words) {
            // 设置单词出现次数，若之前没有出现则取默认的次数0
            cMap.put(word, cMap.getOrDefault(word, 0) + 1);
        }

        // 创建队列，并且直接设置存储对象为map的entry（便于取key与value），并且设置排序规则
        PriorityQueue<Map.Entry<String, Integer>> queue =
                new PriorityQueue<>((v1, v2) -> v1.getValue().equals(v2.getValue())
                        ? v1.getKey().compareTo(v2.getKey()) : v1.getValue() - v2.getValue());

        // 向队列中添加值
        for (Map.Entry<String, Integer> entry : cMap.entrySet()) {
            // 插入值
            queue.offer(entry);
            // 若队列长度大于k，则抛弃队头，队头数据最小
            if(queue.size() > k){
                queue.poll();
            }
        }

        List<String> list = new ArrayList<>();
        while (!queue.isEmpty()){
            list.add(queue.poll().getKey());
        }
        return list;
    }


    private static List<String> firstSolve(String[] words, int k) {
        Map<String, Integer> cMap = new HashMap<>(words.length);
        for (String word : words) {
            // 设置单词出现次数，若之前没有出现则取默认的次数0
            cMap.put(word, cMap.getOrDefault(word, 0) + 1);
        }
        // 获取去重之后的单词列
        List<String> wordRes = new ArrayList<>(cMap.keySet());
        // 排序，若出现次数相等，则以单词字母排序，若不等，则以出现次数决定排序
        wordRes.sort((v1, v2) -> cMap.get(v1).equals(cMap.get(v2)) ? v1.compareTo(v2) : cMap.get(v2) - cMap.get(v1));
        return wordRes.subList(0, k);
    }
}