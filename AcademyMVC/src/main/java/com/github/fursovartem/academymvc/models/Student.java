package com.github.fursovartem.academymvc.models;

import javafx.beans.property.*;

import java.util.Date;

public class Student {
    private final IntegerProperty studentId;
    private final StringProperty lastName;
    private final StringProperty firstName;
    private final StringProperty middleName;
    private final ObjectProperty<Date> birthDate;
    private final IntegerProperty group;

    public Student(Integer studentId, String lastName, String firstName, String middleName, Date birthDate, Integer group) {
        this.studentId = new SimpleIntegerProperty(studentId);
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName = new SimpleStringProperty(middleName);
        this.birthDate = new SimpleObjectProperty<>(birthDate);
        this.group = new SimpleIntegerProperty(group);
    }

    public IntegerProperty studentIdProperty() {
        return studentId;
    }

    public final Integer getStudentId() {
        return studentId.get();
    }

    public final void setStudentId(Integer studentId) {
        this.studentId.set(studentId);
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

    public IntegerProperty groupProperty() { return group; }

    public final Integer getGroup() { return group.get(); }

    public final void setGroup(Integer group) { this.group.set(group); }
}
