package org.example.academyfx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.academyfx.models.Direction;
import org.example.academyfx.view.DirectionOverviewController;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    private Stage stage;
    private BorderPane rootLayout;
    private TabPane tabPane;
    private Tab tabDirections;

    private ObservableList<Direction> directionData = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Academy FX");

        initRootLayout();

        showDirectionOverview();
    }

    public HelloApplication() {
        directionData.add(new Direction(777, "test"));
        directionData.add(new Direction(123, "qwerty"));
        directionData.add(new Direction(456, "asdfg"));
        directionData.add(new Direction(789, "zxcvb"));
    }

    public ObservableList<Direction> getDirectionData() {
        return directionData;
    }

    public Stage getStage() {
        return stage;
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/view/RootLayout.fxml"));
            rootLayout = loader.load();
            tabPane = (TabPane) rootLayout.getCenter();
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showDirectionOverview() {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/view/DirectionOverview.fxml"));
            AnchorPane directionOverview = loader.load();
            tabDirections = tabPane.getTabs().get(1);
            tabDirections.setContent(directionOverview);
            DirectionOverviewController controller = loader.getController();
            controller.setHelloApplication(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}