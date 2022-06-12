import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProfitsCalculatorVisitorTest {

    @Test
    void calculateProfits() {
        ProfitsCalculatorVisitor profitsCalculator = new ProfitsCalculatorVisitor(10,10,10,10);
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        Workshop workshop1 =  Workshop.createWorkshop("workshop1",Location.Multiple, LocalDate.now(),10,10);
        Concert concert1 =  Concert.createConcert("concert1",Location.ParcJeanDrapeau, LocalDate.now(),10,10,new Artist("artist"));
        ComingSoon comingSoon1 = new ComingSoon("comingSoon1", LocalDate.now());
        Screening screening1 =  Screening.createScreening("screening1",Location.OlympicStadium,LocalDate.now(),10,10,Rating.R);
        ArrayList<Event> events = new ArrayList<>();
        events.add(gala1);
        events.add(workshop1);
        events.add(concert1);
        Festival festival1 = Festival.createFestival("name",events);
        events.add(festival1);
        events.add(comingSoon1);
        events.add(screening1);

        double profits = profitsCalculator.calculateProfits(new FilterResults(events,new EndFilter()));

        assertEquals(70,profits);
        assertThrows(IllegalArgumentException.class, () -> {
            profitsCalculator.calculateProfits(null);
        });

    }

    @Test
    void visitGala() {
        ProfitsCalculatorVisitor profitsCalculator = new ProfitsCalculatorVisitor(10,10,10,10);
        Gala gala1 =  Gala.createGala("gala1",Location.PlaceDesArts, LocalDate.now(),10,10);
        ArrayList<Event> events = new ArrayList<>();
        events.add(gala1);

        profitsCalculator.visitGala(gala1);
        double profits = profitsCalculator.calculateProfits(new FilterResults(events,new EndFilter()));

        assertEquals(20,profits);
        assertThrows(IllegalArgumentException.class, () -> {
            profitsCalculator.visitGala(null);
        });
    }

    @Test
    void visitorConcert() {
        ProfitsCalculatorVisitor profitsCalculator = new ProfitsCalculatorVisitor(10,10,10,10);
        Concert concert1 =  Concert.createConcert("concert1",Location.ParcJeanDrapeau, LocalDate.now(),10,10,new Artist("artist"));
        ArrayList<Event> events = new ArrayList<>();
        events.add(concert1);

        profitsCalculator.visitorConcert(concert1);
        double profits = profitsCalculator.calculateProfits(new FilterResults(events,new EndFilter()));

        assertEquals(20,profits);
        assertThrows(IllegalArgumentException.class, () -> {
            profitsCalculator.visitorConcert(null);
        });
    }

    @Test
    void visitorScreening() {
        ProfitsCalculatorVisitor profitsCalculator = new ProfitsCalculatorVisitor(10,10,10,10);
        Screening screening1 =  Screening.createScreening("screening1",Location.OlympicStadium,LocalDate.now(),10,10,Rating.R);
        ArrayList<Event> events = new ArrayList<>();
        events.add(screening1);

        profitsCalculator.visitorScreening(screening1);
        double profits = profitsCalculator.calculateProfits(new FilterResults(events,new EndFilter()));

        assertEquals(20,profits);
        assertThrows(IllegalArgumentException.class, () -> {
            profitsCalculator.visitorScreening(null);
        });
    }

    @Test
    void visitorWorkshop() {
        ProfitsCalculatorVisitor profitsCalculator = new ProfitsCalculatorVisitor(10,10,10,10);
        Workshop workshop1 =  Workshop.createWorkshop("workshop1",Location.Multiple, LocalDate.now(),10,10);
        ArrayList<Event> events = new ArrayList<>();
        events.add(workshop1);

        profitsCalculator.visitorWorkshop(workshop1);
        double profits = profitsCalculator.calculateProfits(new FilterResults(events,new EndFilter()));
        assertEquals(20,profits);
        assertThrows(IllegalArgumentException.class, () -> {
            profitsCalculator.visitorWorkshop(null);
        });
    }
}