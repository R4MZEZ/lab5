package content;

import java.util.Arrays;

public enum Transport {
    FEW,
    LITTLE,
    NORMAL;

    public static void TransportToString(){
        System.out.println("Список возможных значений 'transport': " + Arrays.toString(Transport.values()));
    }
}