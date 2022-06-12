import java.time.LocalDate;
import java.util.HashMap;

/**
 * Class to manage the events that are created.
 *
 * @invariant no two events can have the same date and location.
 */
public class EventFactory {

    private static final HashMap<String, Event> EVENTS_CREATED = new HashMap<>(); // a hashmap that has a list of all the events that will be created.

    /**
     * @param location
     *              the location of the event
     * @param date
     *              the date of the event
     * @return the Event Object in the hashmap if it is exists else it returns null.
     * @throws IllegalArgumentException if  location == null, and date == null
     */
    public static Event getEvent(Location location, LocalDate date){
        if(location == null) throw new IllegalArgumentException("location can not be null");
        if(date == null) throw new IllegalArgumentException("date can not be null");

        String eventDetails = "" +location+date;

        if(EVENTS_CREATED.containsKey(eventDetails)){
           return EVENTS_CREATED.get(eventDetails);
        }
        return null;
    }

    /**
     * Adds an event to the hashmap if no other event has the same location and date.
     *
     * @param event
     *          the event that will be added to the hashmap
     * @return the event if it was added else it will return null.
     * @throws IllegalArgumentException if event == null
     */
    public static Event addEvent(Event event){
        if(event == null) throw new IllegalArgumentException("Event can not be null");

        String eventDetails = "" +event.getLocation().get()+event.getDate();

        if(!EVENTS_CREATED.containsKey(eventDetails)){
            EVENTS_CREATED.put(eventDetails,event);
            return EVENTS_CREATED.get(eventDetails);
        }
        return null;
    }
}
