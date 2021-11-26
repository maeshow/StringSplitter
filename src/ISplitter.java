import java.util.List;

public interface ISplitter {
    public static String EMPTY = "";
    public static String PERIOD = "。";
    public static String LINE_BREAK = "¥n";
    public static int NOT_EXIST_RESULT = -1;

    public static boolean hasString(int index) {
        return index != NOT_EXIST_RESULT;
    }

    public List<String> split(List<String> list);
}
