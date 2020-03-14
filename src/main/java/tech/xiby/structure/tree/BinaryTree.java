package tech.xiby.structure.tree;

import tech.xiby.structure.tree.domain.BinaryTreeNode;
import tech.xiby.structure.tree.domain.VisitMethod;
import tech.xiby.structure.tree.function.VisitFunction;

/**
 * 二叉树的基本操作
 *
 * @param <T>
 * @author xiby
 */
public class BinaryTree<T> {
    BinaryTreeNode<T> root;

    private int index = 0;

    public BinaryTree() {
        root = null;
    }

    /**
     * 根据遍历方式来进行二叉树的构造
     *
     * @param datas
     * @param method
     */
    public BinaryTree(T[] datas, VisitMethod method) {
        if (datas.length != 0) {
            root = preBuild(datas);
        }
    }

    private BinaryTreeNode<T> preBuild(T[] datas) {
        if (index >= datas.length) {
            return null;
        }
        if (datas[index] == null) {
            index++;
            return null;
        }
        BinaryTreeNode node = new BinaryTreeNode(datas[index++]);
        node.lchild = preBuild(datas);
        node.rchild = preBuild(datas);
        return node;
    }

    public void preVisit(VisitFunction function){
        preVisit(root, function);
    }

    /**
     * 先序遍历，从node节点开始遍历，但这样很麻烦
     */
    private void preVisit(BinaryTreeNode node, VisitFunction function) {
        if(node == null){
            return;
        }
        function.visit(node);
        if(node.lchild != null) {
            preVisit(node.lchild, function);
        }
        if(node.rchild != null) {
            preVisit(node.rchild, function);
        }
    }
}
