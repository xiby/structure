package tech.xiby.structure.sort;

/**
 * 比较
 *
 * @param <T>
 * @author xiby
 */
public interface IComparator<T> {
    /**
     * 进行两个数的比较
     * 如果num1 > num2 则返回true
     * 否则返回false
     *
     * @param num1
     * @param num2
     * @return
     */
    boolean compair(T num1, T num2);
}
