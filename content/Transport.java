package content;

import java.util.Arrays;

public enum Transport {
    FEW,
    LITTLE,
    NORMAL;

    public static void TransportToString(){
        System.out.println("Список возможных транспортов: " + Arrays.toString(Transport.values()));
    }
}