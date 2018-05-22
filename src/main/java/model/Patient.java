package model;

import java.sql.Date;
import java.util.Objects;

public class Patient extends Human {
    private int patientCard;

    public Patient() {
    }

    public Patient(String passportNumber, String firstName, String secondName, String lastName,
                   Date birthday, int patientCard) {
        super(passportNumber, firstName, secondName, lastName, birthday);
        this.patientCard = patientCard;
    }

    public Patient(String firstName, String secondName, String lastName, Date birthday, int patientCard) {
        super(firstName, secondName, lastName, birthday);
        this.patientCard = patientCard;
    }

    public int getPatientCard() {
        return patientCard;
    }

    public void setPatientCard(int patientCard) {
        this.patientCard = patientCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getPatientCard(), patient.getPatientCard());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getPatientCard());
    }
}
