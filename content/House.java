package content;

public class House implements Comparable{
    public House(String name, Integer year, int numberOfFlatsOnFloor) {
        this.name = name;
        this.year = year;
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public int getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    private String name; //Поле не может быть null
    private Integer year; //Значение поля должно быть больше 0
    private int numberOfFlatsOnFloor; //Значение поля должно быть больше 0

    @Override
    public int compareTo(Object o) {
        House obj = (House)o;
        return this.year - obj.getYear();
    }
}