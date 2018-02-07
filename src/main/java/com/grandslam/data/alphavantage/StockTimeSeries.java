package com.grandslam.data.alphavantage;

import com.google.gson.JsonObject;
import com.granslam.symbols.SupportedSymbols;

import java.util.ArrayList;
import java.util.Date;

/**
 * POJO to holding the historic stock time series data
 */
public class StockTimeSeries {

    /**
     * Holds the metadata of the time series
     */
    private class TimeSeriesMetaData {
        private String description;
        private SupportedSymbols symbol;
        private Date lastRefreshed;
        private long interval;
        private String outputSize;
        private String timeZone;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public SupportedSymbols getSymbol() {
            return symbol;
        }

        public void setSymbol(SupportedSymbols symbol) {
            this.symbol = symbol;
        }

        public Date getLastRefreshed() {
            return lastRefreshed;
        }

        public void setLastRefreshed(Date lastRefreshed) {
            this.lastRefreshed = lastRefreshed;
        }

        public long getInterval() {
            return interval;
        }

        public void setInterval(long interval) {
            this.interval = interval;
        }

        public String getOutputSize() {
            return outputSize;
        }

        public void setOutputSize(String outputSize) {
            this.outputSize = outputSize;
        }

        public String getTimeZone() {
            return timeZone;
        }

        public void setTimeZone(String timeZone) {
            this.timeZone = timeZone;
        }

        @Override
        public String toString() {
            return "TimeSeriesMetaData{" +
                    "description='" + description + '\'' +
                    ", symbol=" + symbol +
                    ", lastRefreshed=" + lastRefreshed +
                    ", interval=" + interval +
                    ", outputSize='" + outputSize + '\'' +
                    ", timeZone='" + timeZone + '\'' +
                    '}';
        }
    }

    /**
     * Stores the series point
     */
    private class TimeSeriesPoint {
        private Date startTime;
        private double open;
        private double high;
        private double low;
        private double close;
        private double volume;

        @Override
        public String toString() {
            return "TimeSeriesPoint{" +
                    "startTime=" + startTime +
                    ", open=" + open +
                    ", high=" + high +
                    ", low=" + low +
                    ", close=" + close +
                    ", volume=" + volume +
                    '}';
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }
    }

    private TimeSeriesMetaData timeSeriesMetaData;
    private ArrayList<TimeSeriesPoint> timeSeriesDataPoints = new ArrayList<>();

    public void setTimeSeriesMetaData(String desc, String symbol, String dateString, String intervalString, String outputSize, String timezone){
        timeSeriesMetaData = new TimeSeriesMetaData();
        timeSeriesMetaData.setDescription(desc);
        timeSeriesMetaData.setSymbol(SupportedSymbols.valueOf(symbol));
//        timeSeriesMetaData.setLastRefreshed(new Date(dateString)); //TODO: parse the date properly
//        timeSeriesMetaData.setInterval(intervalString); //TODO: parse the interval properly
        timeSeriesMetaData.setOutputSize(outputSize);
        timeSeriesMetaData.setTimeZone(timezone);
    }

    /**
     * Adds data point to the time series
     * @param date
     * @param open
     * @param high
     * @param low
     * @param close
     * @param volume
     */
    public void addTimeSeriesPoint(String date, double open, double high, double low, double close, double volume){
        TimeSeriesPoint point = new TimeSeriesPoint();
        point.setClose(close);
        point.setHigh(high);
        point.setLow(low);
        point.setOpen(open);
        point.setStartTime(Util.parseDate(date));
        point.setVolume(volume);

        this.timeSeriesDataPoints.add(point);
    }

    public TimeSeriesMetaData getTimeSeriesMetaData() {
        return timeSeriesMetaData;
    }

    public ArrayList<TimeSeriesPoint> getTimeSeriesDataPoints() {
        return timeSeriesDataPoints;
    }

    @Override
    public String toString() {
        return "StockTimeSeries{" +
                "timeSeriesMetaData=" + timeSeriesMetaData +
                ", timeSeriesDataPoints=" + timeSeriesDataPoints +
                '}';
    }
}
