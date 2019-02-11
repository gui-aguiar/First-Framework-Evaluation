import com.SimpleDataForm.SimpleDataForm;
import com.SimpleDataForm.GenericFormData;
import com.SimpleDataForm.GenericFormDataFactory;
import com.SimpleDataForm.FormDataCollection;
import com.SimpleDataForm.NotificationPopUp;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instance;
import weka.core.DenseInstance;

public class FlorDeIrisEvaluator {
    public static void main(String[] args){
        try
        {
            // From Instances weka api page:
            DataSource source = new DataSource("data/iris.arff");
            Instances trainDataset = source.getDataSet();
            // Make the last attribute be the class
            trainDataset.setClassIndex(trainDataset.numAttributes() - 1);
            Classifier classifier = (Classifier) weka.core.SerializationHelper.read("data/IRIS.model");

            IrisInstanceDataFactory factory = new IrisInstanceDataFactory(trainDataset,classifier);
            // Iris-virginica: {"5.8","2.7","5.1","1.9"}
            FormDataCollection IrisInstanceDataCollection = new FormDataCollection();
            SimpleDataForm.start(IrisInstanceDataCollection, factory);

            String msg = ""
                + "O objetivo da plicação a seguir é testar um classificador "
                + "de Machine Learning, treinado para o problema de identificação "
                + "de espécies de Flor de Iris, a partir de algumas de suas "
                + "características físicas.<br><br>"
                + "Por favor, preencha os campos a seguir com valores em "
                + "<b>centímetros</b>, insira apenas os valores numéricos.";
            NotificationPopUp pop = new NotificationPopUp("Flor de Iris: Instruções", msg);

        }
        catch(Exception e)
        {
            System.out.println("On FlorDeIrisEvaluator.main(): "+e);
        }
    }
}

class IrisInstanceData implements GenericFormData{
    private String attributes[];
    private String attributeNames[];
    private String result;
    private Instance instance;
    private Classifier classifier;
    private Instances trainDataset;

    public IrisInstanceData(String[] newData, Instances trainDataset, Classifier classifier){
        this.trainDataset = trainDataset;
        this.classifier = classifier;
        String fields[] = this.getFields();
        this.attributes = new String[fields.length + 1];
        this.attributeNames = new String[fields.length + 1];
        if (newData != null && newData.length > 0){
            for ( int  i = 0; i < newData.length; i++){
                this.attributes[i] = newData[i];
                this.attributeNames[i] = fields[i];
                if (this.attributes[i].contains(",")){
                    this.attributes[i] = this.attributes[i].replaceAll(",",".");
                }
            }
            this.attributeNames[this.attributes.length -1] = "class";
            this.attributes[this.attributes.length -1] = "Iris-setosa";
            this.result = this.process();
        }
    }

    public String[] getData(){
    	return this.attributes;
    }

    public void setData(String[] newData){
    	this.attributes = newData;
    }

    public String getResult(){
        return this.result;
    }

    public String process(){
        this.result = "";
        try{
            this.instance = new DenseInstance(this.attributes.length);
            this.instance.setDataset(this.trainDataset);
            for (int i = 0; i < (this.attributes.length - 1); i++){
                this.instance.setValue(i,Double.parseDouble(this.attributes[i]));
            }
            this.instance.setValue(this.attributes.length - 1,"Iris-setosa");

            double value=this.classifier.classifyInstance(this.instance);
            String prediction=this.trainDataset.classAttribute().value((int)value);
            this.result = prediction;
        }
        catch(Exception e)
        {
            System.out.println("On IrisInstanceData.process(): "+e);
            return "error";
        }
        return this.result;
    }

    public String toString(){
        if (this.result.equals("error")) {
            return "Inválido";
        }
        else{
            String out = "";
            String fieldNames[] = this.attributeNames;
            for ( int  i = 0; i < (this.attributes.length - 1); i++){
                out += fieldNames[i]+": "+this.attributes[i];
                if (i < (this.attributes.length - 1)){
                    out += ", ";
                }
            }
            out += ": "+fieldNames[fieldNames.length - 1]+": "+this.attributes[this.attributes.length - 1];
            return out;
        }

    }
    public String[] getFields(){
        return new String[]{"Comprimento da Sépala", "Largura da Sépala",
            "Comprimento da Pétala", "Largura da Pétala"};
    }
}

class IrisInstanceDataFactory implements GenericFormDataFactory{
    private Classifier classifier;
    private Instances trainDataset;

    public IrisInstanceDataFactory(Instances trainDataset, Classifier classifier){
        this.trainDataset = trainDataset;
        this.classifier = classifier;
    }

	public GenericFormData create(String[] data){
		return new IrisInstanceData(data, this.trainDataset, this.classifier);
	}
}
