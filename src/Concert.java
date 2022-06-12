import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Representation of a Concert
 *
 * @invariant VIPs != null
 * @invariant artist != null
 */
public class Concert extends BasicTemplateEvent {

    private final ArrayList<VIP> VIPs; // List of Vips
    private Artist artist; // the artist


    /**
     * Constructor of the Concert. To create new Concert Object.
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
     * @param artist
     *          the artist that is preforming
     * @throws AssertionError if artist == null
     */
    private Concert(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets, Artist artist){
        super(name, Optional.of(location),date,Optional.of(ticketPrice),Optional.of(maxNumberOfTickets));
        assert artist != null: "artist can not be null";

        this.VIPs = new ArrayList<>();
        this.artist = artist;
    }

    /**
     *  Static method to call the private Concert constructor.
     *  Checks if there is already an event created at the location and date.
     *  If there is then if the type of Event is Concert then that object is returned.
     *  If the Event is not a Concert then this method returns null.
     *  Else if it does not exist then it creates a new Concert and returns it.
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
     * @param artist
     *          the artist that is preforming
     * @throws IllegalArgumentException if name == null, artist == null, location == null, date ==null, ticketPrice < 0, or if maximumOfTickets < 0.
     * @return a Concert Object or null if you can not create one with this date and location.
     */
    public static Concert createConcert(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets, Artist artist){
        if(name == null) throw new IllegalArgumentException("name can not be null");
        if(artist == null) throw new IllegalArgumentException("artist can not be null");
        if(location == null) throw new IllegalArgumentException("location can not be null");
        if(date == null) throw new IllegalArgumentException("date can not be null");
        if(ticketPrice < 0) throw new IllegalArgumentException("ticket price should be bigger than or equal to 0");
        if(maxNumberOfTickets < 0) throw new IllegalArgumentException("number of tickets has to be bigger than or equal to 0");

        if(EventFactory.getEvent(location, date) instanceof Concert){
            return (Concert) EventFactory.getEvent(location, date);
        }else if (EventFactory.getEvent(location, date) == null){
            Concert newConcert = new Concert(name, location, date, ticketPrice, maxNumberOfTickets, artist);
            EventFactory.addEvent(newConcert);
            return newConcert;
        }else {
            return null;
        }
    }

    /**
     * @return the Artist Object.
     */
    public Artist getArtist(){
        return this.artist;
    }

    /**
     * Changes the Artist of the show to the new Artist passed in as a parameter.
     * @param artist
     *              The artist that will be preforming the Concert.
     * @throws IllegalArgumentException if artist == null
     */
    public void setArtist(Artist artist){
        if(artist == null) throw new IllegalArgumentException("artist can not be null");

        this.artist = artist;
    }

    /**
     * @return an Unmodifiable list of the VIPs
     */
    public List<VIP> getVIPs(){
        return Collections.unmodifiableList(this.VIPs);
    }

    /**
     * Adds a VIP to the VIPs list if it is not in the VIP list.
     * @param vip
     *          the vip to be added to the Vip List
     * @throws IllegalArgumentException if vip == null
     */
    public void addVIP(VIP vip){
        if(vip == null) throw new IllegalArgumentException("VIP name can not be null");

        if(!VIPs.contains(vip)) {
            VIPs.add(vip);
        }
    }

    /**
     * Method to accepts Visitor Objects.
     * calls the visitConcert method in the visitor Object and passes in the object that is calling it.
     *
     * @param visitor
     *              the Visitor Object that the visitConcert will be called on.
     * @throws IllegalArgumentException if visitor == null
     */
    @Override
    public void acceptVisitor(Visitor visitor) {
        if(visitor == null) throw new IllegalArgumentException("The visitor object can not be null");
        visitor.visitorConcert(this);
    }
}
