package com.rui.java;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Route {
    private ArrayList<City> cities = new ArrayList<City>();

    public Route(ArrayList<City> cities) {
        this.cities = cities;
        Collections.shuffle(cities); //make the list randomly.
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

    @Override
    public String toString() {
        ArrayList<Integer> route = new ArrayList<>();
        for (City i : cities) {
            route.add(i.getID());
        }
        return Arrays.toString(route.toArray());
    }
}
