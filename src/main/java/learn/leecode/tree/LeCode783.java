package learn.leecode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 783. 二叉搜索树节点最小距离
 *
 * @author zgq
 */
public class LeCode783 {

    public static void main(String[] args) {
//        TreeNode root2 = new TreeNode(2);
//        root2.left = new TreeNode(1);
//        root2.right = new TreeNode(3);
//        TreeNode root2 = new TreeNode(5);
//        root2.left = new TreeNode(1);
//        root2.right = new TreeNode(4);
//        root2.right.left = new TreeNode(3);
//        root2.right.right = new TreeNode(6);
        TreeNode root2 = new TreeNode(7);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(10);
        root2.right.left = new TreeNode(8);
        root2.right.right = new TreeNode(20);
        int diff = minDiffInBst(root2);
        int diff2 = minDiffInBst2(root2);
        System.out.println(diff);
        System.out.println(diff2);
    }

    /**
     * (783)二叉搜索树节点最小距离
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     */
    private static int minDiffInBst(TreeNode root) {
        // 中序遍历得到一个递增序列
        List<Integer> list = new ArrayList<>();
        inOrderTraversal(root, list);
        System.out.println(list);
        // 获取序列中2值的最小差值
        int min = Integer.MAX_VALUE;
        if (list.size() < 2) {
            return list.get(0);
        }
        for (int i = 1; i < list.size(); i++) {
            min = Math.min(min, list.get(i) - list.get(i - 1));
        }
        return min;
    }

    private static void inOrderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, list);
        list.add(root.val);
        inOrderTraversal(root.right, list);
    }

    public static int pre;
    public static int res;

    private static int minDiffInBst2(TreeNode root) {
        // 给比较直预赋予一个负值
        pre = -1;
        // 另最小值为Integer.MaxValue
        // 中序遍历得到一个递增序列
        res = Integer.MAX_VALUE;

        inOrderTraversal2(root);
        return res;
    }

    private static void inOrderTraversal2(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal2(root.left);
        // 比较获取最小值
        if(pre == -1){
            // 若进来的是跟节点直接赋值
            pre = root.val;
        }else {
            // 非根节点，获取最小值
            res = Math.min(res, root.val - pre);
            // 前置数据变更
            pre = root.val;
        }
        inOrderTraversal2(root.right);
    }
}
