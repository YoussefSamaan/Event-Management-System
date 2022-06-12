import java.util.ArrayList;
import java.util.List;

/**
 * Price filter
 */
public class PriceFilter extends FilterTemplate {
    private final double lowerRange;
    private final double upperRange;

    /**
     * Constructor of the Price Filter Object
     *
     * @param filter
     *              the other filter(s) to be applied on the list
     * @param lowerRange
     *              the lowest price that the filter keeps
     * @param upperRange
     *              the highest price the filter keeps
     */
    public PriceFilter(Filter filter, double lowerRange, double upperRange) {
        super(filter);
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
    }

    /**
     * Filters events that cost strictly more than the upper rage and strictly less than the lower range.
     *
     * @param events
     *          the List of events to be filtered
     * @return returns a list of events where the event prices fall in between the range
     * @throws IllegalArgumentException if filter == null.
     */
    @Override
    public List<Event> filter(List<Event> events) {
        if(events == null) throw new IllegalArgumentException("events can not be null");
        ArrayList<Event> newEvents = new ArrayList<>(events);
        for(Event e: events){
            if(e.getPrice().isPresent()) {
                if (e.getPrice().get() < lowerRange || e.getPrice().get() > upperRange) {
                    newEvents.remove(e);
                }
            }
        }
        return newEvents;
    }
}
