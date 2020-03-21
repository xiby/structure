package tech.xiby.structure.sort;

import org.junit.Test;

import java.util.Random;

public class SorterTest {

    @Test
    public void bubbleSort() {
        Integer[] nums = {0, 2, 5, 3, 1, 7, 9, 8, 4, 6};
        Sorter.bubbleSort(nums, ((num1, num2) -> num1 > num2));
        for (int i = 0; i < nums.length; ++i) {
            System.out.println(nums[i]);
        }
    }

    @Test
    public void quickSort() {
        Integer[] nums = new Integer[10];
        for (int i = 0; i < 10; ++i) {
            nums[i] = new Random().nextInt(100);
        }
        Sorter.quickSort(nums, ((num1, num2) -> num1 >= num2));
        for (int i = 0; i < nums.length; ++i) {
            System.out.println(nums[i]);
        }
    }

    @Test
    public void compair() {
        int count = 1000;
        //对100组数据进行排序
        Integer[][] datas1 = new Integer[count][];
        Integer[][] datas2 = new Integer[count][];
        //排序100次
        int size = 1000;

        //首先构造数据
        for (int i = 0; i < count; ++i) {
            datas1[i] = new Integer[size];
            datas2[i] = new Integer[size];
            for (int j = 0; j < size; ++j) {
                Integer num = new Random().nextInt(size * 10);
                datas1[i][j] = num;
                datas2[i][j] = num;
            }
        }
        System.out.println("结束构造");

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            Sorter.bubbleSort(datas1[i], ((num1, num2) -> num1 >= num2));
        }
        System.out.println("冒泡排序耗时：" + (System.currentTimeMillis() - start) + " 毫秒");

        start = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            Sorter.quickSort(datas2[i], ((num1, num2) -> num1 >= num2));
        }
        System.out.println("冒泡排序耗时：" + (System.currentTimeMillis() - start) + " 毫秒");
    }
}