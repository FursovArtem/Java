package com.github.fursovartem.academymvc.models;

import javafx.beans.property.*;

import java.util.Date;

public class Teacher {
    private final IntegerProperty teacherId;
    private final StringProperty lastName;
    private final StringProperty firstName;
    private final StringProperty middleName;
    private final ObjectProperty<Date> birthDate;
    private final ObjectProperty<Date> workSince;

    public Teacher(Integer teacherId, String lastName, String firstName, String middleName, Date birthDate, Date workSince) {
        this.teacherId = new SimpleIntegerProperty(teacherId);
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName = new SimpleStringProperty(middleName);
        this.birthDate = new SimpleObjectProperty<>(birthDate);
        this.workSince = new SimpleObjectProperty<>(workSince);
    }

    public IntegerProperty teacherIdProperty() {
        return teacherId;
    }

    public final Integer getTeacherId() {
        return teacherId.get();
    }

    public final void setTeacherId(Integer teacherId) {
        this.teacherId.set(teacherId);
    }

    public StringProperty lastNameProperty() { return lastName; }

    public final String getLastName() { return lastName.get(); }

    public final void setLastName(String lastName) { this.lastName.set(lastName); }

    public StringProperty firstNameProperty() { return firstName; }

    public final String getFirstName() { return firstName.get(); }

    public final void setFirstNameName(String firstName) { this.firstName.set(firstName); }

    public StringProperty middleNameProperty() { return middleName; }

    public final String getMiddleName() { return middleName.get(); }

    public final void setMiddleName(String middleName) { this.middleName.set(middleName); }

    public ObjectProperty<Date> birthDateProperty() { return birthDate; }

    public final Date getBirthDate() { return birthDate.get(); }

    public final void setBirthDate(Date birthDate) { this.birthDate.set(birthDate); }

    public ObjectProperty<Date> workSinceProperty() { return workSince; }

    public final Date getWorkSince() { return workSince.get(); }

    public final void setWorkSince(Date workSince) { this.workSince.set(workSince); }
}
