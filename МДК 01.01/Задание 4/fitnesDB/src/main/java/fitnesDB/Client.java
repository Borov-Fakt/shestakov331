package fitnesDB;

import fitnesDB.enums.Gender;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private int birthYear;
    private Gender gender;
    private int balance;

    public Client(int id, String firstName, String lastName, int birthYear, Gender gender, int balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.gender = gender;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }
    public Gender getGender() {
        return gender;
    }
    public int getBalance() {
        return balance;
    }


    public String toString() {
        return "ID: " + id + " | " + firstName + " " + lastName + " | Год: " + birthYear + " | Пол: " + gender + " | Баланс: " + balance;
    }
}