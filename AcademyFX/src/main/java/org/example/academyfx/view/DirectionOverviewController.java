package org.example.academyfx.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.academyfx.HelloApplication;
import org.example.academyfx.models.Direction;

public class DirectionOverviewController {
    @FXML
    private TableView<Direction> directionTable;
    @FXML
    private TableColumn<Direction, Integer> directionIdColumn;
    @FXML
    private TableColumn<Direction, String> directionNameColumn;

    private HelloApplication helloApplication;

    public DirectionOverviewController() {

    }

    @FXML
    private void initialize() {
        directionIdColumn.setCellValueFactory(cellData -> cellData.getValue().directionIdProperty().asObject());
        directionNameColumn.setCellValueFactory(cellData -> cellData.getValue().directionNameProperty());
    }

    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
        directionTable.setItems(helloApplication.getDirectionData());
    }
}