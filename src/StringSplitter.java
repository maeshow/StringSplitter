import java.util.Arrays;
import java.util.List;

public class StringSplitter {
    private static String LINE_BREAK = "¥n";

    public static void main(String[] args) {
        List<String> lines = splitWithLineBreakCode("１行目。¥n２行目。¥n３行目。¥n４行目。¥n¥n５行目¥n");
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static List<String> splitWithLineBreakCode(String str) {
        return Arrays.asList(str.split(LINE_BREAK));
    }
}
