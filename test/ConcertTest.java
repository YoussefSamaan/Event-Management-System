import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConcertTest {

    @Test
    void createConcert() {
        LocalDate date = LocalDate.now();
        Concert concert1 = Concert.createConcert("concert1", Location.BellCentre, date,10,10, new Artist("artist1"));
        Workshop workshop1 = Workshop.createWorkshop("workshop1",Location.Multiple,date,10,10);
        Concert concert2 = Concert.createConcert("concert1", Location.BellCentre, date,10,10, new Artist("artist1"));
        Concert concert3 = Concert.createConcert("concert2",Location.Multiple,date,10,10,new Artist("artist2"));

        assertNotNull(concert1);
        assertEquals(concert1,concert2);
        assertNull(concert3);
        assertThrows(IllegalArgumentException.class, () -> {
            Concert.createConcert(null, Location.BellCentre, date,10,10, new Artist("artist1"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Concert.createConcert("concert1", null, date,10,10, new Artist("artist1"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Concert.createConcert("concert1", Location.BellCentre, null,10,10, new Artist("artist1"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Concert.createConcert("concert1", Location.BellCentre, date,-10,10, new Artist("artist1"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Concert.createConcert("concert1", Location.BellCentre, date,10,-10, new Artist("artist1"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Concert.createConcert("concert1", Location.BellCentre, date,10,10, null);
        });

    }

    @Test
    void getArtist() {
        Artist artist = new Artist("artist");
        Concert concert1 = Concert.createConcert("concert1", Location.BellCentre, LocalDate.now(),10,10, artist);
        assertEquals(artist,concert1.getArtist());
    }

    @Test
    void setArtist() {
        Artist artist = new Artist("artist2");
        Concert concert1 = Concert.createConcert("concert1", Location.BellCentre, LocalDate.now(),10,10, new Artist("artist1"));

        concert1.setArtist(artist);

        assertEquals(artist,concert1.getArtist());
        assertThrows(IllegalArgumentException.class, () -> {
            concert1.setArtist(null);
        });
    }

    private void assertArrayEquals(List<VIP> vips, List<VIP> viPs) {
        assertEquals(vips.size(),viPs.size());
        for(int i=0; i<vips.size();i++){
            assertEquals(vips.get(i),viPs.get(i));
        }
    }

    @Test
    void getVIPs() {
        Concert concert1 = Concert.createConcert("concert1", Location.BellCentre, LocalDate.now(),10,10, new Artist("artist1"));
        VIP vip = new VIP("vip");
        List<VIP> vips = new ArrayList<>();

        concert1.addVIP(vip);
        vips.add(vip);

        assertArrayEquals(vips,concert1.getVIPs());
    }

    // used Reflection
    @Test
    void addVIP(){
        Concert concert1 = Concert.createConcert("concert1", Location.BellCentre, LocalDate.now(),10,10, new Artist("artist1"));
        VIP vip = new VIP("vip");
        concert1.addVIP(vip);

        try {
            String VIPsField = "VIPs";
            Field concertVips = Concert.class.getDeclaredField(VIPsField);
            concertVips.setAccessible(true);
            ArrayList<VIP> VIPs = (ArrayList<VIP>) concertVips.get(concert1);
            assertEquals(vip,VIPs.get(0));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        concert1.addVIP(vip);

        assertThrows(IllegalArgumentException.class, () -> {
            concert1.addVIP(null);
        });
    }

    @Test
    void acceptVisitor() {
        CalculateNumberOfVIPsVisitor vipCalculator = new CalculateNumberOfVIPsVisitor();
        Concert concert1 = Concert.createConcert("concert1", Location.BellCentre, LocalDate.now(),10,10, new Artist("artist1"));
        Concert concert2 = Concert.createConcert("concert2", Location.PlaceDesArts, LocalDate.now(),10,10, new Artist("artist1"));
        VIP vip1 = new VIP("vip1");
        VIP vip2 = new VIP("vip2");
        concert2.addVIP(vip2);
        concert1.addVIP(vip1);
        ArrayList<Event> events = new ArrayList<>();
        events.add(concert1);

        concert2.acceptVisitor(vipCalculator);

        assertEquals(2,vipCalculator.calculateNumberOfVips(new FilterResults(events,new EndFilter())));
        assertThrows(IllegalArgumentException.class, () -> {
            concert1.acceptVisitor(null);
        });
    }
}