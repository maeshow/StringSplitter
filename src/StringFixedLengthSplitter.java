import java.util.ArrayList;
import java.util.List;

public class StringFixedLengthSplitter {
    private static String EMPTY = "";
    private static String PERIOD = "。";
    private static String LINE_BREAK = "¥n";

    public static void main(String[] args) {
        List<String> lines = splitFixedLengthWithLineBreakCodeAndPeriod("このプログラムは、文字列を指定された幅で改行するサンプルプログラムです。", 6);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static List<String> splitFixedLengthWithLineBreakCodeAndPeriod(String str, int width) {
        return splitFixedLength(splitWithLineBreak(splitWithPeriod(str)), width);
    }

    private static List<String> splitFixedLength(List<String> list, int width) {
        List<String> result = new ArrayList<>();
        for (String str : list) {
            int beginIndex = 0;
            int endIndex = width;
            while (!isOver(str.length(), beginIndex)) {
                if (isOver(str.length(), endIndex)) {
                    result.add(str.substring(beginIndex));
                    break;
                }
                result.add(str.substring(beginIndex, endIndex));
                beginIndex += width;
                endIndex += width;
            }
        }
        return result;
    }

    private static boolean isOver(int strLength, int index) {
        return strLength < index;
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
