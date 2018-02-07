import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;


public class Main {

    private static final String BASE_URL = "https://www.alphavantage.co/query?";
    private static final String apiKey = "JXQHPO8NX2ZFXV2Z";

    private static final String stockSymbol = "AAPL";
    private static final String timeInterval = "5";

    public static void main(String[] args) {
        String jsonString = getRequest();

        try {
            JSONObject json = new JSONObject(jsonString);
            System.out.println(json.toString(4));
        } catch (JSONException e){
            System.out.println("error with json");
        }

    }

    public static String getRequest() {

        String requestURI = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" +
                stockSymbol + "&interval=" + timeInterval + "min&apikey=JXQHPO8NX2ZFXV2Z";

        try {
            URL request = new URL(requestURI);
            URLConnection connection = request.openConnection();
            //connection.setConnectTimeout(timeOut);
            //connection.setReadTimeout(timeOut);

            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            StringBuilder responseBuilder = new StringBuilder();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                responseBuilder.append(line);
            }

            bufferedReader.close();
            return responseBuilder.toString();
        } catch (IOException e) {
            System.out.println("error getting request");
        }
        return "";
    }
}
