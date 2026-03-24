package fitnes;

import fitnes.enums.Gender;

public class Client {
    private String firstName;
    private String lastName;
    private int birthYear;
    private Gender gender;

    public Client(String firstName, String lastName, int birthYear, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.gender = gender;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Gender getGender() {
        return gender;
    }
}