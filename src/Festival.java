import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Representation of a festival Holds the events happening in it.
 */
public class Festival extends BasicTemplateEvent {

    private final static double discount = 0.8; // the discount applied on the tickets of the festival
    private final ArrayList<Event> LIST_OF_EVENTS = new ArrayList<>(); // the list of events in the festival

    /**
     * Constructor of the Festival. To create new Festival Object.
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
     * @param events
     *          the events happening in the festival
     * @throws AssertionError if event == null
     */
    private Festival(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets, List<Event> events){
        super(name, Optional.of(location),date,Optional.of(ticketPrice),Optional.of(maxNumberOfTickets));
        assert events!= null: "the list of events can not be null";
        this.LIST_OF_EVENTS.addAll(events);
    }

    /**
     * Constructor of the Festival. To create new Festival Object if there are no events or if there are only coming soon Events.
     *
     * @param name
     *          the name of the coming soon event
     * @param date
     *          the date when the concert is happening
     * @param events
     *          the events happening in the festival
     * @throws AssertionError if event == null
     */
    private Festival(String name, LocalDate date, List<Event> events){
        super(name, Optional.empty(),date,Optional.empty(),Optional.empty());
        assert events!= null: "the list of events can not be null";
        for(Event e: events) {
            this.LIST_OF_EVENTS.add(e);
        }
    }

    /**
     * Creates a festival Object from the name and the List of events.
     * the Price of the ticket will be the sum of the prices of all the tickets in the festival multiplied by a discount.
     * the location will either be the single location where all the festivals are happening, or it will indicate multiple.
     * the date will be the earliest date of the event that is happening in the festival.
     * the number of tickets will be equal to the least number of tickets sold at an event.
     *
     * @param name
     *          the name of the coming soon event
     * @param events
     *          the events happening in the festival
     * @return a festival object
     * @throws IllegalArgumentException if name == null, or the list of events == null
     */
    public static Festival createFestival(String name, List<Event> events){
        if(name == null) throw new IllegalArgumentException("name can not be null");
        if(events == null) throw new IllegalArgumentException("list of events can not be null");

        // variables
        Location location = null;
        LocalDate date = LocalDate.MAX;
        double ticketPrice = 0;
        int numberOfTickets = Integer.MAX_VALUE;

        // setup of the fields
        for(Event e: events) {

            // calculating ticket price
            if(e.getPrice().isPresent()) {
                ticketPrice += e.getPrice().get();
            }

            // location
            if(e.getLocation().isPresent()) {
                if (location != Location.Multiple) {
                    if (location == null) {
                        location = e.getLocation().get();
                    } else if (location != e.getLocation().get()) {
                        location = Location.Multiple;
                    }
                }
            }

            // date
            if(date == null || date.isAfter(e.getDate())){
                date = e.getDate();
            }

            // max number of tickets
            if(e.getNumTickets().isPresent()) {
                if (numberOfTickets > e.getNumTickets().get()) {
                    numberOfTickets = e.getNumTickets().get();
                }
            }

        }

        ticketPrice = ticketPrice * (1-discount);

        try {
            return new Festival(name, location, date, ticketPrice, numberOfTickets, events);
        }catch (NullPointerException | NoSuchElementException e){
            return new Festival(name, date, events);
        }
    }


    /**
     * Accepts a Visitor Object. Calls the acceptVisitor method on the events that are happening in the festival.
     *
     * @param visitor
     *              the Visitor Object that will be used to call the acceptVisitor of the events in the festival.
     * @throws IllegalArgumentException if visitor == null
     */
    @Override
    public void acceptVisitor(Visitor visitor) {
        if(visitor == null) throw new IllegalArgumentException("The visitor object can not be null");
        for(Event e: LIST_OF_EVENTS){
            e.acceptVisitor(visitor);
        }
    }
}
