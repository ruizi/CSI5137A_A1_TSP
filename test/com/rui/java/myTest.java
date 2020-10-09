package com.rui.java;

import com.rui.java.Algorithms.HillClimbing;
import org.junit.Test;

import java.util.ArrayList;

public class myTest {
    @Test
    public void cityTest() {
        City city = new City(1, 2, 3);
        City city1 = new City(2, 3, 2);
        double distance = city.calculateDistance(city1);
        System.out.println(distance);
    }

    @Test
    public void routeTest() {
        City city = new City(1, 2, 3);
        City city1 = new City(2, 3, 2);
        City city2 = new City(3, 4, 1);
        ArrayList<City> cities = new ArrayList<>();
        cities.add(city);
        cities.add(city1);
        cities.add(city2);
        Route route = new Route(cities);
        double distance = route.calTotalDistance();
        System.out.println(distance);
    }

    @Test
    public void hillClimbingTest() {
        City city1 = new City(1, 2, 3);
        City city2 = new City(2, 3, 3);
        City city3 = new City(3, 3, 2);
        City city4 = new City(4, 2, 2);
        ArrayList<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        Route route = new Route(cities);
        HillClimbing hillClimbing = new HillClimbing();
        hillClimbing.shortestRoute(route);
    }

    @Test
    public void test() {

        String line = "  good12 morning34 good56 night78";

        //对String进行split之后的数组的输出
        String[] tt = line.trim().split("\\s+");
        for (String s : tt) {
            System.out.println(s);
        }
        System.out.println("#####");
    }
}