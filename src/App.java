import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        task4();
    }

    private static void task1() {
        List<String> lines = splitWithLineBreakCode("１行目。¥n２行目。¥n３行目。¥n４行目。¥n¥n５行目¥n");
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static void task2() {
        List<String> lines = splitWithLineBreakCodeAndPeriod("１行目。２行目。¥n３行目。４行目。¥n¥n５行目。");
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static void task3() {
        List<String> lines = splitFixedLengthWithLineBreakCodeAndPeriod("このプログラムは、文字列を指定された幅で改行するサンプルプログラムです。", 6);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static void task4() {
        List<String> lines = splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(
                "このプログラムは、句読点を行頭禁則処理するサンプル。¥n" + "最後の行です", 8);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static List<String> splitWithLineBreakCode(String str) {
        List<String> list = new ArrayList<>();
        list.add(str);
        StringSplitter splitter = new StringSplitter();
        return splitter.split(list);
    }

    private static List<String> splitWithLineBreakCodeAndPeriod(String str) {
        List<String> list = new ArrayList<>();
        list.add(str);
        StringMoreSplitter splitter = new StringMoreSplitter();
        return splitter.split(list);
    }

    private static List<String> splitFixedLengthWithLineBreakCodeAndPeriod(String str, int width) {
        List<String> list = new ArrayList<>();
        list.add(str);
        StringFixedLengthSplitter splitter = new StringFixedLengthSplitter();
        return splitter.splitByWidth(list, width);
    }

    private static List<String> splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(String str, int width) {
        List<String> list = new ArrayList<>();
        list.add(str);
        StringJaHyphenationSplitter splitter = new StringJaHyphenationSplitter();
        return splitter.splitByWidth(list, width);
    }
}
