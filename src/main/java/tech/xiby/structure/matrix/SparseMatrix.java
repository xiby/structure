package tech.xiby.structure.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏矩阵的定义
 *
 * @param <T>
 * @author xiby
 */
public class SparseMatrix<T> {
    private static final Integer DEFAULT_VALUE = 0;
    /**
     * 行数
     */
    private int rows;

    /**
     * 列数
     */
    private int columns;

    /**
     * 非零元个数
     */
    private int nums;

    /**
     * 数据列表
     */
    private List<TripleNode<T>> datas;

    /**
     * 初始化一个矩阵
     */
    public SparseMatrix(int rows, int columns) {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException();
        }
        this.rows = rows;
        this.columns = columns;
        datas = new ArrayList<>();
    }

    public void addNode(int row, int column, T data) {
        if (row >= rows || column >= columns) {
            throw new IllegalArgumentException();
        }
        if (data.equals(DEFAULT_VALUE)) {
            return;
        }
        TripleNode node = new TripleNode(row, column, data);
        if (nums == 0) {
            datas.add(node);
            ++nums;
            return;
        }
        for (int i = 0; i < nums; ++i) {
            TripleNode current = datas.get(i);
            if (row > current.getRow()) {
                //未找到合适的位置，继续下一步
                continue;
            }
            if (row == current.getRow() && column == current.getColumn()) {
                //替换
                current.setData(data);
                return;
            }
            if (row <= current.getRow() && column > current.getColumn()) {
                continue;
            }
            if (row <= current.getRow() && column < current.getColumn()) {
                //此时找到了合适的位置，需要新增
                datas.add(i, node);
                nums++;
                return;
            }
        }
        datas.add(nums, node);
        nums++;
    }

    /**
     * 展示稀疏矩阵
     */
    public void show() {
        int index = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < rows; ++j) {
                if (index >= nums) {
                    System.out.print(DEFAULT_VALUE + " ");
                } else {
                    TripleNode node = datas.get(index);
                    if (i == node.getRow() && j == node.getColumn()) {
                        System.out.print(node.getData() + " ");
                        ++index;
                    } else {
                        System.out.print(DEFAULT_VALUE + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * 快速转置
     *
     * @return
     */
    public SparseMatrix<T> fastTrans() {
        SparseMatrix target = new SparseMatrix(columns, rows);
        target.nums = nums;
        if (nums == 0) {
            return target;
        }
        int[] num = new int[columns];
        int[] cpot = new int[columns];
        //计算每一列非零元的个数
        prepareColumnNotZero(target, num);
        //计算每一列的第一个元素应该存储的位置
        //因为原矩阵有着顺序性，所以可以这样来求解
        for (int i = 1; i < columns; ++i) {
            cpot[i] = cpot[i - 1] + num[i - 1];
        }
        doTransport(target, cpot);
        return target;
    }

    private void prepareColumnNotZero(SparseMatrix target, int[] num) {
        for (TripleNode node : datas) {
            num[node.getColumn()]++;
            target.datas.add(null);
        }
    }

    /**
     * 存储空间优化的快速转置
     *
     * @return
     */
    public SparseMatrix<T> fastTrans_opt() {
        //只用这一个向量来保存每一列的第一个元素应该存储的位置
        SparseMatrix target = new SparseMatrix(columns, rows);
        target.nums = nums;
        int[] cpot = new int[columns];
        //计算每一列中非零的个数，便于后面位置的计算
        prepareColumnNotZero(target, cpot);
        int before = 0;
        int last = 0;
        for (int i = 1; i < columns; ++i) {
            last = cpot[i];
            cpot[i] = before + cpot[i - 1];
            before = last;
        }
        doTransport(target, cpot);
        return target;
    }

    /**
     * 进行转置
     *
     * @param target
     * @param cpot
     */
    private void doTransport(SparseMatrix target, int[] cpot) {
        for (TripleNode node : datas) {
            TripleNode targetNode = new TripleNode(node.getColumn(), node.getRow(), node.getData());
            target.datas.set(cpot[node.getColumn()], targetNode);
            cpot[node.getColumn()]++;
        }
    }
    public T getData(int row, int colunm) {
        if (row >= rows || colunm >= columns) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < nums; ++i) {
            if (row == datas.get(i).getRow() && colunm == datas.get(i).getColumn()) {
                return datas.get(i).getData();
            }
        }
        return (T) DEFAULT_VALUE;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getNums() {
        return nums;
    }

    /**
     * 三元组的定义
     *
     * @param <T>
     */
    public class TripleNode<T> {
        private int row;

        private int column;

        T data;

        public TripleNode(int row, int column, T data) {
            this.row = row;
            this.column = column;
            this.data = data;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
