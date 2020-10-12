package com.rui.java;

import com.rui.java.Algorithms.HillClimbing.FirstAscentHillClimbing;
import com.rui.java.Algorithms.HillClimbing.HillClimbing;
import com.rui.java.Algorithms.HillClimbing.RandomAscentHillClimbing;
import com.rui.java.Algorithms.HillClimbing.SteepestHillClimbing;
import com.rui.java.Algorithms.RandomRestart.RandomRestart;
import com.rui.java.Algorithms.SimulatedAnnealing.SA_Three_Cooling_Schedules;
import com.rui.java.Algorithms.TabuSearch.TabuSearch;
import com.rui.java.Utils.test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        String pathname = "src/com/rui/res/"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        //String pathname = "src/com/rui/res/a280.tsp.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        String inputFileName = "";
        String filename = "";
        if (args.length > 1) {
            System.out.println("Invalid input numbers");
            System.exit(1);
        } else if (args.length == 0) {
            System.out.println("Use default input file. (a280.tsp)");
            filename = "ch150.tsp.txt";
            inputFileName = pathname + filename;
        } else {
            filename = args[0];
            inputFileName = filename; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        }


        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            ArrayList<City> cities = new ArrayList<>();
            /* 读入TXT文件 */

            File file = new File(inputFileName); // 要读取以上路径的input.txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
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
                System.out.println(line);
                String[] arr = line.trim().split("\\s+");

                City city = new City(Integer.parseInt(arr[0]), Double.parseDouble(arr[1]), Double.parseDouble(arr[2]));
                cities.add(city);
            }
            Route initRoute = new Route(cities);

            System.out.println("Choose the algorithms you want to run!");
            System.out.println("1.Hill Climbing.");
            System.out.println("2.Steepest Hill Climbing.");
            System.out.println("3.First Ascent Hill Climbing.");
            System.out.println("4.Random Ascent Hill Climbing.");
            System.out.println("5.Simulated Annealing Exponential Cooling.");
            System.out.println("6.Simulated Annealing Liner Cooling.");
            System.out.println("7.Simulated Annealing Logarithmic Cooling.");
            System.out.println("8.Tabu Search.");
            Scanner scanner = new Scanner(System.in);
            int choseAlgorithms;
            while (true) {
                choseAlgorithms = scanner.nextInt();
                if (choseAlgorithms >= 1 && choseAlgorithms <= 8) {
                    break;
                } else {
                    System.out.println("Input Valid.");
                }
            }
            Route shortestRoute = null;
            if (choseAlgorithms == 1) {
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
            } else {
                shortestRoute = new TabuSearch().shortestRoute(initRoute);
            }
            System.out.println(shortestRoute.toString());
            new test().Paint(shortestRoute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
