package tech.xiby.structure.tree;

import org.junit.Test;
import tech.xiby.structure.tree.domain.VisitMethod;

public class BinaryTreeTest {

    private static Integer[] datas = {1, 2, 3, null, null, 4, null, 6, null, null, 7, 8, null, 9, null, null, null};

    @Test
    public void buildTest() {
        Integer[] nums = new Integer[3];
        nums[0] = 1;
        nums[1] = null;
        nums[2] = null;
        BinaryTree<Integer> tree = new BinaryTree<>(nums, VisitMethod.PRE_ORDER);
        System.out.println("构造完成");
    }

    @Test
    public void preVisitTest() {
        BinaryTree<Integer> tree = new BinaryTree<>(datas, VisitMethod.PRE_ORDER);
        tree.preVisit((node) -> System.out.println(node.val));
    }

}