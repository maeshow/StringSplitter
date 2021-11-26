import java.util.List;

public class StringJaHyphenationSplitter implements ISplitterByWidth {

    private ISplitterByWidth splitterByWidth;
    private static int BEGINNING_OF_LINE = 0;

    public StringJaHyphenationSplitter() {
        this.splitterByWidth = new StringFixedLengthSplitter();
    }

    @Override
    public List<String> splitByWidth(List<String> list, int width) {
        return fixedIllegalChar(splitterByWidth.splitByWidth(list, width));
    }

    private static List<String> fixedIllegalChar(List<String> list) {
        int lineNumber = 0;
        for (String str : list) {
            if (isIllegalChar(str)) {
                alignLine(list, lineNumber);
            }
            lineNumber++;
        }
        return list;
    }

    private static void alignLine(List<String> list, int lineNumber) {
        while (isSafeBounds(list, lineNumber)) {
            addLineEnd(list, lineNumber - 1, deleteLineHead(list, lineNumber));
            if (isLineBreak(list.get(lineNumber))) {
                break;
            }
            lineNumber++;
        }
    }

    private static char deleteLineHead(List<String> list, int lineNumber) {
        String str = list.get(lineNumber);
        StringBuilder builder = new StringBuilder(str);
        char result = builder.charAt(BEGINNING_OF_LINE);
        builder.deleteCharAt(BEGINNING_OF_LINE);
        list.set(lineNumber, builder.toString());
        return result;
    }

    private static void addLineEnd(List<String> list, int lineNumber, char addChar) {
        if (isSafeBounds(list, lineNumber)) {
            StringBuilder builder = new StringBuilder(list.get(lineNumber));
            builder.append(addChar);
            list.set(lineNumber, builder.toString());
        }
    }

    private static boolean isLineBreak(String str) {
        return str.indexOf(PERIOD) == str.length() - 1;
    }

    private static boolean isSafeBounds(List<String> list, int index) {
        return 0 <= index && index < list.size();
    }

    private static boolean isIllegalChar(String str) {
        return isPeriodOnHead(str) || isPunctuationOnHead(str);
    }

    private static boolean isPeriodOnHead(String str) {
        return str.indexOf(PERIOD) == BEGINNING_OF_LINE;
    }

    private static boolean isPunctuationOnHead(String str) {
        return str.indexOf(PUNCTUATION) == BEGINNING_OF_LINE;
    }
}
