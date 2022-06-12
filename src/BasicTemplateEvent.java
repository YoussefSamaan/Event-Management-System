import java.time.LocalDate;
import java.util.Optional;

/**
 * Basic functionality of an Event. All events have to extend this class
 *
 * @invariant name != null
 * @invariant date != null
 * @invariant location != null
 * @invariant ticketPrice != null
 * @invariant maxNumberOfTickets != null
 */
public abstract class BasicTemplateEvent implements Event{
    private final String name; // name of the event
    private final Optional<Location> location; // location of the event
    private final LocalDate date; // date of the event
    private final Optional<Double> ticketPrice; // price of the ticket
    private final Optional<Integer> maxNumberOfTickets; // number of tickets that can be sold

    /**
     * Creates an event with the parameters provided
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
     * @throws IllegalArgumentException if name == null, date == null, ticketPrice if
     */
    protected BasicTemplateEvent(String name, Optional<Location> location, LocalDate date, Optional<Double> ticketPrice, Optional<Integer> maxNumberOfTickets){
        if( name == null) throw new IllegalArgumentException("name can not be null");
        if (date == null) throw new IllegalArgumentException("date can not be null");
        if (ticketPrice.isPresent()){
            if(ticketPrice.get() < 0) throw new IllegalArgumentException("ticket price should be bigger than or equal to 0");
        }
        if (maxNumberOfTickets.isPresent()){
            if(maxNumberOfTickets.get() < 0) throw new IllegalArgumentException("number of tickets has to be bigger than or equal to 0");
        }

        this.name = name;
        this.location = location;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.maxNumberOfTickets = maxNumberOfTickets;
    }

    /**
     * @return the name of the event
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Optional argument indicating the location of the Event
     */
    public Optional<Location> getLocation() {
        return this.location;
    }

    /**
     * @return the date when the festival is being held
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * @return Optional argument indicating the price of the ticket
     */
    public Optional<Double> getPrice() {
        return this.ticketPrice;
    }

    /**
     * @return Optional argument indicating the number of tickets that can be sold
     */
    public Optional<Integer> getNumTickets() {
        return this.maxNumberOfTickets;
    }

    /**
     * A method that has to be implemented in every class to be able to manage Visitor Objects into their class.
     *
     * @param visitor
     *          the visitor Object that will be handled by the concrete events.
     */
    public abstract void acceptVisitor(Visitor visitor);
}
