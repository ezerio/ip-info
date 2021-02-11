package com.ezerio.ipinfo.utility;

public class GeoCoordinate {

    private double latitude;
    private double longitude;

    public GeoCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoCoordinate(String latitude, String longitude) {
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int distanceTo(double latitude, double longitude) {
        double dLat = Math.toRadians(latitude - this.latitude);
        double dLon = Math.toRadians(longitude - this.longitude);
        this.latitude = Math.toRadians(this.latitude);
        latitude = Math.toRadians(latitude);
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(this.latitude) *
                        Math.cos(latitude);
        double rad = 6371;
        double result = (2 * Math.asin(Math.sqrt(a))) * rad;
        return (int) Math.round(result);
    }

}
