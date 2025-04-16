package com.github.fursovartem.academymvc.controllers;

import com.github.fursovartem.academymvc.Connector;
import com.github.fursovartem.academymvc.models.Direction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DirectionViewController {
    @FXML
    private TableView<Direction> directionTable;
    @FXML
    private TableColumn<Direction, Integer> directionIdColumn;
    @FXML
    private TableColumn<Direction, String> directionNameColumn;

    private final ObservableList<Direction> list = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        try {
            directionIdColumn.setCellValueFactory(data -> data.getValue().directionIdProperty().asObject());
            directionNameColumn.setCellValueFactory(data -> data.getValue().directionNameProperty());
            Statement statement = Connector.getConnection().createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM Directions");
            while (set.next()) list.add(new Direction(set.getInt(1), set.getString(2)));
            directionTable.setItems(list);
            set.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}