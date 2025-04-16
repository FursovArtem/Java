module org.example.academyfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.apache.commons.dbutils;
    requires java.sql;

    opens org.example.academyfx to javafx.fxml;
    exports org.example.academyfx;
    exports org.example.academyfx.view;
    opens org.example.academyfx.view to javafx.fxml;
    exports org.example.academyfx.models;
    opens org.example.academyfx.models to javafx.fxml;
}