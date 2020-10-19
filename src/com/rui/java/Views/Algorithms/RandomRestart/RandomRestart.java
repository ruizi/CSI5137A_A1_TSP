package com.rui.java.Views.Algorithms.RandomRestart;

import com.rui.java.Models.City;
import com.rui.java.Models.Route;

import java.util.ArrayList;

public class RandomRestart {
    private static final int MAX_RANDOM_TIME = 10;

    public Route shortestRoute(Route currentRoute) {
        ArrayList<Route> opt_Routes = new ArrayList<>();
        for (int i = 0; i < MAX_RANDOM_TIME; i++) {
            System.out.println("Random Restart, Try times:" + i);
            Route newStartPoint = new Route(currentRoute.getCities());
            opt_Routes.add(hillClimbing2Shortest(newStartPoint, i));
        }
        for (Route opt_route : opt_Routes) {
            if (opt_route.calTotalDistance() < currentRoute.calTotalDistance()) {
                currentRoute = opt_route;
                System.out.println("Find better instance in all memory   | ->move ｜ Route distance:" + currentRoute.calTotalDistance());
            }
        }
        System.out.println("May be the shortest distance : " + currentRoute.calTotalDistance());
        return currentRoute;
    }

    public Route hillClimbing2Shortest(Route curRoute, int restartTimes) {
        boolean climb = true;
        while (climb) {
            //find best neighbor
            ArrayList<Route> neighborRoutes = getNeighborRoutes(curRoute);
            climb = false;
            double curDistance = curRoute.calTotalDistance();
            for (Route route : neighborRoutes) {
                if (route.calTotalDistance() < curDistance) {
                    curDistance = route.calTotalDistance();
                    curRoute = route;
                    climb = true;
                }
            }
            if (climb) {
                System.out.println("Restart time:" + restartTimes + " Find better instance   | ->move ｜ Route distance:" + curRoute.calTotalDistance());
            } else {
                System.out.println("Restart time:" + restartTimes + " May be the shortest distance : " + curRoute.calTotalDistance());
            }
        }
        return curRoute;
    }

    public ArrayList<Route> getNeighborRoutes(Route route) {
        ArrayList<Route> allNeighborRoutes = new ArrayList<>();
        for (int i = 0; i < route.getCities().size() - 1; i++) {
            for (int j = i + 1; j < route.getCities().size(); j++) {
                ArrayList<City> citiesNew = new ArrayList<>(route.getCities());
                City cityOne = route.getCities().get(i);
                City cityTwo = route.getCities().get(j);
                citiesNew.set(i, cityTwo);
                citiesNew.set(j, cityOne);
                Route newNeighborRoute = new Route(citiesNew, false);
                allNeighborRoutes.add(newNeighborRoute);
            }
        }
        return allNeighborRoutes;
    }
}
