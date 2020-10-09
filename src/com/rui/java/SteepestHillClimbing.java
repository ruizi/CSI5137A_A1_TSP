package com.rui.java;

import java.util.ArrayList;

public class SteepestHillClimbing {
    public Route shortestRoute(Route currentRoute) {
        ArrayList<Route> neighborRoutes = getNeighborRoutes(currentRoute);
        double currentDistance = currentRoute.calTotalDistance();
        int shortestRouteNum = -1;
        for (int i = 0; i < neighborRoutes.size(); i++) {
            double neighborRouteDistance = neighborRoutes.get(i).calTotalDistance();
            if (neighborRouteDistance < currentDistance) {
                currentDistance = neighborRouteDistance;
                shortestRouteNum = i;
            }
        }
        return neighborRoutes.get(shortestRouteNum);
    }

    public ArrayList<Route> getNeighborRoutes(Route currentRoute) {
        ArrayList<Route> allNeighborRoutes = new ArrayList<>();
        ArrayList<City> cities = currentRoute.getCities();
        for (int i = 0; i < cities.size(); i++) {
            for (int j = i + 1; j < cities.size(); j++) {
                ArrayList<City> citiesNew = new ArrayList<>(cities);
                City temp = cities.get(i);
                citiesNew.set(i, cities.get(j));
                citiesNew.set(j, temp);
                Route newNeighborRoute = new Route(citiesNew);
                allNeighborRoutes.add(newNeighborRoute);
            }
        }
        return allNeighborRoutes;
    }
}
