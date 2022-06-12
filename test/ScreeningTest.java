import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScreeningTest {

    @Test
    void createScreening() {
        LocalDate date = LocalDate.now();
        Workshop workshop1 = Workshop.createWorkshop("workshop1",Location.Multiple,date,10,10);
        Screening screening1 = Screening.createScreening("screening1",Location.OlympicStadium,date,10,10,Rating.G);
        Screening screening2 = Screening.createScreening("screening2",Location.OlympicStadium,date,10,10,Rating.PG);
        Screening screening3 = Screening.createScreening("screening3",Location.Multiple,date,10,10,Rating.PG_13);

        assertNotNull(screening1);
        assertEquals(screening1,screening2);
        assertNull(screening3);
        assertThrows(IllegalArgumentException.class, () -> {
            Screening.createScreening(null,Location.OlympicStadium,date,10,10,Rating.G);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Screening.createScreening("screening1",null,date,10,10,Rating.G);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Screening.createScreening("screening1",Location.OlympicStadium,null,10,10,Rating.G);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Screening.createScreening("screening1",Location.OlympicStadium,date,-10,10,Rating.G);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Screening.createScreening("screening1",Location.OlympicStadium,date,10,-10,Rating.G);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Screening.createScreening("screening1",Location.OlympicStadium,date,10,10,null);
        });
    }

    @Test
    void getRating() {
        Screening screening1 = Screening.createScreening("screening1",Location.OlympicStadium,LocalDate.now(),10,10,Rating.G);

        assertEquals(Rating.G, screening1.getRating());

    }

    @Test
    void setRating() {
        Screening screening1 = Screening.createScreening("screening1",Location.OlympicStadium,LocalDate.now(),10,10,Rating.G);

        screening1.setRating(Rating.PG);

        assertEquals(Rating.PG,screening1.getRating());
        assertThrows(IllegalArgumentException.class, () -> {
            screening1.setRating(null);
        });
    }

    @Test
    void acceptVisitor() {
        ProfitsCalculatorVisitor profitsCalculator = new ProfitsCalculatorVisitor(10,10,10,10);
        Screening screening1 = Screening.createScreening("screening1",Location.OlympicStadium,LocalDate.now(),10,10,Rating.G);
        ArrayList<Event> events =new ArrayList<>();
        events.add(screening1);

        screening1.acceptVisitor(profitsCalculator);

        assertEquals(20,profitsCalculator.calculateProfits(new FilterResults(events,new EndFilter())));
        assertThrows(IllegalArgumentException.class, () -> {
            screening1.acceptVisitor(null);
        });
    }
}