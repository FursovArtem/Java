package org.example.academyfx.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Direction {
    private final IntegerProperty directionId;
    private final StringProperty directionName;

    public Direction(Integer directionId, String directionName) {
        this.directionId = new SimpleIntegerProperty(directionId);
        this.directionName = new SimpleStringProperty(directionName);
    }

    public IntegerProperty directionIdProperty() {
        return directionId;
    }

    public final Integer getDirectionId() {
        return directionId.get();
    }

    public final void setDirectionId(Integer directionId) {
        this.directionId.set(directionId);
    }

    public StringProperty directionNameProperty() {
        return directionName;
    }

    public final String getDirectionName() {
        return directionName.get();
    }

    public final void setDirectionName(String directionName) {
        this.directionName.set(directionName);
    }

}
