package hw2;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.Stopwatch;

public class PercolationStats {
    public int times;
    public double[] threshold_list;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N<= 0 || T <=0){
            throw new IllegalArgumentException("N and T must be positive!");
        }
        threshold_list = new double[T];
        times = T;
        for (int i = 0; i < T; i++){
            Percolation p = pf.make(N);
            while (!p.percolates()){
                int a = StdRandom.uniform(N);
                int b = StdRandom.uniform(N);
                p.open(a, b);
            }
            threshold_list[i] = p.numberOfOpenSites()/(double)(N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(threshold_list);
    };

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(threshold_list);
    };

    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        return (mean() - 1.96*stddev()/Math.sqrt(times));
    };

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return (mean() + 1.96*stddev()/Math.sqrt(times));
    }

    public static void main(String[] args){
        PercolationFactory pf = new PercolationFactory();
        Stopwatch timer1 = new Stopwatch();
        PercolationStats p = new PercolationStats(5, 10, pf);
        double time1 = timer1.elapsedTime();
        StdOut.printf("mean: %.3f\n low_value: %.3f\n high_value: %.3f\n (%.3f seconds)\n",p.mean(), p.confidenceLow(), p.confidenceHigh(), time1);
    }
}