import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * This class creates the graph and the Adjacency Matrix.
 */
public class AdjacencyMatrix {

    /**
     * {@code @graph} stores the adjacency matrix.
     */
    private final int[][] graph;

    /**
     * {@code @trees} stores a list of all the coordinates
     * of the trees (vertices).
     */
    public LinkedList<Double[]> trees;

    /**
     * {@code @AdjacencyMatrix} is the constructor where the
     * latitude and longitude coordinates are read from a
     * .txt file and added to the @trees LinkedList. Then,
     * the adjacency matrix is created by creating an edge
     * only between trees that are within a certain distance
     * of each other using the @calculateDistance method.
     */
    public AdjacencyMatrix() {

        // creates a new BufferedReader to read
        // the .txt file with the coordinates.
        BufferedReader b;
        try {
            // the coordinates.txt file is an example of
            // how data that is inputted into our code should look
            b = new BufferedReader(new FileReader("src/coordinates"));
            String s = b.readLine();
            trees = new LinkedList<Double[]>();
            LinkedList<String> lines = new LinkedList<String>();


            while (s != null) {
                lines.add(s);
                s = b.readLine();
            }

            // adding the coordinates to a Double array which
            // is then added to the trees linked-list.
            for (String line : lines) {
                String[] arr = line.split("\\s+");
                Double[] db = new Double[2];
                db[0] = Double.parseDouble(arr[0]);
                db[1] = Double.parseDouble(arr[1]);
                trees.add(db);
            }

            // iterating through the empty adjacency
            // matrix and adding 1's and 0's to indicate
            // if an edge exists between the two coordinates or not.
            // This is decided using the @calculateDistance method.
            graph = new int[trees.size()][trees.size()];
            for (int i = 0; i < trees.size(); i++) {
                for (int j = 0; j < trees.size(); j++) {
                    if (i != j) {
                        double dist = calculateDistance(trees.get(i)[0],
                                trees.get(i)[1],
                                trees.get(j)[0],
                                trees.get(j)[1]);
                        // 0.01 kilometres or 10 metres.
                        // Only adds an edge if the distance
                        // between 2 trees is less than 10m
                        if (dist < 0.01) {
                            graph[i][j] = 1;
                            graph[j][i] = 1;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@code @calculateDistance} is the method
     * which uses the Haversine formula in order
     * to convert distance from latitude/longitude
     * coordinates to kilometers.
     * @param lat1 latitude of coordinate 1
     * @param lat2 latitude of coordinate 2
     * @param lon1 longitude of coordinate 1
     * @param lon2 longitude of coordinate 2
     * @return distance.
     */
    public static double calculateDistance(double lat1,
                                           double lon1,
                                           double lat2,
                                           double lon2) {
        // Convert latitude and longitude values from degrees to radians
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // Calculate differences in latitude and longitude
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        // Apply Haversine formula to calculate great circle distance
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double radius = 6371;  // Radius of the earth in kilometers
        double distance = radius * c;

        return distance;
    }

    /**
     * {@code @getAdjacencyMatrix} is the method simply returns
     * the adjacency matrix.
     * @return int[][] adjacency matrix.
     */
    public int[][] getAdjacencyMatrix() {
        return graph;
    }

}
