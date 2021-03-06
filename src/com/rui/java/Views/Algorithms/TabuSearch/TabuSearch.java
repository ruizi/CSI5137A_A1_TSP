package com.rui.java.Views.Algorithms.TabuSearch;

import com.rui.java.Models.City;
import com.rui.java.Models.Route;

import java.util.ArrayList;

public class TabuSearch {
    public static final int MAX_ITERATION = 1000;
    public static final int MAX_TABU_SIZE = 10;

    public Route shortestRoute(Route currentRoute) {
        int iterationCount = 0;
        Route bestRoute = currentRoute; //this stores the best points the algorithm has got. 用来存储已经直到的最优点，区别于climbing中s即是当前点也是最优点
        ArrayList<Route> tabuList = new ArrayList<>();
        while (iterationCount < MAX_ITERATION) {
            ArrayList<Route> neighborRoutes = getNeighborRoutes(new Route(currentRoute));
            Route bestNeighborRoute = null;
            for (Route neighborRoute : neighborRoutes) {
                double neighborRouteDistance = neighborRoute.calTotalDistance();
                //remember visited solutions and avoid coming back.
                if ((!tabuList.contains(neighborRoute)) && (bestNeighborRoute == null || neighborRouteDistance < bestNeighborRoute.calTotalDistance())) { //如果不在tabuList中，同时效果比当前好
                    bestNeighborRoute = neighborRoute;
                }
            }
            currentRoute = bestNeighborRoute;
            if (currentRoute.calTotalDistance() < bestRoute.calTotalDistance()) { //if current route is better than the global optima far now, then the global best move to current route. 如果当前最优点比全局最优点都好，就把全局最优更新
                bestRoute = currentRoute;
                tabuList.add(currentRoute);
            }
            if (tabuList.size() > MAX_TABU_SIZE) {
                tabuList.remove(0);
            }
            if (iterationCount % 10 == 0) {
                System.out.println("Iteration:" + iterationCount + "｜ Route distance:" + currentRoute.calTotalDistance());
            }
            iterationCount += 1;
        }
        System.out.println("May be the shortest distance : " + bestRoute.calTotalDistance());
        return bestRoute;
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
