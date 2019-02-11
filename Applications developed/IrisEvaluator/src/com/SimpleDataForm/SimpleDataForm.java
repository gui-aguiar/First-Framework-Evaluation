package com.SimpleDataForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// TODO: handle window resizing
// TODO: decouple listeners, make them customizable
// TODO: fix spacing between text fields, add H spacing between buttons.
// TODO: make the list scale better, reduce list H width a little.
// TODO: add language/caption customizing support
// TODO: customizable help/about session
// TODO: make it pretty

public class SimpleDataForm {

    private static JFrame frame;
    private static JPanel inputPane;
    private static JPanel outputPane;
    private static Container fieldPanes[];
    private static JPanel inputButtonsPane;
    private static JPanel outputButtonsPane;

    private static JButton buttonOk;
    private static JButton buttonClear;
    private static JButton buttonExit;
    private static JButton buttonRemoveEntry;

    private static JTextField textFields[];
    private static JList<String> outputList;
    private static DefaultListModel<String> outputListModel;
    private static JLabel outputLabel;

    public static void start(FormDataCollection dataList, GenericFormDataFactory factory){
        String inputFields[] = factory.create(null).getFields();
        render(inputFields);
        setListeners(dataList, factory);
    }

    public static void updateOutput(FormDataCollection dataList){
        outputLabel.setText(dataList.get(dataList.size()-1).getResult());
        outputListModel.removeAllElements();
        for (int i = 0; i < dataList.size(); i++){
            outputListModel.addElement(dataList.get(i).toString());
        }
    }

    private static void setListeners(FormDataCollection dataList, GenericFormDataFactory factory){
        // TODO: decouple listeners from here, make them customizable.
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String textFieldContents[] = new String[textFields.length];
                for (int i = 0; i < textFields.length; i++) {
                    textFieldContents[i] = textFields[i].getText();
                }
                dataList.add(factory.create(textFieldContents));
                updateOutput(dataList);
            }
        });

        buttonClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                outputListModel.removeAllElements();
                dataList.clear();
            }
        });

        buttonRemoveEntry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int idx = outputList.getSelectedIndex();
                if (idx > -1){
                    outputListModel.remove(idx);
                    dataList.remove(idx);
                }
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }

    private static void render(String[] dataFieldNames) {
        //Creating the Frame
        frame = new JFrame("SimpleDataForm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Input Pane
        inputPane = new JPanel();
        inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.Y_AXIS));
        inputPane.setBorder(BorderFactory.createTitledBorder("Formulário"));
        // inputPane.setSize(300,400);
        fieldPanes = new Container[dataFieldNames.length];
        textFields = new JTextField[dataFieldNames.length];
        for (int i = 0; i<fieldPanes.length;i++){
            fieldPanes[i] = new JPanel(); //default flow layout
            fieldPanes[i].add(new JLabel(dataFieldNames[i]));
            textFields[i] = new JTextField(10);
            fieldPanes[i].add(textFields[i]);
        }
        inputButtonsPane = new JPanel();
        inputButtonsPane.setLayout(new BoxLayout(inputButtonsPane, BoxLayout.X_AXIS));
        buttonOk = new JButton("Enviar");
        buttonExit = new JButton("Sair");
        inputButtonsPane.add(buttonOk);
        inputButtonsPane.add(buttonExit);

        inputPane.add(Box.createVerticalGlue());
        for (int i = 0; i<fieldPanes.length;i++){
            inputPane.add(fieldPanes[i]);
        }
        inputPane.add(Box.createVerticalGlue());
        inputPane.add(inputButtonsPane);
        inputPane.add(Box.createVerticalGlue());

        //Output Pane
        outputPane = new JPanel();
        outputPane.setBorder(BorderFactory.createTitledBorder("Dados"));
        // outputPane.setSize(300,400);

        outputListModel = new DefaultListModel<String>();
        outputList = new JList<String>(outputListModel);
        outputList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(outputList);

        outputLabel = new JLabel("Mensagem de saída");
        outputButtonsPane = new JPanel();
        outputButtonsPane.setLayout(new BoxLayout(outputButtonsPane, BoxLayout.X_AXIS));
        buttonRemoveEntry = new JButton("Remover");
        buttonClear = new JButton("Limpar Dados");
        outputButtonsPane.add(buttonRemoveEntry);
        outputButtonsPane.add(buttonClear);

        buttonRemoveEntry.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        outputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        outputPane.setLayout(new BoxLayout(outputPane, BoxLayout.Y_AXIS));

        outputPane.add(Box.createVerticalGlue());
        outputPane.add(outputLabel);
        outputPane.add(Box.createVerticalGlue());
        outputPane.add(scrollPane);
        outputPane.add(Box.createVerticalGlue());
        outputPane.add(outputButtonsPane);
        outputPane.add(Box.createVerticalGlue());

        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        frame.getContentPane().add(inputPane, BorderLayout.LINE_START);
        frame.getContentPane().add(outputPane, BorderLayout.LINE_END);

        frame.setVisible(true);
    }
}
