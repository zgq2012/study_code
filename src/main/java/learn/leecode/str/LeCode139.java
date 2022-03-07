package learn.leecode.str;

import cn.hutool.core.collection.CollectionUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/3/3
 **/
public class LeCode139 {
    public static void main(String[] args) {
//        String s = "catdog";""
//        List<String> wordDict = CollectionUtil.newArrayList("eat", "cat", "dog");
        String s ="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = CollectionUtil.newArrayList("a","aa","aaa","aaaa","aaaaa","aaaaaa",
                "aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
//        boolean b = wordBreak(s, wordDict);
        boolean b = wordBreak2(s, wordDict);
        System.out.println("b = " + b);
    }

    private static boolean wordBreak2(String s, List<String> wordDict) {
        // 列表hash化，便于取值匹配
        Set<String> set = new HashSet<>(wordDict);
        // 动态规划：dp[i] = dp[j] && check(i,j)
        // 1、dp[j] == true 时，表示前j位字符串可以被满足组合
        // 2、check(i,j)校验字符串最后j位字符串是否在列表中
        // 3、若同时满足表示i内字符串满足条件
        boolean[] dp = new boolean[s.length() + 1];
        dp[0]  = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if(dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    /**
     * 超时算法：pei,垃圾
     */
    private static boolean wordBreak(String s, List<String> wordDict) {
        if ("".equals(s)) {
            return true;
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                boolean isTrue = wordBreak(s.substring(word.length()), wordDict);
                if (isTrue) {
                    return true;
                }
            }
        }

        return false;
    }
}
