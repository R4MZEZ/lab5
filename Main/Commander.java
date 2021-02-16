package Main;

import content.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Commander {

    private final CollectionManager manager;
    private String fullUserCommand = "";

    BufferedInputStream stream;
    JAXBContext context = JAXBContext.newInstance(Flat.class, CollectionManager.class, House.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();

    public Commander(String filePath) throws JAXBException, FileNotFoundException {
        stream = new BufferedInputStream(new FileInputStream(filePath));
        this.manager = (CollectionManager) unmarshaller.unmarshal(stream);
    }

    public void interactiveMod() {
        System.out.println("***\tНачало работы. Для просмотра доступных команд напишите 'help'\t***");
        try (Scanner commandReader = new Scanner(System.in)) {
            while (!fullUserCommand.equals("exit")) {
                fullUserCommand = commandReader.nextLine();
                String[] command = fullUserCommand.trim().split(" ", 2);
                try {
                    switch (command[0]) {
                        case "":
                            break;
                        case "help":
                            manager.help();
                            break;
                        case "info":
                            manager.info();
                            break;
                        case "show":
                            manager.show();
                            break;
                        case "add":
                            manager.add(commandReader);
                            System.out.println("===================================\nЭлемент успешно добавлен.");
                            break;
                        case "update":
                            manager.update(command[1], commandReader);
                            break;
                        case "remove_by_id":
                            manager.remove_by_id(command[1]);
                            break;
                        case "clear":
                            manager.clear();
                            break;
                        case "save":
                            try {
                                manager.save(manager);
                            } catch (FileNotFoundException e) {
                                System.out.println("Ошибка. Файл не найден, проверьте пусть и доступ к файлу.");
                            } catch (IOException e) {
                                System.out.println("Ошибка сохранения.");
                            } catch (MarshalException e) {
                                System.out.println("Ошибка сериализации коллекции в XML.");
                            } catch (JAXBException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "remove_at":
                            manager.remove_at(command[1]);
                            break;
                        case "remove_last":
                            manager.remove_last();
                            System.out.println("Последний элемент успешно удалён.");
                            break;
                        case "shuffle":
                            manager.shuffle();
                            break;
                        case "average_of_living_space":
                            manager.average_of_living_space();
                            break;
                        case "max_by_house":
                            manager.max_by_house();
                            break;
                        case "filter_less_than_view":
                            manager.filter_less_than_view(command[1]);
                            break;
                        default:
                            if (!fullUserCommand.equals("exit")) {
                                System.out.println("Неопознанная команда. Наберите 'help' для справки.");
                            } else System.out.println("***\tВыход из интерактивного режима\t***");
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("Отсутствует аргумент.");
                }
            }
        }
    }

}
