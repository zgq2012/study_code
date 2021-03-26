package learn.leecode.tree;


/**
 * 450
 *
 * @author zgq
 */
public class LeCode450 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);
        int key = 3;
        TreeNode deep = deleteNode(root, key);
        System.out.println(deep.val);
        System.out.println(deep.left.val);
        System.out.println(deep.right.val);
    }

    /**
     * (450)删除二叉搜索树中的节点
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
     * 返回二叉搜索树（有可能被更新）的根节点的引用。
     * <p>
     * 一般来说，删除节点可分为两个步骤：
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
     * <p>
     * 示例:
     * root = [5,3,6,2,4,null,7]
     * key = 3
     * 5
     * /   \
     * 3     6
     * /\    / \
     * 2  4  7  8
     * <p>
     * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     * <p>
     * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
     * 5
     * / \
     * 4   6
     * /     \
     * 2       7
     * <p>
     * 另一个正确答案是 [5,2,6,null,4,null,7]。
     * 5
     * / \
     * 2   6
     * \   \
     * 4   7
     */
    private static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        int rootVal = root.val;
        if (key < rootVal) {
            // 左节点寻值,并且替换原来的
            root.left = deleteNode(root.left, key);
        }
        if (key > rootVal) {
            // 右节点寻值,并且替换原来的
            root.right = deleteNode(root.right, key);
        }
        /*
        分类讨论：
        1.如果删除的节点为叶子节点（无左右子节点），那么直接删除就行
        2.如果删除的节点只有一个左子节点或者右子节点，那么直接删除后，使用左子节点或右子节点取代替删除节点
        3.如果删除的节点含有左右双子节点，那么用前置节点（比该节点小的最大节点）或后继节点（比该节点大的最小节点）取代替它，
          并且删除前置节点或后置节点（删除方法同理->递归）
         */
        if (key == rootVal) {
            // 该节点为删除节点
            if (root.left == null && root.right == null) {
                // 删除的节点为叶子节点
                root = null;
            } else if (root.left != null && root.right != null) {
                // 使用后继节点替代
                int successor = successor(root);
                // 需要替换root而不是root.right,因为传入的是root
                root = deleteNode(root, successor);
                root.val = successor;
            } else if (root.left != null) {
                // 左子节点在，右子节点不存在
                root = root.left;
            } else {
                // 右子节点在，左子节点不存在
                root = root.right;
            }
        }
        return root;
    }

    /**
     * 后继节点
     */
    private static int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    /**
     * 前置节点
     */
    private static int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }
}
