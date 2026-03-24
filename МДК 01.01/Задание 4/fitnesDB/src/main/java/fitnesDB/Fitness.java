package fitnesDB;

import fitnesDB.enums.FitnessZone;
import fitnesDB.enums.TypeSubscription;
import java.time.LocalDate;
import java.time.LocalTime;

public class Fitness {
    private Subscription[] gymMembers = new Subscription[20];
    private Subscription[] poolMembers = new Subscription[20];
    private Subscription[] groupMembers = new Subscription[20];

    public void processSubscription(Subscription sub, FitnessZone zone) {
        if (sub.getExpirationDate().isBefore(LocalDate.now())) {
            System.out.println("Доступ запрещен: Срок действия истек.");
            return;
        }

        LocalTime now = LocalTime.now();
        boolean isZoneAllowed = false;
        boolean isTimeAllowed = false;

        if (sub.getType() == TypeSubscription.ONETIME) {
            if (zone == FitnessZone.GYM || zone == FitnessZone.SWIMPOOL) isZoneAllowed = true;
            if (now.isAfter(LocalTime.of(8, 0)) && now.isBefore(LocalTime.of(22, 0))) isTimeAllowed = true;
        } else if (sub.getType() == TypeSubscription.DAYTIME) {
            if (zone == FitnessZone.GYM || zone == FitnessZone.GROUP_EXERCISE) isZoneAllowed = true;
            if (now.isAfter(LocalTime.of(8, 0)) && now.isBefore(LocalTime.of(16, 0))) isTimeAllowed = true;
        } else if (sub.getType() == TypeSubscription.FULLTIME) {
            isZoneAllowed = true;
            if (now.isAfter(LocalTime.of(8, 0)) && now.isBefore(LocalTime.of(22, 0))) isTimeAllowed = true;
        }

        if (!isZoneAllowed) {
            System.out.println("Доступ запрещен к зоне " + zone);
            return;
        }
        if (!isTimeAllowed) {
            System.out.println("Доступ запрещен в данное время.");
            return;
        }

        Subscription[] targetArray = gymMembers;
        if (zone == FitnessZone.SWIMPOOL) targetArray = poolMembers;
        if (zone == FitnessZone.GROUP_EXERCISE) targetArray = groupMembers;

        for (int i = 0; i < targetArray.length; i++) {
            if (targetArray[i] != null && targetArray[i].getOwner().getId() == sub.getOwner().getId()) {
                System.out.println("Ошибка: Клиент уже находится в этой зоне.");
                return;
            }
        }

        boolean added = false;
        for (int i = 0; i < targetArray.length; i++) {
            if (targetArray[i] == null) {
                targetArray[i] = sub;
                added = true;
                System.out.println("Успех: " + sub.getOwner().getFullName() + " вошел в " + zone);
                break;
            }
        }

        if (!added) System.out.println("Доступ запрещен: нет свободных мест.");
    }

    public void closeClub() {
        for (int i = 0; i < 20; i++) {
            gymMembers[i] = null;
            poolMembers[i] = null;
            groupMembers[i] = null;
        }
        System.out.println("Клуб закрыт. Все клиенты покинули зоны.");
    }
}
