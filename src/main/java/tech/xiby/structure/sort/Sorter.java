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

    /**
     * 快速排序
     * 利用递归实现
     *
     * @param datas
     * @param comparator
     * @param <T>
     */
    public static <T> void quickSort(T[] datas, IComparator<T> comparator) {
        if (datas.length == 0) {
            return;
        }
        doQuickSort(datas, 0, datas.length - 1, comparator);
    }

    private static <T> void doQuickSort(T[] datas, int start, int end, IComparator<T> comparator) {
        if (start >= end) {
            return;
        }
        T pivot = datas[start];
        int low = start;
        int high = end;
        int pivotPosition = start;
        T tmp = null;
        while (low < high) {
            while (low < high && comparator.compair(datas[high], pivot)) {
                --high;
            }
            tmp = datas[low];
            datas[low] = datas[high];
            datas[high] = tmp;
            while (low < high && comparator.compair(pivot, datas[low])) {
                ++low;
            }
            tmp = datas[high];
            datas[high] = datas[low];
            datas[low] = tmp;
            pivotPosition = low;
        }
        doQuickSort(datas, start, pivotPosition - 1, comparator);
        doQuickSort(datas, pivotPosition + 1, end, comparator);
    }
}
