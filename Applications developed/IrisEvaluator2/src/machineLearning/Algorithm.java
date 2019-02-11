package machineLearning;

public abstract class Algorithm {
	PerformanceData performanceData;
	
	public abstract double predict(double[] features);

	public abstract boolean fit(double[][] features, double[] labels);
	
	public abstract void save();

	public abstract void read();	
}