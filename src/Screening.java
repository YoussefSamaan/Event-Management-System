import java.time.LocalDate;
import java.util.Optional;

/**
 * Representation of a Screening
 *
 * @invariant rating != null
 */
public class Screening extends BasicTemplateEvent {

    private Rating rating; // rating of the screening

    /**
     * Constructor of a Screening. To create a new Screening Object.
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
     * @param rating
     *          the rating of the Screening
     * @throws AssertionError if rating == null
     */
    private Screening(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets, Rating rating){
        super(name, Optional.of(location),date,Optional.of(ticketPrice),Optional.of(maxNumberOfTickets));
        assert rating != null: "rating can not be null";

        this.rating = rating;
    }

    /**
     *  Static method to call the private Screening constructor.
     *  Checks if there is already an event created at the location and date.
     *  If there is then if the type of Event is Screening then that object is returned.
     *  If the Event is not a Screening then this method returns null.
     *  Else if it does not exist then it creates a new Screening and returns it.
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
     * @param rating
     *          the rating of the Screening
     * @return a Screening Object or null if you can not create one with this date and location.
     * @throws IllegalArgumentException if name == null, rating == null, location == null, date ==null, ticketPrice < 0, or if maximumOfTickets < 0.
     */
    public static Screening createScreening(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets, Rating rating){
        if(name == null) throw new IllegalArgumentException("name can not be null");
        if(rating == null) throw new IllegalArgumentException("rating can not be null");
        if(location == null) throw new IllegalArgumentException("location can not be null");
        if(date == null) throw new IllegalArgumentException("date can not be null");
        if(ticketPrice < 0) throw new IllegalArgumentException("ticket price should be bigger than or equal to 0");
        if(maxNumberOfTickets < 0) throw new IllegalArgumentException("number of tickets has to be bigger than or equal to 0");

        if(EventFactory.getEvent(location, date) instanceof Screening){
            return (Screening) EventFactory.getEvent(location, date);
        }else if (EventFactory.getEvent(location, date) == null){
            Screening newScreening = new Screening(name, location, date, ticketPrice, maxNumberOfTickets, rating);
            EventFactory.addEvent(newScreening);
            return newScreening;
        }else {
            return null;
        }
    }

    /**
     * @return rating of the Screening object
     */
    public Rating getRating(){
        return this.rating;
    }

    /**
     * Changes the rating of the Screening Object
     * @param rating
     *          the rating of the Screening to be changed.
     * @throws IllegalArgumentException if rating == null
     */
    public void setRating(Rating rating){
        if(rating == null) throw new IllegalArgumentException("Rating can not be null");

        this.rating = rating;
    }

    /**
     * Method to accepts Visitor Objects.
     * calls the visitScreening method in the visitor Object and passes in the object that is calling it.
     *
     * @param visitor
     *              the Visitor Object that the visitScreening will be called on.
     * @throws IllegalArgumentException if visitor == null
     */
    @Override
    public void acceptVisitor(Visitor visitor) {
        if(visitor == null) throw new IllegalArgumentException("The visitor object can not be null");
        visitor.visitorScreening(this);
    }
}
