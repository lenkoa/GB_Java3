package lesson6;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Lesson6Test {

    private Lesson6 lesson;

    @BeforeEach
    void startUp() {
        lesson = new Lesson6();
        System.out.println("тест начался");
    }

    @AfterEach
    void shutdown() {
        System.out.println("тест завершен");
    }

    @BeforeAll
    static void init() {
        System.out.println("ГЛАВНОЕ НАЧАЛО!");
    }

    @AfterAll
    static void shutdownAfterAll() {
        System.out.println("ГЛАВНЫЙ КОНЕЦ!");
    }

    @DisplayName("Test null")
    @Test
    public void test1() {
        Assertions.assertThrows(RuntimeException.class, ()-> lesson.arrayTail(null));
    }

    @DisplayName("Test zero length array")
    @Test
    public void test2() {
        Assertions.assertThrows(RuntimeException.class, ()-> lesson.arrayTail(new int[0]));
    }

    @DisplayName("Test no 4")
    @Test
    public void test3() {
        Assertions.assertThrows(RuntimeException.class, ()-> lesson.arrayTail(new int[]{1, 2, 3, 5}));
    }

    @DisplayName("Param test")
    @ParameterizedTest
    @MethodSource("data")
    public void paramTest(int[] testArray, int[] result){
        Assertions.assertArrayEquals(lesson.arrayTail(testArray), result);
    }

    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 4, 3, 4, 4, 5, 6, 7}, new int[]{5, 6, 7}),
                Arguments.arguments(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{5, 6, 7}),
                Arguments.arguments(new int[]{1, 2, 3, 4}, new int[0])
        );
    }

    @DisplayName("Boolean Param test")
    @ParameterizedTest
    @MethodSource("data2")
    public void paramTest2(int[] testArray, boolean result) {
        Assertions.assertEquals(lesson.testNumbers(testArray), result);
    }

    static Stream<Arguments> data2() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 1, 1}, false),
                Arguments.arguments(new int[]{4, 4, 4, 4}, false),
                Arguments.arguments(new int[]{1, 1, 4, 4}, true),
                Arguments.arguments(new int[]{1, 4, 1, 4}, true)
        );
    }
}