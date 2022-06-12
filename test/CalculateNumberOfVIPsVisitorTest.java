import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculateNumberOfVIPsVisitorTest {

    @Test
    void calculateNumberOfVips() {
        CalculateNumberOfVIPsVisitor vipCalculator = new CalculateNumberOfVIPsVisitor();
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

        assertEquals(4,vipCalculator.calculateNumberOfVips(new FilterResults(events,new EndFilter())));

        assertThrows(IllegalArgumentException.class, () -> {
            CalculateNumberOfVIPsVisitor visitor = new CalculateNumberOfVIPsVisitor();
            visitor.calculateNumberOfVips(null);
        });
    }

    @Test
    void visitGala() {
        CalculateNumberOfVIPsVisitor vipCalculator = new CalculateNumberOfVIPsVisitor();
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        Gala gala2 =  Gala.createGala("gala2",Location.OlympicStadium, LocalDate.now(),10,10);
        VIP vip1 = new VIP("vip1");
        VIP vip2 = new VIP("vip2");
        gala2.addVIP(vip2);
        gala1.addVIP(vip1);
        ArrayList<Event> events = new ArrayList<>();
        events.add(gala1);

        vipCalculator.visitGala(gala2);

        assertEquals(2,vipCalculator.calculateNumberOfVips(new FilterResults(events,new EndFilter())));
        assertThrows(IllegalArgumentException.class, () -> {
            CalculateNumberOfVIPsVisitor visitor = new CalculateNumberOfVIPsVisitor();
            visitor.visitGala(null);
        });
    }

    @Test
    void visitorConcert() {
        CalculateNumberOfVIPsVisitor vipCalculator = new CalculateNumberOfVIPsVisitor();
        Concert concert1 =  Concert.createConcert("concert1",Location.PlaceDesArts, LocalDate.now(),10,10,new Artist("artist1"));
        Concert concert2 =  Concert.createConcert("concert2",Location.OlympicStadium, LocalDate.now(),10,10,new Artist("artist1"));
        VIP vip1 = new VIP("vip1");
        VIP vip2 = new VIP("vip2");
        concert2.addVIP(vip2);
        concert1.addVIP(vip1);
        ArrayList<Event> events = new ArrayList<>();
        events.add(concert1);

        vipCalculator.visitorConcert(concert2);

        assertEquals(2,vipCalculator.calculateNumberOfVips(new FilterResults(events,new EndFilter())));
        assertThrows(IllegalArgumentException.class, () -> {
            CalculateNumberOfVIPsVisitor visitor = new CalculateNumberOfVIPsVisitor();
            visitor.visitorConcert(null);
        });
    }

    @Test
    void visitorScreening() {
        CalculateNumberOfVIPsVisitor vipCalculator = new CalculateNumberOfVIPsVisitor();
        Screening screening1 =  Screening.createScreening("screening1",Location.PlaceDesArts, LocalDate.now(),10,10,Rating.G);
        Screening screening2 =  Screening.createScreening("screening2",Location.OlympicStadium, LocalDate.now(),10,10,Rating.G);
        ArrayList<Event> events = new ArrayList<>();
        events.add(screening1);

        vipCalculator.visitorScreening(screening2);

        assertEquals(0,vipCalculator.calculateNumberOfVips(new FilterResults(events,new EndFilter())));
    }

    @Test
    void visitorWorkshop() {
        CalculateNumberOfVIPsVisitor vipCalculator = new CalculateNumberOfVIPsVisitor();
        Workshop workshop1 =  Workshop.createWorkshop("workshop1",Location.PlaceDesArts, LocalDate.now(),10,10);
        Workshop workshop2 =  Workshop.createWorkshop("workshop2",Location.OlympicStadium, LocalDate.now(),10,10);
        ArrayList<Event> events = new ArrayList<>();
        events.add(workshop1);

        vipCalculator.visitorWorkshop(workshop2);

        assertEquals(0,vipCalculator.calculateNumberOfVips(new FilterResults(events,new EndFilter())));
    }
}