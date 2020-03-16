package tech.xiby.structure.sort;

/**
 * 排序工具
 *
 * @author xiby
 */
public class Sorter {
    /**
     * 冒泡排序
     *
     * @param datas
     * @param <T>
     */
    public static <T> void bubbleSort(T[] datas, IComparator<T> comparator) {
        int length = datas.length;
        for (int i = 0; i < length - 1; ++i) {
            for (int j = 0; j < length - 1 - i; ++j) {
                if (comparator.compair(datas[j], datas[j + 1])) {
                    T tmp = datas[j];
                    datas[j] = datas[j + 1];
                    datas[j + 1] = tmp;
                }
            }
        }
    }
}
