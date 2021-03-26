package learn.leecode.tree;

/**
 * 二叉树
 *
 * @author zgq
 */
public class BsTreeNode<T extends Comparable<T>> {
    /**
     * 关键字（键值）
     */
    T nodeKey;
    /**
     * 左子节点
     */
    BsTreeNode<T> left;
    /**
     * 右子节点
     */
    BsTreeNode<T> right;
    /**
     * 父节点
     */
    BsTreeNode<T> parent;

    /**
     * 无参构造
     */
    public BsTreeNode() {
    }

    /**
     * 键值有参构造
     *
     * @param value 键值
     */
    public BsTreeNode(T value) {
        this.nodeKey = value;
    }

    /**
     * 重写toString()
     *
     * @return 键值的toString化
     */
    @Override
    public String toString() {
        return nodeKey.toString();
    }

    /**
     * 中序遍历：从左到右输出树的所有节点值
     *
     * @param root 跟节点
     */
    public void getTreeListByInOrder(BsTreeNode<T> root) {
        // 输入根节点，校验，根节点为空表示该树为空
        if (root == null) {
            return;
        }
        // 遍历左节点
        getTreeListByInOrder(root.left);
        // 左节点遍历完成输出节点值
        System.out.print(root.toString() + " ");
        getTreeListByInOrder(root.right);
    }

    /**
     * 查找该键值所在的节点
     *
     * @param root 根节点
     * @param key  要查找的键值
     * @return 键值所在的节点
     */
    public BsTreeNode<T> getNode(BsTreeNode<T> root, T key) {
        if (root == null) {
            // 为空，或者没找到直接返回null
            return null;
        }

        if (root.nodeKey.compareTo(key) > 0) {
            // 表示当前节点大于输入值，则从左子树查找
            return getNode(root.left, key);
        } else if (root.nodeKey.compareTo(key) < 0) {
            // 表示当前节点小于输入值，则从右子树查找
            return getNode(root.right, key);
        } else {
            // 等于时，直接返回
            return root;
        }
    }

    /**
     * 插入节点
     *
     * @param root       需要插入的所在树
     * @param insertNode 插入的节点
     */
    public boolean addNode(BsTreeNode<T> root, BsTreeNode<T> insertNode) {
        // 根节点为空时，插入节点作为根节点，直接返回
        if (root == null) {
            root = insertNode;
            return true;
        }

        // 查找插入节点的所在位置
        if (root.nodeKey.compareTo(insertNode.nodeKey) > 0) {
            // 表示当前节点大于输入值，则从左子树查找
            if (root.left == null) {
                // 表示其左子节点没有值，那么插入值就应该放在这里
                root.left = insertNode;
                insertNode.parent = root;
                return true;
            }
            // 有值，则继续查找插入位置
            return addNode(root.left, insertNode);
        } else if (root.nodeKey.compareTo(insertNode.nodeKey) < 0) {
            // 表示当前节点小于输入值，则从右子树查找
            if (root.right == null) {
                // 表示其右子节点没有值，那么插入值就应该放在这里
                root.right = insertNode;
                insertNode.parent = root;
                return true;
            }
            return addNode(root.right, insertNode);
        } else {
            // 等于时，直接返回
            return false;
        }
    }

    /**
     * 删除键值所在节点
     *
     * @param root 所在树
     * @param key  删除的键值
     */
    public boolean deleteNode(BsTreeNode<T> root, T key) {
        // 根节点值为空，或者未找到，直接返回false
        if (root == null) {
            return false;
        }
        // 查找该键值所在节点的位置
        if (root.nodeKey.compareTo(key) > 0) {
            // 表示当前节点大于输入值，则从左子树查找
            return deleteNode(root.left, key);
        } else if (root.nodeKey.compareTo(key) < 0) {
            // 表示当前节点小于输入值，则从右子树查找
            return deleteNode(root.right, key);
        }

        // 已找到该键值的节点时，判断节点状态，删除节点，返回
        /*
        节点状态：
            1.处于叶子节点，直接删除，
            2.处于树节点，删除，并且将该树节点的叶子节点重新分配，即寻找新的树节点挂上，
         */
        // 通过父节点来删除节点，否则可能产生对象销毁失败（因为外层对象引用没变）
        BsTreeNode<T> parent = root.parent;
        if (root.left == null && root.right == null) {
            // 2.1 叶子节点都为空
            if (parent.left == root) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return true;
        }
        // 2.2 若为树节点，且只有一个叶子节点，则用其下的叶子节点作为新的树节点
        if (root.left == null) {
            root.right.parent = root.parent;
            if (parent.left == root) {
                parent.left = root.right;
            } else {
                parent.right = root.right;
            }
            return true;
        }
        if (root.right == null) {
            root.left.parent = root.parent;
            if (parent.left == root) {
                parent.left = root.left;
            } else {
                parent.right = root.left;
            }
            return true;
        }

        // 2.3 若左右子节点都有，那么要么采用前驱节点为新树节点，要么采用后继节点为新树节点
        // 获取前驱节点,左子节点树的最右边的节点
        BsTreeNode<T> left = root.left;
        while (left.right != null) {
            left = left.right;
        }
        // 将当前节点值替换为前驱节点的值，
        root.nodeKey = left.nodeKey;
        // 删除前驱节点
        deleteNode(left, left.nodeKey);
        // 删除完成
        return true;
    }
}