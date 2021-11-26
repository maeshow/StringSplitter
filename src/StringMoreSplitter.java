import java.util.ArrayList;
import java.util.List;

public class StringMoreSplitter implements ISplitter {
    private StringSplitter splitter;

    public StringMoreSplitter() {
        this.splitter = new StringSplitter();
    }

    @Override
    public List<String> split(List<String> list) {
        return splitter.split(splitWithPeriod(list));
    }

    private static List<String> splitWithPeriod(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String str : list) {
            int beginIndex = 0;
            int endIndex = str.indexOf(PERIOD, beginIndex);
            while (ISplitter.hasString(endIndex)) {
                endIndex++;
                result.add(str.substring(beginIndex, endIndex));
                beginIndex = endIndex;
                endIndex = str.indexOf(PERIOD, beginIndex);
            }
            result.add(str.substring(beginIndex));
        }
        return result;
    }
}
