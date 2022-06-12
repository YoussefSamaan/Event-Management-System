/**
 * Visitor interface that all visitors need to implements
 */
public interface Visitor {
    void visitGala(Gala gala);
    void visitorConcert(Concert concert);
    void visitorScreening(Screening screening);
    void visitorWorkshop(Workshop workshop);
}
