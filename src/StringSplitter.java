import java.util.ArrayList;
import java.util.List;

public class StringSplitter implements ISplitter {
    private static int BEGINNING_OF_LINE = 0;

    @Override
    public List<String> split(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String str : list) {
            int lineBreakIndex = str.indexOf(LINE_BREAK);
            if (!ISplitter.hasString(lineBreakIndex)) {
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

    private static boolean isIgnoreLineBreak(int index) {
        return index == BEGINNING_OF_LINE;
    }
}
