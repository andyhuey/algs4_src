/*----------------------------------------------------------------
 *  Author:        Andrew Huey
 *  Written:       11/08/2013
 *  Last updated:  11/08/2013
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation
 *  needs stdlib.jar and algs4.jar in classpath.
 *  
 *  https://class.coursera.org/algs4partI-003/assignment/view?assignment_id=1
 *  http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 *  http://coursera.cs.princeton.edu/algs4/checklists/percolation.html
 *
 *  By convention, the indices i and j are integers between 1 and N,
 *  where (1, 1) is the upper-left site.
 *
 *  I think I'm supposed to implement this using WeightedQuickUnionUF.java.
 *  http://algs4.cs.princeton.edu/15uf/WeightedQuickUnionUF.java.html
 *
 *  Let's say blocked = 0, empty open = 1, full open = 2.
 *  grid_map is for convenience; could just be (i-1)*N + (j-1)
 *
 *----------------------------------------------------------------*/

public class Percolation {
    
    private int N;
    private int[][] grid_state;     // 0,1,2
    private int[][] grid_map;       // 0..N^2 - map to UF obj
    private WeightedQuickUnionUF uf;
    private int top_site_n, bottom_site_n;
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N)
    {
        this.N = N;
        // create an N x N grid (ignoring zero elements, for simplicity.)
        grid_state = new int[N+1][N+1];
        grid_map = new int[N+1][N+1];
        // start with all sites blocked (=0)
        int k=0;
        for (int i=1; i <= N; i++)
            for (int j=1; j <= N; j++)
            {
                grid_state[i][j] = 0;
                grid_map[i][j] = k++;
            }
        
        // need to create a graph with N^2+2 cells, with the UF class.
        uf = new WeightedQuickUnionUF((N * N) + 2);
        // create 'virtual' top & bottom sites.
        top_site_n = N*N;
        bottom_site_n = (N*N)+1;
        // connect them to row 1, all cells, and row N all cells.
        for (int j=1; j <= N; j++)
        {
            uf.union(top_site_n, grid_map[1][j]);
            uf.union(bottom_site_n, grid_map[N][j]);
        }
        // we know it perc's if virtual top is connected to virtual bottom.
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j)
    {
        check_parms(i,j);
        // Mark new site as open;
        grid_state[i][j] = 1;
        // connect it to all of its adjacent open sites.
        // up to 4 calls to union()
        if (i > 1 && this.isOpen(i-1, j))
            uf.union(grid_map[i][j], grid_map[i-1][j]);
        if (i < N && this.isOpen(i+1, j))
            uf.union(grid_map[i][j], grid_map[i+1][j]);
        if (j > 1 && this.isOpen(i, j-1))
            uf.union(grid_map[i][j], grid_map[i][j-1]);
        if (j < N && this.isOpen(i, j+1))
            uf.union(grid_map[i][j], grid_map[i][j+1]);
    }
    
    // is site (row i, column j) open?
    // (not sure if this should check for just empty open or also full open?)
    public boolean isOpen(int i, int j)
    {
        check_parms(i,j);
        return grid_state[i][j] == 1; 
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j)
    {
        check_parms(i,j);
        return grid_state[i][j] == 2; 
    }
    
    // does the system percolate?
    // (System percolates iff top and bottom are connected by open sites.)
    public boolean percolates()
    {
        return uf.connected(top_site_n, bottom_site_n);
    }
    
    // i & j must be between 1 and N
    private void check_parms(int i, int j)
    {
        if (i < 1 || j < 1 || i > N || j > N)
        {
            throw new java.lang.IndexOutOfBoundsException();
        }        
    }
    
    public static void main(String[] args)
    {
        Percolation p3 = new Percolation(5);
        System.out.println(p3.percolates() ? 
            "It percolates." : "It doesn't percolate.");
        for (int i=1; i<=5; i++)
            p3.open(i,i);
        System.out.println(p3.percolates() ? 
            "It percolates." : "It doesn't percolate.");
        
        Percolation p = new Percolation(5);
        if (p.isOpen(1,1))
            System.out.println("1,1 is open.");
        p.open(1,1);
        if (p.isOpen(1,1))
            System.out.println("1,1 is open.");
        System.out.println(p.percolates() ? 
            "It percolates." : "It doesn't percolate.");
        //p.open(6,1);
        
        // open column 1 in rows 1..5 - should perc.
        for (int i=1; i <= 5; i++)
            p.open(i,1);
        System.out.println(p.percolates() ? 
            "It percolates." : "It doesn't percolate.");

        // open all in row 1 - should not perc.
        Percolation p2 = new Percolation(5);
        for (int j=1; j <= 5; j++)
            p2.open(1,j);
        System.out.println(p2.percolates() ? 
            "It percolates." : "It doesn't percolate.");
    }
}