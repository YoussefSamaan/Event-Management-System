import java.util.HashSet;
import java.util.List;

/**
 * A class to calculate the number of VIPs in Filtered Objects.
 */
public class CalculateNumberOfVIPsVisitor implements Visitor{

    private final HashSet<VIP> VIPList; // to only add a same VIP only once.

    /**
     * Constructor to make objects of this
     */
    public CalculateNumberOfVIPsVisitor(){
        this.VIPList = new HashSet<>();
    }

    /**
     * Calculates the number of VIPs in the filtered results Object.
     * Does not count the same VIP twice.
     *
     * @param filteredResults
     *              the filtered results Object
     * @return the number of VIPs in the filtered results
     * @throws IllegalArgumentException if FilterResults parameter is null
     */
    public int calculateNumberOfVips(FilterResults filteredResults){
        if(filteredResults == null) throw new IllegalArgumentException("FilterResults object can not be null");
        List<Event> filteredResultsList = filteredResults.getFilteredEvents();
        for(Event e: filteredResultsList){
            e.acceptVisitor(this);
        }
        return this.VIPList.size();
    }

    /**
     * Calculates the number of VIPs in the Gala Object
     * @param gala
     *          the Gala Object used to calculate the number of VIPs
     * @throws IllegalArgumentException if the Gala Object is null
     */
    @Override
    public void visitGala(Gala gala) {
        if(gala == null) throw new IllegalArgumentException("Gala object can not be null");
        this.VIPList.addAll(gala.getVIPs());
    }

    /**
     * Calculates the number of VIPs in the Concert Object
     * @param concert
     *          the Concert Object used to calculate the number of VIPs
     * @throws IllegalArgumentException if the Concert Object is null
     */
    @Override
    public void visitorConcert(Concert concert) {
        if(concert == null) throw new IllegalArgumentException("Concert object can not be null");
        this.VIPList.addAll(concert.getVIPs());
    }

    /**
     * Does nothing Since there are no VIPs in a Screening Event
     * @param screening
     *              the Screening Object used to calculate the number of VIPs
     */
    @Override
    public void visitorScreening(Screening screening) {}

    /**
     * Does nothing Since there are no VIPs in a Workshop Event
     * @param workshop
     *              the Workshop Object used to calculate the number of VIPs
     */
    @Override
    public void visitorWorkshop(Workshop workshop) {}
}
