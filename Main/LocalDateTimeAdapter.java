package Main;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String,LocalDateTime> {
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.of(Integer.parseInt(v.split(",")[0].split("\\.")[2]),
                                Integer.parseInt(v.split(",")[0].split("\\.")[1]),
                                Integer.parseInt(v.split(",")[0].split("\\.")[1]),
                                Integer.parseInt(v.split(",")[1].split(":")[0]),
                                Integer.parseInt(v.split(",")[1].split(":")[1]),
                                Integer.parseInt(v.split(",")[1].split(":")[2]));
    }
    public String marshal(LocalDateTime l) throws Exception {
        return l.format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss"));
    }
}