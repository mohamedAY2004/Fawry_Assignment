import java.time.LocalDate;

public class Expirable implements Trait {
    private final LocalDate expiryDate;

    public Expirable(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired() {
         return expiryDate.isBefore(LocalDate.now().minusDays(1));
    }

    @Override
    public Class<? extends Trait> getType() {
        return Expirable.class;
    }
}
