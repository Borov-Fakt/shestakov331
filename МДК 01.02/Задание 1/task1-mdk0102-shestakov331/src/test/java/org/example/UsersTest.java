package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsersTest {

    private Users users;

    @BeforeEach
    void setUp() {
        users = new Users();
        users.addUser("Ivan", 25, true);
        users.addUser("Maria", 22, false);
        users.addUser("Petr", 33, true);
        users.addUser("Anna", 30, false);
    }

    @Test
    void testGenerateUsersReport() {
        String report = users.generateAllUsers();
        assertTrue(report.contains("name='Ivan'"));
        assertTrue(report.contains("gender='woman'"));
        assertTrue(report.contains("gender='man'"));
        assertTrue(report.contains("id=2"));
    }

    @Test
    void testGetMales() {
        User[] males = users.getMales();
        assertEquals(2, males.length, "Должно быть 2 мужчины");
        for (User user : males) {
            assertTrue(user.isMale());
        }
    }

    @Test
    void testGetFemales() {
        User[] females = users.getFemales();
        assertEquals(2, females.length, "Должно быть 2 женщины");
        for (User user : females) {
            assertFalse(user.isMale());
        }
    }

    @Test
    void testGetTotalUserCount() {
        assertEquals(4, users.getTotalUserCount());
        users.addUser("Елена", 40, false);
        assertEquals(5, users.getTotalUserCount());
    }

    @Test
    void testGetAverageAge() {
        double expectedAverage = (25 + 22 + 33 + 30) / 4.0;
        assertEquals(expectedAverage, users.getAverageAge(), 0.001);
    }

    @Test
    void testGetAverageAgeEmptyList() {
        Users emptyUsers = new Users();
        assertEquals(0.0, emptyUsers.getAverageAge());
    }
}
