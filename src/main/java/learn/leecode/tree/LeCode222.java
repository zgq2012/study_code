package learn.leecode.tree;

/**
 * 222
 *
 * @author zgq
 */
public class LeCode222 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        // 二分搜索最后一层
        int deep = countNodes(root);
        // 优解
        int deep2 = countNodes2(root);
        System.out.println(deep);
        System.out.println(deep2);
    }

    /**
     * (222)完全二叉树的节点个数
     * 给出一个完全二叉树，求出该树的节点个数。
     * <p>
     * 说明：
     * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
     * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     * <p>
     * 示例:
     * 输入:
     * 1
     * / \
     * 2   3
     * / \  /
     * 4  5 6
     * <p>
     * 输出: 6
     */
    public static int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 只存在2中情况，1.左右深度相等->左边必定是满二叉 2.左右不等，那么右子树必是满二叉
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if (left == right) {
            // 1.左右深度相等->左边必定是满二叉
            return countNodes(root.right) + (1 << left);
        } else {
            // 2.左右不等，那么右子树必是满二叉
            return countNodes(root.left) + (1 << right);
        }
    }

    private static int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }

    private static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 获取完全二叉树的深度
        int deep = deepNode(root);
        if (deep == 0) {
            return 1;
        }

        // 计算最下一层级的节点数
        int left = 1;
        int right = (int) Math.pow(2, deep) - 1;
        int pivot;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (isExistNode(root, pivot, deep)) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }

        // 完全二叉树的节点总数 = 他的满二叉树节点数（2的(deep -1)次方 - 1） + 最下一层级的节点数(deep层的节点数1~2*deep)
        return (int) Math.pow(2, deep) - 1 + left;
    }

    private static boolean isExistNode(TreeNode node, int idx, int d) {
        int left = 0;
        int right = (int) Math.pow(2, d) - 1;
        int pivot;
        for (int i = 0; i < d; ++i) {
            pivot = left + (right - left) / 2;
            if (idx <= pivot) {
                node = node.left;
                right = pivot;
            } else {
                node = node.right;
                left = pivot + 1;
            }
        }
        return node != null;
    }

    private static int deepNode(TreeNode root) {
        int deep = 0;
        while (root.left != null) {
            root = root.left;
            deep++;
        }
        return deep;
    }
}
