package Main;

import content.Building;
import content.Flat;
import content.House;
import content.View;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {
    LinkedHashMap<String, String> manual = new LinkedHashMap<>();

    public CollectionManager() {
        manual.put("help","Вывести справку по доступным командам");
        manual.put("info", "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        manual.put("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        manual.put("add {element}","Добавить новый элемент в коллекцию");
        manual.put("update id {element}", "Обновить значение элемента коллекции, id которого равен заданному");
        manual.put("remove_by_id id","Удалить элемент коллекции по его id");
        manual.put("clear", "Очистить коллекцию");
        manual.put("save","Сохранить коллекцию в файл");
        manual.put("execute_script file_name","Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        manual.put("exit","Завершить программу (без сохранения в файл)");
        manual.put("remove_at index", "Удалить элемент, находящийся в заданной позиции коллекции (index)");
        manual.put("remove_last","Удалить последний элемент из коллекции");
        manual.put("shuffle","Перемешать элементы коллекции в случайном порядке");
        manual.put("average_of_living_space","Вывести среднее значение поля livingSpace для всех элементов коллекции");
        manual.put("max_by_house","Вывести любой объект из коллекции, значение поля house которого является максимальным");
        manual.put("filter_less_than_view view", "Вывести элементы, значение поля view которых меньше заданного");

    }

    public void help() {
        System.out.println(MapToString(manual));
    }

    public void info(Collection c) {
        String info = "Тип - " + c.getClass().getName() +
                "\nДата инициализации - " + Building.getInitDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")) +
                "\nКоличество элементов - " + c.size();
        System.out.println(info);
    }

    public void show(Collection c) {
        for (Object obj : c)
            System.out.println(c.toString());
    }

//    public void add(){
//
//    }

//    public void update(long id){
//
//    }

    public void remove_by_id(LinkedList<Flat> list, long id){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                System.out.println("Элемент с id = '" + id + "' успешно удалён." );
                return;
            }
        }
        System.out.println("Элемента с id = '" + id + "' не найдено.");
    }

    public void clear(Collection c){
        c.clear();
        System.out.println("Коллекция успешно очищена.");
    }

    public void save() throws IOException, JAXBException {
        FileWriter writer = new FileWriter("C:\\Users\\User\\IdeaProjects\\labb5\\src\\inputData\\output.xml");
        JAXBContext context = JAXBContext.newInstance(Flat.class, Building.class, House.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(new Building(), writer);
    }

//    public void execute_script(){
//
//    }

    public void remove_at(LinkedList list, int index) {
        try {
            list.remove(index);
            System.out.println("Элемент коллекции успешно удалён.");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Ошибка. Элемента с таким индексом не существует.");
        }
    }

    public void remove_last(LinkedList list) {
        try {
            list.removeLast();
            System.out.println("Последний элемент успешно удалён.");
        } catch (NoSuchElementException ex) {
            System.out.println("Ошибка. Невозможно удалить последний элемент коллекции, т.к. она пуста.");
        }

    }

    public void shuffle(LinkedList list){
        Collections.shuffle(list);
        System.out.println("Элементы коллекции успешно перемешаны");
    }

    public void average_of_living_space(LinkedList<Flat> list){
        if (list.size() > 0){
            Iterator iterator = list.iterator();
            long sum = 0;
            while (iterator.hasNext()){
                Flat flat = (Flat)iterator.next();
                sum += flat.getLivingSpace();
            }
            System.out.println("Cреднее значение поля livingSpace равно " + sum / list.size());
        }
        else System.out.println("Ошибка. Коллекция пуста.");
    }

    public void max_by_house(LinkedList<Flat> list){
        if (list.size() > 0){
            Iterator iterator = list.iterator();
            House maxHouse = list.get(0).getHouse();
            int index = 0;
            while (iterator.hasNext()){
                Flat flat = (Flat)iterator.next();
                if (flat.getHouse().compareTo(maxHouse) > 0){
                    index = list.indexOf(flat);
                    maxHouse = flat.getHouse();
                }
            }
            System.out.println("Описание элемента с максимальным значением поля 'house':\n" + list.get(index).NiceToString());
        }
        else System.out.println("Ошибка. Коллекция пуста.");
    }

    public void filter_less_than_view(LinkedList<Flat> list, View view) {
        if (list.size() > 0) {
            Iterator iterator = list.iterator();
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

