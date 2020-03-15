package tech.xiby.structure.tree;

import com.google.common.collect.Lists;
import javafx.util.Pair;
import org.junit.Test;

import java.util.List;

public class HuffmanTreeTest {

    @Test
    public void preVisit() {
        HuffmanTree<String> huffmanTree = getStringHuffmanTree();
        huffmanTree.preVisit((node) -> {
            if (node.val != null) {
                System.out.println(node.val + " ");
            }
        });
    }

    private HuffmanTree<String> getStringHuffmanTree() {
        List<Pair<String, Double>> datas = Lists.newArrayList(new Pair<>("A", 0.05),
                new Pair<>("B", 0.29), new Pair<>("C", 0.07), new Pair<>("D", 0.08),
                new Pair<>("E", 0.14), new Pair<>("F", 0.23), new Pair<>("G", 0.03),
                new Pair<>("H", 0.11));
        return new HuffmanTree<>(datas);
    }

    @Test
    public void huffmanCode() {
        HuffmanTree<String> huffmanTree = getStringHuffmanTree();
        System.out.println(huffmanTree.huffmanCode("0", "1"));
    }

    @Test
    public void huffmanDecode() {
        HuffmanTree<String> huffmanTree = getStringHuffmanTree();
        String code = "00000001001011011011101111";
        String decode = "GAHFBECD";
        List<String> result = huffmanTree.huffmanDecode(code, '0', '1');
        System.out.println(result);
    }
}