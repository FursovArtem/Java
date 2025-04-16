package com.github.fursovartem.academymvc.controllers;

import com.github.fursovartem.academymvc.Connector;
import com.github.fursovartem.academymvc.models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class TeacherViewController {
    @FXML
    private TableView<Teacher> teacherTable;
    @FXML
    private TableColumn<Teacher, Integer> teacherIdColumn;
    @FXML
    private TableColumn<Teacher, String> lastNameColumn;
    @FXML
    private TableColumn<Teacher, String> firstNameColumn;
    @FXML
    private TableColumn<Teacher, String> middleNameColumn;
    @FXML
    private TableColumn<Teacher, Date> birthDateColumn;
    @FXML
    private TableColumn<Teacher, Date> workSinceColumn;

    private final ObservableList<Teacher> list = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        try {
            teacherIdColumn.setCellValueFactory(data -> data.getValue().teacherIdProperty().asObject());
            lastNameColumn.setCellValueFactory(data -> data.getValue().lastNameProperty());
            firstNameColumn.setCellValueFactory(data -> data.getValue().firstNameProperty());
            middleNameColumn.setCellValueFactory(data -> data.getValue().middleNameProperty());
            birthDateColumn.setCellValueFactory(data -> data.getValue().birthDateProperty());
            workSinceColumn.setCellValueFactory(data -> data.getValue().workSinceProperty());
            Statement statement = Connector.getConnection().createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM Teachers");
            while(set.next()) list.add(new Teacher(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), (Date) set.getObject(5), (Date) set.getObject(6)));
            teacherTable.setItems(list);
            set.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
