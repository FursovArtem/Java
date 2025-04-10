package org.example;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() throws HeadlessException {
        this.setBounds(200, 200, 400, 300);
        this.setLayout((LayoutManager) null);
        this.setVisible(true);
        this.setTitle("AWT - Abstract Window Toolkit");
        Label lblLastName = new Label("Last Name: ");
        Label lblFirstName = new Label("First Name: ");
        Label lblBirthDate = new Label("Birth Date: ");
        TextField textLastName = new TextField();
        TextField textFirstName = new TextField();
        TextField textBirthDate = new TextField();
        Button btnSubmit = new Button("Submit");
        Button btnReset = new Button("Reset");
        lblLastName.setBounds(20, 50, 100, 24);
        lblFirstName.setBounds(20, 100, 100, 24);
        lblBirthDate.setBounds(20, 150, 100, 24);
        textLastName.setBounds(120, 50, 100, 24);
        textFirstName.setBounds(120, 100, 100, 24);
        textBirthDate.setBounds(120, 150, 100, 24);
        btnSubmit.setBounds(20, 200, 100, 32);
        btnReset.setBounds(120, 200, 100, 32);
        this.add(lblLastName);
        this.add(lblFirstName);
        this.add(lblBirthDate);
        this.add(textLastName);
        this.add(textFirstName);
        this.add(textBirthDate);
        this.add(btnSubmit);
        this.add(btnReset);
    }
}