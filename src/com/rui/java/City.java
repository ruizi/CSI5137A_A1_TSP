package com.rui.java;

import java.text.DecimalFormat;

public class City {
    private int ID;
    private int coordinate_x;
    private int coordinate_y;

    public City(int ID, int coordinate_x, int coordinate_y) {
        this.ID = ID;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
    }

    public int getID() {
        return ID;
    }

    public int getCoordinate_x() {
        return coordinate_x;
    }

    public int getCoordinate_y() {
        return coordinate_y;
    }

    public double calculateDistance(City city) {
        int delta_x = coordinate_x - city.getCoordinate_x();
        int delta_y = coordinate_y - city.getCoordinate_y();
        double distance = Math.sqrt(Math.abs(delta_x * delta_x + delta_y * delta_y)); //measure the distance between two cities.
        //System.out.println("the distance from " + this.ID + " to " + city.getID() + " is " + distance);
        return distance;
    }

    @Override
    public String toString() {
        return "City{" +
                "ID=" + ID +
                '}';
    }
}
