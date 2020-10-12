package com.rui.java.Algorithms.SimulatedAnnealing;

import com.rui.java.City;
import com.rui.java.Route;

import java.util.ArrayList;
import java.util.Collections;

public class SA_Three_Cooling_Schedules {
    public static final double CoolingRate = 0.999;
    public static final double InitTemperature = 3000;
    public static final double MIN_TEMP = 0.04;
    public static final int MAX_ITERATION_IN_ONE_TEMPERATURE = 280;
    double x = 1.001;

    public Route shortestRoute(Route currentRoute, String mode) {
        System.out.println("init route:" + currentRoute.toString() + "| Route distance:" + currentRoute.calTotalDistance());
        double temperature = InitTemperature;
        while (true) {
            if (temperature < MIN_TEMP) {
                break;
            }
            for (int i = 0; i < MAX_ITERATION_IN_ONE_TEMPERATURE; i++) {
                Route neighborRoute = getANeighborRoute_Reverse(new Route(currentRoute));
                double acceptProbability = annealingProbability(currentRoute, neighborRoute, temperature);
                if (acceptProbability >= (Math.random())) {
                    currentRoute = neighborRoute;
                    System.out.printf("Find better instance   | ->move | Route distance: %7.2f | temperature: %7.2f \n", currentRoute.calTotalDistance(), temperature);
                } else {
                    //System.out.printf("Not a better instance  | ->Stay | Route distance: %7.2f | iteration  : %7d \n", currentRoute.calTotalDistance(), i);
                }
            }
            if (mode.equals("Exponential")) {
                temperature *= CoolingRate;
            } else if (mode.equals("Liner")) {
                temperature -= 0.1;
            } else {
                temperature -= Math.log(x);
                x += 0.001;
            }
        }
        System.out.println("May be the shortest distance : " + currentRoute.calTotalDistance());
        return currentRoute;
    }

    public double annealingProbability(Route currentRoute, Route neighborRoute, Double temperature) {
        double distanceChange = neighborRoute.calTotalDistance() - currentRoute.calTotalDistance();
        if (distanceChange < 0.0) {
            return 1.00;
        } else {
            return Math.exp(-distanceChange / temperature);
        }
    }

    public Route getANeighborRoute_Swap(Route route) {
        int cityOneIndex = 0;
        int cityTwoIndex = 0;
        while (cityOneIndex == cityTwoIndex) {
            cityOneIndex = (int) (route.getCities().size() * Math.random());
            cityTwoIndex = (int) (route.getCities().size() * Math.random());
        }
        City cityOne = route.getCities().get(cityOneIndex);
        City cityTwo = route.getCities().get(cityTwoIndex);
        route.getCities().set(cityOneIndex, cityTwo);
        route.getCities().set(cityTwoIndex, cityOne);
        return route;
    }

    public Route getANeighborRoute_Reverse(Route route) {
        int cityOneIndex = 0;
        int cityTwoIndex = 0;
        while (cityOneIndex == cityTwoIndex) {
            cityOneIndex = (int) (route.getCities().size() * Math.random());
            cityTwoIndex = (int) (route.getCities().size() * Math.random());
        }
        if (cityOneIndex < cityTwoIndex) {
            route.reverseFromA2B(cityOneIndex, cityTwoIndex);
        } else {
            route.reverseFromA2B(cityTwoIndex, cityOneIndex);
        }
        return route;
    }

}
