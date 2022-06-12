import java.util.List;

/**
 * Represents the basic features that all filters must have
 */
public abstract class FilterTemplate implements Filter {

    private final Filter filter; // a placeholder to the other filters

    /**
     * Creates a filter and stores the next filter.
     *
     * @param filter
     *              the other filter(s) to be applied on the list
     * @throws IllegalArgumentException if filter == null.
     */
    protected FilterTemplate(Filter filter){
        if(filter == null) throw new IllegalArgumentException("filter can not be null");
        this.filter = filter;
    }

    /**
     * Calls the filter method on the list and calls the filterEvents of the filter attribute on the list
     * and returns the filtered events
     *
     * @param events
     *              the List of events to be filtered
     * @return List of events after being filtered
     */
    public List<Event> filterEvents(List<Event> events){
        if(events == null) throw new IllegalArgumentException("the list of events can not be null");
        List<Event> listOfEventsAfterPreviousFilters = filter.filterEvents(events);
        return this.filter(listOfEventsAfterPreviousFilters);
    }

    /**
     * Filters the list of events depending on some criteria.
     *
     * @param events
     *          the List of events to be filtered
     * @return new list of events after being filtered.
     */
    abstract List<Event> filter(List<Event> events);
}
