package Percolation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats{
	public Percolation percolation;
	double [] resultArray;
	int numberOfTrials;
	Random rand = new Random();
	public PercolationStats(int n, int trials){
	   // perform trials independent experiments on an n-by-n grid
		numberOfTrials = trials;
		findThreshold(n);
		resultArray = new double [trials];
//		for(int i = 0; i < trials; i++){
//			resultArray[i] = findThreshold(n);
//		}
   }
	
	public double findThreshold(int n){
		int x, y;
		int counter = 0;
		percolation = new Percolation(n);
		while(!percolation.percolates()){
			x = StdRandom.uniform(n) + 1;
			y = StdRandom.uniform(n) + 1;
			if(!percolation.isOpen(x, y)){
				percolation.open(x, y);
			}
			
		}
		return ((double)percolation.numberOfOpenSites() / Math.pow(n, 2));
	}
	
	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(resultArray);
	}

	public double stddev()  {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(resultArray);
	}

	public double confidenceLo() {
		// low  endpoint of 95% confidence interval
		return mean() - 1.96*stddev()/Math.sqrt(numberOfTrials);
	}

	public double confidenceHi()   {
		// high endpoint of 95% confidence interval
		return mean() + 1.96*stddev()/Math.sqrt(numberOfTrials);
	}

	public static void main(String[] args)  {
		// test client (described below)
		Scanner unos = new Scanner(System.in);
		int N = unos.nextInt();
		int T = unos.nextInt();
//		int N = Integer.parseInt(args[0]);
//	    int T = Integer.parseInt(args[1]);
	    PercolationStats stats = new PercolationStats(10, 1);
	    System.out.println("% java PercolationStats " + N + " " + T);
	    System.out.println("mean		         = " + stats.mean());
	    System.out.println("stddev 		 	 = " + stats.stddev());
	    System.out.println("95% confidence interval = [" + stats.confidenceLo() +", " + stats.confidenceHi() + "]");
	    System.out.println(stats.percolation.grid);
//	    try {
//	         FileOutputStream fileOut =
//	         new FileOutputStream("\\grid.txt");
//	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
//	         out.writeObject();
//	         out.close();
//	         fileOut.close();
//	         System.out.printf("Serialized data is saved in /tmp/employee.ser");
//	      }catch(IOException i) {
//	         i.printStackTrace();
//	      }
		
	}
}