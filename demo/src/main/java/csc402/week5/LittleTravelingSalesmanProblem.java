package csc402.week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LittleTravelingSalesmanProblem {
    public static void main(String[] args) {
        int[][] distanceMatrix = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        List<Integer> cities = Arrays.asList(0, 1, 2, 3);
        List<Integer> bestRoute = new ArrayList<>();
        int[] minDistance = {Integer.MAX_VALUE};

        permute(cities, 0, distanceMatrix, bestRoute, minDistance);

        System.out.println("Shortest route: " + bestRoute);
        System.out.println("Minimum distance: " + minDistance[0]);
    }

    public static void permute(List<Integer> cities, int start, int[][] distanceMatrix, List<Integer> bestRoute, int[] minDistance) {
        if (start == cities.size() - 1) {
            int currentDistance = calculateTotalDistance(cities, distanceMatrix);
            if (currentDistance < minDistance[0]) {
                minDistance[0] = currentDistance;
                bestRoute.clear();
                bestRoute.addAll(cities);
            }
        } else {
            for (int i = start; i < cities.size(); i++) {
                swap(cities, start, i);
                permute(cities, start + 1, distanceMatrix, bestRoute, minDistance);
                swap(cities, start, i);
            }
        }
    }

    public static void swap(List<Integer> cities, int i, int j) {
        int temp = cities.get(i);
        cities.set(i, cities.get(j));
        cities.set(j, temp);
    }

    public static int calculateTotalDistance(List<Integer> cities, int[][] distanceMatrix) {
        int totalDistance = 0;
        for (int i = 0; i < cities.size() - 1; i++) {
            totalDistance += distanceMatrix[cities.get(i)][cities.get(i + 1)];
        }
        totalDistance += distanceMatrix[cities.get(cities.size() - 1)][cities.get(0)];
        return totalDistance;
    }
}
