import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private int numOfTrails;
    private double[] fraction;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        else {
            numOfTrails = trials;
            fraction = new double[trials];
            for (int i = 0; i < numOfTrails; i++) {
                Percolation p = new Percolation(n);
                while (!p.percolates()) {
                    int row = StdRandom.uniform(1, n + 1);
                    int col = StdRandom.uniform(1, n + 1);
                    p.open(row, col);
                }
                fraction[i] = (double) p.numberOfOpenSites() / (double) (n * n);
            }

        }

    }


    // sample mean of percolation threshold
    public double mean() {
        return edu.princeton.cs.algs4.StdStats.mean(fraction);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return edu.princeton.cs.algs4.StdStats.stddev(fraction);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(numOfTrails);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(numOfTrails);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println(
                "95% confidence interval = [ " + ps.confidenceLo() + ", " + ps.confidenceHi()
                        + "]");
    }
}
