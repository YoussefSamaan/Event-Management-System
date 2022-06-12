import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents a Workshop
 *
 * @invariant prerequisites != null
 */
public class Workshop extends BasicTemplateEvent {

    private final ArrayList<Workshop> prerequisites; // the workshops that need to be done before this one.

    /**
     * Constructor of the Workshop. To create new Workshop Object.
     *
     * @param name
     *          the name of the coming soon event
     * @param location
     *          the location where the concert is happening
     * @param date
     *          the date when the concert is happening
     * @param ticketPrice
     *          the price of the ticket
     * @param maxNumberOfTickets
     *          the maximum number of tickets that can be sold
     */
    private Workshop(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets){
        super(name, Optional.of(location),date,Optional.of(ticketPrice),Optional.of(maxNumberOfTickets));
        this.prerequisites = new ArrayList<>();
    }

    /**
     *  Static method to call the private Workshop constructor.
     *  Checks if there is already an event created at the location and date.
     *  If there is then if the type of Event is Workshop then that object is returned.
     *  If the Event is not a Workshop then this method returns null.
     *  Else if it does not exist then it creates a new Workshop and returns it.
     *
     * @param name
     *          the name of the coming soon event
     * @param location
     *          the location where the concert is happening
     * @param date
     *          the date when the concert is happening
     * @param ticketPrice
     *          the price of the ticket
     * @param maxNumberOfTickets
     *          the maximum number of tickets that can be sold
     * @throws IllegalArgumentException if name == null, location == null, date ==null, ticketPrice < 0, or if maximumOfTickets < 0.
     * @return a Workshop Object or null if you can not create one with this date and location.
     */
    public static Workshop createWorkshop(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets){
        if(name == null) throw new IllegalArgumentException("name can not be null");
        if(location == null) throw new IllegalArgumentException("location can not be null");
        if(date == null) throw new IllegalArgumentException("date can not be null");
        if(ticketPrice < 0) throw new IllegalArgumentException("ticket price should be bigger than or equal to 0");
        if(maxNumberOfTickets < 0) throw new IllegalArgumentException("number of tickets has to be bigger than or equal to 0");

        if(EventFactory.getEvent(location, date) instanceof Workshop){
            return (Workshop) EventFactory.getEvent(location, date);
        }else if (EventFactory.getEvent(location, date) == null){
            Workshop newWorkshop = new Workshop(name, location, date, ticketPrice, maxNumberOfTickets);
            EventFactory.addEvent(newWorkshop);
            return newWorkshop;
        }else {
            return null;
        }
    }

    /**
     * @return an unmodifiable list of the prerequisite workshops.
     */
    public List<Workshop> getPrerequisiteWorkshops(){
        return Collections.unmodifiableList(this.prerequisites);
    }

    /**
     * Adds a prerequisite workshop to the prerequisite workshop list.
     *
     * @param workshop
     *              the workshop that will be added to the prerequisites
     * @throws IllegalArgumentException if workshop == null
     */
    public void addToPrerequisiteWorkshops(Workshop workshop){
        if(workshop == null) throw new IllegalArgumentException("Workshop can not be null");

        this.prerequisites.add(workshop);
    }

    /**
     * removes a workshop from the list of prerequisite workshops.
     *
     * @param workshop
     *              the workshop to be removed
     * @throws IllegalArgumentException if workshop == null
     */
    public void removePrerequisiteWorkshop(Workshop workshop){
        if(workshop == null) throw new IllegalArgumentException("Workshop can not be null");

        this.prerequisites.remove(workshop);
    }

    /**
     * Method to accepts Visitor Objects.
     * calls the visitWorkshop method in the visitor Object and passes in the object that is calling it.
     *
     * @param visitor
     *              the Visitor Object that the visitWorkshop will be called on.
     * @throws IllegalArgumentException if visitor == null
     */
    @Override
    public void acceptVisitor(Visitor visitor) {
        if(visitor == null) throw new IllegalArgumentException("The visitor object can not be null");
        visitor.visitorWorkshop(this);
    }

}
