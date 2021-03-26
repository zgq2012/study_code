package learn.leecode.tree;

/**
 * 二叉树搜索
 *
 * @author zgq
 */
public class BstTree {
    public static void main(String[] args) {
        BsTreeNode<Integer> root = new BsTreeNode<>(10);
        root.left = new BsTreeNode<>(6);
        root.left.parent = root;
        root.left.left = new BsTreeNode<>(5);
        root.left.left.parent = root.left;
        root.left.right = new BsTreeNode<>(7);
        root.left.right.parent = root.left;
        root.right = new BsTreeNode<>(15);
        root.right.parent = root;
        root.right.left = new BsTreeNode<>(13);
        root.right.left.parent = root.right;
        root.right.right = new BsTreeNode<>(20);
        root.right.right.parent = root.right;

        // 中序遍历
        System.out.println("++++++++++++++++++++++++");
        root.getTreeListByInOrder(root);
        System.out.println("\r\n++++++++++++++++++++++++");
        // 二叉树搜索
        BsTreeNode<Integer> fixNode = root.getNode(root, 5);
        System.out.println(fixNode);
        BsTreeNode<Integer> fixNode2 = root.getNode(root, 100);
        System.out.println(fixNode2);
        System.out.println("++++++++++++++++++++++++");
        // 插入
        boolean b = root.addNode(root, new BsTreeNode<>(14));
        System.out.println("插入：" + b);
        root.getTreeListByInOrder(root);
        System.out.println("++++++++++++++++++++++++");
        // 删除
        boolean del = root.deleteNode(root, 6);
        System.out.println("删除：" + del);
        root.getTreeListByInOrder(root);
        System.out.println("++++++++++++++++++++++++");
    }


}
