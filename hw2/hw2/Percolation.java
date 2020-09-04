package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;

public class Percolation {
    boolean[] status;
    WeightedQuickUnionUF UF;
    public int side;
    public int num;

    //creat N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        if(N <=0){
            throw new IllegalArgumentException("N must be positive value!");
        }
        status = new boolean[N*N +2];
        status[N*N] = status[N*N +1] = true;
        UF = new WeightedQuickUnionUF(N*N +2);
        side = N;
        num = 0;
    };

    private int xyTo1d(int x, int y){
        return x * side + y;
    };

    //open the site(row, col) if it is not open already
    public void open(int row, int col){
        if (row <0 || col <0 || row > side-1 || col > side-1){
            throw new IndexOutOfBoundsException("Index out of Bounds");
        }
        if (isOpen(row, col)){
            return;
        }
        int index = xyTo1d(row, col);

        int left_index = xyTo1d(row, col - 1);
        int right_index = xyTo1d(row, col + 1);
        int up_index = xyTo1d(row -1, col);
        int down_index = xyTo1d(row + 1, col);

        status[index] = true;
        num += 1;
        if (row == 0){
            UF.union(side*side, index);
        }
        if(left_index>=0 && col > 0 && isOpen(row, col - 1)){
            UF.union(index, left_index);
        }
        if(right_index <= side*side && col < side-1 && isOpen(row, col + 1)){
            UF.union(index, right_index);
        }
        if(up_index>=0 && row > 0 && isOpen(row - 1, col)){
            UF.union(index, up_index);
        }
        if(down_index <= side*side && row < side-1 && isOpen(row + 1, col)){
            UF.union(index, down_index);
        }
    };

    //is the site(row, col) open?
    public boolean isOpen(int row, int col){
        if (row <0 || col <0 || row > side || col > side){
            throw new IndexOutOfBoundsException("Index out of Bounds");
        }
        int index = xyTo1d(row, col);
        return status[index];
    };

    //is the site(row, col) full?
    public boolean isFull(int row, int col){
        if (row <0 || col <0 || row > side - 1 || col > side - 1){
            throw new IndexOutOfBoundsException("Index out of Bounds");
        }

        int index = xyTo1d(row, col);

        if (UF.connected(index, side*side)){
            return true;
        }
        return false;
    };

    //number of open sites
    public int numberOfOpenSites(){
        return num;
    };

    //does the system percolate?
    public boolean percolates(){
        if (side == 1 && isOpen(0, 0)){
            return true;
        }

        for (int i = 0; i < side; i++){
            int index = xyTo1d(side -1, i);
            if (UF.connected(side * side, index)){
                UF.union(side*side + 1, index);
            }
        }

        if (UF.connected(side*side+1, side*side)){
            return true;
        }
        return false;
    };

    //use for unit testing(not required, but keep this for the autograder)
    public static void main(String[] args){};
}
