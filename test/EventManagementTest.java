import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EventManagementTest {

    @Test
    void addConcertEvent() {
        EventManagement eventManagement = new EventManagement();
        Concert concert1 = eventManagement.addConcertEvent("concert1",Location.Multiple, LocalDate.now(),10,10,new Artist("artist"));
        Concert concert2 = Concert.createConcert("concert1",Location.Multiple, LocalDate.now(),10,10,new Artist("artist"));

        assertEquals(concert1,concert2);
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addConcertEvent(null,Location.Multiple, LocalDate.now(),10,10,new Artist("artist"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addConcertEvent("concert1",null, LocalDate.now(),10,10,new Artist("artist"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addConcertEvent("concert1",Location.Multiple, null,10,10,new Artist("artist"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addConcertEvent("concert1",Location.Multiple, LocalDate.now(),-10,10,new Artist("artist"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addConcertEvent("concert1",Location.Multiple, LocalDate.now(),10,-10,new Artist("artist"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addConcertEvent("concert1",Location.Multiple, LocalDate.now(),10,10,null);
        });
    }

    @Test
    void addGalaEvent() {
        EventManagement eventManagement = new EventManagement();
        Gala gala1 = eventManagement.addGalaEvent("gala1",Location.BellCentre,LocalDate.now(),10,10);
        Gala gala2 = Gala.createGala("gala1",Location.BellCentre,LocalDate.now(),10,10);

        assertEquals(gala1,gala2);
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addGalaEvent(null,Location.BellCentre,LocalDate.now(),10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addGalaEvent("gala1",null,LocalDate.now(),10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addGalaEvent("gala1",Location.BellCentre,null,10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addGalaEvent("gala1",Location.BellCentre,LocalDate.now(),-10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addGalaEvent("gala1",Location.BellCentre,LocalDate.now(),10,-10);
        });
    }

    @Test
    void addScreeningEvent() {
        EventManagement eventManagement = new EventManagement();
        Screening screening1 = eventManagement.addScreeningEvent("screening1",Location.BellCentre,LocalDate.now(),10,10,Rating.R);
        Screening screening2 = Screening.createScreening("screening1",Location.BellCentre,LocalDate.now(),10,10,Rating.R);

        assertEquals(screening1,screening2);
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addScreeningEvent(null,Location.BellCentre,LocalDate.now(),10,10,Rating.R);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addScreeningEvent("screening1",null,LocalDate.now(),10,10,Rating.R);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addScreeningEvent("screening1",Location.BellCentre,null,10,10,Rating.R);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addScreeningEvent("screening1",Location.BellCentre,LocalDate.now(),-10,10,Rating.R);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addScreeningEvent("screening1",Location.BellCentre,LocalDate.now(),10,-10,Rating.R);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addScreeningEvent("screening1",Location.BellCentre,LocalDate.now(),10,10,null);
        });
    }

    @Test
    void addWorkshopEvent() {
        EventManagement eventManagement = new EventManagement();
        Workshop workshop1 = eventManagement.addWorkshopEvent("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);
        Workshop workshop2 = Workshop.createWorkshop("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);

        assertEquals(workshop1,workshop2);
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addWorkshopEvent(null,Location.OlympicStadium,LocalDate.now(),10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addWorkshopEvent("workshop1",null,LocalDate.now(),10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addWorkshopEvent("workshop1",Location.OlympicStadium,null,10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addWorkshopEvent("workshop1",Location.OlympicStadium,LocalDate.now(),-10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addWorkshopEvent("workshop1",Location.OlympicStadium,LocalDate.now(),10,-10);
        });
    }

    @Test
    void addFestivalEvent() {
        EventManagement eventManagement = new EventManagement();
        Workshop workshop1 = eventManagement.addWorkshopEvent("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);
        Screening screening1 = eventManagement.addScreeningEvent("screening1",Location.BellCentre,LocalDate.MAX,10,1,Rating.R);
        Gala gala1 = eventManagement.addGalaEvent("gala1",Location.BellCentre,LocalDate.MAX,10,10);
        Concert concert1 = eventManagement.addConcertEvent("concert1",Location.ParcJeanDrapeau, LocalDate.MAX,10,10,new Artist("artist"));

        Festival festival1 = eventManagement.addFestivalEvent("festival",eventManagement.getHostedEvents());

        assertEquals(Location.Multiple, festival1.getLocation().get());
        assertEquals(LocalDate.now(),festival1.getDate());
        assertEquals(1, festival1.getNumTickets().get());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addFestivalEvent(null,eventManagement.getHostedEvents());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addFestivalEvent("festival",null);
        });
    }

    @Test
    void addComingSoonEvent() {
        EventManagement eventManagement = new EventManagement();
        ComingSoon comingSoon1 = eventManagement.addComingSoonEvent("comingSoon1",LocalDate.now());

        assertEquals(Optional.empty(),comingSoon1.getLocation());
        assertEquals(Optional.empty(),comingSoon1.getPrice());
        assertEquals(Optional.empty(),comingSoon1.getNumTickets());
        assertEquals("comingSoon1",comingSoon1.getName());
        assertEquals(LocalDate.now(),comingSoon1.getDate());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addComingSoonEvent(null,LocalDate.now());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addComingSoonEvent("comingSoon1",null);
        });
    }

    @Test
    void calculateProfit() {
        EventManagement eventManagement = new EventManagement();
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        Workshop workshop1 =  Workshop.createWorkshop("workshop1",Location.Multiple, LocalDate.now(),10,10);
        Concert concert1 =  Concert.createConcert("concert1",Location.ParcJeanDrapeau, LocalDate.now(),10,10,new Artist("artist"));
        ComingSoon comingSoon1 = new ComingSoon("comingSoon1", LocalDate.now());
        Screening screening1 =  Screening.createScreening("screening1",Location.OlympicStadium,LocalDate.now(),10,10,Rating.R);
        ArrayList<Event> events = new ArrayList<>();
        events.add(gala1);
        events.add(workshop1);
        events.add(concert1);
        Festival festival1 = Festival.createFestival("name",events);
        events.add(festival1);
        events.add(comingSoon1);
        events.add(screening1);

        assertEquals(70,eventManagement.calculateProfit(new FilterResults(events,new EndFilter()),10,10,10,10));
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.calculateProfit(null,10,10,10,10);
        });
    }

    @Test
    void calculateNumberOfVIPs() {
        EventManagement eventManagement = new EventManagement();
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        Gala gala2 =  Gala.createGala("gala2",Location.Multiple, LocalDate.now(),10,10);
        Gala gala3 =  Gala.createGala("gala3",Location.ParcJeanDrapeau, LocalDate.now(),10,10);
        ArrayList<Event> events = new ArrayList<>();
        events.add(gala1);
        events.add(gala2);
        events.add(gala3);
        Festival festival1 = Festival.createFestival("name",events);
        events.remove(gala3);
        events.add(festival1);
        VIP vip1 = new VIP("vip1");
        VIP vip2 = new VIP("vip2");
        VIP vip3 = new VIP("vip3");
        VIP vip4 = new VIP("vip4");
        gala1.addVIP(vip1);
        gala1.addVIP(vip2);
        gala2.addVIP(vip3);
        gala3.addVIP(vip4);

        assertEquals(4,eventManagement.calculateNumberOfVIPs(new FilterResults(events,new EndFilter())));
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.calculateNumberOfVIPs(null);
        });
    }

    @Test
    void getNumberOfTickets() {
        EventManagement eventManagement = new EventManagement();
        EventStub eventStub = new EventStub();

        assertEquals(10,eventManagement.getNumberOfTickets(eventStub).get());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.getNumberOfTickets(null);
        });
    }

    @Test
    void getPriceOfTicket() {
        EventManagement eventManagement = new EventManagement();
        EventStub eventStub = new EventStub();

        assertEquals(10.0,eventManagement.getPriceOfTicket(eventStub).get());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.getPriceOfTicket(null);
        });
    }

    @Test
    void getLocationOfEvent() {
        EventManagement eventManagement = new EventManagement();
        EventStub eventStub = new EventStub();

        assertEquals(Location.OlympicStadium, eventManagement.getLocationOfEvent(eventStub).get());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.getLocationOfEvent(null);
        });
    }

    @Test
    void getDateOfEvent() {
        EventManagement eventManagement = new EventManagement();
        EventStub eventStub = new EventStub();

        assertEquals(LocalDate.MAX, eventManagement.getDateOfEvent(eventStub));
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.getDateOfEvent(null);
        });
    }

    @Test
    void getNameOfEvent() {
        EventManagement eventManagement = new EventManagement();
        EventStub eventStub = new EventStub();

        assertEquals("eventStub",eventManagement.getNameOfEvent(eventStub));
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.getNameOfEvent(null);
        });
    }

    @Test
    void getArtistAtConcert() {
        EventManagement eventManagement = new EventManagement();
        Concert concert1 = eventManagement.addConcertEvent("concert1",Location.ParcJeanDrapeau, LocalDate.MAX,10,10,new Artist("artist"));

        assertEquals(new Artist("artist"),eventManagement.getArtistAtConcert(concert1));
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.getArtistAtConcert(null);
        });
    }

    @Test
    void changeArtistAtConcert() {
        EventManagement eventManagement = new EventManagement();
        Concert concert1 = eventManagement.addConcertEvent("concert1",Location.ParcJeanDrapeau, LocalDate.MAX,10,10,new Artist("artist"));

        eventManagement.changeArtistAtConcert(concert1, new Artist("newArtist"));

        assertEquals(new Artist("newArtist"),eventManagement.getArtistAtConcert(concert1));
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.changeArtistAtConcert(null, new Artist(""));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.changeArtistAtConcert(concert1, null);
        });
    }

    @Test
    void getVIPsOfConcert() {
        EventManagement eventManagement = new EventManagement();
        Concert concert1 = eventManagement.addConcertEvent("concert1",Location.ParcJeanDrapeau, LocalDate.MAX,10,10,new Artist("artist"));
        VIP vip1 = new VIP("vip1");

        concert1.addVIP(vip1);

        assertEquals(1,eventManagement.getVIPsOfConcert(concert1).size());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.getVIPsOfConcert(null);
        });
    }

    @Test
    void addVIPToConcert() {
        EventManagement eventManagement = new EventManagement();
        Concert concert1 = eventManagement.addConcertEvent("concert1",Location.ParcJeanDrapeau, LocalDate.MAX,10,10,new Artist("artist"));
        VIP vip1 = new VIP("vip1");

        eventManagement.addVIPToConcert(concert1, vip1);

        assertEquals(1,eventManagement.getVIPsOfConcert(concert1).size());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addVIPToConcert(null, vip1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addVIPToConcert(concert1, null);
        });
    }

    @Test
    void getVIPsOfGala() {
        EventManagement eventManagement = new EventManagement();
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        VIP vip1 = new VIP("vip1");

        gala1.addVIP(vip1);

        assertEquals(1,eventManagement.getVIPsOfGala(gala1).size());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.getVIPsOfGala(null);
        });
    }

    @Test
    void addVIPToGala() {
        EventManagement eventManagement = new EventManagement();
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        VIP vip1 = new VIP("vip1");

        eventManagement.addVIPToGala(gala1,vip1);

        assertEquals(1,eventManagement.getVIPsOfGala(gala1).size());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addVIPToGala(null,vip1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addVIPToGala(gala1,null);
        });
    }

    @Test
    void getScreeningRating() {
        EventManagement eventManagement = new EventManagement();
        Screening screening1 =  Screening.createScreening("screening1",Location.OlympicStadium,LocalDate.now(),10,10,Rating.R);

        assertEquals(Rating.R,eventManagement.getScreeningRating(screening1));
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.getScreeningRating(null);
        });
    }

    @Test
    void changeScreeningRating() {
        EventManagement eventManagement = new EventManagement();
        Screening screening1 =  Screening.createScreening("screening1",Location.OlympicStadium,LocalDate.now(),10,10,Rating.R);

        eventManagement.changeScreeningRating(screening1,Rating.PG);

        assertEquals(Rating.PG,eventManagement.getScreeningRating(screening1));
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.changeScreeningRating(null,Rating.PG);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.changeScreeningRating(screening1,null);
        });
    }

    @Test
    void getPrerequisiteWorkshops(){
        EventManagement eventManagement = new EventManagement();
        Workshop workshop1 = eventManagement.addWorkshopEvent("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);

        assertEquals(0,eventManagement.getPrerequisiteWorkshops(workshop1).size());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.getPrerequisiteWorkshops(null);
        });
    }

    @Test
    void addToPrerequisiteWorkshops(){
        EventManagement eventManagement = new EventManagement();
        Workshop workshop1 = eventManagement.addWorkshopEvent("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);
        Workshop workshop2 = eventManagement.addWorkshopEvent("workshop2",Location.Multiple,LocalDate.now(),10,10);

        eventManagement.addToPrerequisiteWorkshops(workshop1,workshop2);

        assertEquals(1,eventManagement.getPrerequisiteWorkshops(workshop1).size());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addToPrerequisiteWorkshops(workshop1,null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.addToPrerequisiteWorkshops(null,workshop2);
        });
    }

    @Test
    void removeFromPrerequisiteWorkshops(){
        EventManagement eventManagement = new EventManagement();
        Workshop workshop1 = eventManagement.addWorkshopEvent("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);
        Workshop workshop2 = eventManagement.addWorkshopEvent("workshop2",Location.Multiple,LocalDate.now(),10,10);

        workshop1.addToPrerequisiteWorkshops(workshop2);
        eventManagement.removeFromPrerequisiteWorkshops(workshop1,workshop2);

        assertEquals(0,eventManagement.getPrerequisiteWorkshops(workshop1).size());
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.removeFromPrerequisiteWorkshops(null,workshop2);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            eventManagement.removeFromPrerequisiteWorkshops(workshop1,null);
        });
    }

    @Test
    void getHostedEvents() {
        EventManagement eventManagement = new EventManagement();
        Workshop workshop1 = eventManagement.addWorkshopEvent("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);
        Screening screening1 = eventManagement.addScreeningEvent("screening1",Location.BellCentre,LocalDate.now(),10,10,Rating.R);
        Gala gala1 = eventManagement.addGalaEvent("gala1",Location.BellCentre,LocalDate.now(),10,10);
        Concert concert1 = eventManagement.addConcertEvent("concert1",Location.Multiple, LocalDate.now(),10,10,new Artist("artist"));
        ArrayList<Event> events = new ArrayList<>();
        events.add(workshop1);
        events.add(screening1);
        events.add(concert1);

        assertEquals(events,eventManagement.getHostedEvents());
    }
}