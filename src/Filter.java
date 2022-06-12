import java.util.List;

/**
 * Filter interface that all filters have to implement
 */
public interface Filter {
    /**
     * filters events from the list of events.
     * @param events
     *              the List of events to be filtered
     * @return a new list of events that pass the filtering criteria.
     */
    List<Event> filterEvents(List<Event> events);
}
