package learn.leecode.tree;

import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 938. 二叉搜索树的范围和
 *
 * @author zgq
 */
public class LeCode938 {

    public static void main(String[] args) {
        TreeNode root3 = new TreeNode(10);
        root3.left = new TreeNode(5);
        root3.left.left = new TreeNode(3);
        root3.left.left.left = new TreeNode(1);
        root3.left.right = new TreeNode(7);
        root3.left.right.left = new TreeNode(6);

        root3.right = new TreeNode(15);
        root3.right.left = new TreeNode(13);
        root3.right.right = new TreeNode(18);

        int low = 6;
        int high = 10;
        int sum = rangeSumBst(root3, low, high);
        System.out.println(sum);
    }

    /**
     * (938)二叉搜索树的范围和
     * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     * <p>
     * 提示：
     * 树中节点数目在范围 [1, 2 * 10^4] 内
     * 1 <= Node.val <= 10^5
     * 1 <= low <= high <= 10^5
     * 所有 Node.val 互不相同
     */
    private static int sum = 0;

    public static int rangeSumBst(TreeNode root, int low, int high) {
        // 定义全局遍历来接收sum
        /*
        getVal(root, low, high);
        // 清空sum, 不然测试用例时静态变量sum不会被重置
        int res = sum;
        sum = 0;
        return res;
        */

        // 优化解2：满足条件直接返回和值
        return getSum(root, low, high);
    }

    private static int getSum(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        // 中序遍历
        // 若当前节点大于最大值，则只需要 左子树继续遍历
        if (root.val > high) {
            return getSum(root.left, low, high);
        } else if (root.val >= low && root.val <= high) {
            // 若当前节满足条件，则记录值，并继续左右子树遍历
            return root.val + getSum(root.left, low, high) + getSum(root.right, low, high);
        } else {
            // 若当前节点小于最小值，则只需要 右子树继续遍历
            return getSum(root.right, low, high);
        }
    }

    private static void getVal(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }
        // 中序遍历
        // 若当前节点大于最大值，则只需要 左子树继续遍历
        if (root.val > high) {
            getVal(root.left, low, high);
        } else if (root.val >= low && root.val <= high) {
            // 若当前节满足条件，则记录值，并继续左右子树遍历
            System.out.println("当前节点值：" + root.val);
            sum += root.val;
            getVal(root.left, low, high);
            getVal(root.right, low, high);
        } else {
            // 若当前节点小于最小值，则只需要 右子树继续遍历
            getVal(root.right, low, high);
        }
    }
}
