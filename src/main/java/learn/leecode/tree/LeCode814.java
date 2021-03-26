package learn.leecode.tree;

/**
 * 814
 *
 * @author zgq
 */
public class LeCode814 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);
        TreeNode deep = pruneTree(root);
        System.out.println(deep.val);
    }

    /**
     * (814)二叉树剪枝
     * 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
     * 返回移除了所有不包含 1 的子树的原二叉树。( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
     * <p>
     * 示例1:
     * 输入: [1,null,0,0,1]  输出: [1,null,0,null,1]
     * <p>
     * 解释:
     * 只有红色节点满足条件“所有不包含 1 的子树”。右图为返回的答案。
     * <p>
     * 示例2:
     * 输入: [1,0,1,0,0,0,1]  输出: [1,null,1,null,1]
     * <p>
     * 示例3:
     * 输入: [1,1,0,1,1,0,1,0]   输出: [1,1,0,1,1,null,1]
     * <p>
     * 说明:
     * 给定的二叉树最多有 100 个节点。
     * 每个节点的值只会为 0 或 1 
     */
    private static TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 先删除叶节点
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        // 再判定当前节点是否要删除
        if (root.val == 0 && root.left == null && root.right == null) {
            root = null;
        }
        return root;
    }
}
