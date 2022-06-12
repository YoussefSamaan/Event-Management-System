import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FestivalTest {

    @Test
    void createFestival() {
        ArrayList<Event> events = new ArrayList<>();
        Workshop workshop = Workshop.createWorkshop("workshop",Location.Multiple, LocalDate.now(),10,10);
        Festival festival2 = Festival.createFestival("festival2", events);
        ComingSoon comingSoon1 = new ComingSoon("comingSoon", LocalDate.now());
        events.add(comingSoon1);
        Festival festival3 = Festival.createFestival("festival3", events);
        events.add(workshop);
        Festival festival1 = Festival.createFestival("festival1",events);

        assertEquals(Location.Multiple,festival1.getLocation().get());
        assertEquals("festival2", festival2.getName());
        assertEquals(Optional.empty(),festival3.getLocation());
        assertThrows(IllegalArgumentException.class, () -> {
            Festival.createFestival(null,events);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Festival.createFestival("festival1",null);
        });
    }

    @Test
    void acceptVisitor() {
        CalculateNumberOfVIPsVisitor vipCalculator = new CalculateNumberOfVIPsVisitor();
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        Gala gala2 =  Gala.createGala("gala2",Location.Multiple, LocalDate.now(),10,10);
        Gala gala3 =  Gala.createGala("gala3",Location.ParcJeanDrapeau, LocalDate.now(),10,10);
        ArrayList<Event> events = new ArrayList<>();
        VIP vip1 = new VIP("vip1");
        VIP vip2 = new VIP("vip2");
        VIP vip3 = new VIP("vip3");
        gala1.addVIP(vip1);
        gala2.addVIP(vip2);
        events.add(gala1);
        events.add(gala2);
        events.add(gala3);
        Festival festival1 = Festival.createFestival("name",events);
        events.add(festival1);

        assertEquals(2,vipCalculator.calculateNumberOfVips(new FilterResults(events, new EndFilter())));

        gala3.addVIP(vip3);
        festival1.acceptVisitor(vipCalculator);

        assertEquals(3,vipCalculator.calculateNumberOfVips(new FilterResults(events, new EndFilter())));

        assertThrows(IllegalArgumentException.class, () -> {
            festival1.acceptVisitor(null);
        });
    }
}