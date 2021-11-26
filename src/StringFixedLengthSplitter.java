import java.util.ArrayList;
import java.util.List;

public class StringFixedLengthSplitter implements ISplitterByWidth {
    private StringMoreSplitter splitter;

    public StringFixedLengthSplitter() {
        this.splitter = new StringMoreSplitter();
    }

    @Override
    public List<String> splitByWidth(List<String> list, int width) {
        return splitFixedLength(splitter.split(list), width);
    }

    private static List<String> splitFixedLength(List<String> list, int width) {
        List<String> result = new ArrayList<>();
        for (String str : list) {
            int beginIndex = 0;
            int endIndex = width;
            while (!ISplitterByWidth.isOver(str.length(), beginIndex)) {
                if (ISplitterByWidth.isOver(str.length(), endIndex)) {
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
}
