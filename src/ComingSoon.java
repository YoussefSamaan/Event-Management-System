import java.time.LocalDate;
import java.util.Optional;

/**
 * Representation of Coming Soon event
 */
public class ComingSoon extends BasicTemplateEvent {

    /**
     * Constructor of the ComingSoon Event
     *
     * @param name
     *          the name of the coming soon event
     * @param date
     *          the date of the coming soon event
     */
    public ComingSoon(String name, LocalDate date) {
        super(name, Optional.empty(),date,Optional.empty(),Optional.empty());
    }

    /**
     * Method to accepts Visitor Objects.
     * Does nothing since it is still a coming soon event
     *
     * @param visitor
     *              the Visitor Object that the appropriate methode will be called on it.
     */
    @Override
    public void acceptVisitor(Visitor visitor) {}
}
