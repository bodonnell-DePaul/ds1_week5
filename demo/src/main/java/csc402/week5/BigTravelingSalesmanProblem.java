package csc402.week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import csc402.week5.ProfessionalSportsTeam;

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

        // Calculate the shortest distance between each location and 5 of the other cities
        List<String> cities = Arrays.asList("Chicago, IL", "Los Angeles, CA", "New York, NY", "Phoenix, AZ", "Boston, MA", "Miami, FL", "Philadelphia, PA", "Seattle, WA", "Atlanta, GA", "Denver, CO");
        for (String city : cities) {
            List<String> otherCities = new ArrayList<>(cities);
            otherCities.remove(city);
            //List<String> subset = otherCities.subList(0, 5);
            double shortestDistance = calculateShortestDistance(city, otherCities, distanceMatrix);
            System.out.println("Shortest distance from " + city + " to 5 other cities: " + shortestDistance + " miles");
        }
    }
   
    public static double calculateShortestDistance(String startCity, List<String> cities, DistanceMatrix distanceMatrix) {
        List<Integer> bestRoute = new ArrayList<>();
        double[] minDistance = {Double.MAX_VALUE};

        List<Integer> currentRoute = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            currentRoute.add(i);
        }

        permute(currentRoute, 0, cities, distanceMatrix, bestRoute, minDistance, startCity);

        return minDistance[0];
    }

    public static void permute(List<Integer> route, int start, List<String> cities, DistanceMatrix distanceMatrix, List<Integer> bestRoute, double[] minDistance, String startCity) {
        if (start == route.size() - 1) {
            double currentDistance = calculateTotalDistance(route, cities, distanceMatrix, startCity);
            if (currentDistance < minDistance[0]) {
                minDistance[0] = currentDistance;
                bestRoute.clear();
                bestRoute.addAll(route);
            }
        } else {
            for (int i = start; i < route.size(); i++) {
                swap(route, start, i);
                permute(route, start + 1, cities, distanceMatrix, bestRoute, minDistance, startCity);
                swap(route, start, i);
            }
        }
    }

    public static void swap(List<Integer> route, int i, int j) {
        int temp = route.get(i);
        route.set(i, route.get(j));
        route.set(j, temp);
    }

    public static double calculateTotalDistance(List<Integer> route, List<String> cities, DistanceMatrix distanceMatrix, String startCity) {
        double totalDistance = 0;
        String currentCity = startCity;
        for (int i = 0; i < route.size(); i++) {
            String nextCity = cities.get(route.get(i));
            totalDistance += distanceMatrix.getDistance(currentCity, nextCity);
            currentCity = nextCity;
        }
        totalDistance += distanceMatrix.getDistance(currentCity, startCity); // Return to the starting point
        return totalDistance;
    }
}