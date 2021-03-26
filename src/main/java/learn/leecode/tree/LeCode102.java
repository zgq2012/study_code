package learn.leecode.tree;


import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 102
 *
 * @author zgq
 */
public class LeCode102 {

    public static void main(String[] args) {
        TreeNode root3 = new TreeNode(3);
        TreeNode root20 = new TreeNode(20);
        root20.left = new TreeNode(15);
        root20.right = new TreeNode(7);
        root3.left = new TreeNode(9);
        root3.right = root20;

        List<List<Integer>> deep = levelOrder(root3);
        System.out.println(deep);
    }

    /**
     * (102)二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * <p>
     * 示例：二叉树：[3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     *
     * @param root 树
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> node = new ArrayList<>();
        node.add(root.val);
        result.add(node);
        setOther(root, result);
        return result;
    }

    private static void setOther(TreeNode root, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        List<Integer> node = new ArrayList<>();
        if (root.left != null) {
            node.add(root.left.val);
        }
        if (root.right != null) {
            node.add(root.right.val);
        }
        if (!CollectionUtil.isEmpty(node)) {
            result.add(node);
        }
        setOther(root.left, result);
        setOther(root.right, result);
    }
}
