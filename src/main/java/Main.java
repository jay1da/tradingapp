import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grandslam.data.alphavantage.StockTimeSeries;
import com.grandslam.data.alphavantage.StockTimeSeriesAdapter;
import com.granslam.symbols.SupportedSymbols;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String BASE_URL = "https://www.alphavantage.co/query?";
    private static final String apiKey = "JXQHPO8NX2ZFXV2Z";
    private static final String outputSizeFull = "full";

//    private static final String stockSymbol = "AAPL";
    private static final String timeInterval = "5";

    public static void main(String[] args) {

        for (SupportedSymbols symbol : SupportedSymbols.values()){

            String jsonString = getRequest(symbol);

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(StockTimeSeries.class, new StockTimeSeriesAdapter().nullSafe());
            Gson gson = builder.create();
            StockTimeSeries stockTimeSeries = gson.fromJson(jsonString, StockTimeSeries.class);
            logger.info(stockTimeSeries.toString());

            try {
                JSONObject json = new JSONObject(jsonString);
                logger.info(json.toString(4));
            } catch (JSONException e){
                logger.error("error with json", e);
            }
        }

    }

    public static String getRequest(SupportedSymbols stockSymbol){

        String requestURI = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" +
                stockSymbol + "&interval=" + timeInterval + "min&apikey=JXQHPO8NX2ZFXV2Z ";
//                + "&outputsize=" + outputSizeFull ;

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
            logger.error("error getting request", e);
        }
        return "";
    }
}
