import java.util.Objects;

/**
 * Represents an Artist
 *
 * @invariant fullName != null
 */
public class Artist {
    private final String fullName; //  name of the artist

    /**
     * Constructor of the Artist.
     * Creates new Artist object.
     *
     * @param fullName
     *              full name of the artist
     * @throws IllegalArgumentException if full name == null.
     */
    public Artist(String fullName){
        if(fullName == null) throw new IllegalArgumentException("name can not be null");

        this.fullName = fullName;
    }

    /**
     * @return the full name of the Artist
     */
    public String getFullName(){
        return this.fullName;
    }

    /**
     * Equals method to compare an artist to another object. Returns true if and only if they have the same full name.
     *
     * @param o
     *          The object to be compared to the artist
     * @return  True if both objects have the same full name. False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return fullName.equals(artist.fullName);
    }

    /**
     * Returns the hash code of the full name of the Artist Object.
     * Artists are hashed based on their full name.
     *
     * @return value of the hash as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }
}
