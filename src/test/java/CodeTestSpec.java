//import org.junit.Test;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;



/*
 *   Please code the tests in the format of reverseArray_returnsExpectedResult. This is an example of how we write our tests at Cardano.
 *
 *   Test 1-4 tests are easy as the function returns a result that can be asserted. Tests 5-7 are more difficult to assert as they are
 *   void, use your knowledge to write a meaningful test.
 *
 *   Feel free to use the internet to help you with you answers but make sure you understand the answer as we will ask you questions.
 */

public class CodeTestSpec {
    @Test
    public void reverseArray_returnsExpectedResult() {
        // arrange
        final String[] EXPECTED = {"x", "y", "z"};

        // act
        final String[] actual = CodeTest.reverseArray(new String[]{"z", "y", "x"});

        // assert
        assertArrayEquals(EXPECTED, actual);
        assertArrayEquals(new String[]{}, CodeTest.reverseArray(new String[]{}));

        // validate is NullPointerException throws with expected message.
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> CodeTest.reverseArray(null));
        assertEquals("Input value must be non null.", nullPointerException.getMessage());

    }

    @Test
    public void uppercaseArray_returnsExpectedResult() {
        // arrange
        final String[] EXPECTED = {"X", "Y", "Z"};

        // act
        final String[] actual = CodeTest.uppercaseArray(new String[]{"x", "y", "z"});

        // assert
        assertArrayEquals(EXPECTED, actual);
        assertArrayEquals(new String[]{}, CodeTest.uppercaseArray(new String[]{}));

        // validate is NullPointerException throws with expected message.
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> CodeTest.uppercaseArray(null));
        assertEquals("Input value must be non null.", nullPointerException.getMessage());

    }

    @Test
    public void findWordCount_returnsExpectedResult() {
        // arrange
        final int EXPECTED = 3;

        // act
        final int actual = CodeTest.findWordCount("test test a test", "test");

        // assert
        assertEquals(EXPECTED, actual);

        // validate is NullPointerException throws with expected message.
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> CodeTest.findWordCount(null, null));
        assertEquals("Input value must be non null.", nullPointerException.getMessage());

        nullPointerException = assertThrows(NullPointerException.class, () -> CodeTest.findWordCount(null, "null"));
        assertEquals("Input value must be non null.", nullPointerException.getMessage());

        nullPointerException = assertThrows(NullPointerException.class, () -> CodeTest.findWordCount("null", null));
        assertEquals("Input value must be non null.", nullPointerException.getMessage());
    }

    @Test
    public void getCountOfWords_returnsExpectedResult() {
        // arrange
        final Map<String, Integer> EXPECTED = new HashMap<>();
        EXPECTED.put("test", 3);
        EXPECTED.put("a", 1);

        // act
        final Map<String, Integer>
                actual = CodeTest.getCountOfWords("test test a test");

        // assert
        assertThat(EXPECTED, is(actual));

        // validate is NullPointerException throws with expected message.
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> CodeTest.getCountOfWords(null));
        assertEquals("Input value must be non null.", nullPointerException.getMessage());
    }

    @Test
    public void composeU_returnsExpectedResult() {
        // arrange
        final int EXPECTED = 42;

        //act
        Function<Integer, Integer> halfNumber = x -> x / 2;
        Function<Integer, Integer> customMath = y -> (y + 8) * y;
        Function<Integer, Integer> composedFunctionTest = CodeTest.composeU(halfNumber, customMath);

        // assert
        assertThat(composedFunctionTest.apply(6), equalTo(EXPECTED));

        // validate is NullPointerException throws with expected message.
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> CodeTest.composeU(null, null));
        assertEquals("Input value must be non null.", nullPointerException.getMessage());
    }

    @Test
    public void writeContentsToConsole_returnsExpectedResult() {
        // arrange
        final String EXPECTED = "prop2=value2\n" +
                "prop1=value1\n" +
                "prop4=value4\n" +
                "prop3=value3\n";

        //act
        PrintStream oldStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        CodeTest.writeContentsToConsole();
        String value = outputStream.toString();

        //assert
        assertEquals(EXPECTED, value);
        System.setOut(oldStream);
    }

    @Test
    public void handleInvalidArgument_returnsExpectedResult() {

        //assert?? just call the main method and check it's not throwing exception.
        CodeTest.main(new String[]{"value", "test"});

        // validate is IllegalArgumentException throws with expected message.
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> CodeTest.main(new String[]{"value1", "test"}));
        assertEquals("Only following command line arguments allowed : [check, value, class, test, now, pension]", illegalArgumentException.getMessage());

        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> CodeTest.main(null));
        assertEquals("Input value must be non null.", nullPointerException.getMessage());

    }

    @Test
    public void puzzle_returnsExpectedResult() {
        // arrange
        final String EXPECTED = "1,2,4,4,'Snap'";

        //act
        PrintStream oldStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        CodeTest.puzzle(new String[]{"1", "2", "4", "4", "8", "9"});
        String value = outputStream.toString();

        //assert
        assertEquals(EXPECTED, value);
    }
}
