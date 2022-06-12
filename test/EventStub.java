import java.time.LocalDate;
import java.util.Optional;

public class EventStub implements Event{

    public EventStub(){}

    @Override
    public String getName() {
        return "eventStub";
    }

    @Override
    public Optional<Location> getLocation() {
        return Optional.of(Location.OlympicStadium);
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.MAX;
    }

    @Override
    public Optional<Double> getPrice() {
        return Optional.of(10.0);
    }

    @Override
    public Optional<Integer> getNumTickets() {
        return Optional.of(10);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {

    }
}
