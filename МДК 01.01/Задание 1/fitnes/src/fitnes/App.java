package fitnes;

import fitnes.enums.FitnessZone;
import fitnes.enums.Gender;
import fitnes.enums.TypeSubscription;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Client client1 = new Client("Иван", "Ага", 1990, Gender.MALE);
        Client client2 = new Client("Маша", "Петрова", 1995, Gender.FEMALE);

        Subscription subOneTime = new Subscription(client1, TypeSubscription.ONETIME);
        Subscription subDay = new Subscription(client2, TypeSubscription.DAYTIME, LocalDate.now().plusYears(1));

        Fitness fitness = new Fitness();
        fitness.processSubscription(subOneTime, FitnessZone.SWIMPOOL);
        fitness.processSubscription(subDay, FitnessZone.GROUP_EXERCISES);
        fitness.closeClub();
    }
}

