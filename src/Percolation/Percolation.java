package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  public WeightedQuickUnionUFPublicSiz uf;
  int virtualTopSite;
  int virtualBottomSite;
  boolean [] grid;
  int n;
  public Percolation(int n){
    if(n < 0) throw new IllegalArgumentException ("n must be greater than 0!");
    this.n = n;
     // create n-by-n grid, with all sites blocked
    uf = new WeightedQuickUnionUFPublicSiz(n*n+3);
    virtualBottomSite = n*n;
    virtualTopSite = n*n+1;
    grid = new boolean [n * n];
  }

    public void open(int row, int col){
     // open site (row, col) if it is not open already
      if(isOpen(row, col)) return;
      grid[translate(row, col)] = true;
      if(row == 1){  
        //top row case
          uf.union(virtualTopSite, translate(row,col));
          if(isOpen(row+1,col))    uf.union(translate(row,col), translate(row+1, col));
          if(isOpen(row,col+1))   uf.union(translate(row,col), translate(row, col+1));
          if(isOpen(row,col-1))   uf.union(translate(row,col), translate(row, col-1));
          return;
      }
      if(row == n){
        //bottom row case
        uf.union(translate(row,col), virtualBottomSite);
          if(isOpen(row-1,col))   uf.union(translate(row,col), translate(row-1, col));
          if(isOpen(row,col+1))   uf.union(translate(row,col), translate(row, col+1));
          if(isOpen(row,col-1))   uf.union(translate(row,col), translate(row, col-1));
          return;
      }
      if(doesItHaveNeighbours(row,col)){
        // the rest
        if(isOpen(row+1,col))   uf.union(translate(row,col), translate(row+1, col));
        if(isOpen(row-1,col))   uf.union(translate(row,col), translate(row-1, col));
        if(isOpen(row,col+1))   uf.union(translate(row,col), translate(row, col+1));
        if(isOpen(row,col-1))   uf.union(translate(row,col), translate(row, col-1));
        return;
      } 
    }
   public boolean isOpen(int row, int col){
     // is site (row, col) open?
       if(row <= 0 || col <= 0 || row > n || col > n) return false;
    return grid[translate(row, col)];
     
   }
   public boolean isFull(int row, int col){
     // is site (row, col) full?
     return(uf.connected(translate(row,col), virtualTopSite));
     
   }
   public int numberOfOpenSites(){
     // number of open sites
     int counter = 0;
     for(int i = 0; i < grid.length; i++){
       if(grid[i] == true) counter++;
     }
     return counter;
     
   }
   public boolean doesItHaveNeighbours(int row, int col){
     return(isOpen(row+1, col) || isOpen(row-1, col) || isOpen(row, col+1) || isOpen(row, col-1));
   }
   public boolean percolates(){
     // does the system percolate?
     return uf.connected(virtualTopSite, virtualBottomSite);
   }
   public int translate(int row, int column){
     return (row - 1)*n + column-1;   
   }
//   public static void main(String[] args) {
//   // test client (optional)
//	   Percolation perky = new Percolation(2);
//	   Scanner unos = new Scanner(System.in);
//	   for(int i = 0; i < 2; i++){
//		   perky.open(unos.nextInt(), unos.nextInt());
//	   }
//	   System.out.println("perkulacija: " + perky.percolates());
//		System.out.println(perky.numberOfOpenSites());
//	}
}