package tech.xiby.structure.tree.function;

import tech.xiby.structure.tree.domain.BinaryTreeNode;

/**
 * 访问抽象
 *
 * @author xiby
 */
public interface VisitFunction {
    /**
     * 访问一个节点
     *
     * @param node
     */
    void visit(BinaryTreeNode node);
}
