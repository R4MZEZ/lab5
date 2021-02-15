package Main;

import content.Coordinates;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CoordinatesAdapter extends XmlAdapter<String, Coordinates> {
    public Coordinates unmarshal(String v) throws Exception {
        return new Coordinates(Float.parseFloat(v.split(";")[0]), Long.parseLong(v.split(";")[1]));
    }
    public String marshal(Coordinates c) throws Exception {
        return c.getStringValue();
    }
}