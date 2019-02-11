package formsManagement;

import javax.swing.JRadioButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.*;

public class IrisQuestionPanel extends QuestionPanel{
	private static final long serialVersionUID = 1L;
	private JTextField textFields[];
	private Container fieldPanes[];
	
	public IrisQuestionPanel(MainForm mainForm) {
		this.mainForm = mainForm;
		this.createComponents();
		this.setVisible(true);
		
		
		
	}
	
	private void createComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		String[] dataFieldNames = new String[]{"Comprimento da Sépala", "Largura da Sépala",
	            "Comprimento da Pétala", "Largura da Pétala"};
		fieldPanes = new Container[dataFieldNames.length];
        textFields = new JTextField[dataFieldNames.length];
        for (int i = 0; i<fieldPanes.length;i++){
            fieldPanes[i] = new JPanel(); //default flow layout
            fieldPanes[i].add(new JLabel(dataFieldNames[i]));
            textFields[i] = new JTextField(10);
            fieldPanes[i].add(textFields[i]);
        }
        Component[] previousComponents = this.getComponents();
        this.add(Box.createVerticalGlue());
        for (int i = 0; i<fieldPanes.length;i++){
            this.add(fieldPanes[i]);
        }
//        this.add(Box.createVerticalGlue());
        for (Component component: previousComponents) {
        	this.add(component);
        }
        this.add(Box.createVerticalGlue());
	}

	@Override
	protected boolean checkAnswerRules() {
//		System.out.println("IrisQuestionPanel.checkAnswerRules(): implementar.");
		for (int i = 0; i < textFields.length; i++) {
            if (textFields[i].getText() == null || textFields[i].getText().isEmpty()) {
            	return false;
            }
        }
		return true;
	}

	@Override
	protected void collectData() {
//		“collectData” deve levar os dados dos campos preenchidos pelo usuário 
//		até a variável global “data”. Para auxiliar nessa tarefa, a variável global “index” 
//		deve ser utilizada. Tal variável dese ver preenchida com o valor do índice do formulário 
//		com relação a todos os formulários de questões criados. 
//		this.data[i] = valorInseridoNoCampo[i];
		for (int i = 0; i < textFields.length; i++) {
            	this.data[i] = Double.parseDouble(this.textFields[i].getText());
        }
		
//		System.out.println("IrisQuestionPanel.collectData(): ");
//		for (int i = 0; i<this.data.length;i++) {
//			System.out.println(this.data[i]);
//		}
//		System.out.println("IrisQuestionPanel.collectData(): implementar.");
	}

}
