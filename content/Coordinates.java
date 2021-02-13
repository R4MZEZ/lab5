package content;

public class Coordinates {
    public Coordinates(Float x, long y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    private Float x; //Поле не может быть null
    private long y; //Максимальное значение поля: 368
}