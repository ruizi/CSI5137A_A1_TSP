package com.rui.java.Views.Algorithms.HillClimbing;

import com.rui.java.Models.City;
import com.rui.java.Models.Route;

import java.util.ArrayList;

public class RandomAscentHillClimbing {
    public Route shortestRoute(Route currentRoute) {
        while (true) {
            ArrayList<Route> neighborRoutes = getNeighborRoutes(new Route(currentRoute));
            ArrayList<Route> betterRoute = new ArrayList<>();
            double shortestDistance = currentRoute.calTotalDistance();
            for (Route neighborRoute : neighborRoutes) {
                double neighborRouteDistance = neighborRoute.calTotalDistance();
                if (neighborRouteDistance < shortestDistance) {
                    betterRoute.add(neighborRoute);
                }
            }
            if (betterRoute.size() > 0) {
                currentRoute = betterRoute.get((int) (betterRoute.size() * Math.random()));
                System.out.println("Find [" + betterRoute.size() + "] better instances,Randomly choose one  | ->move ｜ Route distance:" + currentRoute.calTotalDistance());
            } else {
                System.out.println("May be the shortest distance : " + currentRoute.calTotalDistance());
                break;
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
                citiesNew.set(i, cityTwo);
                citiesNew.set(j, cityOne);
                Route newNeighborRoute = new Route(citiesNew, false);
                allNeighborRoutes.add(newNeighborRoute);
            }
        }
        return allNeighborRoutes;
    }
}
