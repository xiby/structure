package tech.xiby.structure.tree;

import tech.xiby.structure.tree.domain.BinaryTreeNode;
import tech.xiby.structure.tree.domain.VisitMethod;
import tech.xiby.structure.tree.function.VisitFunction;

import java.util.Stack;

/**
 * 二叉树的基本操作
 *
 * @param <T>
 * @author xiby
 */
public class BinaryTree<T> {
    private BinaryTreeNode<T> root;

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

    public void preVisit(VisitFunction function) {
        preVisit(root, function);
    }

    /**
     * 非递归先序遍历
     *
     * @param function
     */
    public void preVisiUnRecursive(VisitFunction function) {
        if (root == null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode current = root;
        while (current != null || !stack.empty()) {
            if (current != null) {
                function.visit(current);
                stack.push(current);
                current = current.lchild;
            } else {
                current = stack.pop().rchild;
            }
        }
    }

    /**
     * 非递归中序遍历
     *
     * @param function
     */
    public void midVisitUnRecursive(VisitFunction function) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode current = root;
        while (current != null || !stack.empty()) {
            if (current != null) {
                stack.push(current);
                current = current.lchild;
            } else {
                current = stack.pop();
                function.visit(current);
                current = current.rchild;
            }
        }

    }

    void postVisitUnRecursive(VisitFunction function) {
        if (root == null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode current = root;
        BinaryTreeNode lastVisit = null;

        while (current != null || !stack.empty()) {
            if (current != null) {
                stack.push(current);
                current = current.lchild;
            } else {
                current = stack.peek();
                if (current.rchild == null) {
                    stack.pop();
                    function.visit(current);
                    lastVisit = current;
                    current = null;
                } else {
                    if (current.rchild == lastVisit) {
                        //右子树被访问完成
                        stack.pop();
                        function.visit(current);
                        lastVisit = current;
                        current = null;
                    } else {
                        current = current.rchild;
                    }
                }
            }
        }
    }

    /**
     * 先序遍历，从node节点开始遍历，但这样很麻烦
     */
    private void preVisit(BinaryTreeNode node, VisitFunction function) {
        if (node == null) {
            return;
        }
        function.visit(node);
        if (node.lchild != null) {
            preVisit(node.lchild, function);
        }
        if (node.rchild != null) {
            preVisit(node.rchild, function);
        }
    }
}
