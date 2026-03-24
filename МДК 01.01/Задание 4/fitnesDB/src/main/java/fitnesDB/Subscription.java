package fitnesDB;

import fitnesDB.enums.TypeSubscription;
import java.time.LocalDate;

public class Subscription {
    private int id;
    private Client owner;
    private TypeSubscription type;
    private LocalDate registrationDate;
    private LocalDate expirationDate;

    public Subscription(int id, Client owner, TypeSubscription type, LocalDate regDate, LocalDate expDate) {
        this.id = id;
        this.owner = owner;
        this.type = type;
        this.registrationDate = regDate;
        this.expirationDate = expDate;
    }

    public Client getOwner() {
        return owner;
    }
    public TypeSubscription getType() {
        return type;
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String toString() {
        return "Абонемент: " + type + " | Действует до: " + expirationDate;
    }
}
