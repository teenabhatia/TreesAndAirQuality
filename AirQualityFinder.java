import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class uses the airnow API to find the AQI at a certain coordinate.
 */

public class AirQualityFinder {
    /**
     * {@code main} constructor for the class which throws an IO
     * exception in case no valid file is found.
     * @param args
     * The commented out code below does the same
     * thing as the code which is not commented out
     * except it reads the coordinates from a .txt
     * file rather than taking input from the user.
     * We found that the API could not handle so many
     * requests at once (we were receiving an HTTP 429
     * Too Many Requests response status code), which is why
     * we decided to use this code as it makes one request
     * at a time.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(System.in));
        System.out.println("Enter latitude:");
        String lat = reader.readLine();
        System.out.println("Enter longitude:");
        String lon = reader.readLine();

        String apiKey = "54FEA077-4F82-4F19-8D60-97D9B23286F0";
        String urlString = "https://www.airnowapi.org/aq/forecast"
                + "/latLong/?format=application/json&latitude="
                + lat + "&longitude=" + lon + "&API_KEY=" + apiKey;

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader responseReader = new BufferedReader(new
                InputStreamReader(connection.getInputStream()));
        String responseLine;
        StringBuilder response = new StringBuilder();
        while ((responseLine = responseReader.readLine()) != null) {
            response.append(responseLine.trim());
        }
        responseReader.close();

        System.out.println(response.toString());
    }
}

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class AirQualityFinder {
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader("src/averages"));
//        ArrayList<String> responses = new ArrayList<String>();
//        String apiKey = "54FEA077-4F82-4F19-8D60-97D9B23286F0";
//        String line;
//        while ((line = reader.readLine()) != null) {
//            String[] parts = line.split("\\s+");
//            String lat = parts[0];
//            String lon = parts[1];
//            String urlString = "https://www.airnowapi.org/aq/forecast/latLong/?format=application/json&latitude="
//            + lat + "&longitude=" + lon + "&API_KEY=" + apiKey;
//
//            URL url = new URL(urlString);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String responseLine;
//            StringBuilder response = new StringBuilder();
//            while ((responseLine = responseReader.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//            responseReader.close();
//
//            responses.add(response.toString());
//        }
//        reader.close();
//
//        // Process the responses
//        for (String response : responses) {
//            String[] lines = response.split("\\r?\\n");
//            for (String line2 : lines) {
//                if (line2.contains("AQI")) {
//                    String[] parts = line2.split(":");
//                    String aqi = parts[1].trim();
//                    System.out.println(aqi);
//                }
//            }
//        }
//    }
//}

