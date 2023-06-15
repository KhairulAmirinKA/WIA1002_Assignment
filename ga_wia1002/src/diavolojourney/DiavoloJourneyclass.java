/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diavolojourney;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Abdul Hadi
 */
public class DiavoloJourneyclass {
     static class Graph {
        private int numLocations;
        Map<String, Integer> locationIndices;
        List<List<Location>> adjacencyList;

        public Graph(int numLocations) {
            this.numLocations = numLocations;
            locationIndices = new HashMap<>();
            adjacencyList = new ArrayList<>();

            for (int i = 0; i < numLocations; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addLocation(String locationName) {
            locationIndices.put(locationName, locationIndices.size());
        }

        public void addEdge(String source, String destination, int distance) {
            int sourceIndex = locationIndices.get(source);
            int destinationIndex = locationIndices.get(destination);

            adjacencyList.get(sourceIndex).add(new Location(destinationIndex, distance));
            adjacencyList.get(destinationIndex).add(new Location(sourceIndex, distance));
        }

        public List<String> findShortestPath(String source, String destination) {
            int sourceIndex = locationIndices.get(source);
            int destinationIndex = locationIndices.get(destination);

            double[] distances = new double[numLocations];
            int[] previous = new int[numLocations];
            Arrays.fill(distances, Double.POSITIVE_INFINITY);
            Arrays.fill(previous, -1);

            distances[sourceIndex] = 0;

            PriorityQueue<Location> minHeap = new PriorityQueue<>(Comparator.comparingDouble(l -> l.distance));
            minHeap.offer(new Location(sourceIndex, 0));

            while (!minHeap.isEmpty()) {
                Location current = minHeap.poll();

                if (current.index == destinationIndex) {
                    break;
                }

                if (current.distance > distances[current.index]) {
                    continue;
                }

                for (Location neighbor : adjacencyList.get(current.index)) {
                    double newDistance = distances[current.index] + neighbor.distance;

                    if (newDistance < distances[neighbor.index]) {
                        distances[neighbor.index] = newDistance;
                        previous[neighbor.index] = current.index;
                        minHeap.offer(new Location(neighbor.index, (int) newDistance));
                    }
                }
            }

            List<String> path = new ArrayList<>();
            int currentIndex = destinationIndex;

            while (currentIndex != -1) {
                path.add(0, getLocationName(currentIndex));
                currentIndex = previous[currentIndex];
            }

            return path;
        }

        private String getLocationName(int index) {
            for (Map.Entry<String, Integer> entry : locationIndices.entrySet()) {
                if (entry.getValue() == index) {
                    return entry.getKey();
                }
            }
            return null;
        }
    }

    static class Location {
        int index;
        int distance;

        public Location(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }}
