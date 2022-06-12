import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FilterResultsTest {

    @Test
    void testConstructor(){
        ArrayList<Event> events  = new ArrayList<>();
        events.add(new EventStub());

        new FilterResults(events,new EndFilter());

        assertThrows(IllegalArgumentException.class, () -> {
            new FilterResults(null, new EndFilter());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new FilterResults(events,null);
        });
    }

    @Test
    void getFilteredEvents() {
        CalculateNumberOfVIPsVisitor vipCalculator = new CalculateNumberOfVIPsVisitor();
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        Gala gala2 =  Gala.createGala("gala2",Location.Multiple, LocalDate.now(),10,10);
        Gala gala3 =  Gala.createGala("gala3",Location.ParcJeanDrapeau, LocalDate.now(),10,10);
        ArrayList<Event> events = new ArrayList<>();
        events.add(gala1);
        events.add(gala2);
        events.add(gala3);
        Festival festival1 = Festival.createFestival("name",events);
        events.add(festival1);

        FilterResults filterResults = new FilterResults(events,new EndFilter());
    }
}