package content;

import java.util.Arrays;

public enum View {
    YARD,
    BAD,
    NORMAL,
    GOOD,
    TERRIBLE;

    public static void ViewToString(){
        System.out.println("Список возможных значений 'view': " + Arrays.toString(View.values()));
    }
}