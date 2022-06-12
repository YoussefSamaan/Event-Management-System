import java.util.ArrayList;
import java.util.List;

/**
 * Filtered Results Object that contains the results of the filtering done on the List of events
 */
public class FilterResults{
    private final List<Event> aFilteredEvents; // list of the filtered results

    /**
     * Constructor of the FilterResults object.
     * Creates a Filtered list by applying the filter(s) on the list of events
     *
     * @param events
     *              event list to be filtered
     * @param filter
     *              filters to applied on the list of events
     * @throws IllegalArgumentException if the list of events == null or filter == null
     */
    public FilterResults(List<Event> events, Filter filter){
        if(events == null) throw new IllegalArgumentException("events can not be null");
        if(filter == null) throw new IllegalArgumentException("filter can not be null");
        this.aFilteredEvents = filter.filterEvents(new ArrayList<>(events));
    }

    /**
     * @return the list of events after filtering
     */
    public List<Event> getFilteredEvents(){
        return new ArrayList<>(aFilteredEvents);
    }
}
