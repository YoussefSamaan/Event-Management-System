import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ArtistTest {

    @Test
    void checkThrowsErrorCreatingArtist(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Artist(null);
        });
    }


    @Test
    void getFullNameTest() {
        Artist artist1 = new Artist("artist1");

        assertEquals("artist1",artist1.getFullName());
    }

    @Test
    void testEqualsTest() {
        Artist artist1 = new Artist("artist1");
        Artist artist2 = new Artist("artist1");
        VIP vip1 = new VIP("vip1");

        assertTrue(artist1.equals(artist1));
        assertFalse(artist1.equals(vip1));
        assertTrue(artist1.equals(artist2));
    }

    @Test
    void testHashCodeTest() {
        Artist artist1 = new Artist("artist1");
        Artist artist2 = new Artist("artist1");

        assertEquals(artist1.hashCode(),artist2.hashCode());
    }
}