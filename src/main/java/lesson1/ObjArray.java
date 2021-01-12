package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class ObjArray<T> {

    private T[] data;

    public ObjArray(T... data){
        this.data = data;
    }

    public void exchange(int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public ArrayList<T> toArrayList(T[] someArray){
        return new ArrayList<T>(Arrays.asList(someArray));

    }


}
