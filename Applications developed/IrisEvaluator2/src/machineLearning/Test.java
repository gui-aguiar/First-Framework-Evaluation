package machineLearning;

public class Test extends OperationMode {

	@Override
	public void initialize() {
		classifier.load();
		this.test();		
	}

	@Override
	public double classify(double[] features) {
		return -1;
	}

	public double test() {
		return 0;
	}

	@Override
	public boolean train(int featuresSize) {
		return false;
	}

}