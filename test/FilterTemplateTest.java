import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FilterTemplateTest {

    @Test
    void testConstructor(){
        assertThrows(IllegalArgumentException.class, () -> {
            new PriceFilter(null,10,10);
        });
    }

    @Test
    void filterEvents() {
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        Gala gala2 =  Gala.createGala("gala2",Location.Multiple, LocalDate.now(),10,10);
        Gala gala3 =  Gala.createGala("gala3",Location.ParcJeanDrapeau, LocalDate.now(),10,10);
        ArrayList<Event> events = new ArrayList<>();
        events.add(gala1);
        events.add(gala2);
        events.add(gala3);
        Festival festival1 = Festival.createFestival("name",events);
        events.add(festival1);

        LocationFilter locationFilter = new LocationFilter(new LocationFilter(new
                EndFilter(),Location.PlaceDesArts),
                        Location.Multiple
                        );
        ArrayList<Event> events1 = new ArrayList<>(events);
        events1.remove(festival1);
        events1.remove(gala2);
        events1.remove(gala1);

        assertEquals(events1, locationFilter.filterEvents(events));
        assertThrows(IllegalArgumentException.class, () -> {
            locationFilter.filterEvents(null);
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

        LocationFilter locationFilter = new LocationFilter(new LocationFilter(new
                EndFilter(),Location.PlaceDesArts),
                Location.Multiple
        );
        ArrayList<Event> events1 = new ArrayList<>(events);
        events1.remove(festival1);
        events1.remove(gala2);

        assertEquals(events1, locationFilter.filter(events));
    }
}