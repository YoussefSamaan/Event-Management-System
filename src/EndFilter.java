import java.util.List;

/**
 * Represents the last filter applied on the List.
 */
public class EndFilter implements Filter{

    public EndFilter(){}

    /**
     * Does nothing. Used to end the filtering.
     *
     * @param events
     *              the List of events that will be used to get the new List of events after applying the filter.
     * @return the events since this is the last filter
     */
    @Override
    public List<Event> filterEvents(List<Event> events) {
        return events;
    }
}
