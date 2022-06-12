import java.util.ArrayList;
import java.util.List;

/**
 * Location filter
 */
public class LocationFilter extends FilterTemplate{

    private final Location location; // the location to be removed

    /**
     * Constructor of the Location Filter Object
     *
     * @param filter
     *              the other filter(s) to be applied on the list
     * @param unwantedLocation
     *              the location to be removed
     * @throws IllegalArgumentException if unwantedLocation ==null
     */
    public LocationFilter(Filter filter,Location unwantedLocation) {
        super(filter);
        if(unwantedLocation == null) throw new IllegalArgumentException("Location can not be null");
        this.location = unwantedLocation;
    }

    /**
     * removes the location provided in the constructor form the list of events
     *
     * @param events
     *          the List of events to be filtered
     * @return a list of events without the location provided in the constructor
     * @throws IllegalArgumentException if filter == null.
     */
    @Override
    List<Event> filter(List<Event> events) {
        if(events == null) throw new IllegalArgumentException("events can not be null");
        ArrayList<Event> newEvents = new ArrayList<>(events);
        for(Event e: events){
            if(e.getLocation().isPresent()) {
                if (e.getLocation().get() == location) {
                    newEvents.remove(e);
                }
            }
        }
        return newEvents;
    }
}
