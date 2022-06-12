import java.util.Objects;

/**
 * Representation of a VIP
 */
public class VIP {
    private final String name; // name of the VIP

    /**
     * Constructor of the VIP.
     * Creates new VIP object.
     *
     * @param name
     *              name of the VIP
     * @throws IllegalArgumentException if name == null
     */
    public VIP(String name){
        if(name == null) throw new IllegalArgumentException("name can not be null");

        this.name = name;
    }

    /**
     * @return the name of the VIP
     */
    public String getName() {
        return this.name;
    }

    /**
     * Equals method to compare a VIP to another object. Returns true if and only if they have the same name.
     *
     * @param o
     *          The object to be compared to the VIP
     * @return  True if both objects have the same name. False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VIP)) return false;
        VIP vip = (VIP) o;
        return name.equals(vip.name);
    }

    /**
     * Returns the hash code of the full name of the VIP Object.
     * VIP are hashed based on their full name.
     *
     * @return value of the hash as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
