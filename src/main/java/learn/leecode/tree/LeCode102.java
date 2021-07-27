package learn.leecode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102
 *
 * @author zgq
 */
public class LeCode102 {

    public static void main(String[] args) {
        TreeNode root3 = new TreeNode(3);
        TreeNode root20 = new TreeNode(20);
        root20.left = new TreeNode(15);
        root20.right = new TreeNode(7);
        root3.left = new TreeNode(9);
        root3.right = root20;
        root3.left.left = new TreeNode();

        List<List<Integer>> deep = levelOrder(root3);
        System.out.println(deep);
    }

    /**
     * (102)二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * <p>
     * 示例：二叉树：[3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     *
     * @param root 树
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        // root == null 时，直接返回空数组
        if (root == null) {
            return ret;
        }

        // 采用队列存储树，利用队列性质来取数据以及剔除数据
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 如果队列不为空，表示还有数据
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            // 获取树的某一层级的count
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                // 抛出队列的头数据，
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                // 记录队列的值
                level.add(node.val);

                if (node.left != null) {
                    // 将抛出的节点的左子节点放到队尾
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    // 将抛出的节点的右子节点放到队尾
                    queue.offer(node.right);
                }
            }

            // 记录该层级的数据
            ret.add(level);
        }

        return ret;
    }
}
