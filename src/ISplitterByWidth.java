import java.util.List;

public interface ISplitterByWidth {
    public static String EMPTY = "";
    public static String PERIOD = "。";
    public static String PUNCTUATION = "、";

    public static boolean isOver(int a, int b) {
        return a < b;
    }

    public List<String> splitByWidth(List<String> list, int width);
}
