package csc402.week5;

public class QuadraticTime {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        for (int i : array) {
            for (int j : array) {
                System.out.println(i + " " + j); // Nested loops
            }
        }
    }
}
