package com.bjqg.web.utils.map;

import java.io.Serializable;

/**
 * @author: lbj
 * @date: 2023/1/4 10:45
 */

public class Point implements Serializable {

    private static final long serialVersionUID = 3584864663880053897L;

    private double lng;

    private double lat;

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Point{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }

    public Point(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public Point() {
    }
}
