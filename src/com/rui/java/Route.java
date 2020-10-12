package com.rui.java;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Route {
    private ArrayList<City> cities = new ArrayList<City>();

    public Route(ArrayList<City> cities) {
        this.cities = cities;
        Collections.shuffle(cities); //make the list randomly.
    }

    public Route(ArrayList<City> cities, boolean isShuffle) {
        this.cities = cities;
    }

    public Route(Route route) {
        this.cities.addAll(route.cities);
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public double calTotalDistance() {
        double sum = 0.00;
        for (int i = 0; i < cities.size() - 1; i++) {
            sum += cities.get(i).calculateDistance(cities.get(i + 1));
        }
        sum += cities.get(cities.size() - 1).calculateDistance(cities.get(0)); //back to the start city.
        DecimalFormat df = new DecimalFormat("#.00"); //set the format standard.
        return Double.parseDouble(df.format(sum));
    }

    public void reverseFromA2B(int pointA, int pointB) {
        int citiesSize = this.cities.size();
        List<City> preFix = new ArrayList<City>(this.cities).subList(0, pointA);
        List<City> postFix = new ArrayList<City>(this.cities).subList(pointB + 1, citiesSize);
        List<City> reverseMiddle = new ArrayList<City>(this.cities).subList(pointA, pointB+1);
        Collections.reverse(reverseMiddle);
        this.cities = new ArrayList<>();
        this.cities.addAll(preFix);
        this.cities.addAll(reverseMiddle);
        this.cities.addAll(postFix);
    }

    @Override
    public String toString() {
        ArrayList<Integer> route = new ArrayList<>();
        for (City i : cities) {
            route.add(i.getID());
        }
        return Arrays.toString(route.toArray());
    }
}
