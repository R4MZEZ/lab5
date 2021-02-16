package Main;

import content.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@XmlType(name = "root")
@XmlRootElement
public class CollectionManager {
    LinkedHashMap<String, String> manual = new LinkedHashMap<>();
    @XmlElementWrapper(name = "collection")
    private LinkedList<Flat> flats = new LinkedList<>();
    private static LocalDateTime initDate = LocalDateTime.now();
    private String[] temp = new String[11];

    public CollectionManager() {
        manual.put("help", "Вывести справку по доступным командам");
        manual.put("info", "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        manual.put("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        manual.put("add {element}", "Добавить новый элемент в коллекцию");
        manual.put("update id {element}", "Обновить значение элемента коллекции, id которого равен заданному");
        manual.put("remove_by_id id", "Удалить элемент коллекции по его id");
        manual.put("clear", "Очистить коллекцию");
        manual.put("save", "Сохранить коллекцию в файл");
        manual.put("execute_script file_name", "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        manual.put("exit", "Завершить программу (без сохранения в файл)");
        manual.put("remove_at index", "Удалить элемент, находящийся в заданной позиции коллекции (index)");
        manual.put("remove_last", "Удалить последний элемент из коллекции");
        manual.put("shuffle", "Перемешать элементы коллекции в случайном порядке");
        manual.put("average_of_living_space", "Вывести среднее значение поля livingSpace для всех элементов коллекции");
        manual.put("max_by_house", "Вывести любой объект из коллекции, значение поля house которого является максимальным");
        manual.put("filter_less_than_view view", "Вывести элементы, значение поля view которых меньше заданного");
        flats.add(new Flat("Mark", new Coordinates(1F, 2), 100L, 5, 50L, View.BAD, Transport.FEW, new House("hostel", 2000, 5)));
        flats.add(new Flat("Alex", new Coordinates(1F, 2), 100L, 5, 50L, View.BAD, Transport.FEW, new House("hostel", 2000, 5)));
    }


    public static LocalDateTime getInitDate() {
        return initDate;
    }

    public LinkedList<Flat> getFlats() {
        return flats;
    }



    public void help() {
        System.out.println(MapToString(manual));
    }

    public void info() {
        String info = "Тип - " + flats.getClass().getName() +
                "\nДата инициализации - " + getInitDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")) +
                "\nКоличество элементов - " + flats.size();
        System.out.println(info);
    }

    public void show() {
        for (Flat flat : flats)
            System.out.println(flat.NiceToString());
    }

    public void add(Scanner commandReader){
        System.out.print("Введите имя собственника: ");
        temp[0] = commandReader.nextLine();
        while (!Checker.isString(temp[0])) {
            System.out.println("Ошибка! Имя должно быть непустой строкой.");
            System.out.print("Введите имя собственника: ");
            temp[0] = commandReader.nextLine();
        }

        System.out.print("Введите координату X: ");
        temp[1] = commandReader.nextLine();
        while (!Checker.isFloat(temp[1])){
            System.out.println("Ошибка! Координата должна быть числом.");
            System.out.print("Введите координату X: ");
            temp[1] = commandReader.nextLine();
        }

        System.out.print("Введите координату Y в диапазоне (-inf,368]: ");
        temp[2] = commandReader.nextLine();
        while (!Checker.isLong(temp[2]) || Long.parseLong(temp[2]) > 368){
            System.out.println("Ошибка! Координата Y должна быть числом в диапазоне от -9223372036854775808 до 368.");
            System.out.print("Введите координату Y в диапазоне (-inf,368]: ");
            temp[2] = commandReader.nextLine();
        }

        System.out.print("Введите жил.площадь: ");
        temp[3] = commandReader.nextLine();
        while (!Checker.isLong(temp[3]) || Long.parseLong(temp[3]) < 0){
            System.out.println("Ошибка! Жил.площадь должна быть положительным числом в диапазоне от 0 до 9223372036854775807.");
            System.out.print("Введите жил.площадь: ");
            temp[3] = commandReader.nextLine();
        }

        System.out.print("Введите количество комнат: ");
        temp[4] = commandReader.nextLine();
        while (!Checker.isInteger(temp[4]) || Integer.parseInt(temp[4]) < 0){
            System.out.println("Ошибка! Количество комнат должно быть целым положительным числом в диапазоне от 0 до 2147483647.");
            System.out.print("Введите количество комнат: ");
            temp[4] = commandReader.nextLine();
        }

        System.out.print("Введите площадь жилых комнат: ");
        temp[5] = commandReader.nextLine();
        while (!Checker.isLong(temp[5]) || Long.parseLong(temp[5]) < 0){
            System.out.println("Ошибка! Площадь жилых комнат должна быть положительным числом в диапазоне от 0 до 9223372036854775807.");
            System.out.print("Введите площадь жилых комнат: ");
            temp[5] = commandReader.nextLine();
        }

        View.ViewToString();
        System.out.print("Введите вид из окна: ");
        temp[6] = commandReader.nextLine();
        while (!Checker.isView(temp[6])){
            System.out.println("Ошибка! Введенное значение недопустимо.");
            View.ViewToString();
            System.out.print("Введите вид из окна: ");
            temp[6] = commandReader.nextLine();
        }

        Transport.TransportToString();
        System.out.print("Введите транспорт собственника: ");
        temp[7] = commandReader.nextLine();
        while (!Checker.isTransport(temp[7])){
            System.out.println("Ошибка! Введенное значение недопустимо.");
            Transport.TransportToString();
            System.out.print("Введите транспорт собственника: ");
            temp[7] = commandReader.nextLine();
        }

        System.out.print("Введите название дома: ");
        temp[8] = commandReader.nextLine();
        while (!Checker.isString(temp[8])) {
            System.out.println("Ошибка! Название должно быть непустой строкой.");
            System.out.print("Введите название дома: ");
            temp[8] = commandReader.nextLine();
        }

        System.out.print("Введите год постройки дома: ");
        temp[9] = commandReader.nextLine();
        while (!Checker.isInteger(temp[9]) || Integer.parseInt(temp[9]) < 0){
            System.out.println("Ошибка! Год постройки дома должен быть целым положительным числом в диапазоне от 0 до 2147483647.");
            System.out.print("Введите год постройки дома: ");
            temp[9] = commandReader.nextLine();
        }

        System.out.print("Введите количество квартир на этаже: ");
        temp[10] = commandReader.nextLine();
        while (!Checker.isInteger(temp[10]) || Integer.parseInt(temp[10]) < 0){
            System.out.println("Ошибка! Количество квартир на этаже должно быть целым положительным числом в диапазоне от 0 до 2147483647.");
            System.out.print("Введите количество квартир на этаже: ");
            temp[10] = commandReader.nextLine();
        }

        flats.add(new Flat(temp[0], new Coordinates(Float.parseFloat(temp[1]), Long.parseLong(temp[2])),
                    Long.parseLong(temp[3]), Integer.parseInt(temp[4]), Long.parseLong(temp[5]),
                    View.valueOf(temp[6]),Transport.valueOf(temp[7]), new House(temp[8], Integer.parseInt(temp[9]),
                    Integer.parseInt(temp[10]))));
        System.out.println("===================================\nЭлемент успешно добавлен.");
    }

    public void update(String id, Scanner commandReader){
        while (!Checker.isLong(id))

    }

    public void remove_by_id(long id) {
        for (int i = 0; i < flats.size(); i++) {
            if (flats.get(i).getId() == id) {
                flats.remove(i);
                System.out.println("Элемент с id = '" + id + "' успешно удалён.");
                return;
            }
        }
        System.out.println("Элемента с id = '" + id + "' не найдено.");
    }

    public void clear() {
        flats.clear();
        System.out.println("Коллекция успешно очищена.");
    }

    public void save(CollectionManager manager) throws IOException, JAXBException {
        FileWriter writer = new FileWriter("C:\\Users\\User\\IdeaProjects\\labb5\\src\\inputData\\output.xml");
        JAXBContext context = JAXBContext.newInstance(Flat.class, CollectionManager.class, House.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(manager, writer);
    }

//    public void execute_script(){
//
//    }

    public void remove_at(int index) {
        try {
            flats.remove(index);
            System.out.println("Элемент коллекции успешно удалён.");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Ошибка. Элемента с таким индексом не существует.");
        }
    }

    public void remove_last() {
        try {
            flats.removeLast();
            System.out.println("Последний элемент успешно удалён.");
        } catch (NoSuchElementException ex) {
            System.out.println("Ошибка. Невозможно удалить последний элемент коллекции, т.к. она пуста.");
        }

    }

    public void shuffle() {
        Collections.shuffle(flats);
        System.out.println("Элементы коллекции успешно перемешаны");
    }

    public void average_of_living_space() {
        if (flats.size() > 0) {
            Iterator iterator = flats.iterator();
            long sum = 0;
            while (iterator.hasNext()) {
                Flat flat = (Flat) iterator.next();
                sum += flat.getLivingSpace();
            }
            System.out.println("Cреднее значение поля livingSpace равно " + sum / flats.size());
        } else System.out.println("Ошибка. Коллекция пуста.");
    }

    public void max_by_house() {
        if (flats.size() > 0) {
            Iterator iterator = flats.iterator();
            House maxHouse = flats.get(0).getHouse();
            int index = 0;
            while (iterator.hasNext()) {
                Flat flat = (Flat) iterator.next();
                if (flat.getHouse().compareTo(maxHouse) > 0) {
                    index = flats.indexOf(flat);
                    maxHouse = flat.getHouse();
                }
            }
            System.out.println("Описание элемента с максимальным значением поля 'house':\n" + flats.get(index).NiceToString());
        } else System.out.println("Ошибка. Коллекция пуста.");
    }

    public void filter_less_than_view(View view) {
        if (flats.size() > 0) {
            Iterator iterator = flats.iterator();
            String res = "";
            while (iterator.hasNext()) {
                Flat flat = (Flat) iterator.next();
                if (flat.getView().compareTo(view) < 0)
                    res += flat.NiceToString() + "\n";
            }
            if (res == "") System.out.println("Не найдено элементов со значением поля view меньше заданного.");
        } else System.out.println("Ошибка. Коллекция пуста.");
    }

    public String MapToString(Map map) {
        String res = "";
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Object value = map.get(key);
            res += key + ": " + value + "\n";
        }
        return res;

    }

}

