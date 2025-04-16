package com.github.fursovartem.academymvc.controllers;

import com.github.fursovartem.academymvc.Connector;
import com.github.fursovartem.academymvc.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class MainViewController {
    @FXML
    private Tab tabStart;
    @FXML
    private BorderPane startRoot;
    @FXML
    private Tab tabDirections;
    @FXML
    private Tab tabStudents;
    @FXML
    private Tab tabTeachers;
    @FXML
    private TextField textFieldBottomLine;
    @FXML
    private ComboBox<String> comboBoxTables;

    @FXML
    private void initialize() {
        textFieldBottomLine.setText("Successfully connected   |   " + Connector.getConnection().toString() + "   |   " + Connector.getConnectionString());
        try {
            Statement statement = Connector.getConnection().createStatement();
            ResultSet set = statement.executeQuery("""
                    SELECT TABLE_NAME
                    FROM INFORMATION_SCHEMA.TABLES
                    WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_CATALOG = 'PD_212'
                    """);
            while (set.next()) comboBoxTables.getItems().add(set.getString(1));
            set.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onComboBoxTablesChange() {
        if (!comboBoxTables.getItems().isEmpty()) {
            try {
                TableView<String[]> table = new TableView<>();
                table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                Statement statement = Connector.getConnection().createStatement();
                ResultSet set = statement.executeQuery("""
                        SELECT *
                        FROM %s
                        """.formatted(comboBoxTables.getSelectionModel().selectedItemProperty().getValue()));
                ResultSetMetaData meta = set.getMetaData();
                for (int i = 0; i < meta.getColumnCount(); i++) {
                    TableColumn<String[], String> column = new TableColumn<>(meta.getColumnLabel(i + 1));
                    table.getColumns().add(column);
                    final int f = i;
                    column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[f]));
                }
                while (set.next()) {
                    Collection<String> list = new ArrayList<>();
                    for (int i = 0; i < meta.getColumnCount(); i++) {
                        list.add(set.getObject(i + 1).toString());
                    }
                    String[] array = new String[list.size()];
                    list.toArray(array);
                    table.getItems().addAll(array);
                }
                set.close();
                statement.close();
                startRoot.setCenter(table);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    protected void onTabDirectionsSelected() throws IOException {
        if (tabDirections.isSelected()) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("direction-view.fxml"));
            tabDirections.setContent(fxmlLoader.load());
        }
    }

    @FXML
    protected void onTabStudentsSelected() throws IOException {
        if(tabStudents.isSelected()) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("student-view.fxml"));
            tabStudents.setContent(fxmlLoader.load());
        }
    }

    @FXML
    protected void onTabTeachersSelected() throws IOException {
        if(tabTeachers.isSelected()) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("teacher-view.fxml"));
            tabTeachers.setContent(fxmlLoader.load());
        }
    }
}