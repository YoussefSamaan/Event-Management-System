import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WorkshopTest {

    @Test
    void createWorkshop() {
        LocalDate date = LocalDate.now();
        Gala gala1 = Gala.createGala("gala1",Location.Multiple,date,10,10);
        Workshop workshop1= Workshop.createWorkshop("workshop1",Location.OlympicStadium,date,10,10);
        Workshop workshop2 = Workshop.createWorkshop("workshop2",Location.OlympicStadium,date,10,10);
        Workshop workshop3 = Workshop.createWorkshop("workshop3",Location.Multiple,date,10,10);

        assertNotNull(workshop1);
        assertEquals(workshop1,workshop2);
        assertNull(workshop3);
        assertThrows(IllegalArgumentException.class, () -> {
            Workshop.createWorkshop(null,Location.OlympicStadium,date,10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Workshop.createWorkshop("workshop1",null,date,10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Workshop.createWorkshop("workshop1",Location.OlympicStadium,null,10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Workshop.createWorkshop("workshop1",Location.OlympicStadium,date,-10,10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Workshop.createWorkshop("workshop1",Location.OlympicStadium,date,10,-10);
        });
    }

    @Test
    void getPrerequisiteWorkshops() {
        Workshop workshop1= Workshop.createWorkshop("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);

        assertEquals(0,workshop1.getPrerequisiteWorkshops().size());
    }

    @Test
    void addToPrerequisiteWorkshops() {
        Workshop workshop1= Workshop.createWorkshop("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);
        Workshop workshop2= Workshop.createWorkshop("workshop2",Location.PlaceDesArts,LocalDate.now(),10,10);

        workshop1.addToPrerequisiteWorkshops(workshop2);

        assertEquals(1,workshop1.getPrerequisiteWorkshops().size());
        assertThrows(IllegalArgumentException.class, () -> {
            workshop1.addToPrerequisiteWorkshops(null);
        });
    }

    @Test
    void removePrerequisiteWorkshop() {
        Workshop workshop1= Workshop.createWorkshop("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);
        Workshop workshop2= Workshop.createWorkshop("workshop2",Location.PlaceDesArts,LocalDate.now(),10,10);

        workshop1.addToPrerequisiteWorkshops(workshop2);
        workshop1.removePrerequisiteWorkshop(workshop2);

        assertEquals(0,workshop1.getPrerequisiteWorkshops().size());
        assertThrows(IllegalArgumentException.class, () -> {
            workshop1.removePrerequisiteWorkshop(null);
        });
    }

    @Test
    void acceptVisitor() {
        ProfitsCalculatorVisitor profitsCalculator = new ProfitsCalculatorVisitor(10,10,10,10);
        Workshop workshop1= Workshop.createWorkshop("workshop1",Location.OlympicStadium,LocalDate.now(),10,10);
        ArrayList<Event> events =new ArrayList<>();
        events.add(workshop1);

        workshop1.acceptVisitor(profitsCalculator);

        assertEquals(20,profitsCalculator.calculateProfits(new FilterResults(events,new EndFilter())));
        assertThrows(IllegalArgumentException.class, () -> {
            workshop1.acceptVisitor(null);
        });
    }
}