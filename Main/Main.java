package Main;

import javax.xml.bind.JAXBException;
import java.io.*;

public class Main {
    /**
     * @author Kazachenko Roman 312515
     */
    public static void main(String[] args){
        try {
            Commander commander = new Commander(args[0]);
            commander.interactiveMod(System.in);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Ошибка. Отсутствует аргумент входного файла.");
        }
        catch (JAXBException e){
            System.out.println("Ошибка при десериализации документа. Проверьте правильность разметки.");
        }
        catch (FileNotFoundException e){
            System.out.println("Ошибка! Файл с входными данными не найден, проверьте путь и права доступа к файлу.");
        }
    }

//    private static class XMLHandler extends DefaultHandler {
//        @Override
//        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//            if (qName.equals("house")) {
//                String name = attributes.getValue("name");
//                Integer year = Integer.valueOf(attributes.getValue("year"));
//                int numberOfFlatsOnFloor = Integer.parseInt(attributes.getValue("numberOfFlatsOnFloor"));
//                House house = new House(name, year, numberOfFlatsOnFloor);
//                houses.add(house);
//                isNewHouse = true;
//            }
//            if (qName.equals("flats")) {
//                String name = attributes.getValue("name");
//                Float x = Float.valueOf(attributes.getValue("coordinates").split(",")[0]);
//                int y = Integer.parseInt(attributes.getValue("coordinates").split(",")[1]);
//                Long area = Long.valueOf(attributes.getValue("area"));
//                Integer numberOfRooms = Integer.valueOf(attributes.getValue("numberOfRooms"));
//                long livingSpace = Long.valueOf(attributes.getValue("livingSpace"));
//                Transport transport = Transport.valueOf(attributes.getValue("transport"));
//                View view = View.valueOf(attributes.getValue("view"));
//                if (isNewHouse) {
//                    numOfHouse++;
//                    isNewHouse = false;
//                }
//                flats.add(new Flat(name, new Coordinates(x, y), area, numberOfRooms, livingSpace, view, transport, houses.get(numOfHouse)));
//            }
//        }
//    }
}
