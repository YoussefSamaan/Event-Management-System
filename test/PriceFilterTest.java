import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceFilterTest {

    @Test
    void filter() {
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),100,10);
        Gala gala2 =  Gala.createGala("gala2",Location.Multiple, LocalDate.now(),10,10);
        Gala gala3 =  Gala.createGala("gala3",Location.ParcJeanDrapeau, LocalDate.now(),1,10);
        ArrayList<Event> events = new ArrayList<>();
        events.add(gala1);
        events.add(gala2);
        events.add(gala3);
        Festival festival1 = Festival.createFestival("name",events);
        events.add(festival1);


        PriceFilter priceFilter = new PriceFilter(
                new PriceFilter(
                        new EndFilter(),
                        8,
                        9),
                10,
                23);

        List<Event> events2 = priceFilter.filter(events);

        events.remove(gala1);
        events.remove(gala3);

        assertEquals(events,events2);
        assertThrows(IllegalArgumentException.class, () -> {
            priceFilter.filter(null);
        });
    }
}