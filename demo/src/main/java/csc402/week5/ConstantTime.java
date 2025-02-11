package csc402.week5;

import java.util.HashMap;

public class ConstantTime {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(array[0]); // Accessing an element in an array

        HashMap<String, String> map = new HashMap<>();
        map.put("Name", "Brian");
        map.put("Dog", "Fido");
        System.out.println(map.get("Name")); // Accessing a value in a HashMap
    }
}