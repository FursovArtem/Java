package com.github.fursovartem.academymvc.controllers;

import com.github.fursovartem.academymvc.Connector;
import com.github.fursovartem.academymvc.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class StudentViewController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> studentIdColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> middleNameColumn;
    @FXML
    private TableColumn<Student, Date> birthDateColumn;
    @FXML
    private TableColumn<Student, Integer> groupColumn;

    private final ObservableList<Student> list = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        try {
            studentIdColumn.setCellValueFactory(data -> data.getValue().studentIdProperty().asObject());
            lastNameColumn.setCellValueFactory(data -> data.getValue().lastNameProperty());
            firstNameColumn.setCellValueFactory(data -> data.getValue().firstNameProperty());
            middleNameColumn.setCellValueFactory(data -> data.getValue().middleNameProperty());
            birthDateColumn.setCellValueFactory(data -> data.getValue().birthDateProperty());
            groupColumn.setCellValueFactory(data -> data.getValue().groupProperty().asObject());
            Statement statement = Connector.getConnection().createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM Students");
            while(set.next()) list.add(new Student(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), (Date) set.getObject(5), set.getInt(6)));
            studentTable.setItems(list);
            set.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
