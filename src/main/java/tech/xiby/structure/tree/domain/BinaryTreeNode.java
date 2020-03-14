package tech.xiby.structure.tree.domain;

/**
 * 二叉树结点定义
 *
 * @author xiby
 */
public class BinaryTreeNode<T> {
    /**
     * 节点取值
     */
    public T val;

    /**
     * 左孩子
     */
    public BinaryTreeNode lchild;

    /**
     * 右孩子
     */
    public BinaryTreeNode rchild;

    public BinaryTreeNode(T val) {
        this.val = val;
        lchild = null;
        rchild = null;
    }
}
