package csc402.week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigTravelingSalesmanProblem {
    public static void main(String[] args) {
        DistanceMatrix distanceMatrix = new DistanceMatrix();

        // Populate the distance matrix with the provided data
        distanceMatrix.addDistance("Chicago, IL", "Los Angeles, CA", 2015);
        distanceMatrix.addDistance("Chicago, IL", "New York, NY", 790);
        distanceMatrix.addDistance("Chicago, IL", "Phoenix, AZ", 1750);
        distanceMatrix.addDistance("Chicago, IL", "Boston, MA", 980);
        distanceMatrix.addDistance("Chicago, IL", "Miami, FL", 1380);
        distanceMatrix.addDistance("Chicago, IL", "Philadelphia, PA", 760);
        distanceMatrix.addDistance("Chicago, IL", "Seattle, WA", 2060);
        distanceMatrix.addDistance("Chicago, IL", "Atlanta, GA", 715);
        distanceMatrix.addDistance("Chicago, IL", "Denver, CO", 1000);

        distanceMatrix.addDistance("Los Angeles, CA", "New York, NY", 2780);
        distanceMatrix.addDistance("Los Angeles, CA", "Phoenix, AZ", 370);
        distanceMatrix.addDistance("Los Angeles, CA", "Boston, MA", 2990);
        distanceMatrix.addDistance("Los Angeles, CA", "Miami, FL", 2730);
        distanceMatrix.addDistance("Los Angeles, CA", "Philadelphia, PA", 2720);
        distanceMatrix.addDistance("Los Angeles, CA", "Seattle, WA", 1130);
        distanceMatrix.addDistance("Los Angeles, CA", "Atlanta, GA", 2180);
        distanceMatrix.addDistance("Los Angeles, CA", "Denver, CO", 1010);

        distanceMatrix.addDistance("New York, NY", "Phoenix, AZ", 2460);
        distanceMatrix.addDistance("New York, NY", "Boston, MA", 215);
        distanceMatrix.addDistance("New York, NY", "Miami, FL", 1280);
        distanceMatrix.addDistance("New York, NY", "Philadelphia, PA", 95);
        distanceMatrix.addDistance("New York, NY", "Seattle, WA", 2860);
        distanceMatrix.addDistance("New York, NY", "Atlanta, GA", 860);
        distanceMatrix.addDistance("New York, NY", "Denver, CO", 1800);

        distanceMatrix.addDistance("Phoenix, AZ", "Boston, MA", 2890);
        distanceMatrix.addDistance("Phoenix, AZ", "Miami, FL", 2330);
        distanceMatrix.addDistance("Phoenix, AZ", "Philadelphia, PA", 2300);
        distanceMatrix.addDistance("Phoenix, AZ", "Seattle, WA", 1420);
        distanceMatrix.addDistance("Phoenix, AZ", "Atlanta, GA", 1845);
        distanceMatrix.addDistance("Phoenix, AZ", "Denver, CO", 850);

        distanceMatrix.addDistance("Boston, MA", "Miami, FL", 1490);
        distanceMatrix.addDistance("Boston, MA", "Philadelphia, PA", 310);
        distanceMatrix.addDistance("Boston, MA", "Seattle, WA", 3070);
        distanceMatrix.addDistance("Boston, MA", "Atlanta, GA", 1075);
        distanceMatrix.addDistance("Boston, MA", "Denver, CO", 1950);

        distanceMatrix.addDistance("Miami, FL", "Philadelphia, PA", 1220);
        distanceMatrix.addDistance("Miami, FL", "Seattle, WA", 3280);
        distanceMatrix.addDistance("Miami, FL", "Atlanta, GA", 660);
        distanceMatrix.addDistance("Miami, FL", "Denver, CO", 2060);

        distanceMatrix.addDistance("Philadelphia, PA", "Seattle, WA", 2870);
        distanceMatrix.addDistance("Philadelphia, PA", "Atlanta, GA", 800);
        distanceMatrix.addDistance("Philadelphia, PA", "Denver, CO", 1760);

        distanceMatrix.addDistance("Seattle, WA", "Atlanta, GA", 2640);
        distanceMatrix.addDistance("Seattle, WA", "Denver, CO", 1300);

        distanceMatrix.addDistance("Atlanta, GA", "Denver, CO", 1400);

        // Example usage
        System.out.println("Distance from Chicago, IL to Los Angeles, CA: " + distanceMatrix.getDistance("Chicago, IL", "Los Angeles, CA") + " miles");
        System.out.println("Distance from New York, NY to Miami, FL: " + distanceMatrix.getDistance("New York, NY", "Miami, FL") + " miles");

        // Calculate the shortest and longest distances to visit all the cities
        List<String> cities = Arrays.asList("Chicago, IL", "Los Angeles, CA", "New York, NY", "Phoenix, AZ", "Boston, MA", "Miami, FL", "Philadelphia, PA", "Seattle, WA", "Atlanta, GA", "Denver, CO");
        double shortestDistance = calculateShortestDistance(cities, distanceMatrix);
        System.out.println("Shortest distance to visit all cities: " + shortestDistance + " miles");
    }

    public static double calculateShortestDistance(List<String> cities, DistanceMatrix distanceMatrix) {
        
        double retVal = -1;
        for(int i = 0; i < cities.size(); i++)
        {
            List<String> bestRoute = new ArrayList<>();
            List<String> worstRoute = new ArrayList<>();
            double[] minDistance = {Double.MAX_VALUE};
            double[] maxDistance = {Double.MIN_VALUE};

            permute(cities, i, distanceMatrix, bestRoute, worstRoute, minDistance, maxDistance);

            System.out.println("Best route: " + bestRoute + " Distance: " + minDistance[0] + " miles");
            System.out.println("Worst route: " + worstRoute + " Distance: " + maxDistance[0] + " miles");
            retVal = minDistance[0];
        }
    return retVal;
}

    public static void permute(List<String> route, int start, DistanceMatrix distanceMatrix, List<String> bestRoute, List<String> worstRoute, double[] minDistance, double[] maxDistance) {
        if (start == route.size() - 1) {
            double currentDistance = calculateTotalDistance(route, distanceMatrix);
            //System.out.println("Current route: " + route + " Distance: " + currentDistance + " miles");
            if (currentDistance < minDistance[0]) {
                minDistance[0] = currentDistance;
                bestRoute.clear();
                bestRoute.addAll(route);
            }
            if (currentDistance > maxDistance[0]) {
                maxDistance[0] = currentDistance;
                worstRoute.clear();
                worstRoute.addAll(route);
            }
        } else {
            for (int i = start; i < route.size(); i++) {
                swap(route, start, i);
                permute(route, start + 1, distanceMatrix, bestRoute, worstRoute, minDistance, maxDistance);
                swap(route, start, i);
            }
        }
    }

    public static void swap(List<String> route, int i, int j) {
        String temp = route.get(i);
        route.set(i, route.get(j));
        route.set(j, temp);
    }

    public static double calculateTotalDistance(List<String> route, DistanceMatrix distanceMatrix) {
        double totalDistance = 0;
        String currentCity = route.get(0);
        for (int i = 1; i < route.size(); i++) {
            String nextCity = route.get(i);
            totalDistance += distanceMatrix.getDistance(currentCity, nextCity);
            currentCity = nextCity;
        }
        totalDistance += distanceMatrix.getDistance(currentCity, route.get(0)); // Return to the starting point
        return totalDistance;
    }
}