package csc402.week5;

import java.util.HashMap;
import java.util.Map;

public class DistanceMatrix {
    private Map<String, Map<String, Double>> distanceMap;

    public DistanceMatrix() {
        distanceMap = new HashMap<>();
    }

    public void addDistance(String city1, String city2, double distance) {
        distanceMap.putIfAbsent(city1, new HashMap<>());
        distanceMap.putIfAbsent(city2, new HashMap<>());
        distanceMap.get(city1).put(city2, distance);
        distanceMap.get(city2).put(city1, distance); // Assuming the distance is symmetric
    }

    public double getDistance(String city1, String city2) {
        if (distanceMap.containsKey(city1) && distanceMap.get(city1).containsKey(city2)) {
            return distanceMap.get(city1).get(city2);
        }
        return -1; // Return -1 if the distance is not found
    }
}