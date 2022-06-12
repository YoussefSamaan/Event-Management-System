import java.time.LocalDate;

public class Driver {
    public static void main(String[] args){
        // creating artists
        Artist artist1 = new Artist("artist1");
        Artist artist2 = new Artist("artist2");

        // creating vips
        VIP vip1 = new VIP("vip1");
        VIP vip2 = new VIP("vip2");

        // creating events
        EventManagement eventManagement = new EventManagement();
        Concert concert1 = eventManagement.addConcertEvent("concert1",Location.OlympicStadium, LocalDate.now(),10,10,artist1);
        Workshop workshop1 = eventManagement.addWorkshopEvent("workshop1", Location.BellCentre, LocalDate.now(), 10,10);
        Gala gala1 = eventManagement.addGalaEvent("gala1",Location.ParcJeanDrapeau,LocalDate.now(),10,10);
        Screening screening1 = eventManagement.addScreeningEvent("screening1",Location.PlaceDesArts,LocalDate.now(),10,10,Rating.R);
        ComingSoon comingSoon1 = eventManagement.addComingSoonEvent("comingSoon1",LocalDate.now());
        Festival festival1 = eventManagement.addFestivalEvent("festival1",eventManagement.getHostedEvents());
        Workshop workshop2 = eventManagement.addWorkshopEvent("workshop2", Location.Multiple, LocalDate.now(), 10,10);

        // mutating the events
        eventManagement.changeArtistAtConcert(concert1,artist2);
        eventManagement.addVIPToConcert(concert1,vip1);
        eventManagement.addVIPToGala(gala1,vip2);
        eventManagement.changeScreeningRating(screening1,Rating.PG);
        eventManagement.addToPrerequisiteWorkshops(workshop1,workshop2);
        eventManagement.removeFromPrerequisiteWorkshops(workshop1,workshop2);


        // getting information from an event
        eventManagement.getNumberOfTickets(workshop1);
        eventManagement.getPriceOfTicket(festival1);
        eventManagement.getLocationOfEvent(workshop1);
        eventManagement.getDateOfEvent(comingSoon1);
        eventManagement.getNameOfEvent(gala1);
        eventManagement.getArtistAtConcert(concert1);
        eventManagement.getVIPsOfConcert(concert1);
        eventManagement.getVIPsOfGala(gala1);
        eventManagement.getScreeningRating(screening1);
        eventManagement.getPrerequisiteWorkshops(workshop1);
        eventManagement.getHostedEvents();


        // creating Filters and getting FilterResults
        FilterResults filterResults1 = new FilterResults(
             eventManagement.getHostedEvents(),
             new LocationFilter(
                     new LocationFilter(
                             new PriceFilter(
                                     new EndFilter(),
                                     1,
                                     11
                             ),
                             Location.BellCentre
                     ),
                     Location.OlympicStadium
             )
        );


        // calculating profits and number of VIPs
        eventManagement.calculateNumberOfVIPs(filterResults1);
        eventManagement.calculateProfit(filterResults1,10,10,10,10);


        // for invalid cases are tested in the test folder.
    }
}
