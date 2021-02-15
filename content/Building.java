package content;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

@XmlType(name = "building")
@XmlRootElement
public class Building {

    @XmlElementWrapper(name = "floor")
    public static LinkedList<Flat> flats = new LinkedList<>();
    private static LocalDateTime initDate = LocalDateTime.now();

    public static LocalDateTime getInitDate() {
        return initDate;
    }

    public LinkedList<Flat> getFlats() {
        return flats;
    }




}
