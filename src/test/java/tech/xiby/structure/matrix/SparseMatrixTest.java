package tech.xiby.structure.matrix;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SparseMatrixTest {

    @Test
    public void matrixTest(){
        int rows = 10;
        int columns = 10;
        SparseMatrix<Integer> matrix = new SparseMatrix<>(rows, columns);
        matrix.addNode(1, 2, 10);
        matrix.addNode(0, 1, 12);
        matrix.addNode(4, 6, 2);
        matrix.addNode(3, 4, 3);
        matrix.show();
        System.out.println("================================");
        SparseMatrix target = matrix.fastTrans();
        target.show();
        System.out.println("================================");
        SparseMatrix target2 = matrix.fastTrans_opt();
        target2.show();

    }

}