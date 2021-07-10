package main;

import java.net.URL;
import java.util.Scanner;
import java.io.IOException;
import java.net.HttpURLConnection;


public class API {
    /**
     * URL HTTP link of the Bitcoin API
     */
    private String urlLink;
    /**
     * JSON text received from the Bitcoin API
     */
    private String jsonText;
    /**
     * @param link is the user provided Bitcoin API price link
     */
    public API(String link) {
    	urlLink = link;
    }
    /**
     * @return the JSON text file accessed from the user provided API link
     */
    public String getJSON() {

        try {
            URL url = new URL(urlLink);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Casting connection to open URL to HTTP object (required to process requests)
            // Modification of URL objects
            conn.setRequestMethod("GET"); // Using HTTP object to connect to API URL and connecting based on the fully formed conn HTTP object
            conn.connect();
            int responseCode = conn.getResponseCode(); // Instance method of conn object returns integers representing result of HTTP GET request and connection
            try {
                if (responseCode != 200) { // Anything besides a code of 200 signifies an invalid HTTP-URL connection and GET initialization
                    throw new RuntimeException("HttpResponseCode: " + responseCode);
                }
                else {
                    jsonText = "";
                    Scanner scanner = new Scanner(url.openStream());
                    while (scanner.hasNext()) {
                        jsonText += scanner.nextLine(); // String concatenation - refactor with a stringbuffer/builder to optimize memory
                    }
                    scanner.close();
                }
            }
            catch (RuntimeException re) {
                System.out.println("ERROR: " + re.getMessage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonText;
    }
}
