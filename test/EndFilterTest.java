import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EndFilterTest {

    @Test
    void filterEvents() {
        EndFilter endFilter = new EndFilter();
        Workshop workshop1 =  Workshop.createWorkshop("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        ArrayList<Event> events = new ArrayList<>();
        events.add(workshop1);

        assertEquals(events,endFilter.filterEvents(events));
    }
}