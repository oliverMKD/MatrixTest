package com.oli.matrixtest.klasi;


import java.io.Serializable;

public class Coord implements Serializable {


    public Double lon;

    public Double lat;

    public Coord() {
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
