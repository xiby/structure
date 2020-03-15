package tech.xiby.structure.tree.domain;

/**
 * 哈夫曼树节点定义
 *
 * @author xiby
 */
public class HuffmanTreeNode<T> extends BinaryTreeNode<T> {
    /**
     * 权值
     */
    public double weight;

    /**
     * 数据字段
     */
    public T data;

    public HuffmanTreeNode(double weight, T data) {
        super(data);
        this.weight = weight;
    }
}
