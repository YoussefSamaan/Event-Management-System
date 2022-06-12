import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationFilterTest {

    @Test
    void testConstructor(){
        assertThrows(IllegalArgumentException.class, () -> {
            new LocationFilter(null,Location.Multiple);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new LocationFilter(new EndFilter(),null);
        });
    }

    @Test
    void filter() {
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        Gala gala2 =  Gala.createGala("gala2",Location.Multiple, LocalDate.now(),10,10);
        Gala gala3 =  Gala.createGala("gala3",Location.ParcJeanDrapeau, LocalDate.now(),10,10);
        ArrayList<Event> events = new ArrayList<>();
        events.add(gala1);
        events.add(gala2);
        events.add(gala3);
        Festival festival1 = Festival.createFestival("name",events);
        events.add(festival1);

        LocationFilter locationFilter1 = new LocationFilter(
                new LocationFilter(
                        new EndFilter(),
                        Location.BellCentre),
                Location.Multiple);

        List<Event> events2 = locationFilter1.filter(events);

        events.remove(gala2);
        events.remove(festival1);

        assertEquals(events,events2);
        assertThrows(IllegalArgumentException.class, () -> {
            locationFilter1.filter(null);
        });
    }
}