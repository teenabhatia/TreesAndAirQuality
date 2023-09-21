/**
 * {@code Main}
 * Initializes the program and prints out the output.
 */
public class Main {
    /**
     * {@code main}
     * Initializes the program and prints out the output.
     * @param args
     */
    // Main method
    public static void main(String[] args) {
        // Print out the intro message
        printIntro();

        BreadthFirstSearch bfs = new BreadthFirstSearch();
        bfs.getOutput();
    }
    /**
     * Print out the intro message to the application.
     */
    private static void printIntro() {
        System.out.println("******************************");
        System.out.println("Trees in New York City \n");
        System.out.println("Created by Teena Bhatia and Anushka Daga");
        System.out.println("*******************************\n");
    }

}
