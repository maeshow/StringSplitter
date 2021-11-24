import java.util.ArrayList;
import java.util.List;

public class StringJaHyphenationSplitter {
    private static String EMPTY = "";
    private static String PERIOD = "。";
    private static String LINE_BREAK = "¥n";
    private static String PUNCTUATION = "、";

    public static void main(String[] args) {
        List<String> lines = splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(
                "このプログラムは、句読点を行頭禁則処理するサンプル。¥n" + "最後の行です", 8);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static List<String> splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(String str, int width) {
        return splitFixedLength(splitWithLineBreak(splitWithPeriod(str)), width);
    }

    private static List<String> splitFixedLength(List<String> list, int width) {
        List<String> result = new ArrayList<>();
        int i = 0;
        for (String str : list) {
            int beginIndex = 0;
            int endIndex = width;
            String resultStr = "";
            while (!isOver(str.length(), beginIndex)) {
                if (isOver(str.length(), endIndex)) {
                    resultStr = str.substring(beginIndex);
                    if (isIllegalChar(resultStr)) {
                        setPrevLine(result, i, resultStr);
                        if (isOver(resultStr.length(), beginIndex + 1)) {
                            break;
                        }
                        resultStr = str.substring(beginIndex + 1);
                    }
                    result.add(resultStr);
                    break;
                }
                resultStr = str.substring(beginIndex, endIndex);
                if (isIllegalChar(resultStr)) {
                    setPrevLine(result, i, resultStr);
                    beginIndex++;
                    endIndex++;
                    resultStr = alignLine(str, beginIndex, endIndex);
                }
                result.add(resultStr);
                beginIndex += width;
                endIndex += width;
                i++;
            }
        }
        return result;
    }

    private static boolean isOver(int a, int b) {
        return a < b;
    }

    private static void setPrevLine(List<String> list, int index, String currentStr) {
        if (isSafeBounds(list, index - 1)) {
            StringBuilder builder = new StringBuilder();
            builder.append(list.get(index - 1));
            if (isPeriodOnHead(currentStr)) {
                builder.append(PERIOD);
            }
            if (isPunctuationOnHead(currentStr)) {
                builder.append(PUNCTUATION);
            }
            list.set(index - 1, builder.toString());
        }
    }

    private static boolean isSafeBounds(List<String> list, int index) {
        return 0 <= index && index < list.size();
    }

    private static boolean isIllegalChar(String str) {
        return isPeriodOnHead(str) || isPunctuationOnHead(str);
    }

    private static boolean isPeriodOnHead(String str) {
        return str.indexOf(PERIOD) == 0;
    }

    private static boolean isPunctuationOnHead(String str) {
        return str.indexOf(PUNCTUATION) == 0;
    }

    private static String alignLine(String origin, int beginIndex, int endIndex) {
        if (!isOver(origin.length(), endIndex)) {
            return origin.substring(beginIndex, endIndex);
        }
        return origin.substring(beginIndex);
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
            endIndex++;
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
