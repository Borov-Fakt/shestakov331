package org.example;

public class Users {
    private int nextId = 1;
    private User[] userArray;
    private int userCount;

    public Users() {
        this.userArray = new User[10];
        this.userCount = 0;
    }

    public void addUser(String name, int age, boolean isMale) {
        if (userCount == userArray.length) {
            User[] newArray = new User[userArray.length * 2];
            for (int i = 0; i < userArray.length; i++) {
                newArray[i] = userArray[i];
            }
            userArray = newArray;
        }

        userArray[userCount] = new User(nextId, name, age, isMale);
        userCount++;
        nextId++;
    }

    public String generateAllUsers() {
        if (userCount == 0) {
            return "Список пуст";
        }
        String report = " ";
        for (int i = 0; i < userCount; i++) {
            report += userArray[i].toString();
        }
        return report;
    }


    public void displayAllUsers() {
        System.out.println(generateAllUsers());
    }

    private User[] UsersGender(boolean isMaleToFilter) {
        int matchingCount = 0;
        for (int i = 0; i < userCount; i++) {
            if (userArray[i].isMale() == isMaleToFilter) {
                matchingCount++;
            }
        }
        User[] filteredUsers = new User[matchingCount];
        int index = 0;

        for (int i = 0; i < userCount; i++) {
            if (userArray[i].isMale() == isMaleToFilter) {
                filteredUsers[index] = userArray[i];
                index++;
            }
        }
        return filteredUsers;
    }

    public User[] getMales() {
        return UsersGender(true);
    }

    public User[] getFemales() {
        return UsersGender(false);
    }

    public int getTotalUserCount() {
        return userCount;
    }

    public double getAverageAge() {
        if (userCount == 0) {
            return 0.0;
        }
        double totalAge = 0;
        for (int i = 0; i < userCount; i++) {
            totalAge += userArray[i].getAge();
        }
        return totalAge / userCount;
    }
}


