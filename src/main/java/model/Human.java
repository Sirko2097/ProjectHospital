package model;

import java.sql.Date;
import java.util.Objects;

public class Human {
    private String passportNumber;
    private String firstName;
    private String secondName;
    private String lastName;
    private Date birthday;

    public Human() {
    }

    public Human(String passportNumber, String firstName, String secondName, String lastName, Date birthday) {
        this.passportNumber = passportNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return Objects.equals(getPassportNumber(), human.getPassportNumber()) &&
                Objects.equals(getFirstName(), human.getFirstName()) &&
                Objects.equals(getSecondName(), human.getSecondName()) &&
                Objects.equals(getLastName(), human.getLastName()) &&
                Objects.equals(getBirthday(), human.getBirthday());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPassportNumber(), getFirstName(), getSecondName(), getLastName(), getBirthday());
    }
}
