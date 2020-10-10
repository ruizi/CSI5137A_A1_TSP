package com.rui.java.Algorithms.HillClimbing;

import com.rui.java.City;
import com.rui.java.Route;

import java.util.ArrayList;

public class HillClimbing {

    public static final int MAX_ITERATION_BEFORE_STOP = 100;

    public Route shortestRoute(Route currentRoute) {
        int iterationCount = 0;
        System.out.println("init route:" + currentRoute.toString() + "| Route distance:" + currentRoute.calTotalDistance());
        while (iterationCount < MAX_ITERATION_BEFORE_STOP) {
            Route neighborRoute = getANeighborRoute(new Route(currentRoute));
            if (neighborRoute.calTotalDistance() < currentRoute.calTotalDistance()) {
                currentRoute = neighborRoute;
                iterationCount = 0;
                System.out.printf("Find shorter instance | ->Move | iteration Count: %7d", iterationCount);
            } else {
                iterationCount += 1;
                System.out.printf("No better instance    | ->Stay | iteration Count: %7d", iterationCount);
            }
            System.out.printf(" | Now route:" + currentRoute.toString() + " | Route distance: %7.2f \n", currentRoute.calTotalDistance());
        }
        if (iterationCount == MAX_ITERATION_BEFORE_STOP) {
            System.out.println("May be the shortest distance : " + currentRoute.calTotalDistance());
        }
        return currentRoute;
    }

    public Route getANeighborRoute(Route route) {
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
}
