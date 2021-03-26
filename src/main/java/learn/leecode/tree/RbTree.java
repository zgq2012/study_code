package learn.leecode.tree;

/**
 * 红黑树定义
 *
 * @author zgq
 * @version 1.0
 * @date 2021/3/8
 **/
public class RbTree<T extends Comparable<T>> {
    /**
     * 根节点
     */
    private RbTreeNode<T> mRoot;
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RbTree() {
        this.mRoot = null;
    }

    /**
     * 获取某个节点的父节点
     *
     * @param node 当前节点
     */
    private RbTreeNode<T> parentOf(RbTreeNode<T> node) {
        return node != null ? node.parent : null;
    }

    /**
     * 获取某个节点的颜色
     *
     * @param node 当前节点
     */
    private boolean colorOf(RbTreeNode<T> node) {
        return node != null ? node.color : BLACK;
    }

    /**
     * 判断某个节点是否为红色
     *
     * @param node 当前节点
     */
    private boolean isRed(RbTreeNode<T> node) {
        return node != null && !node.color;
    }

    /**
     * 判断某个节点是否为黑色
     *
     * @param node 当前节点
     */
    private boolean isBlack(RbTreeNode<T> node) {
        return node == null || node.color;
    }

    /**
     * 设置某个节点为黑色
     *
     * @param node 当前节点
     */
    private void setBlack(RbTreeNode<T> node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 设置某个节点为红色
     *
     * @param node 当前节点
     */
    private void setRed(RbTreeNode<T> node) {
        if (node != null) {
            node.color = RED;
        }
    }

    /**
     * 设置某个节点的父节点
     *
     * @param node   当前节点
     * @param parent 当前节点需要设置的父节点
     */
    private void setRed(RbTreeNode<T> node, RbTreeNode<T> parent) {
        if (node != null) {
            node.parent = parent;
        }
    }

    /**
     * 设置某个节点的颜色
     *
     * @param node  当前节点
     * @param color 节点颜色
     */
    private void setColor(RbTreeNode<T> node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    /**
     * 节点左旋
     *
     * @param node 旋转节点（支点）
     */
    private void leftRotate(RbTreeNode<T> node) {
        if (node == null) {
            return;
        }

        // 支点的右子节点
        RbTreeNode<T> rightNode = node.right;
        // 支点的右子节点的左子节点
        RbTreeNode<T> rightNodeLeft = rightNode.left;
        // 因为是左旋，支点的左子节点不需要改动，但右子节点要变更为原右子节点的左子节点
        node.right = rightNodeLeft;

        // 不为null时，修改leftNodeLeft的父节点为支点
        if (rightNodeLeft != null) {
            rightNodeLeft.parent = node;
        }

        // 修正支点原右子节点的父节点为支点的父节点
        rightNode.parent = node.parent;

        if (node.parent == null) {
            // 若支点就是根节点，则直接修改根节点为原右子节点
            this.mRoot = rightNode;
        } else {
            // 若支点不是根节点，则修改支点的父节点的其中子节点为原右子节点
            if (node.parent.left == node) {
                node.parent.left = rightNode;
            } else {
                node.parent.right = rightNode;
            }
        }

        // 修改支点的父节点
        rightNode.left = node;
        node.parent = rightNode;
    }

    /**
     * 节点右旋
     *
     * @param node 旋转节点（支点）
     */
    private void rightRotate(RbTreeNode<T> node) {
        if (node == null) {
            return;
        }

        /*
         1.支点右旋时，支点的右子节点不需要改动，只需要旋转左子节点
         2.修改支点的左子节点为原左子节点的右子节点
         3.修改支点的原父节点的其中子节点为原左子节点
         4.修改支点的父节点为原左子节点
         */
        RbTreeNode<T> leftNode = node.left;
        RbTreeNode<T> leftNodeRight = leftNode.right;

        // 修改支点的左子节点为原左子节点的右子节点
        node.left = leftNodeRight;

        if (leftNodeRight != null) {
            leftNodeRight.parent = node;
        }

        if (node.parent == null) {
            // 支点就是根节点,那直接设置原左子节点为根节点
            this.mRoot = leftNode;
        } else {
            // 将原左子节点挂在支点原父节点的下面
            if (node.parent.left == node) {
                node.parent.left = leftNode;
            } else {
                node.parent.right = leftNode;
            }
        }

        // 修改左子节点的父节点为支点的父节点
        leftNode.parent = node.parent;

        // 变更支点的父节点，以及原左子节点的左子节点
        node.parent = leftNode;
        leftNode.left = node;
    }

    /**
     * 红黑树插入节点
     *
     * @param root       树跟节点
     * @param insertNode 插入的节点
     * @return 是否插入成功
     */
    private boolean insertNode(RbTreeNode<T> root, RbTreeNode<T> insertNode) {
        if (insertNode == null) {
            return false;
        }

        if (root == null) {
            this.mRoot = insertNode;
            return true;
        }

        // 遍历找到该节点的插入位置
        if (root.nodeKey.compareTo(insertNode.nodeKey) > 0) {
            if (root.left == null) {
                // 若跟节点没有左子树，则在此插入，
                root.left = insertNode;
                insertNode.parent = root;
                insertNode.color = RED;
                // 并且旋转、变色调节树结构
                insertFixNode(insertNode);
                return true;
            } else {
                // 跟节点值大于插入节点，从root的左子树继续搜索
                return insertNode(root.left, insertNode);
            }
        } else if (root.nodeKey.compareTo(insertNode.nodeKey) < 0) {
            if (root.right == null) {
                root.right = insertNode;
                insertNode.parent = root;
                insertNode.color = RED;
                insertFixNode(insertNode);
                return true;
            } else {
                return insertNode(root.right, insertNode);
            }
        } else {
            return false;
        }
    }

    /**
     * 红黑树修正
     *
     * @param insertNode 修正节点
     */
    private void insertFixNode(RbTreeNode<T> insertNode) {
        // 1.若插入节点就是根节点，那么只需要改变节点的颜色为黑色就完了
        if (insertNode == this.mRoot) {
            this.setBlack(this.mRoot);
            return;
        }

        // 2.若插入节点的父节点本身就是黑色，那么则不管
        if (isBlack(parentOf(insertNode))) {
            return;
        }

        // 3.若其父节点为红色时，则需要修改节点颜色，并旋转
        // 定义父节点
        RbTreeNode<T> parent;
        // 定义祖父节点
        RbTreeNode<T> gParent;

        // 父节点为红色
        while (isRed((parent = parentOf(insertNode)))) {
            gParent = parentOf(parent);

            // 若父节点为祖父节点的左子树
            if (parent == gParent.left) {
                // 若当前节点的叔叔节点为红色时
                RbTreeNode<T> uncleNode = gParent.right;
                if (isRed(uncleNode)) {
                    // 将当前节点的叔叔节点设为黑色
                    setBlack(uncleNode);
                    // 将当前节点的父节点设为黑色
                    setBlack(parent);
                    // 将当前节点的祖父节点设为黑色
                    setRed(gParent);
                    // 将当前节点的祖父节点设为新的当前节点
                    insertNode = gParent;
                    continue;
                }
                // 若当前节点的叔叔节点为黑色，且当前插入节点是右子树
                if (parent.right == insertNode) {
                    RbTreeNode<T> temp;
                    // 以当前节点的父节点左旋
                    leftRotate(parent);
                    // 以当前节点的父节点作为新的当前节点继续操作
                    temp = parent;
                    insertNode = temp;
                    continue;
                }
                // 若当前节点是黑色，且为左子树
                setBlack(parent);
                setRed(gParent);
                // 右旋祖父节点
                rightRotate(gParent);
            } else {
                // 若当前节点的叔叔节点为红色时
                RbTreeNode<T> uncleNode = gParent.left;
                if (isRed(uncleNode)) {
                    // 将当前节点的叔叔节点设为黑色
                    setBlack(uncleNode);
                    // 将当前节点的父节点设为黑色
                    setBlack(parent);
                    // 将当前节点的祖父节点设为黑色
                    setRed(gParent);
                    // 将当前节点的祖父节点设为新的当前节点
                    insertNode = gParent;
                    continue;
                }
                // 若当前节点的叔叔节点为黑色，且当前插入节点是右子树
                if (parent.right == insertNode) {
                    RbTreeNode<T> temp;
                    // 以当前节点的父节点左旋
                    leftRotate(parent);
                    // 以当前节点的父节点作为新的当前节点继续操作
                    temp = parent;
                    insertNode = temp;
                    continue;
                }
                // 若当前节点是黑色，且为左子树
                setBlack(parent);
                setRed(gParent);
                // 右旋祖父节点
                rightRotate(gParent);
            }
        }
    }

    /**
     * 键值搜索节点
     *
     * @param root 搜索树
     * @param key  键值
     * @return 键值节点
     */
    private RbTreeNode<T> searchNode(RbTreeNode<T> root, T key) {
        if (root == null) {
            return null;
        }

        if (root.nodeKey.compareTo(key) > 0) {
            // 左子树搜索
            return searchNode(root.left, key);
        } else if (root.nodeKey.compareTo(key) < 0) {
            // 右子树搜索
            return searchNode(root.right, key);
        } else {
            // 搜索到键值的节点
            return root;
        }
    }

    /**
     * 红黑树指定键值删除节点
     *
     * @param key 需要删除的键值
     * @return 是否删除
     */
    public boolean remove(T key) {
        RbTreeNode<T> removeNode;
        if ((removeNode = searchNode(mRoot, key)) != null) {
            return remove(removeNode);
        }

        return false;
    }

    /**
     * 指定节点删除
     *
     * @param removeNode 删除节点
     * @return 是否删除
     */
    private boolean remove(RbTreeNode<T> removeNode) {
        if (removeNode == null) {
            return false;
        }
        RbTreeNode<T> parent = removeNode.parent;
        if (parent == null) {
            // 表示为根节点
            this.mRoot = null;
            return true;
        }

        // 1.删除节点叶子节点，
        if (removeNode.left == null && removeNode.right == null) {
            if (parent.left == removeNode) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return true;
        }
        // 2.删除的节点只有一个子节点
        if (removeNode.left == null) {
            // 2.1 右子节点不为空
            removeWithOneNode(removeNode, removeNode.right);
            return true;
        }
        if (removeNode.right == null) {
            // 2.2 左子节点不为空
            removeWithOneNode(removeNode, removeNode.left);
            return true;
        }
        // 3.删除的节点右2个子节点
        removeWithTwoChild(removeNode);

        return false;
    }

    private void removeWithTwoChild(RbTreeNode<T> removeNode) {
        // 找到当前节点的中序后继节点，称为取代节点
        RbTreeNode<T> right = removeNode.right;
        while (right.left != null) {
            right = right.left;
        }
        // 已找到后继节点，开始删除
        // 将后继节点的键值赋值给要删除的节点，
        removeNode.nodeKey = right.nodeKey;
        // 然后删除后继节点, 巧妙的将删除节点转换为删除后继节点，只需要递归就完事了
        remove(right);
    }

    private void removeWithOneNode(RbTreeNode<T> removeNode, RbTreeNode<T> oneChildNode) {
        RbTreeNode<T> parent = removeNode.parent;

        // 以删除节点的右子节点作为删除节点的新子节点
        if (parent.left == removeNode) {
            parent.left = oneChildNode;
        } else {
            parent.right = oneChildNode;
        }
        oneChildNode.parent = parent;

        // 若被删除的节点的颜色时红色，不会破坏红黑树的性质，不需要进行删除修复，
        // 只有当被删除节点时黑节点的时候才可能进行删除修复，
        if (removeNode.color) {
            removeFixUp(oneChildNode, parent);
        }
    }

    private void removeFixUp(RbTreeNode<T> removeNode, RbTreeNode<T> parent) {
        // case1:红+黑组合
        if (isRed(removeNode)) {
            setBlack(removeNode);
            return;
        }
        // case2:黑+黑组合,且删除节点是根节点
        if (isBlack(removeNode) && removeNode == this.mRoot) {
            return;
        }
        // case3:黑+黑组合,且非根节点
        RbTreeNode<T> other;
        while ((removeNode == null || isBlack(removeNode)) && (removeNode != this.mRoot)) {
            if (parent.left == removeNode) {
                other = parent.right;
                if (isRed(other)) {
                    // case3.1:removeNode的兄弟节点是红色的
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }
                if ((other.left == null || isBlack(other.left))
                        && (other.right == null || isBlack(other.right))) {
                    // case3.2:removeNode的兄弟节点是黑色，且其2个孩子节点也是黑色的
                    setRed(other);
                    removeNode = parent;
                    parent = parentOf(removeNode);
                } else {
                    if(other.right == null || isBlack(other.right)){
                        // case3.2:removeNode的兄弟节点是黑色，且其左孩子是红色，右孩子是黑色
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    removeNode = this.mRoot;
                    break;
                }
            } else {

            }
        }
    }
}
