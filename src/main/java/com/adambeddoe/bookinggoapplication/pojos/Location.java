package com.adambeddoe.bookinggoapplication.pojos;

public class Location {

    private float latitiude;

    private float longitude;

    public Location(float latitiude, float longitude) {
        this.latitiude = latitiude;
        this.longitude = longitude;
    }

    public static Location locationFromString(String latitiude, String longitude) throws NumberFormatException {
        return new Location(Float.valueOf(latitiude), Float.valueOf(longitude));
    }

    public float getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(float latitiude) {
        this.latitiude = latitiude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String printPair() {
        return latitiude + "," + longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitiude=" + latitiude +
                ", longitude=" + longitude +
                '}';
    }
}
