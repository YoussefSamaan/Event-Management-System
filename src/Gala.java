import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents a Gala
 */
public class Gala extends BasicTemplateEvent {

    private final ArrayList<VIP> VIPs; // list of VIPs that wll attend the gala

    /**
     * Constructor of the Gala. Creates new Gala Objects.
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
    private Gala(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets){
        super(name, Optional.of(location),date,Optional.of(ticketPrice),Optional.of(maxNumberOfTickets));
        this.VIPs = new ArrayList<>();
    }

    /**
     *  Static method to call the private Gala constructor.
     *  Checks if there is already an event created at the location and date.
     *  If there is then if the type of Event is Gala then that object is returned.
     *  If the Event is not a Gala then this method returns null.
     *  Else if it does not exist then it creates a new Gala and returns it.
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
     * @return a Gala Object or null if you can not create one with this date and location.

     */
    public static Gala createGala(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets){
        if(name == null) throw new IllegalArgumentException("name can not be null");
        if(location == null) throw new IllegalArgumentException("location can not be null");
        if(date == null) throw new IllegalArgumentException("date can not be null");
        if(ticketPrice < 0) throw new IllegalArgumentException("ticket price should be bigger than or equal to 0");
        if(maxNumberOfTickets < 0) throw new IllegalArgumentException("number of tickets has to be bigger than or equal to 0");

        if(EventFactory.getEvent(location, date) instanceof Gala){
            return (Gala) EventFactory.getEvent(location, date);
        }else if (EventFactory.getEvent(location, date) == null){
            Gala newGala = new Gala(name, location, date, ticketPrice, maxNumberOfTickets);
            EventFactory.addEvent(newGala);
            return newGala;
        }else {
            return null;
        }
    }

    /**
     * @return an unmodifiable list of the VIPs added to the Gala
     */
    public List<VIP> getVIPs(){
        return Collections.unmodifiableList(this.VIPs);
    }

    /**
     * Adds a VIP to the VIPs list if it is not in the VIP list.
     *
     * @param vip
     *          the vip to be added to the Vip List
     * @throws IllegalArgumentException if vip == null
     */
    public void addVIP(VIP vip){
        if(vip == null) throw new IllegalArgumentException("VIP name can not be null");

        VIPs.add(vip);
    }

    /**
     * Method to accepts Visitor Objects.
     * calls the visitGala method in the visitor Object and passes in the object that is calling it.
     *
     * @param visitor
     *              the Visitor Object that the visitGala will be called on.
     * @throws IllegalArgumentException if visitor == null
     */
    @Override
    public void acceptVisitor(Visitor visitor) {
        if(visitor == null) throw new IllegalArgumentException("The visitor object can not be null");
        visitor.visitGala(this);
    }

}
