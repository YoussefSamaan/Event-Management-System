import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComingSoonTest {

    @Test
    void acceptVisitor() {
        ProfitsCalculatorVisitor visitor1 = new ProfitsCalculatorVisitor(10,10,10,10);
        ComingSoon comingSoon1 = new ComingSoon("comingSoon1", LocalDate.now());
        List<Event> events = new ArrayList<>();
        events.add(comingSoon1);

        assertEquals(0,visitor1.calculateProfits(new FilterResults(events,new EndFilter())));
    }
}