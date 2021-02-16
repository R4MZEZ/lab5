package Main;

import content.Coordinates;
import content.Transport;
import content.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Commander {

    private CollectionManager manager;
    private String fullUserCommand = "";
    private String[] command;
    private String[] temp = new String[8];

    public Commander(CollectionManager manager) {
        this.manager = manager;
    }

    public void interactiveMod() throws IOException {
        System.out.println("***\tНачало работы. Для просмотра доступных команд напишите 'help'\t***");
        try (Scanner commandReader = new Scanner(System.in)) {
            while (!fullUserCommand.equals("exit")) {
                fullUserCommand = commandReader.nextLine();
                command = fullUserCommand.trim().split(" ", 2);
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
                            break;
                        case "clear":
                            manager.clear();
                            break;
                        case "update":
                            manager.update(command[1], commandReader);
                        default:
                            if (!fullUserCommand.equals("exit")) {
                            System.out.println("Неопознанная команда. Наберите 'help' для справки.");
                        }
                            else System.out.println("***\tВыход из интерактивного режима\t***");
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("Отсутствует аргумент.");
                }
            }
        }
    }

}
