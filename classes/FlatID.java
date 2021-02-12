package classes;

public abstract class FlatID {
    private static long id = 0;

    public static long getId() {
        ++FlatID.id;
        return id;
    }
}