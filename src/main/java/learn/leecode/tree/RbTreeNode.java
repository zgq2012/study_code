package learn.leecode.tree;

/**
 * 红黑树的节点
 *
 * @author zgq
 * @version 1.0
 * @date 2021/3/8
 **/
public class RbTreeNode<T extends Comparable<T>> {
    /**
     * 节点颜色，黑色-true,红色-false,
     * 1.每个节点都有颜色，
     * 2.红黑树的根节点是黑色，
     * 3.每个叶子节点（NIL）都是黑色的
     * 4.如果一个节点是红色的，那么他的叶子节点一定是黑色的
     * 5.任意一个节点到该节点下的每个叶子节点的所有路径上包含相同数据的黑节点
     */
    boolean color;
    /**
     * 键值
     */
    T nodeKey;
    /**
     * 左子节点
     */
    RbTreeNode<T> left;
    /**
     * 右子节点
     */
    RbTreeNode<T> right;
    /**
     * 父节点
     */
    RbTreeNode<T> parent;

    public RbTreeNode(boolean color, T nodeKey, RbTreeNode<T> left, RbTreeNode<T> right, RbTreeNode<T> parent) {
        this.color = color;
        this.nodeKey = nodeKey;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return nodeKey.toString();
    }
}
