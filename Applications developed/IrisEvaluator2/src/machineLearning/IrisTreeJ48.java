package machineLearning;

import java.util.ArrayList;
//import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class IrisTreeJ48 extends Algorithm {
	
	Instances myTestInstances; 
	weka.classifiers.Classifier myClassifier;
	ArrayList<Attribute> attributes;
	
	
	@Override
	public boolean fit(double[][] features, double[] labels) {
		return false;      
	}
	
	
	@Override
	public double predict(double[] features) {		
		Instance inst = new DenseInstance(5);
        inst.setDataset(this.myTestInstances);
        inst.setValue(0,features[0]);
        inst.setValue(1,features[1]);
        inst.setValue(2,features[2]);
        inst.setValue(3,features[3]);
        inst.setValue(4,"Iris-setosa"); //Valor errado de proposito
        try {
        	double value = this.myClassifier.classifyInstance(inst);
//        	String prediction = this.myTestInstances.classAttribute().value((int)value);
//        	System.out.println(prediction);
        	return value;
        }
        catch(Exception e)
        {
			System.out.println("DecisionTree.predict() Exception:"+e);
        }
        
		return -1;
	}

	@Override
	public void save() {
		// unimplemented for this application		
	}

	@Override
	public void read() {
		// sepallength
        // sepalwidth
        // petallength
        // petalwidth
        // class: {Iris-setosa,Iris-versicolor,Iris-virginica}
//		Instances(java.lang.String name, java.util.ArrayList<Attribute> attInfo, int capacity)
		this.attributes = new ArrayList<>();
//		numeric attributes:
		this.attributes.add(new Attribute("sepallength"));
		this.attributes.add(new Attribute("sepalwidth")); 
		this.attributes.add(new Attribute("petallength")); 
		this.attributes.add(new Attribute("petalwidth"));
//		nominal attributes:
		ArrayList<String> my_nominal_values = new ArrayList<String>(3);
		
		my_nominal_values.add("Iris-setosa");
		my_nominal_values.add("Iris-versicolor");
		my_nominal_values.add("Iris-virginica");
		this.attributes.add(new Attribute("class", my_nominal_values));
		this.myTestInstances = new Instances("Test", attributes, 0);
		this.myTestInstances.setClassIndex(this.myTestInstances.numAttributes() - 1);
		try {
			this.myClassifier = (weka.classifiers.Classifier) weka.core.SerializationHelper.read("IRIS.model");
		}
		catch(Exception e)
        {
			System.out.println("DecisionTree.read() Exception:"+e);
        }
	}	

}
