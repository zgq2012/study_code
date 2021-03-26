package learn.leecode.tree;


/**
 * 700
 *
 * @author zgq
 */
public class LeCode700 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
         root.left = new TreeNode(2);
         root.right = new TreeNode(7);
         root.left.left = new TreeNode(1);
         root.left.right = new TreeNode(3);
        int val = 2;
        TreeNode deep = searchBst( root, val);
        System.out.println(deep.val);
    }

    /**
     * (700)二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。
     * 如果节点不存在，则返回 NULL。
     * <p>
     * 例如， 给定二叉搜索树:
     * 4
     * / \
     * 2   7
     * / \
     * 1   3
     * 和值: 2
     * 你应该返回如下子树:
     * 2
     * / \
     * 1   3
     * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
     */
    private static TreeNode searchBst(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        // 递归
        return val < root.val ? searchBst(root.left, val) : searchBst(root.right, val);
        // 迭代 空间复杂度缩减至O(1)
        /*
        while (root != null && val != root.val)
            root = val < root.val ? root.left : root.right;
        return root;
        */
    }
}
