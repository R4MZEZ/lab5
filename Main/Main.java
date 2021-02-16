package Main;

import content.*;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, JAXBException {
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream(args[0]));
        CollectionManager manager = new CollectionManager();

        Commander commander = new Commander(manager);
        commander.interactiveMod();
        //        JAXBContext context = JAXBContext.newInstance(Flat.class, Building.class, House.class);
//        Unmarshaller unmarshaller = context.createUnmarshaller();
//
//        Building b = (Building) unmarshaller.unmarshal(stream);
//        System.out.println(b.getFlats().NiceToString());

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
