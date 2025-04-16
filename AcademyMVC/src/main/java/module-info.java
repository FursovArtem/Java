module com.github.fursovartem.academymvc {
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

    opens com.github.fursovartem.academymvc to javafx.fxml;
    exports com.github.fursovartem.academymvc;
    exports com.github.fursovartem.academymvc.controllers;
    opens com.github.fursovartem.academymvc.controllers to javafx.fxml;
}