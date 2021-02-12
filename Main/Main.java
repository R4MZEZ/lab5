package Main;

import java.io.*;
import java.util.LinkedList;
import classes.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {
    static LinkedList<Flat> flats = new LinkedList<>();

    static LinkedList<House> houses = new LinkedList<>();
    static boolean isNewHouse = false;
    static int numOfHouse = -1;

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream("C:\\Users\\User\\IdeaProjects\\labb5\\src\\inputData\\input.xml"));
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(stream, handler);

        for (Flat flat : flats)
            System.out.println(flat.toString());
    }
    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("house")) {
                String name = attributes.getValue("name");
                Integer year = Integer.valueOf(attributes.getValue("year"));
                int numberOfFlatsOnFloor = Integer.parseInt(attributes.getValue("numberOfFlatsOnFloor"));
                House house = new House(name, year, numberOfFlatsOnFloor);
                houses.add(house);
                isNewHouse = true;
            }
            if (qName.equals("flat")) {
                String name = attributes.getValue("name");
                Float x = Float.valueOf(attributes.getValue("coordinates").split(",")[0]);
                int y = Integer.parseInt(attributes.getValue("coordinates").split(",")[1]);
                Long area = Long.valueOf(attributes.getValue("area"));
                Integer numberOfRooms = Integer.valueOf(attributes.getValue("numberOfRooms"));
                long livingSpace = Long.valueOf(attributes.getValue("livingSpace"));
                Transport transport = Transport.valueOf(attributes.getValue("transport"));
                View view = View.valueOf(attributes.getValue("view"));
                if (isNewHouse) {
                    numOfHouse++;
                    isNewHouse = false;
                }
                flats.add(new Flat(name, new Coordinates(x,y), area, numberOfRooms, livingSpace, view, transport, houses.get(numOfHouse)));
            }
        }
    }
}
