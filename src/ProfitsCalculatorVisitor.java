import java.util.List;

/**
 * A class that calculates the total profit
 */
public class ProfitsCalculatorVisitor implements Visitor{
    private final double profitsOfConcert;
    private final double profitsOfGala;
    private final double profitsOfWorkshop;
    private final double profitsOfScreening;
    private double allProfits;

    /**
     * Constructor for the Profits calculator.
     * Takes in parameters in percentage values.
     *
     * @param profitsOfConcert
     *                  the percentage of profit for a concert.
     * @param profitsOfWorkshop
     *                  the percentage of profits for a Workshop.
     * @param profitsOfGala
     *                  the percentage of profits for a gala.
     * @param profitsOfScreening
     *                  the percentage of profits for a Workshop.
     */
    public ProfitsCalculatorVisitor( double profitsOfConcert,double profitsOfWorkshop,double profitsOfGala, double profitsOfScreening){
        this.profitsOfConcert = profitsOfConcert;
        this.profitsOfWorkshop = profitsOfWorkshop;
        this.profitsOfGala = profitsOfGala;
        this.profitsOfScreening = profitsOfScreening;
    }

    /**
     * Calcultes the total profits of the Filtered results
     *
     * @param filteredResults
     *                  the filtered results Object that is used to calculate the profits of the events inside it.
     * @return the total profits of all the events in the filtered results.
     */
    public double calculateProfits(FilterResults filteredResults) {
        if(filteredResults == null) throw new IllegalArgumentException("FilterResults object can not be null");
        List<Event> filteredResultsList = filteredResults.getFilteredEvents();
        for(Event e: filteredResultsList){
            e.acceptVisitor(this);
        }
        return this.allProfits;
    }

    /**
     * Calculates the profits of a Gala event.
     *
     * @param gala
     *              the gala event used to calculate the profits
     * @throws IllegalArgumentException if gala == null.
     */
    @Override
    public void visitGala(Gala gala) {
        if(gala == null) throw new IllegalArgumentException("Gala object can not be null");
        int numberOfTickets = gala.getNumTickets().get();
        double priceOfTicket = gala.getPrice().get();
        this.allProfits += numberOfTickets * priceOfTicket * (this.profitsOfGala/100);
    }

    /**
     * Calculates the profits of a Concert event.
     *
     * @param concert
     *              the concert event used to calculate the profits
     * @throws IllegalArgumentException if concert == null.
     */
    @Override
    public void visitorConcert(Concert concert) {
        if(concert == null) throw new IllegalArgumentException("Concert object can not be null");
        int numberOfTickets = concert.getNumTickets().get();
        double priceOfTicket = concert.getPrice().get();
        this.allProfits += numberOfTickets * priceOfTicket * (this.profitsOfConcert/100);
    }

    /**
     * Calculates the profits of a Screening event.
     *
     * @param screening
     *              the screening event used to calculate the profits
     * @throws IllegalArgumentException if screening == null.
     */
    @Override
    public void visitorScreening(Screening screening) {
        if(screening == null) throw new IllegalArgumentException("Screening object can not be null");
        int numberOfTickets = screening.getNumTickets().get();
        double priceOfTicket = screening.getPrice().get();
        this.allProfits += numberOfTickets * priceOfTicket * (this.profitsOfScreening/100);
    }

    /**
     * Calculates the profits of a Workshop event.
     *
     * @param workshop
     *              the workshop event used to calculate the profits
     * @throws IllegalArgumentException if workshop == null.
     */
    @Override
    public void visitorWorkshop(Workshop workshop) {
        if(workshop == null) throw new IllegalArgumentException("Workshop object can not be null");
        int numberOfTickets = workshop.getNumTickets().get();
        double priceOfTicket = workshop.getPrice().get();
        this.allProfits += numberOfTickets * priceOfTicket * (this.profitsOfWorkshop/100);
    }
}
