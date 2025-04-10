package org.example;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws SQLException {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1f1f22;");

        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
                "database=PD_212;" +
                "user=PHP;" +
                "password=111;" +
                "TrustServerCertificate=True;");
        Statement statement = connection.createStatement();
        ResultSet tableName = statement.executeQuery("""
                SELECT
                TABLE_NAME
                FROM
                INFORMATION_SCHEMA.TABLES
                WHERE
                TABLE_TYPE = 'BASE TABLE' AND TABLE_CATALOG = 'PD_212'
                """);
        ObservableList<String> tableNames = FXCollections.observableArrayList();
        while (tableName.next()) {
            tableNames.add(tableName.getObject(1).toString());
        }

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 10, 15, 10));
        hbox.setSpacing(50);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-border-style: dotted;" +
                "-fx-border-color: #e7e7e7;");

        Button buttonTest = new Button("_Test");
        buttonTest.setPrefSize(100, 24);
        buttonTest.setOnAction(_ -> System.out.println("button Test pressed"));
        Button buttonDisconnect = new Button("_Disconnect");
        buttonDisconnect.setPrefSize(100, 24);
        buttonDisconnect.setOnAction(_ -> {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        hbox.getChildren().addAll(buttonTest, buttonDisconnect);

        root.setTop(hbox);
        BorderPane.setMargin(hbox, new Insets(5, 5, 5, 5));

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15, 10, 15, 10));
        vbox.setSpacing(50);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setStyle("-fx-border-style: dashed;" +
                "-fx-border-color: #e7e7e7;");

        ComboBox<String> tablesComboBox = new ComboBox<>(tableNames);
        tablesComboBox.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            try {
                TableView<String[]> table = createTableBySelectingComboBoxVar(statement, newValue);
                table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
                root.setCenter(table);
                BorderPane.setMargin(table, new Insets(5, 5, 5, 5));
            } catch (SQLException e) {
                Label error = new Label("Disconnected");
                error.setStyle("-fx-text-fill: #e7e7e7;" +
                        "-fx-font-size: 32px;");
                root.setCenter(error);
                throw new RuntimeException(e);
            }
        });
        vbox.getChildren().add(tablesComboBox);

        root.setLeft(vbox);
        BorderPane.setMargin(vbox, new Insets(5, 5, 5, 5));

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Hello JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public TableView<String[]> createTableBySelectingComboBoxVar(Statement statement, String comboBoxString) throws SQLException {
        TableView<String[]> table = new TableView<>();
        ResultSet resultSet = statement.executeQuery("""
                SELECT
                *
                FROM
                %s
                """.formatted(comboBoxString));
        ResultSetMetaData meta = resultSet.getMetaData();
        for (int i = 0; i < meta.getColumnCount(); i++) {
            TableColumn<String[], String> column = new TableColumn<>(meta.getColumnLabel(i + 1));
            table.getColumns().add(column);
            int iFinal = i;
            column.setCellValueFactory(cellData -> {
                String[] x = cellData.getValue();
                return new SimpleStringProperty(x[iFinal]);
            });
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
        return table;
    }

    public static void main(String[] args) {
        launch();
    }
}