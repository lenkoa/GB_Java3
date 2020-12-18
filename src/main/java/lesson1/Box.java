package lesson1;

import java.util.ArrayList;

public class Box<F extends Fruit> {
    private ArrayList<F> fruits;

    public Box(ArrayList<F> fruits) {
        this.fruits = fruits;
    }

    public void add (F fruit){
        fruits.add(fruit);
    }

    public void remove (F fruit){
        fruits.remove(fruit);
    }

    public F get(int index){
        return fruits.get(index);
    }

    public float getWeight(Box<F> box){
        float sum = 0.0f;
        for (int i = 0; i < box.fruits.size(); i++) {
            F currentFruit = box.fruits.get(i);
            sum += currentFruit.getWeight();
        }
        return sum;
    }

    public float getWeight(){
        return getWeight(this);
    }

    public boolean compare(Box boxToCompare){
        return Math.abs(getWeight() - boxToCompare.getWeight()) <= Float.MIN_VALUE;
    }

    public void moveFruitsToBox(Box<F> sourceBox){
        for(F fruit : sourceBox.fruits){
            add(fruit);
            sourceBox.remove(fruit);
        }
    }
}
