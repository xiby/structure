package tech.xiby.structure.sort;

import org.junit.Test;

public class SorterTest {

    @Test
    public void bubbleSort() {
        Integer[] nums = {0, 2, 5, 3, 1, 7, 9, 8, 4, 6};
        Sorter.bubbleSort(nums, ((num1, num2) -> num1 > num2));
        for (int i = 0; i < nums.length; ++i) {
            System.out.println(nums[i]);
        }
    }
}