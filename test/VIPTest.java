import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VIPTest {

    @Test
    void testConstructor(){
        assertThrows(IllegalArgumentException.class, () -> {
            new VIP(null);
        });
    }

    @Test
    void getName() {
        VIP vip1 = new VIP("vip1");

        assertEquals("vip1",vip1.getName());
    }

    @Test
    void testEquals() {
        VIP vip1 = new VIP("vip1");
        VIP vip2 = new VIP("vip1");
        Artist artist1 = new Artist("artist1");

        assertTrue(vip1.equals(vip1));
        assertFalse(vip1.equals(artist1));
        assertTrue(vip1.equals(vip2));
    }

    @Test
    void testHashCode() {
        VIP vip1 = new VIP("vip1");
        VIP vip2 = new VIP("vip1");

        assertEquals(vip1.hashCode(),vip2.hashCode());
    }
}