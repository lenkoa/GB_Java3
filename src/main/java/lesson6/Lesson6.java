package lesson6;

import java.util.Arrays;

public class Lesson6 {

    public int[] arrayTail(int[] initialArray) {
        if (initialArray.length == 0) {
            throw new RuntimeException("Zero length array");
        }
        for (int i = initialArray.length - 1; i >= 0; i--) {
            if (initialArray[i] == 4) {
                int[] result = new int[initialArray.length - i - 1];
                System.arraycopy(initialArray, i + 1, result, 0, result.length);
                return result;
            }
        }
        throw new RuntimeException("No 4 in array");
    }

    public boolean testNumbers(int[] initialArray){
        Integer[] arr2 = Arrays.stream(initialArray).boxed().toArray(Integer[]::new);
        return Arrays.asList(arr2).contains(1) && Arrays.asList(arr2).contains(4);
    }
}
