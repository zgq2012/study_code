package learn.leecode.tree;

/**
 * 110
 *
 * @author zgq
 */
public class LeCode110 {
    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(4);
        boolean isBalanced = isBalanced(root);
        System.out.println(isBalanced);
    }

    /**
     * (110)平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * <p>
     * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     * <p>
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]  输出：true
     * <p>
     * 示例 2：
     * 输入：root = [1,2,2,3,3,null,null,4,4]  输出：false
     * <p>
     * 示例 3：
     * 输入：root = []  输出：true
     * <p>
     * 提示：树中的节点数在范围 [0, 5000] 内，  -104 <= Node.val <= 104
     */
    private static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        //
        // 自上而下计算深度，每一层都会去计算子节点深度
        // 计算左右深度
//        if (Math.abs(deepNode(root.left) - deepNode(root.right)) > 1) {
//            // 相差1以上直接false
//            return false;
//        } else {
//            // 继续检验左右子节点
//            return isBalanced(root.left) && isBalanced(root.right);
//        }


        // 自下向上计算，及时止损，优解
        return height(root) >= 0;
    }

    private static int deepNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 当前节点的最大深度一定是左节点与右节点的最大值+1
        return Math.max(deepNode(root.left) + 1, deepNode(root.right) + 1);
    }

    private static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight;
        int rightHeight;
        // 左子节点false则不需要继续
        if ((leftHeight = height(root.left)) == -1
                || (rightHeight = height(root.right)) == -1
                || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
