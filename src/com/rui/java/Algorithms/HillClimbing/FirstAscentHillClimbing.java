package com.rui.java.Algorithms.HillClimbing;

import com.rui.java.City;
import com.rui.java.Route;

import java.util.ArrayList;

public class FirstAscentHillClimbing {
    public Route shortestRoute(Route currentRoute) {
        boolean climb = true;
        while (climb) {
            ArrayList<Route> neighborRoutes = getNeighborRoutes(new Route(currentRoute));
            climb = false;
            double shortestDistance = currentRoute.calTotalDistance();
            for (Route neighborRoute : neighborRoutes) { //move to the shortest neighbor route.
                double neighborRouteDistance = neighborRoute.calTotalDistance();
                if (neighborRouteDistance < shortestDistance) {
                    shortestDistance = neighborRouteDistance;
                    currentRoute = neighborRoute;
                    climb = true;
                    break;
                }
            }
            if (climb) {
                System.out.println("Find better instance   | ->move ｜ Route distance:" + currentRoute.calTotalDistance());
            } else {
                System.out.println("May be the shortest distance : " + currentRoute.calTotalDistance());
            }
        }

        return currentRoute;
    }

    public ArrayList<Route> getNeighborRoutes(Route route) {
        ArrayList<Route> allNeighborRoutes = new ArrayList<>();
        for (int i = 0; i < route.getCities().size(); i++) {
            for (int j = i + 1; j < route.getCities().size(); j++) {
                ArrayList<City> citiesNew = new ArrayList<>(route.getCities());
                City cityOne = route.getCities().get(i);
                City cityTwo = route.getCities().get(j);
                //route.getCities().set(i, cityOne);
                citiesNew.set(i, cityTwo);
                citiesNew.set(j, cityOne);
                Route newNeighborRoute = new Route(citiesNew, false);
                allNeighborRoutes.add(newNeighborRoute);
            }
        }
        return allNeighborRoutes;
    }
}
