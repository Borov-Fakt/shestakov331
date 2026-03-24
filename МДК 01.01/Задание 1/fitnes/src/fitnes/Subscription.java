package fitnes;

import fitnes.enums.TypeSubscription;
import java.time.LocalDate;

public class Subscription {
    private Client owner;
    private TypeSubscription type;
    private LocalDate registrationDate;
    private LocalDate expirationDate;

    public Subscription(Client owner, TypeSubscription type) {
        this.owner = owner;
        this.type = type;
        this.registrationDate = LocalDate.now();

        if (type == TypeSubscription.ONETIME) {
            this.expirationDate = LocalDate.now();
        } else {
            this.expirationDate = LocalDate.now().plusYears(1);
        }
    }

    public Subscription(Client owner, TypeSubscription type, LocalDate expirationDate) {
        this.owner = owner;
        this.type = type;
        this.registrationDate = LocalDate.now();
        this.expirationDate = expirationDate;
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
}
