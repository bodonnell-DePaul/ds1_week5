package csc402.week5; // Package declaration

import java.util.Arrays; // Importing the Arrays class for potential array operations

public class LogarithmicTime { // Declaration of the LogarithmicTime class
    public static void main(String[] args) { // Main method, the entry point of the program
        int[] array = new int[100]; // Initializing an array of 100 integers
        for (int i = 0; i < array.length; i++) { // Loop to populate the array
            array[i] = i + 1; // Assigning values from 1 to 100 to the array elements
        }
        int target = 44; // The target value to search for in the array
        int index = binarySearch(array, target); // Calling the binarySearch method to find the target
        if (index != -1) { // Checking if the target was found
            System.out.println("Element found at index: " + index); // Printing the index if found
        } else {
            System.out.println("Element not found"); // Printing a message if the target was not found
        }
    }

    // Method to perform binary search on the array
    public static int binarySearch(int[] array, int target) {
        int left = 0; // Initial left boundary of the search
        int right = array.length - 1; // Initial right boundary of the search
        while (left <= right) { // Loop until the search space is exhausted
            int mid = left + (right - left) / 2; // Calculating the middle index to avoid overflow
            if (array[mid] == target) { // If the middle element is the target
                return mid; // Return the index of the target
            }
            if (array[mid] < target) { // If the middle element is less than the target
                left = mid + 1; // Narrow the search to the right half
            } else {
                right = mid - 1; // Narrow the search to the left half
            }
        }
        return -1; // Return -1 if the target is not found
    }
}