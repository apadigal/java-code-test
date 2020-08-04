import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class CodeTest {
    private static String[] ARGUMENTS;
    public static void main(String[] args) {
        ARGUMENTS = args;
        System.out.println(String.format("reverseArray : %s", objectToString(reverseArray(new String[]{}))));
        System.out.println(String.format("reverseArray : %s", objectToString(reverseArray(new String[]{"a", "b", "c", "d"}))));
        System.out.println(String.format("uppercaseArray : %s", objectToString(uppercaseArray(new String[]{"a", "b", "c", "d"}))));
        System.out.println(String.format("findWordCount : %s", findWordCount("Anand test this test with this test", "test")));
        System.out.println(String.format("getCountOfWords : %s", getCountOfWords("Anand t est this test with this test")));

        Function<Integer, Integer> composedFunc = composeU(x -> x /2, y -> y * y * y);
        System.out.println(String.format("Compose of f1(f2(10)) = (10 * 10 * 10)/2 = %d", composedFunc.apply(10)));

        System.out.println(composedFunc.apply(10));
        writeContentsToConsole();
        handleInvalidArgument();
        puzzle(new String[]{"1", "2", "23", "7", "7", "8"});
    }

    public static String[] reverseArray(String[] input) {
        validateInput((Object) input);
        List<String> list = asList(input);
        Collections.reverse(list);

        return list.toArray(new String[0]);
    }

    public static String[] uppercaseArray(String[] input) {
        validateInput((Object) input);
        List<String> list = asList(input);
        list = list.parallelStream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return list.toArray(new String[0]);
    }

    public static Map<String, Integer> getCountOfWords(String text) {
        validateInput(text);
        List<String> words =  Arrays.asList(text.split(" "));
        return words.stream()
                .collect(groupingBy(Function.identity(), summingInt(e -> 1)));
    }

    public static int findWordCount(String text, String wordToFind) {
        validateInput(text, wordToFind);
        List<String> words =  Arrays.asList(text.split(" "));
        return Math.toIntExact(words.parallelStream()
                .filter( s -> s.equals(wordToFind))
                .count());
        // add code here
    }

    public static Function<Integer,Integer> composeU(Function<Integer,Integer> f1, Function<Integer,Integer> f2){
        validateInput(f1, f2);
        return f1.compose(f2);
    }

    public static void writeContentsToConsole() {
        String fileName = "test.properties";
        Properties properties = new Properties();
        try(InputStream inputStream = CodeTest.class.getResourceAsStream(fileName)){
            properties.load(inputStream);
            properties.entrySet().forEach(System.out::println);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void handleInvalidArgument() {
        // add code here
        validateInput((Object)ARGUMENTS);
        List<String> validArguments=Arrays.asList("check", "value", "class", "test", "now", "pension");
        Arrays.stream(ARGUMENTS)
                .filter(arg -> !validArguments.contains(arg))
                .findAny()
                .ifPresent(ch -> {
                    throw new IllegalArgumentException("Only following command line arguments allowed : "+ objectToString(validArguments));
                });
    }

    public static void puzzle(String[] puzzleToTest) {
        // add code here
        validateInput((Object) puzzleToTest);
        String currentValue = "";
        final String SNAP = ",'Snap'";
        for (String s : puzzleToTest) {
            if (currentValue.equals(s)) {
                System.out.print(s + SNAP);
                break;
            }
            System.out.print(s + ",");
            currentValue = s;
        }
    }

    private static  void validateInput(Object... inputs){
        Arrays.stream(inputs)
                .forEach(input -> requireNonNull(input, "Input value must be non null."));
    }

    private static String objectToString(Object obj) {
        if (Objects.nonNull(obj) && obj.getClass().isArray()) {
            return asList((Object[]) obj).toString();
        } else {
            return String.valueOf(obj);
        }
    }}