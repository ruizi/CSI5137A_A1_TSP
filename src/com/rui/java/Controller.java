package com.rui.java;

import com.rui.java.Algorithms.HillClimbing;
import com.rui.java.Algorithms.SteepestHillClimbing;
import com.rui.java.Utils.test;

import java.io.*;
import java.util.ArrayList;

public class Controller {
    public static void main(String[] args) {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            ArrayList<City> cities = new ArrayList<>();
            /* 读入TXT文件 */
            String pathname = "src/com/rui/res/a280.tsp.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input.txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            while (true) {
                String line = br.readLine();
                if (line.equals("EOF")) {
                    break;
                }
                //System.out.println(line);
                String[] arr = line.trim().split("\\s+");
                City city = new City(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
                cities.add(city);
            }
            Route initRoute = new Route(cities);
            Route shortestRoute_HillClimbing = new HillClimbing().shortestRoute(initRoute);
            //Route shortestRoute_SteepestHillClimbing = new SteepestHillClimbing().shortestRoute(initRoute);
            //new test().Paint(shortestRoute_HillClimbing);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
