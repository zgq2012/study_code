package learn.leecode.tree;

/**
 * 104
 *
 * @author zgq
 */
public class LeCode104 {

    public static void main(String[] args) {
        TreeNode root20 = new TreeNode(20);
        root20.left = new TreeNode(15);
        root20.right = new TreeNode(7);
        root20.right.right = new TreeNode(10);
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(9);
        root3.right = root20;

        int deep = maxDepth(root3);
        System.out.println(deep);
    }

    /**
     * (104)二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度 3 。
     *
     * @param root3 树
     */
    public static int maxDepth(TreeNode root3) {
        if (root3 == null) {
            return 0;
        }
        // 当前节点的最大深度一定是左节点与右节点的最大值+1
        return Math.max(maxDepth(root3.left) + 1, maxDepth(root3.right) + 1);
    }
}
