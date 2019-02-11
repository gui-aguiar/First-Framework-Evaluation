package machineLearning;

public class Application {

	private int numberOfQuestions;
	private int numberOfPages;
	private OperationMode operationMode;

	private Inductor inductor;

    public Application(int numberOfQuestions) {
    	this.numberOfQuestions = numberOfQuestions;
    	inductor = new Inductor(getNumberOfQuestions());
    	createOperationMode();
		inductor.setOperationMode(operationMode);
		
    	inductor.run();
	}
	
    private void createOperationMode() {
    	operationMode = new Classify();
    	operationMode.getClassifier().getAlgorithms().add(new IrisTreeJ48());
    	operationMode.getClassifier().setCurrentAlgorithm(0);
	}

    public OperationMode getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(OperationMode operationMode) {
		this.operationMode = operationMode;
		this.inductor.setOperationMode(operationMode);
	}
	
	public int setNumberOfQuestionForms() {
		return this.numberOfPages;
	}
	public void setNumberOfQuestionForms(int numberOfForms) {
		this.numberOfPages = numberOfForms;		
	}

	public int getNumberOfQuestions() {
		return this.numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;		
	}
	
	public double classify(double[] features) {
		return inductor.classify(features);
	}

	public double test() {
		return inductor.test();
	}

	public boolean train() {
		return inductor.train();
	}
	
	public void close() {
		inductor.close();
	}
}