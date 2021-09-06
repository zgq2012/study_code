package learn.leecode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1104. 二叉树寻路
 *
 * @author zgq
 */
public class LeCode1104 {

    public static void main(String[] args) {
        int label = 26;
        List<Integer> res = pathInZigZagTree(label);
        System.out.println("res = " + res);
    }

    /**
     * (1104) 二叉树寻路
     * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
     * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
     * * * * * * * * 1
     * * * * * * * 3   2
     * * * * * * 4  5 6  7
     * * * * . . . . . . . . .
     * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
     * <p>
     * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
     * <p>
     * 示例 1：
     * 输入：label = 14
     * 输出：[1,3,4,14]
     * <p>
     * 示例 2：
     * 输入：label = 26
     * 输出：[1,2,6,10,26]
     * <p>
     * 提示：
     * 1 <= label <= 10^6
     */

    public static List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();

        int deep = 1;
        // 获取该值在哪一行
        // 表示label在该行
        while (label < (1 << (deep - 1)) || label > (1 << deep) - 1) {
            deep++;
        }

        // 判断该行是单数（从小到大）还是偶数行（从大到小），然后获取label所在的索引位置
        int index = deep % 2 == 0 ? (1 << deep) - label : label - (1 << (deep - 1)) + 1;
        res.add(label);

        while (label != 1) {
            // 获取上级index
            index = index % 2 == 0 ? index / 2 : (index + 1) / 2;
            // 当前行为偶数时，上一行则是奇数（从小到大），label 值 为 2^deep + index - 1
            label = deep % 2 == 0 ? (1 << (deep - 1 - 1)) + index - 1 : (1 << (deep - 1)) - 1 - index + 1;
            deep--;
            res.add(label);
        }

        Collections.reverse(res);
        return res;
    }
}
