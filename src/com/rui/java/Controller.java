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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        String pathname = "src/com/rui/res/"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        //String pathname = "src/com/rui/res/a280.tsp.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        String inputFileName = "";
        String filename = "";
        String VisualBoard_Loc = "";
        String Route_Loc = "";
        if (args.length > 1) {
            System.out.println("Invalid input numbers");
            System.exit(1);
        } else if (args.length == 0) {
            System.out.println("Use default input file. (a280.tsp)");
            filename = "ch150.tsp.txt";
            inputFileName = pathname + filename;
            VisualBoard_Loc = "src/com/rui/java/Utils/VisualBoard.jpeg";
            Route_Loc = "src/com/rui/java/Utils/Route.png";
        } else {
            filename = args[0];
            inputFileName = filename; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            VisualBoard_Loc = "VisualBoard.jpeg";
            Route_Loc = "Route.png";
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
            writeSolution(shortestRoute);
            new test().Paint(shortestRoute, VisualBoard_Loc, Route_Loc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeSolution(Route shortRoute) {
        ArrayList<City> cities = shortRoute.getCities();
        //第一步：设置输出的文件路径
        //如果该目录下不存在该文件，则文件会被创建到指定目录下。如果该目录有同名文件，那么该文件将被覆盖。
        File writeFile = new File("solution.csv");

        try {
            //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(writeFile), StandardCharsets.UTF_8);
            BufferedWriter writeText = new BufferedWriter(out);

            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            for (City city : cities) {
                writeText.newLine();    //换行
                //调用write的方法将字符串写到流中
                writeText.write(String.valueOf(city.getID()));
            }

            //使用缓冲区的刷新方法将数据刷到目的地中
            writeText.flush();
            //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
            //因此，此处的close()方法关闭的是被缓存的流对象
            writeText.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到指定文件");
        } catch (IOException e) {
            System.out.println("文件读写出错");
        }

    }
}
