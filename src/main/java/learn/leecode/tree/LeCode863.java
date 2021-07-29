package learn.leecode.tree;

import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 *
 * @author zgq
 */
public class LeCode863 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode target = root.left;
        int k = 2;
        List<Integer> res = distanceK(root, target, k);
        System.out.println("res = " + res);
    }

    /**
     * (863) 二叉树中所有距离为 K 的结点
     * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
     * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
     * <p>
     * 示例 1：
     * * * * 3
     * * * /  \
     * * 5     1
     * / \    / \
     * 6  2  0  8
     * * / \
     * *7  4
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
     * 输出：[7,4,1]
     * 解释：
     * 所求结点为与目标结点（值为 5）距离为 2 的结点，
     * 值分别为 7，4，以及 1
     * <p>
     * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
     * 上面的输入仅仅是对这些对象进行了序列化描述。
     * <p>
     * 提示：
     * 给定的树是非空的。
     * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
     * 目标结点 target 是树上的结点。
     * 0 <= K <= 1000.
     */
    private static Map<Integer, TreeNode> parentMap = new HashMap<>();
    private static List<Integer> res = new ArrayList<>();

    private static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 获取所有的父节点
        getParent(root);

        // 递归执行，添加参数来源节点，初始化为null, 用于判断是否重复执行
        getRes(target, null, 0, k);

        return res;
    }

    private static void getRes(TreeNode target, TreeNode form, int deep, int k) {
        // 节点为null，直接返回
        if (target == null) {
            return;
        }
        // 递归层数与距离相等时，记录该节点值
        if (deep == k) {
            res.add(target.val);
            return;
        }

        // 左子节点是否与来源节点相同，不相同则继续向下
        if (target.left != form) {
            getRes(target.left, target, deep + 1, k);
        }

        // 右子节点是否与来源节点相同，不相同则继续向下
        if (target.right != form) {
            getRes(target.right, target, deep + 1, k);
        }

        // 父节点是否与来源节点相同，不相同则向上查找
        if (parentMap.get(target.val) != form) {
            getRes(parentMap.get(target.val), target, deep + 1, k);
        }
    }

    private static void getParent(TreeNode root) {
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            getParent(root.left);
        }
        if (root.right != null) {
            parentMap.put(root.right.val, root);
            getParent(root.right);
        }
    }
}
