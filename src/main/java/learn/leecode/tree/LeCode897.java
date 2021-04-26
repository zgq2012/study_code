package learn.leecode.tree;


import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 897. 递增顺序搜索树
 *
 * @author zgq
 */
public class LeCode897 {

    public static void main(String[] args) {
        TreeNode root20 = new TreeNode(20);
        root20.left = new TreeNode(15);
        root20.right = new TreeNode(25);
        root20.left.left = new TreeNode(10);
        root20.left.right = new TreeNode(18);
        root20.right.left = new TreeNode(23);
        root20.right.right = new TreeNode(29);

        TreeNode treeNode = increasingBst(root20);
        System.out.println(treeNode);
    }

    /**
     * (897) 递增顺序搜索树
     * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，
     * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
     * <p>
     * 提示：
     * 树中节点数的取值范围是 [1, 100]
     * 0 <= Node.val <= 1000
     */
    private static TreeNode leftNode = new TreeNode();

    public static TreeNode increasingBst(TreeNode root) {
        // 哨兵节点
        TreeNode dummyNode = new TreeNode(-1);
        // 滑动节点
        leftNode = dummyNode;
        getNode(root);
        return dummyNode.right;
    }

    private static void getNode(TreeNode root) {
        if (root == null) {
            return;
        }
        getNode(root.left);
        leftNode.right = root;
        root.left = null;
        leftNode = root;
        getNode(root.right);
    }
}
