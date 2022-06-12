import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventFactoryTest {

    @Test
    void addAndGetEvent() {
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        EventFactory.addEvent(gala1);
        Event gala2 = EventFactory.getEvent(gala1.getLocation().get(),gala1.getDate());
        assertEquals(gala1, gala2);

        assertThrows(IllegalArgumentException.class, () -> {
            EventFactory.addEvent(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            EventFactory.getEvent(gala1.getLocation().get(),null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            EventFactory.getEvent(null,gala1.getDate());
        });
    }
}