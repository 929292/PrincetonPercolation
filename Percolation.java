import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][]element;
    private int[]state;
    private int top;
    private WeightedQuickUnionUF wuf;
    private int down;
    private int topp;
    public Percolation(int nN) { 
        if (nN <= 0) {
            throw new IllegalArgumentException();
        }
// create N-by-N grid, with all sites blocked
    element = new int[nN][nN];
    state = new int[nN*nN];
    top = nN * nN;
    down = nN * nN + 1;
    topp = nN;
    int value = 0;
    for (int i = 0; i < nN; i++) {
        for (int j = 0; j < nN; j++) {            
        element[i][j] = value;
        state[value] = 0;
        value++;
        }
    }
    wuf = new WeightedQuickUnionUF(nN * nN + 2);
    } // end of constructor
    
 /*   private void print() {
        for (int i = 0; i < top; i++) {
            for (int j = 0; j < top; j++){
                System.out.print(element[i][j] + "  ");
            }
        }
        System.out.println();
    } */
    public void open(int i, int j) {          // open site (row i, column j) if it is not open already
        if (i < 1 || j < 1 || i > topp || j > topp) {
            throw new IndexOutOfBoundsException();
        } else {
            int ele = element[i - 1][j - 1];
            if (state[ele] == 1) {
                return;  
            } else {
                state[ele] = 1;               
                 // check suroundings  
                 /* union open sites together*************by chen */ 
                if (i > 1 && isOpen(i - 1, j)) {
                    wuf.union(ele, element[i - 2][j - 1]);
                } 
                if (i < topp  && isOpen(i + 1, j)) {
                    wuf.union(ele, element[i][j - 1]); 
                } 
                if (j > 1 && isOpen(i, j - 1)) {
                    wuf.union(ele, element[i - 1][j - 2]);
                } 
                if (j < topp && isOpen(i, j + 1)) {
                    wuf.union(ele, element[i - 1][j]);
                } 
                if (i == 1) {
                    wuf.union(ele, top);
                 }
                if (i == topp && isFull(i, j)) {
                     wuf.union(ele, down);
                 }
            } // end of else    
        }
        // System.out.println("is open (" + i + ", " + j + ") = " + isOpen(i, j));
        // System.out.println("is Full (" + i + ", " + j + ") = " + isFull(i, j));
    }
    public boolean isOpen(int i, int j) {     // is site (row i, column j) open?
        if (i < 1 || j < 1 || i > topp || j > topp) {
            throw new IndexOutOfBoundsException();
        } else {
            int ele = element[i - 1][j - 1];
           // if (state[ele] == 0)
            return !(state[ele] == 0);
           // else return true;       
        }
       // return true;      
    }
    public boolean isFull(int i, int j) {     // is site (row i, column j) full?
        if (i < 1 || j < 1 || i > topp || j > topp) {
            throw new IndexOutOfBoundsException();
        } else {
            int ele = element[i - 1][j - 1];
     //       if (wuf.find(ele) == ele) {
     //           return false;
     //   } else if (wuf.find(ele) == top) {
       ///     return true; 
      //  } 
            return wuf.connected(ele, top);
        }
      //  return false;
    }
    public boolean percolates() {             // does the system percolate?
        return wuf.connected(top, down);
    }
    
 /*     public static void main(String[] args){  // test client (optional)
       Percolation percolation=new Percolation(6);
       
       //System.out.println(percolation.isFull(1,1));
       percolation.open(1, 6);
       percolation.open(2, 6);
       percolation.open(3, 6);
       percolation.open(4, 6);
       percolation.open(5, 6);
       percolation.open(6, 5);
       percolation.open(4, 4);
       percolation.open(3, 4);
       percolation.open(2, 4);
       percolation.open(2, 3);
       percolation.open(2, 2);
       percolation.open(2, 1);
       percolation.open(3, 1);
       percolation.open(4, 1);
       percolation.open(5, 1);
       percolation.open(5, 2);
       percolation.open(6, 2);
       percolation.open(5, 4);
       percolation.open(6, 6);
       System.out.println(percolation.percolates());
       } */
}