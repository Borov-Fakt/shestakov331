package org.example;

public class Main {
    static void main(String[] args) {
        Users users = new Users();

        users.addUser("Ivan", 25, true);
        users.addUser("Maria", 25, false);
        users.addUser("Peter", 33, true);

        users.displayAllUsers();
        int totalCount = users.getTotalUserCount();
        double averageAge = users.getAverageAge();
        System.out.println(totalCount);
        System.out.println(averageAge);

    User[] males = users.getMales();
        for (int i = 0; i < males.length; i++) {
        System.out.println(males[i]);
        }
    }
}
