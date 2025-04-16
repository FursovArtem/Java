package org.example.academyfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class HelloController {
    private Connector connector = null;
    @FXML
    private TabPane tabPaneRoot;
    @FXML
    private BorderPane root;
    @FXML
    private ComboBox<String> comboBoxTableSchemas;
    @FXML
    private TextArea textAreaStatus;
    @FXML
    private TableView<String[]> tableDirections;

    @FXML
    protected void onConnectButtonClick() {
        if (connector == null) {
            try {
                connector = new Connector("jdbc:sqlserver://localhost:1433;" +
                        "database=PD_212;" +
                        "user=PHP;" +
                        "password=111;" +
                        "TrustServerCertificate=True;");
                textAreaStatus.setText("Successfully connected\n\n" + connector.getConnection().toString() + "\n\n" + connector.getConnectionString());
            } catch (SQLException e) {
                Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                error.showAndWait();
            }
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR, "Connection already exists", ButtonType.OK);
            error.showAndWait();
        }
    }

    @FXML
    protected void onCheckButtonClick() {
        Alert info;
        if (connector != null) {
            info = new Alert(Alert.AlertType.INFORMATION, "Connected to\n" + connector.getConnectionString(), ButtonType.OK);
        } else {
            info = new Alert(Alert.AlertType.INFORMATION, "Disconnected", ButtonType.OK);
        }
        info.showAndWait();
    }

    @FXML
    protected void onDisconnectButtonClick() {
        if (connector != null) {
            comboBoxTableSchemas.getItems().clear();
            tableDirections.getItems().clear();
            tableDirections.getColumns().clear();
            textAreaStatus.setText("Disconnected");
            root.setCenter(null);
            connector.closeConnection();
            connector = null;
        }
    }

    @FXML
    protected void onLoadButtonClick() {
        if (connector != null && comboBoxTableSchemas.getItems().isEmpty()) {
            try {
                Statement statement = connector.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("""
                        SELECT
                        TABLE_NAME
                        FROM
                        INFORMATION_SCHEMA.TABLES
                        WHERE
                        TABLE_TYPE = 'BASE TABLE' AND TABLE_CATALOG = 'PD_212'
                        """);
                while (resultSet.next()) {
                    comboBoxTableSchemas.getItems().add(resultSet.getString(1));
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                error.showAndWait();
            }
        }
    }

    @FXML
    protected void onComboBoxTableSchemasChange() {
        if (!comboBoxTableSchemas.getItems().isEmpty()) {
            try {
                TableView<String[]> table = new TableView<>();
                Statement statement = connector.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("""
                        SELECT
                        *
                        FROM
                        %s
                        """.formatted(comboBoxTableSchemas.getSelectionModel().selectedItemProperty().getValue()));
                ResultSetMetaData meta = resultSet.getMetaData();
                for (int i = 0; i < meta.getColumnCount(); i++) {
                    TableColumn<String[], String> column = new TableColumn<>(meta.getColumnLabel(i + 1));
                    table.getColumns().add(column);
                    final int f = i;
                    column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[f]));
                }
                while (resultSet.next()) {
                    Collection<String> list = new ArrayList<>();
                    for (int i = 0; i < meta.getColumnCount(); i++) {
                        list.add(resultSet.getObject(i + 1).toString());
                    }
                    String[] array = new String[list.size()];
                    list.toArray(array);
                    table.getItems().addAll(array);
                }
                root.setCenter(table);
                BorderPane.setMargin(table, new Insets(10, 10, 5, 5));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    protected void onDirectionsTabLoad() {
        if (connector != null) {
            try {
                tableDirections.getItems().clear();
                tableDirections.getColumns().clear();
                Statement statement = connector.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Directions");
                for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                    TableColumn<String[], String> column = new TableColumn<>(resultSet.getMetaData().getColumnLabel(i + 1));
                    tableDirections.getColumns().add(column);
                    final int f = i;
                    column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[f]));
                }
                while (resultSet.next()) {
                    Collection<String> list = new ArrayList<>();
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
                        list.add(resultSet.getString(i));
                    String[] array = new String[list.size()];
                    list.toArray(array);
                    tableDirections.getItems().addAll(array);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert info = new Alert(Alert.AlertType.INFORMATION, "You have to establish connection", ButtonType.OK);
            tabPaneRoot.getSelectionModel().select(0);
            info.showAndWait();
        }
    }
}