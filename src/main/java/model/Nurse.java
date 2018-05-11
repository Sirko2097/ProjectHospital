package model;

import java.sql.Date;

public class Nurse extends Human {
    private String licenseNumber;
    private String patientCard;

    public Nurse() {
    }

    public Nurse(String passportNumber, String firstName, String secondName, String lastName,
                 Date birthday, String licenseNumber) {
        super(passportNumber, firstName, secondName, lastName, birthday);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPatientCard() {
        return patientCard;
    }

    public void setPatientCard(String patientCard) {
        this.patientCard = patientCard;
    }


}
