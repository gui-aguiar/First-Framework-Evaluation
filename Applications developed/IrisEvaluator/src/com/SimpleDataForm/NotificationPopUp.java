package com.SimpleDataForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationPopUp extends JFrame{
    private String messages[];
    private String message;
    private String title;
    private JEditorPane msgPane;
    // public NotificationPopUp(messages){
    //     this.messages = new String[100];
    //     if (messages && messages.length > 0){
    //         for (int i = 0; i < messages.length; i++){
    //             //
    //         }
    //         this.messages = messages;
    //     }
    // }

    public NotificationPopUp(String title, String message){
        this.title = title;
        this.message = message;
        this.setTitle(title);
        this.start();
    }
    public void start(){
        this.setSize(500, 400);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.msgPane = new JEditorPane();
        this.msgPane.setContentType("text/html");
        this.msgPane.setText(this.message);
        JButton buttonExit = new JButton("Fechar");
        JFrame frame = this;
        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // close jframe window
                frame.setVisible(false);
                frame.dispose();
            }
        });
        JScrollPane scrollPane = new JScrollPane(this.msgPane);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.getContentPane().add(buttonExit, BorderLayout.CENTER);
        // this.pack();
        this.setVisible(true);
    }

}
