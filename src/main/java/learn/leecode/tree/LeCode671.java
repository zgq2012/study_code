package learn.leecode.tree;

/**
 * 671. 二叉树中第二小的节点
 *
 * @author zgq
 */
public class LeCode671 {

    public static void main(String[] args) {
//        TreeNode root2 = new TreeNode(2);
//        root2.left = new TreeNode(2);
//        root2.right = new TreeNode(5);
//        root2.right.left = new TreeNode(5);
//        root2.right.right = new TreeNode(7);

//        root2.left.left = new TreeNode(1);
//        root2.left.right = new TreeNode(2);

//        TreeNode root2 = new TreeNode(5);
//        root2.left = new TreeNode(8);
//        root2.right = new TreeNode(5);

        TreeNode root2 = new TreeNode(2147483646);
        root2.left = new TreeNode(2147483647);
        root2.right = new TreeNode(2147483646);

        int res = findSecondMinimumValue(root2);
        System.out.println("res = " + res);
    }

    /**
     * (671) 二叉树中第二小的节点
     * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
     * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
     * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
     * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
     * <p>
     * 示例 1：
     * 输入：root = [2,2,5,null,null,5,7]
     * 输出：5
     * 解释：最小的值是 2 ，第二小的值是 5 。
     * <p>
     * 示例 2：
     * 输入：root = [2,2,2]
     * 输出：-1
     * 解释：最小的值是 2, 但是不存在第二小的值。
     * <p>
     * 提示：
     * 树中节点数目在范围 [1, 25] 内
     * 1 <= Node.val <= 2^31 - 1
     * 对于树中每个节点 root.val == min(root.left.val, root.right.val)
     */
    private static int firstMin = Integer.MAX_VALUE;
    private static int secMin = -1;

    private static int findSecondMinimumValue(TreeNode root) {

        dfs(root);

        return secMin == firstMin ? -1 : secMin;
    }

    private static void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(root.left);

        if (firstMin > root.val) {
            if (secMin >= firstMin) {
                secMin = firstMin;
            }

            if (secMin == -1) {
                secMin = root.val;
            }
            firstMin = root.val;
        } else if (firstMin < root.val) {
            if (secMin == firstMin || secMin > root.val) {
                secMin = root.val;
            }
        } else {
            if (secMin == -1) {
                secMin = root.val;
            }
        }

        dfs(root.right);
    }
}
