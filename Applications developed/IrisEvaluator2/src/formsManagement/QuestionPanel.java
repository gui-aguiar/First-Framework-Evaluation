package formsManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.CardLayout;

public abstract class QuestionPanel extends JPanel {
			  
	private static final long serialVersionUID = 1L;

	public QuestionPanel(MainForm mainForm) {
		this.mainForm = mainForm;
	}
	
	CardLayout cardLayout;
	boolean isFirstPanel;
	boolean isLastPanel;
	JButton buttonPrevious;
	JButton buttonNext;
	double[] data;
	int index;
	MainForm mainForm;
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double[] getData() {
		return data;
	}

	public void setData(double[] data) {
		this.data = data;
	}

	public boolean isFirstPanel() {
		return isFirstPanel;
	}

	public void setIsFirstPanel(boolean isFirstPanel) {
		this.isFirstPanel = isFirstPanel;
		buttonPrevious.setVisible(!isFirstPanel);
	}

	public boolean isLastPanel() {
		return isLastPanel;
	}

	public void setIsLastPanel(boolean isLastPanel) {
		this.isLastPanel = isLastPanel;
	}
	
	public QuestionPanel() { 
		initializeButtons();
	}

	protected void initializeButtons() {		
		setLayout(null);
		
		class ControlButtonsActionListenter implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                cardLayout = (CardLayout) (getParent().getLayout());
                String cmd = e.getActionCommand();
                if (cmd.equals("Previous")) {
                	previousPage();
                }
                else if (cmd.equals("Next")) {
                	nextPage();
                }
            }
        }
        ControlButtonsActionListenter controlButtons = new ControlButtonsActionListenter();		
		
		buttonPrevious = new JButton("Previous");
		buttonPrevious.setBounds(10, 470, 70, 22);
		buttonPrevious.addActionListener(controlButtons);
		buttonPrevious.setActionCommand("Previous");
		add(buttonPrevious);
		buttonPrevious.setVisible(!isFirstPanel);
		
		buttonNext = new JButton("Next");
		buttonNext.setBounds(560, 470, 70, 22);
		buttonNext.addActionListener(controlButtons);
		buttonNext.setActionCommand("Next");
		add(buttonNext);
	}

	protected void previousPage() {
		cardLayout.previous(getParent());
	}
	
	protected void nextPage() {
		if (!checkAnswerRules()) {
			JOptionPane.showMessageDialog(this, "Todas as questões devem ser respondidas.",
				    "Atenção", JOptionPane.WARNING_MESSAGE);
		} else{
			collectData();
		    if (!this.isLastPanel) {
		    	cardLayout.next(getParent());
		    } else {
		    	this.mainForm.finish(); 
		    }
	    }	     
	}
	protected abstract boolean checkAnswerRules();
	protected abstract void collectData();	
}