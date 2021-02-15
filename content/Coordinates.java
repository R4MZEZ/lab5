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

    public String getStringValue(){ return Float.toString(x) + ";" + Long.toString(y); }


    private Float x; //Поле не может быть null
    private long y; //Максимальное значение поля: 368


}