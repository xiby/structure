package tech.xiby.structure.tree;

import com.google.common.collect.Lists;
import javafx.util.Pair;
import tech.xiby.structure.tree.domain.BinaryTreeNode;
import tech.xiby.structure.tree.domain.HuffmanTreeNode;
import tech.xiby.structure.tree.function.VisitFunction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * 哈夫曼树的定义
 *
 * @author xiby
 */
public class HuffmanTree<T> {

    private HuffmanTreeNode<T> root;

    /**
     * 根据数组构造一棵哈夫曼树
     *
     * @param datas
     */
    public HuffmanTree(List<Pair<T, Double>> datas) {
        List<NodeContainer> nodes = new ArrayList<>();
        for (Pair<T, Double> pair : datas) {
            HuffmanTreeNode node = new HuffmanTreeNode(pair.getValue(), pair.getKey());
            NodeContainer container = new NodeContainer(node);
            nodes.add(container);
        }
        //根据权值降序排列
        nodes.sort(Comparator.comparing(NodeContainer::getWeight));
        while (nodes.size() > 1) {
            NodeContainer container1 = nodes.get(0);
            nodes.remove(0);
            NodeContainer container2 = nodes.get(0);
            nodes.remove(0);
            NodeContainer newContainer = new NodeContainer(container1, container2);
            nodes.add(newContainer);
            nodes.sort(Comparator.comparing(NodeContainer::getWeight));
        }
        root = nodes.get(0).node;
    }

    public void preVisit(VisitFunction function) {
        preVisit(root, function);
    }

    /**
     * 进行哈夫曼编码
     *
     * @return
     */
    public List<Pair<T, String>> huffmanCode(String left, String right) {
        List<Pair<T, String>> result = Lists.newArrayList();
        Stack<String> stack = new Stack<>();
        doCode(root, left, right, result, stack);
        return result;
    }

    public List<T> huffmanDecode(String code, Character left, Character right) {
        BinaryTreeNode<T> current = root;
        int index = 0;
        List<T> result = Lists.newArrayList();
        while (index <= code.length()) {
            if (current == null) {
                throw new IllegalArgumentException();
            }
            if (current.isLeafNode()) {
                result.add(current.val);
                if (index >= code.length()) {
                    break;
                }
                current = root;
                continue;
            }
            Character ch = code.charAt(index++);
            if (left.equals(ch)) {
                current = current.lchild;
            } else if (right.equals(ch)) {
                current = current.rchild;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return result;
    }

    /**
     * 递归进行哈夫曼编码
     *
     * @param left
     * @param right
     * @param result
     * @param current
     */
    private void doCode(BinaryTreeNode<T> node, String left, String right, List<Pair<T, String>> result, Stack<String> current) {
        if (node.isLeafNode()) {
            StringBuilder builder = new StringBuilder();
            for (String status : current) {
                builder.append(status);
            }
            result.add(new Pair<>(node.val, builder.toString()));
            return;
        }
        current.push(left);
        doCode(node.lchild, left, right, result, current);
        current.pop();
        current.push(right);
        doCode(node.rchild, left, right, result, current);
        current.pop();
    }


    private void preVisit(BinaryTreeNode<T> node, VisitFunction function) {
        if (node == null) {
            return;
        }
        function.visit(node);
        preVisit(node.lchild, function);
        preVisit(node.rchild, function);
    }

    /**
     * 节点容器
     */
    private class NodeContainer {
        public HuffmanTreeNode<T> node;
        public double weight;

        public NodeContainer(HuffmanTreeNode<T> node) {
            this.node = node;
            this.weight = node.weight;
        }

        public NodeContainer(HuffmanTreeNode<T> node, double weight) {
            this.node = node;
            this.weight = weight;
        }

        public NodeContainer(NodeContainer container1, NodeContainer container2) {
            double totle = container1.weight + container2.weight;
            this.node = new HuffmanTreeNode<>(totle, null);
            node.lchild = container1.node;
            node.rchild = container2.node;
            weight = totle;
        }

        public double getWeight() {
            return weight;
        }
    }
}
