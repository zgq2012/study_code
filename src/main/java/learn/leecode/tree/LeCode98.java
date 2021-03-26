package learn.leecode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 98
 *
 * @author zgq
 */
public class LeCode98 {
    /**
     * 记录上一个节点的值，初始值为Long的最小值
     */
    private static long pre = Long.MIN_VALUE;

    public static void main(String[] args) {
//        TreeNode root2 = new TreeNode(2);
//        root2.left = new TreeNode(1);
//        root2.right = new TreeNode(3);
//        TreeNode root2 = new TreeNode(5);
//        root2.left = new TreeNode(1);
//        root2.right = new TreeNode(4);
//        root2.right.left = new TreeNode(3);
//        root2.right.right = new TreeNode(6);
        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(15);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(20);
        boolean deep = isValidBst(root2);
        System.out.println(deep);
    }

    /**
     * (98)验证二叉搜索树
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     * 输入:
     * 2
     * / \
     * 1   3
     * 输出: true
     * 示例 2:
     * 输入:
     * 10
     * / \
     * 5  15
     *          / \
     * 6   20
     * 输出: false  [10,5,15,null,null,6,20]
     * 解释: 输入为: [5,1,4,null,null,3,6]。 根节点的值为 5 ，但是其右子节点值为 4 。
     */
    private static boolean isValidBst(TreeNode root) {
        return inorder(root);
    }

    /**
     * 中序遍历,二叉搜索树遍历得到的结果一定是一个递增序列，若有值不大于前一个值则表示不是二叉搜索树
     */
    private static boolean inorder(TreeNode root) {
        /*if (root == null) {
            return true;
        }
        boolean l = inorder(root.left);
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        boolean r = inorder(root.right);
        return l && r;*/

        // 官方中序遍历解法
        Deque<TreeNode> stack = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                // 进栈
                stack.push(root);
                root = root.left;
            }
            // 出栈
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}
