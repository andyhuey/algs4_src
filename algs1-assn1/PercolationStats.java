/*----------------------------------------------------------------
 *  Author:        Andrew Huey
 *  Written:       11/08/2013
 *  Last updated:  11/08/2013
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats
 *  needs stdlib.jar and algs4.jar in classpath.
 *  
 *  https://class.coursera.org/algs4partI-003/assignment/view?assignment_id=1
 *  http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 *  http://coursera.cs.princeton.edu/algs4/checklists/percolation.html
 * 
 * http://introcs.cs.princeton.edu/java/stdlib/StdOut.java.html
 * http://introcs.cs.princeton.edu/java/stdlib/StdRandom.java.html
 *
 *----------------------------------------------------------------*/

public class PercolationStats {

    private int N;
    
    // perform T independent computational experiments on an N-by-N grid  
    public PercolationStats(int N, int T)
    {
        // check the args.
        if (N <= 0 || T <= 0)
            throw new java.lang.IllegalArgumentException();
        this.N = N;
        
        int results[] = new int[T];
        
        for (int i=0; i<T; i++)
            results[i] = test_iteration();
            
    }
    
    // one iteration of the test. return # of tries to get to perc.
    private int test_iteration()
    {
        // all sites will be initialized as blocked. 
        Percolation p = new Percolation(N);
        
        //Repeat the following until the system percolates:
        //Choose a site (row i, column j) uniformly at random among all blocked sites.
        //Open the site (row i, column j). 
        int tries = 0;
        int open_sites = 0;
        boolean itPercs = false;
        
        try
        {
            while (!itPercs)
            {
                tries++;
                int i = StdRandom.uniform(1, N);
                int j = StdRandom.uniform(1, N);
                if (!p.isOpen(i,j))
                {
                    p.open(i,j);
                    open_sites++;
                }
                itPercs = p.percolates();
                if (open_sites > N*N)
                    throw new Exception("All sites are open and it still doesn't perc!");
//                if (tries % 100 == 0)
//                    StdOut.printf("tries=%d, open=%d\n", tries, open_sites);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        StdOut.printf("tries=%d, open=%d, pct=%.2f\n", 
                      tries, open_sites, (float)100*open_sites/(N*N));
        return tries;
    }
 
    // sample mean of percolation threshold
    public double mean()
    {
        return 0;
    } 
 
    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return 0;
    }
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLo()             
    {
        return 0;
    }
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHi()
    {
        return 0;
    }
    
    // test client
    public static void main(String[] args)
    {
        //  takes two command-line arguments N and T
        if (args.length != 2)
        {
            usage(); 
            return;
        }
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        
        PercolationStats ps = new PercolationStats(N,T);
        
        // %.16f ?
        StdOut.printf("%-23s = %f\n", "mean", ps.mean());
        StdOut.printf("%-23s = %f\n", "stddev", ps.stddev());
        StdOut.printf("%-23s = %f, %f\n",
                      "95% confidence interval",
                      ps.confidenceLo(), ps.confidenceHi());
        
    }
    
    private static void usage()
    {
        System.out.println("usage: PercolationStats N T");
        System.out.println("N=grid size");
        System.out.println("T=iterations");
    }
}