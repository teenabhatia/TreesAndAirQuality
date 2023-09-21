import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Integer.MAX_VALUE;

/**
 * This class implements the Breadth First Search Algorithm.
 */
public class BreadthFirstSearch {
    /**
     * {@code @output} stores the order of the traversal.
     */
    private final ArrayList<Double[]> output = new ArrayList<Double[]>();
    /**
     * {@code @distanceFinal} stores the distance of each
     * of the nodes from the start node.
     */
    private final Integer[] distanceFinal;

    /**
     * {@code @count} used to keep track of how many times the search
     * has to restart in order to check how many connected components there are.
     */
    private int count = 0;

    /**
     * {@code @connectedComponents} used to store all the vertices
     * for each connected component.
     */
    public ArrayList<ArrayList<Double[]>>
            connectedComponents = new ArrayList<>();

    /**
     * {@code @averages} used to store the average latitude and longitude
     * of one connected component along with the size of the component.
     */
    public ArrayList<Double[]> averages;

    /**
     * {@code @BreadthFirstSearch} is the constructor where the BFS algorithm
     * is actually run on the adjacency matrix.
     */
    public BreadthFirstSearch() {
        AdjacencyMatrix ld = new AdjacencyMatrix();
        int[][] matrix = ld.getAdjacencyMatrix();
        Double[] startVertex = ld.trees.getFirst();
        Integer[] distance = new Integer[ld.trees.size()];
        boolean[] visited = new boolean[ld.trees.size()];

        for (int x = 0; x < ld.trees.size(); x++) {
            distance[x] = 0; // initialises the distance of each node to 0
        }
        // temporarily stores the value of
        // node at the front of the queue
        Double[] temp;

        // if all trees have been visited, this value will be true
        boolean allVisited = !convertToList(visited,
                ld.trees.size()).contains(false);

        while (!allVisited) {
            ArrayList<Double[]> store = new ArrayList<>();
            // Used to store the trees that are going to be visited next
            LinkedList<Double[]> queue = new LinkedList<>();
            // adds the start node to the queue
            queue.add(startVertex);
            store.add(startVertex);
            // marks the start vertex as visited
            visited[ld.trees.indexOf(startVertex)] = true;

            // the if condition here is to check that the distance
            // of the node hasn't already been set to infinity
            // because it is not connected to the start vertex.
            // This will make more sense once you see the code below.
            if (!(distance[ld.trees.indexOf(startVertex)] == MAX_VALUE)) {
                distance[ld.trees.indexOf(startVertex)] = 0;
            }
            while (!queue.isEmpty()) {
                temp = queue.get(0);
                output.add(temp);
                store.add(temp);
                // removes the first item from the queue
                queue.removeFirst();

                // iterates over the number of trees and checks if
                // the node exists and if it has not been visited yet
                for (int x = 0; x < ld.trees.size(); x++) {
                    if ((matrix[ld.trees.indexOf(temp)][x] == 1
                            && !visited[x])
                            || (matrix[x][ld.trees.indexOf(temp)] == 1
                            && !visited[x])) {
                        // since it exists and has not been visited,
                        // it is added to the queue
                        queue.add(ld.trees.get(x));
                        // marked as visited
                        visited[x] = true;
                        if (!(distance[x] == MAX_VALUE)) {
                            distance[x] = distance[ld.trees.indexOf(temp)] + 1;
                        }
                    }
                }

            }
            // whichever trees have not been visited at the end
            // of this iteration would not be connected to the start
            // node, thus we have said their distance is infinity
            // (represented by the largest int value in Java)
            for (int x = 0; x < ld.trees.size(); x++) {
                if (!visited[x]) {
                    distance[x] = MAX_VALUE;
                }
            }
            // updates the condition of the while loop,
            // checking if all trees have been visited or not
            allVisited = !convertToList(visited,
                    ld.trees.size()).contains(false);
            for (int x = 0; x < ld.trees.size(); x++) {
                if (!visited[x]) {
                    // counting the number of times the search restarts
                    count++;
                    // resets start node to randomly chosen unvisited node
                    startVertex = ld.trees.get(x);
                    connectedComponents.add(store);
                    store = new ArrayList<>();
                    break;
                }

            }


        }
        distanceFinal = distance;
    }

    /**
     * {@code @convertToList} is a method that converts an array to a list.
     * is actually run on the adjacency matrix.
     *
     * @param numberOftrees is the total number of vertices/trees in the
     *                      graph and the length of the array.
     * @param input is the boolean array that needs to be converted to a list.
     * @return ArrayList<Boolean>
     */
    public ArrayList<Boolean> convertToList(boolean[] input,
                                            int numberOftrees) {
        ArrayList<Boolean> list = new ArrayList<>();
        for (int x = 0; x < numberOftrees; x++) {
            list.add(input[x]);
        }
        return list;
    }

    /**
     * {@code @maximum} is a method that finds maximum value
     * in an array, used to find number of frontiers.
     *
     * @return integer maximum value of the array
     */
    public int maximum() {
        int x;
        int max = distanceFinal[0];
        for (x = 1; x < distanceFinal.length; x++) {
            if (distanceFinal[x] > max && distanceFinal[x] < MAX_VALUE) {
                max = distanceFinal[x];
            }
        }
        return max;
    }

    /**
     * {@code @findData} is a method that finds the average
     * of all the coordinates in a connected component.
     * @param arr array list of a list of all the connected
     *            components and the vertices in those connected
     *            components.
     * @return ArrayList of Double[] containing the average
     * coordinates of each connected component.
     */
    public ArrayList<Double[]> findData(ArrayList<ArrayList<Double[]>>
                                                arr) {
        averages = new ArrayList<>();
        for (ArrayList<Double[]> doubles : arr) {
            double xAvg = 0;
            double yAvg = 0;
            int count = 0;
            for (Double[] aDouble : doubles) {
                xAvg = xAvg + aDouble[0];
                yAvg = yAvg + aDouble[1];
                count++;
            }
            xAvg = xAvg / count;
            yAvg = yAvg / count;
            Double[] db = new Double[3];
            db[0] = xAvg;
            db[1] = yAvg;
            db[2] = (double) count;
            averages.add(db);
        }
        return averages;
    }

    /**
     * {@code @getOutput} is a method that
     * prints out all the outputs we require.
     */
    public void getOutput() {
        System.out.println("Vertices: " + output.size());
        System.out.println("# of restarts: " + count + "\n");
        System.out.println("Connected components: ");
        for (ArrayList<Double[]> cc : connectedComponents) {
            System.out.println("\nNEXT CONNECTED COMPONENT");
            for (Double[] arr : cc) {
                System.out.println(Arrays.toString(arr));
            }
        }
        ArrayList<Double[]> average = findData(connectedComponents);
        System.out.println("\nAverage coordinates of all "
                + "connected components along with size:");

        for (Double[] doubles : average) {
            System.out.print("[");
            for (Double aDouble : doubles) {
                System.out.print(aDouble + "  ");
            }
            System.out.println("]");
        }

    }

}
