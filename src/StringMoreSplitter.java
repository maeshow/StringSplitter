import java.util.ArrayList;
import java.util.List;

public class StringMoreSplitter {
    private static String EMPTY = "";
    private static String PERIOD = "。";
    private static String LINE_BREAK = "¥n";

    public static void main(String[] args) {
        List<String> lines = splitWithLineBreakCodeAndPeriod("１行目。２行目。¥n３行目。４行目。¥n¥n５行目。");
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static List<String> splitWithLineBreakCodeAndPeriod(String str) {
        return splitWithLineBreak(splitWithPeriod(str));
    }

    private static List<String> splitWithPeriod(String str) {
        List<String> result = new ArrayList<>();
        int beginIndex = 0;
        while (true) {
            int endIndex = str.indexOf(PERIOD, beginIndex);
            if (!hasString(endIndex)) {
                result.add(str.substring(beginIndex));
                break;
            }
            endIndex += 1;
            result.add(str.substring(beginIndex, endIndex));
            beginIndex = endIndex;
        }
        return result;
    }

    private static List<String> splitWithLineBreak(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String str : list) {
            int lineBreakIndex = str.indexOf(LINE_BREAK);
            if (!hasString(lineBreakIndex)) {
                result.add(str);
                continue;
            }
            if (isIgnoreLineBreak(lineBreakIndex)) {
                str = str.replaceFirst(LINE_BREAK, EMPTY);
            }
            for (String s : str.split(LINE_BREAK)) {
                result.add(s);
            }
        }
        return result;
    }

    private static boolean hasString(int index) {
        return index != -1;
    }

    private static boolean isIgnoreLineBreak(int index) {
        return index == 0;
    }
}
