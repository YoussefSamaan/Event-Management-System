import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GalaTest {

    @Test
    void createGala() {
        LocalDate date = LocalDate.now();
        Workshop workshop1 = Workshop.createWorkshop("workshop1",Location.Multiple,date,10,10);
        Gala gala1 = Gala.createGala("gala1",Location.OlympicStadium,date,10,10);
        Gala gala2 = Gala.createGala("gala2",Location.OlympicStadium,date,10,10);
        Gala gala3 = Gala.createGala("gala3",Location.Multiple,date,10,10);

        assertNotNull(gala1);
        assertEquals(gala1,gala2);
        assertNull(gala3);
        assertThrows(IllegalArgumentException.class, () -> {
            Gala.createGala(null,Location.OlympicStadium,date,10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Gala.createGala("gala1",null,date,10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Gala.createGala("gala1",Location.OlympicStadium,null,10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Gala.createGala("gala1",Location.OlympicStadium,date,-10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Gala.createGala("gala1",Location.OlympicStadium,date,10,-10);
        });
    }

    @Test
    void GetVIPs() {
        Gala gala1 = Gala.createGala("gala1",Location.OlympicStadium,LocalDate.now(),10,10);
        VIP vip1 = new VIP("vip1");

        gala1.addVIP(vip1);

        assertEquals(vip1,gala1.getVIPs().get(0));
    }

    @Test
    void addVIP() {
        Gala gala1 = Gala.createGala("gala1",Location.OlympicStadium,LocalDate.now(),10,10);
        VIP vip1 = new VIP("vip1");

        assertEquals(0,gala1.getVIPs().size());

        gala1.addVIP(vip1);

        assertEquals(1,gala1.getVIPs().size());
        assertThrows(IllegalArgumentException.class, () -> {
            gala1.addVIP(null);
        });
    }

    @Test
    void acceptVisitor() {
        CalculateNumberOfVIPsVisitor vipCalculator = new CalculateNumberOfVIPsVisitor();
        Gala gala1 = Gala.createGala("gala1",Location.OlympicStadium,LocalDate.now(),10,10);
        Gala gala2 = Gala.createGala("gala2",Location.PlaceDesArts,LocalDate.now(),10,10);
        VIP vip1 = new VIP("vip1");
        VIP vip2 = new VIP("vip2");
        gala2.addVIP(vip2);
        gala1.addVIP(vip1);
        ArrayList<Event> events = new ArrayList<>();
        events.add(gala1);

        gala2.acceptVisitor(vipCalculator);

        assertEquals(2,vipCalculator.calculateNumberOfVips(new FilterResults(events,new EndFilter())));
        assertThrows(IllegalArgumentException.class, () -> {
            gala2.acceptVisitor(null);
        });
    }
}