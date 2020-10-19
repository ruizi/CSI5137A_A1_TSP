package com.rui.java.Controller;

import com.rui.java.Views.Algorithms.HillClimbing.FirstAscentHillClimbing;
import com.rui.java.Views.Algorithms.HillClimbing.HillClimbing;
import com.rui.java.Views.Algorithms.HillClimbing.RandomAscentHillClimbing;
import com.rui.java.Views.Algorithms.HillClimbing.SteepestHillClimbing;
import com.rui.java.Views.Algorithms.RandomRestart.RandomRestart;
import com.rui.java.Views.Algorithms.SimulatedAnnealing.SA_Three_Cooling_Schedules;
import com.rui.java.Views.Algorithms.TabuSearch.TabuSearch;
import com.rui.java.Models.City;
import com.rui.java.Models.Route;
import com.rui.java.Utils.test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        String pathname = "src/com/rui/res/";
        String inputFileName = "";
        String filename = "";
        String VisualBoard_Loc = "";
        String Route_Loc = "";
        if (args.length > 1) {
            System.out.println("Invalid input numbers");
            System.exit(1);
        } else if (args.length == 0) {
            System.out.println("Use default input file. (a280.tsp)");
            filename = "a280.tsp";
            inputFileName = pathname + filename;
            VisualBoard_Loc = "src/com/rui/java/Utils/VisualBoard.jpeg";
            Route_Loc = "src/com/rui/java/Utils/Route.png";
        } else {
            filename = args[0];
            inputFileName = filename;
            VisualBoard_Loc = "VisualBoard.jpeg";
            Route_Loc = "Route.png";
        }


        try {

            ArrayList<City> cities = new ArrayList<>();
            /* read TXT file */

            File file = new File(inputFileName);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            boolean fileHeader = true;
            while (true) {
                String line = br.readLine();

                if (line.equals("EOF")) {
                    break;
                }
                if (line.equals("NODE_COORD_SECTION")) {
                    fileHeader = false;
                    continue;
                }
                if (fileHeader) {
                    continue;
                }
                //System.out.println(line);
                String[] arr = line.trim().split("\\s+");
                City city = new City(Integer.parseInt(arr[0]), Double.parseDouble(arr[1]), Double.parseDouble(arr[2]));
                cities.add(city);
            }
            Route initRoute = new Route(cities);

            while (true) {
                System.out.println("Choose the algorithms you want to run! Or Exit.");
                System.out.println("0.Exit.");
                System.out.println("1.Hill Climbing.");
                System.out.println("2.Steepest Hill Climbing.");
                System.out.println("3.First Ascent Hill Climbing.");
                System.out.println("4.Random Ascent Hill Climbing.");
                System.out.println("5.Simulated Annealing Exponential Cooling.");
                System.out.println("6.Simulated Annealing Liner Cooling.");
                System.out.println("7.Simulated Annealing Logarithmic Cooling.");
                System.out.println("8.Tabu Search.");
                System.out.println("9.Random Restart with Steepest Hill Climbing.");
                Scanner scanner = new Scanner(System.in);
                int choseAlgorithms;
                while (true) {
                    choseAlgorithms = scanner.nextInt();
                    if (choseAlgorithms >= 0 && choseAlgorithms <= 9) {
                        break;
                    } else {
                        System.out.println("Input Valid.");
                    }
                }
                Route shortestRoute = null;
                if (choseAlgorithms == 0) {
                    System.out.println("Program ends.bye");
                    break;
                } else if (choseAlgorithms == 1) {
                    shortestRoute = new HillClimbing().shortestRoute(initRoute);
                } else if (choseAlgorithms == 2) {
                    shortestRoute = new SteepestHillClimbing().shortestRoute(initRoute);
                } else if (choseAlgorithms == 3) {
                    shortestRoute = new FirstAscentHillClimbing().shortestRoute(initRoute);
                } else if (choseAlgorithms == 4) {
                    shortestRoute = new RandomAscentHillClimbing().shortestRoute(initRoute);
                } else if (choseAlgorithms == 5) {
                    shortestRoute = new SA_Three_Cooling_Schedules().shortestRoute(initRoute, "Exponential");
                } else if (choseAlgorithms == 6) {
                    shortestRoute = new SA_Three_Cooling_Schedules().shortestRoute(initRoute, "Liner");
                } else if (choseAlgorithms == 7) {
                    shortestRoute = new SA_Three_Cooling_Schedules().shortestRoute(initRoute, "Logarithmic");
                } else if (choseAlgorithms == 8) {
                    shortestRoute = new TabuSearch().shortestRoute(initRoute);
                } else {
                    shortestRoute = new RandomRestart().shortestRoute(initRoute);
                }
                System.out.println(shortestRoute.toString());
                writeSolution(shortestRoute);
                int visual;
                while (true) {
                    System.out.println();
                    System.out.println("========Visualize the travelling path.=========");
                    System.out.println("(1).Painting on the board.");
                    System.out.println("(2).Skip.");
                    visual = scanner.nextInt();
                    if (visual >= 1 && visual <= 2) {
                        break;
                    } else {
                        System.out.println("Input Valid.");
                    }
                }
                if (visual == 1) {
                    new test().Paint(shortestRoute, VisualBoard_Loc, Route_Loc);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeSolution(Route shortRoute) {
        ArrayList<City> cities = shortRoute.getCities();
        File writeFile = new File("solution.csv");

        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(writeFile), StandardCharsets.UTF_8);
            BufferedWriter writeText = new BufferedWriter(out);
            for (City city : cities) {
                writeText.newLine();
                writeText.write(String.valueOf(city.getID()));
            }
            writeText.flush();
            writeText.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到指定文件");
        } catch (IOException e) {
            System.out.println("文件读写出错");
        }

    }
}
