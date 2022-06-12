import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BasicTemplateEventTest {

    @Test
    void getNameTest() {
        Workshop workshop1 =  Workshop.createWorkshop("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);
        assertEquals("workshop1",workshop1.getName());
    }

    @Test
    void getLocationTest() {
        Workshop workshop1 =  Workshop.createWorkshop("concert1",Location.BellCentre,LocalDate.now(),10,10);
        assertEquals(Optional.of(Location.BellCentre),workshop1.getLocation());
    }

    @Test
    void getDateTest() {
        LocalDate date = LocalDate.now();
        Workshop workshop1 =  Workshop.createWorkshop("concert1",Location.BellCentre,date,10,10);
        assertEquals(date,workshop1.getDate());
    }

    @Test
    void getPriceTest() {
        Workshop workshop1 =  Workshop.createWorkshop("concert1",Location.BellCentre,LocalDate.now(),10,10);
        assertEquals(Optional.of(10.0),workshop1.getPrice());
    }

    @Test
    void getNumTicketsTest() {
        Workshop workshop1 =  Workshop.createWorkshop("concert1",Location.BellCentre,LocalDate.now(),10,10);
        assertEquals(Optional.of(10),workshop1.getNumTickets());
    }

    @Test
    void acceptVisitorTest() {
        ProfitsCalculatorVisitor visitor1 = new ProfitsCalculatorVisitor(10,10,10,10);
        Workshop workshop1 =  Workshop.createWorkshop("workshop1",Location.PlaceDesArts,LocalDate.now(),10,10);
        Concert concert1 = Concert.createConcert("concert1",Location.ParcJeanDrapeau,LocalDate.now(),10,10,new Artist("a"));
        Screening screening1 = Screening.createScreening("screening1",Location.OlympicStadium,LocalDate.now(),10,10,Rating.R);
        Gala gala1 = Gala.createGala("gala1",Location.Multiple,LocalDate.now(),10,10);
        ComingSoon comingSoon1 = new ComingSoon("comingSoon1",LocalDate.now());
        List<Event> events = new ArrayList<>();
        events.add(workshop1);
        events.add(concert1);
        events.add(screening1);
        events.add(gala1);
        events.add(comingSoon1);
        Festival festival1 =  Festival.createFestival("festival1",events);
        events.add(festival1);
        assertEquals(80,visitor1.calculateProfits(new FilterResults(events,new EndFilter())));
    }
}