package content;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flat implements Comparable{
    public Flat(String name, Coordinates coordinates, Long area, Integer numberOfRooms, long livingSpace, View view, Transport transport, House house) {
        this.id = FlatID.getNewId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now(); //LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm:ss"))
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.livingSpace = livingSpace;
        this.view = view;
        this.transport = transport;
        this.house = house;
    }
    public Flat(String name){
        this.name = name;
    }

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Поле не может быть null, Значение поля должно быть больше 0
    private Integer numberOfRooms; //Значение поля должно быть больше 0
    private long livingSpace; //Значение поля должно быть больше 0
    private View view; //Поле не может быть null
    private Transport transport; //Поле может быть null
    private House house; //Поле может быть null


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Long getArea() {
        return area;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public long getLivingSpace() {
        return livingSpace;
    }

    public View getView() {
        return view;
    }

    public Transport getTransport() {
        return transport;
    }

    public House getHouse() {
        return house;
    }

    @Override
    public int compareTo(Object o) {
        Flat obj = (Flat) o;
        return this.name.length() - obj.getName().length();
    }

    @Override
    public String toString() {
        return "\t\t\t\t\t\t\t\t\t\t\tКВАРТИРА " + id + "\n" +
                "Номер квартиры: " + id +
                ", имя собственника: " + name  +
                ", координаты квартиры: (" + coordinates.getX() + ", " + coordinates.getY() + ")" +
                ", время добавления квартиры в коллекцию: " + creationDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm:ss")) +
                ",\n жил.площадь: " + area + " кв.м." +
                ", число комнат: " + numberOfRooms +
                ", площадь жилых комнат: " + livingSpace + " кв.м." +
                ", вид из окна: " + view +
                ", транспорт:" + transport +
                ",\n данные дома {Название - " + house.getName() + ", год постройки - " + house.getYear() + ", число квартир на этаже - " + house.getNumberOfFlatsOnFloor() + "}";
    }

    static class FlatID {
        private static long id = 0;

        static long getNewId() {
            id += 1;
            return id;
        }
    }
}