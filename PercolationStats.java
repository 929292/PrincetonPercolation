import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
   /* public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   public double mean()                          // sample mean of percolation threshold
   public double stddev()                        // sample standard deviation of percolation threshold
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   public double confidenceHi()                  // high endpoint of 95% confidence interval

   public static void main(String[] args)    // test client (described below) */
   
    private int totalOpen = 0;
    private int totalSite = 0;
    private int nN = 0;
    private double[] openFrac;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        
        openFrac = new double[trials];
        totalSite = n * n;
        nN = n;
        trials = trials - 1;
        while (trials >= 0) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {   
            // generate a random number
                int i = StdRandom.uniform(1, n + 1); 
                int j = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(i, j))
                    totalOpen += 1; 
                percolation.open(i, j); 
            }            
            openFrac[trials] = (double) (totalOpen) / totalSite; 
            trials -= 1;
            totalOpen = 0;
        } // end of trials while
    }
    
    public double mean() {
        return StdStats.mean(openFrac);
    }
    
    public double stddev() {
        return StdStats.stddev(openFrac); 
    }
    
    public double confidenceLo() {
        double u = mean();
        double d = stddev();
        return u - (1.965 * d) / Math.sqrt(nN);
    }
    
    public double confidenceHi() {
        double u = mean();
        double d = stddev();
        return u + (1.965 * d) / Math.sqrt(nN);
    }
    
    public static void main(String[] args) {
        PercolationStats perc = new PercolationStats(200, 10);
        System.out.println("% java PercolationStats 200 100");
        System.out.println("mean                    = " + perc.mean());
        System.out.println("stddev                  = " + perc.stddev());
        System.out.println("95% confidence interval = " + perc.confidenceLo() + ", " + perc.confidenceHi());
 
    } 
}