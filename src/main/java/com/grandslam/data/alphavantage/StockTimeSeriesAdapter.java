package com.grandslam.data.alphavantage;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Type adapter used by gson to convert to/from JSON string to object.
 */
public class StockTimeSeriesAdapter extends TypeAdapter<StockTimeSeries> {

    private static final String metaDataTag = "Meta Data";
    @Override
    public void write(JsonWriter out, StockTimeSeries value) throws IOException {
        //TODO: will do later
    }

    @Override
    public StockTimeSeries read(JsonReader in) throws IOException {
//        if (in.peek() == JsonToken.NULL) {
//            in.nextNull();
//            return null;
//        }
        StockTimeSeries stockTimeSeries = new StockTimeSeries();

        in.beginObject();
        while (in.hasNext()){
            JsonToken token = in.peek();
            String fieldName = null;
            if (token.equals(JsonToken.NAME)){
                fieldName = in.nextName();
            }

            if (metaDataTag.equalsIgnoreCase(fieldName)){
                in.beginObject();

                //TODO: change this properly and not the hardcoded stuff
//                while(!JsonToken.END_OBJECT.equals(in.peek())){
//                }
                in.nextName();
                String desc = in.nextString();
                in.nextName();
                String sym = in.nextString();
                in.nextName();
                String date = in.nextString();
                in.nextName();
                String interval = in.nextString();
                in.nextName();
                String outputSize = in.nextString();
                in.nextName();
                String timezone = in.nextString();
                stockTimeSeries.setTimeSeriesMetaData(desc, sym, date, interval, outputSize, timezone);

                in.endObject();
            }
            else {
                in.beginObject();

                while(JsonToken.NAME.equals(in.peek())){
                    String startDate = in.nextName();
                    in.beginObject();
                    in.nextName();
                    double open = in.nextDouble();
                    in.nextName();
                    double high = in.nextDouble();
                    in.nextName();
                    double low = in.nextDouble();
                    in.nextName();
                    double close = in.nextDouble();
                    in.nextName();
                    double volume = in.nextDouble();
                    in.endObject();

                    stockTimeSeries.addTimeSeriesPoint(startDate, open, high, low, close, volume);
                }

                in.endObject();
            }

        }
        in.endObject();
        return stockTimeSeries;
    }
}
