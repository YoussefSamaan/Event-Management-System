import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller to manage events hosted on EventBrite.
 */
public class EventManagement {
    private final List<Event> aHostedEvents = new ArrayList<Event>(); // list of all the created events

    /**
     * Event Management constructor
     */
    public EventManagement(){}

    /**
     * Method to host a new concert event.
     * If the concert was created, or it had been created before then the method will return the Concert Object.
     * Else it will return null.
     * The Concert is added to the list of hosted events if it is not equal to null.
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
    public Concert addConcertEvent(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets, Artist artist){
        if(name == null) throw new IllegalArgumentException("name can not be null");
        if(location == null) throw new IllegalArgumentException("location can not be null");
        if(date == null) throw new IllegalArgumentException("date can not be null");
        if(ticketPrice < 0) throw new IllegalArgumentException("ticket price can not be negative");
        if(maxNumberOfTickets < 0) throw new IllegalArgumentException("the number of tickets can not be negative");
        if(artist == null) throw new IllegalArgumentException("Artist can not be null");

        Concert newConcert = Concert.createConcert(name, location, date, ticketPrice, maxNumberOfTickets, artist);
        if(newConcert != null) {
            aHostedEvents.add(newConcert);
        }

        return newConcert;
    }


    /**
     * Method to host a new Gala event.
     * If the concert was created, or it had been created before then the method will return the Gala Object.
     * Else it will return null.
     * The Gala is added to the list of hosted events if it is not equal to null.
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
     * @throws IllegalArgumentException if name == null, location == null, date ==null, ticketPrice < 0, or if maximumOfTickets < 0.
     * @return a Gala Object or null if you can not create one with this date and location.

     */
    public Gala addGalaEvent(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets){
        if(name == null) throw new IllegalArgumentException("name can not be null");
        if(location == null) throw new IllegalArgumentException("location can not be null");
        if(date == null) throw new IllegalArgumentException("date can not be null");
        if(ticketPrice < 0) throw new IllegalArgumentException("ticket price should be bigger than or equal to 0");
        if(maxNumberOfTickets < 0) throw new IllegalArgumentException("number of tickets has to be bigger than or equal to 0");

        Gala newGala = Gala.createGala(name, location, date, ticketPrice, maxNumberOfTickets);
        if(newGala != null) {
            aHostedEvents.add(newGala);
        }

        return newGala;
    }


    /**
     * Method to host a new Screening event.
     * If the concert was created, or it had been created before then the method will return the Screening Object.
     * Else it will return null.
     * The Screening is added to the list of hosted events if it is not equal to null.
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
     * @param rating
     *              the rating of the Screening
     * @throws IllegalArgumentException if name == null, rating == null, location == null, date ==null, ticketPrice < 0, or if maximumOfTickets < 0.
     * @return a Screening Object or null if you can not create one with this date and location.
     */
    public Screening addScreeningEvent(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets, Rating rating) {
        if (name == null) throw new IllegalArgumentException("name can not be null");
        if (rating == null) throw new IllegalArgumentException("rating can not be null");
        if (location == null) throw new IllegalArgumentException("location can not be null");
        if (date == null) throw new IllegalArgumentException("date can not be null");
        if (ticketPrice < 0) throw new IllegalArgumentException("ticket price should be bigger than or equal to 0");
        if (maxNumberOfTickets < 0)
            throw new IllegalArgumentException("number of tickets has to be bigger than or equal to 0");

        Screening newScreening = Screening.createScreening(name, location, date, ticketPrice, maxNumberOfTickets, rating);
        if (newScreening != null) {
            aHostedEvents.add(newScreening);
        }

        return newScreening;

    }

    /**
     * Method to host a new Workshop event.
     * If the concert was created, or it had been created before then the method will return the Workshop Object.
     * Else it will return null.
     * The Workshop is added to the list of hosted events if it is not equal to null.
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
     * @throws IllegalArgumentException if name == null, location == null, date ==null, ticketPrice < 0, or if maximumOfTickets < 0.
     * @return a Workshop Object or null if you can not create one with this date and location.
     */
    public Workshop addWorkshopEvent(String name, Location location, LocalDate date, double ticketPrice, int maxNumberOfTickets){
        if(name == null) throw new IllegalArgumentException("name can not be null");
        if(location == null) throw new IllegalArgumentException("location can not be null");
        if(date == null) throw new IllegalArgumentException("date can not be null");
        if(ticketPrice < 0) throw new IllegalArgumentException("ticket price should be bigger than or equal to 0");
        if(maxNumberOfTickets < 0) throw new IllegalArgumentException("number of tickets has to be bigger than or equal to 0");

        Workshop newWorkshop = Workshop.createWorkshop(name, location, date, ticketPrice, maxNumberOfTickets);
        if(newWorkshop != null) {
            aHostedEvents.add(newWorkshop);
        }

        return newWorkshop;
    }

    /**
     * Methode to host new Festival.
     * The Festival is added to the list of hosted events.
     *
     * @param name
     *          the name of the coming soon event
     * @param events
     *          the events happening in the festival
     * @return a festival object
     * @throws IllegalArgumentException if name == null, or the list of events == null
     */
    public Festival addFestivalEvent(String name, List<Event> events){
        if(name == null) throw new IllegalArgumentException("name can not be null");
        if(events == null) throw new IllegalArgumentException("list of events can not be null");

        Festival newFestival = Festival.createFestival(name, events);
        aHostedEvents.add(newFestival);
        return newFestival;
    }

    /**
     * Methode to create a Coming Soon event.
     * the ComingSoon Object is added to the list of hosted events.
     *
     * @param name
     *              the name of the coming soon event
     * @param date
     *              the date the coming soon event will occur
     * @return a ComingSoon Object
     * @throws IllegalArgumentException if name == null, or date == null
     */
    public ComingSoon addComingSoonEvent(String name, LocalDate date){
        if(name == null) throw new IllegalArgumentException("name can not be null");
        if(date == null) throw new IllegalArgumentException("list of events can not be null");

        ComingSoon newComingSoon = new ComingSoon(name, date);
        aHostedEvents.add(newComingSoon);
        return newComingSoon;
    }

    /**
     * Calculates the profits of a FilterResult Object
     *
     * @param filteredResults
     *                  the Filtered results Object
     * @param concertProfitInPercentage
     *                  percentage of the cost that is profit for the concert.
     * @param galaProfitInPercentage
     *                  percentage of the cost that is profit for the gala.
     * @param screeningProfitInPercentage
     *                  percentage of the cost that is profit for the Screening.
     * @param workshopProfitInPercentage
     *                  percentage of the cost that is profit for the Workshop.
     * @return the total profits of all the events in the FilterResult Object.
     * @throws IllegalArgumentException id filteredResults == null
     */
    public double calculateProfit(FilterResults filteredResults, double concertProfitInPercentage, double galaProfitInPercentage, double screeningProfitInPercentage, double workshopProfitInPercentage){
        if(filteredResults == null) throw new IllegalArgumentException("FilterResults object can not be null");

        ProfitsCalculatorVisitor profitsCalculator = new ProfitsCalculatorVisitor(concertProfitInPercentage,galaProfitInPercentage,screeningProfitInPercentage,workshopProfitInPercentage);

        return profitsCalculator.calculateProfits(filteredResults);
    }

    /**
     * Calculates the number of VIPs inside a FilterResults Object
     *
     * @param filteredResults
     *                  the FilterResults Object that you want to know how many VIPs are inside it
     * @return the total number of VIPs inside the FilterResults Object.
     * @throws IllegalArgumentException if filteredResults == null
     */
    public int calculateNumberOfVIPs(FilterResults filteredResults){
        if(filteredResults == null) throw new IllegalArgumentException("FilterResults object can not be null");

        CalculateNumberOfVIPsVisitor numVIPs = new CalculateNumberOfVIPsVisitor();

        return numVIPs.calculateNumberOfVips(filteredResults);
    }

    /**
     * @param event
     *          the event that is used to get the number of tickets
     * @return an Optional containing number of tickets for that specific event
     * @throws IllegalArgumentException if event == null
     */
    public Optional<Integer> getNumberOfTickets(Event event){
        if(event == null) throw new IllegalArgumentException("Event can not be null");
        return event.getNumTickets();
    }

    /**
     * @param event
     *          the event that is used to get the price of ticket
     * @return an Optional containing price of a ticket for that specific event
     * @throws IllegalArgumentException if event == null
     */
    public Optional<Double> getPriceOfTicket(Event event){
        if(event == null) throw new IllegalArgumentException("Event can not be null");
        return event.getPrice();
    }

    /**
     * @param event
     *          the event that is used to get its location
     * @return an Optional containing Location of that specific event
     * @throws IllegalArgumentException if event == null
     */
    public Optional<Location> getLocationOfEvent(Event event){
        if(event == null) throw new IllegalArgumentException("Event can not be null");
        return event.getLocation();
    }

    /**
     * @param event
     *          the event that is used to get the date of when it is happening
     * @return the date of when that event will take place
     * @throws IllegalArgumentException if event == null
     */
    public LocalDate getDateOfEvent(Event event){
        if(event == null) throw new IllegalArgumentException("Event can not be null");
        return event.getDate();
    }

    /**
     * @param event
     *              the event that you want to get the name of
     * @return the name of the input event
     * @throws IllegalArgumentException if event == null
     */
    public String getNameOfEvent(Event event){
        if(event == null) throw new IllegalArgumentException("Event can not be null");
        return event.getName();
    }

    /**
     * @param concert
     *              the Concert that you want to get the artist of
     * @return the artist of the input concert
     * @throws IllegalArgumentException if concert == null
     */
    public Artist getArtistAtConcert(Concert concert){
        if(concert == null) throw new IllegalArgumentException("concert can not be null");
        return concert.getArtist();
    }

    /**
     * Changes the Artist at a given Concert Object
     *
     * @param concert
     *              the concert where you want the artist to be changed
     * @param artist
     *              the new artist that will replace the artist the given Concert
     * @throws IllegalArgumentException if concert == null, or artist == null
     */
    public void changeArtistAtConcert(Concert concert, Artist artist){
        if(concert == null) throw new IllegalArgumentException("concert can not be null");
        if(artist == null) throw new IllegalArgumentException("Artist can not be null");
        concert.setArtist(artist);
    }

    /**
     * @param concert
     *              the concert that you want to get the VIPs of
     * @return an unmodifiable list of the VIPs in the Concert
     * @throws IllegalArgumentException if concert == null
     */
    public List<VIP> getVIPsOfConcert(Concert concert){
        if(concert == null) throw new IllegalArgumentException("concert can not be null");
        return concert.getVIPs();
    }

    /**
     * Adds a VIP to the list of VIPs in the concert
     *
     * @param concert
     *              the concert that you want to add the VIP to
     * @param vip
     *              the VIP that you want to add to the Concert
     * @throws IllegalArgumentException if concert == null, or vip == null
     */
    public void addVIPToConcert(Concert concert, VIP vip){
        if(concert == null) throw new IllegalArgumentException("concert can not be null");
        if(vip == null) throw new IllegalArgumentException("vip can not be null");
        concert.addVIP(vip);
    }

    /**
     * @param gala
     *              the gala that you want to get the VIPs of
     * @return an unmodifiable list of the VIPs in the Gala
     * @throws IllegalArgumentException if gala == null
     */
    public List<VIP> getVIPsOfGala(Gala gala){
        if(gala == null) throw new IllegalArgumentException("gala can not be null");
        return gala.getVIPs();
    }

    /**
     * Adds a VIP to the list of VIPs in the gala
     *
     * @param gala
     *              the gala that you want to add the VIP to
     * @param vip
     *              the VIP that you want to add to the gala
     * @throws IllegalArgumentException if gala == null, or vip == null
     */
    public void addVIPToGala(Gala gala, VIP vip){
        if(gala == null) throw new IllegalArgumentException("gala can not be null");
        if(vip == null) throw new IllegalArgumentException("vip can not be null");
        gala.addVIP(vip);
    }

    /**
     * @param screening
     *              the Screening that you want to get the rating of
     * @return the rating of the input screening
     * @throws IllegalArgumentException if screening == null
     */
    public Rating getScreeningRating(Screening screening){
        if(screening == null) throw new IllegalArgumentException("screening can not be null");
        return screening.getRating();
    }

    /**
     * Changes the rating of the Screening Object.
     *
     * @param screening
     *              the Screening that you want to change the rating of
     * @param rating
     *              the new rating of the Screening Object
     * @throws IllegalArgumentException if screening == null, or rating == null
     */
    public void changeScreeningRating(Screening screening, Rating rating){
        if(screening == null) throw new IllegalArgumentException("screening can not be null");
        if(rating == null) throw new IllegalArgumentException("rating can not be null");
        screening.setRating(rating);
    }

    /**
     * @param workshop
     *              the workshop you want to the prerequisites of
     * @return an unmodifiable List of prerequisite workshops
     * @throws IllegalArgumentException if workshop == null
     */
    public List<Workshop> getPrerequisiteWorkshops(Workshop workshop){
        if(workshop == null) throw new IllegalArgumentException("workshop can not be null");
        return workshop.getPrerequisiteWorkshops();
    }

    /**
     * Adds a prerequisite workshop to a workshop.
     *
     * @param workshop
     *              the workshop you want to add the prerequisite to
     * @param prerequisiteWorkshop
     *              the prerequisite workshop
     * @throws IllegalArgumentException if workshop == null or prerequisite workshop == null
     */
    public void addToPrerequisiteWorkshops(Workshop workshop,Workshop prerequisiteWorkshop){
        if(workshop == null) throw new IllegalArgumentException("the workshop can not be null");
        if(prerequisiteWorkshop == null) throw new IllegalArgumentException("the prerequisite workshop can not be null");
        workshop.addToPrerequisiteWorkshops(prerequisiteWorkshop);
    }

    /**
     * Removes a prerequisite workshop from a workshop.
     *
     * @param workshop
     *              the workshop you want to add the prerequisite to
     * @param prerequisiteWorkshop
     *              the prerequisite workshop
     * @throws IllegalArgumentException if workshop == null or prerequisite workshop == null
     */
    public void removeFromPrerequisiteWorkshops(Workshop workshop,Workshop prerequisiteWorkshop){
        if(workshop == null) throw new IllegalArgumentException("the workshop can not be null");
        if(prerequisiteWorkshop == null) throw new IllegalArgumentException("the prerequisite workshop can not be null");
        workshop.removePrerequisiteWorkshop(prerequisiteWorkshop);
    }

    /**
     * @return the list of hosted events on EventBrite.
     */
    public ArrayList<Event> getHostedEvents(){
        return new ArrayList<Event>(aHostedEvents);
    }

}
