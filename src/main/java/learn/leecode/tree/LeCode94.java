package learn.leecode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 *
 * @author zgq
 * @version v5.5.2
 * @since 2022/2/28
 **/
public class LeCode94 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5);


//        TreeNode root = new TreeNode(1);
//        root.right = new TreeNode(2);
//        root.right.left = new TreeNode(3);
//        List<Integer> integers = ecTree(root);
//        List<Integer> integers = dieDai(root);
        List<Integer> integers = inorderTraversal(root);
        System.out.println("integers = " + integers);
    }

    /**
     * Morris 遍历算法整体步骤如下（假设当前遍历到的节点为 x）：
     * 1: 如果 x 无左孩子，先将 x 的值加入答案数组，再访问 x 的右孩子，即 x=x.right。
     * 2: 如果 x 有左孩子，则找到 x 左子树上最右的节点（即左子树中序遍历的最后一个节点，x 在中序遍历中的前驱节点），我们记为predecessor。
     * <p>
     * 根据predecessor 的右孩子是否为空，进行如下操作。
     * 1.1: 如果predecessor 的右孩子为空，则将其右孩子指向 x，然后访问 x 的左孩子，即 x=x.left。
     * 1.2: 如果predecessor 的右孩子不为空，则此时其右孩子指向 x，说明我们已经遍历完 x 的左子树，
     *
     * 我们将predecessor 的右孩子置空，将 x 的值加入答案数组，然后访问 x 的右孩子，即 x=x.right。
     * 3: 重复上述操作，直至访问完整棵树。
     */
    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode predecessor;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    private static void ecTree(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }

        // 递归先遍历左边
        ecTree(res, root.left);
        // 存值
        res.add(root.val);
        // 递归再遍历右边
        ecTree(res, root.right);
    }

    private static void dieDai(List<Integer> res, TreeNode root) {
        // 迭代维护一个栈，将左边node的依次放入栈中，然后取出
        Deque<TreeNode> stk = new LinkedList<>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                // 从中间到左边依次放入栈中
                stk.push(root);
                root = root.left;
            }

            // 弹出栈中最后一个
            root = stk.pop();
            res.add(root.val);
            // 继续遍历树的右边
            root = root.right;
        }
    }
}
