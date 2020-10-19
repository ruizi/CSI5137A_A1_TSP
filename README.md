# CSI5137A_A1_TSP

The repository for assignment of CSI5137A

Attach a jar file in the Fold: [TSP_Project/out/artifacts/TSP_Project_jar](https://github.com/ruizi/CSI5137A_A1_TSP/tree/master/out/artifacts/TSP_Project_jar)

Running this jar with the following scripts
```shell script
java -jar TSP_Project.jar ch150.tsp
```

The VisualBoard.jpeg is a paint board to visualise the travelling path.
The output is the Route.png

a280.tsp path with “Simulated Annealing Exponential Cooling”.
![image](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F0205693a-bc31-41e5-817b-637b45a8d905%2F2020-10-19_17.47.47.png?table=block&id=0a95b293-9663-47e6-a921-7b9e5b7687da&width=1510&userId=&cache=v2)

Cook Book:
---

# 1. Project Structure

---

After downloaded this project from web drive, while you open it with intelliJ IDEA or Eclipse, the project structure will be as follow:

```java
.
├── README.md
├── TSP_Project.iml
├── solution.csv
├── src
│   ├── META-INF
│   │   └── MANIFEST.MF
│   └── com
│       └── rui
│           ├── java
│           │   ├── Controller
│           │   │   └── Controller.java
│           │   ├── Models
│           │   │   ├── City.java
│           │   │   └── Route.java
│           │   ├── Utils
│           │   │   ├── VisualBoard.jpeg
│           │   │   └── test.java
│           │   └── Views
│           │       └── Algorithms
│           │           ├── HillClimbing
│           │           │   ├── FirstAscentHillClimbing.java
│           │           │   ├── HillClimbing.java
│           │           │   ├── RandomAscentHillClimbing.java
│           │           │   └── SteepestHillClimbing.java
│           │           ├── RandomRestart
│           │           │   └── RandomRestart.java
│           │           ├── SimulatedAnnealing
│           │           │   └── SA_Three_Cooling_Schedules.java
│           │           └── TabuSearch
│           │               └── TabuSearch.java
│           └── res
│               └── a280.tsp
└── test
    └── com
        └── rui
            └── java
                └── myTest.java
```

This project mainly consist by 2 different parts.

1. the `java` folder under the path: `src/com/rui` 
    1. this folder contains algorithms I implemented in this assignment.
    2. And the `Controller.java`file is the core control module of this project.
    3. Also there are two important models class (`City.java` and `Route.java`)there.
    4. the Utils folder contains the painting board which used to visualize the routing path and `test.java`implements the painting function 
2. the  `res` fold under the path:`src/com/rui` 

    The input files like a280.tsp are stored here as resource file of this project.
    
# 2. Complier & Building

---

The JDK version of this project is :11.0.6

✔️Please IDE to complier this project.

This project was developed with IntelliJ IDEA which is a integrated development environment. And it follows Model-View-Controller (MVC) architectural pattern, as a result, if you want to change some parameters in this project and then complier & build this project by yourself, please run the `Controller.java`  class under the "Controller" folder in IDE. Otherwise, you can run the jar file attached to simplify your life.

![image](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F85a6cd4f-dc5d-4507-ab19-717d45caa7e0%2F2020-10-18_20.44.57.png?table=block&id=d864c706-1429-46ea-afab-18df1aaa4c6e&width=1250&userId=&cache=v2)

# 3. Running

---

Two different ways: 

## a. By using IDE

---

If you use IDE to open this project ⇒ running the `Controller.java` class and then the algorithms choice panel will show in you terminal.

![image](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fa18108cc-4556-4c48-a66e-abce881704f8%2F2020-10-18_20.52.13.png?table=block&id=d7d4e6f8-996b-49b5-b9cb-3c52a3396ab1&width=1250&userId=&cache=v2)

Controller.java

![image](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F5c0ecd94-d401-4c7c-9761-b248271a1ce5%2F2020-10-18_20.49.49.png?table=block&id=6801a2e6-1872-4d2c-9be5-5780ce75b736&width=1250&userId=&cache=v2)

Terminal outputs.

## b. By using terminal

---

Using the Attach jar file in the Fold: TSP_Project/out/artifacts/TSP_Project_jar

Running this jar with the following scripts

```java
java -jar TSP_Project.jar ch150.tsp
```

![image](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fbb2ac7b4-59b2-4a50-a79e-0967d6520dbf%2F2020-10-18_20.58.58.png?table=block&id=feedf67a-2995-480d-a2aa-6cffc5f6e5bc&width=1250&userId=&cache=v2)

# 4. Results

---

## a. The terminal outputs

---

The program will print the shorting travelling distance the algothms found this turn, also, the mapped routing path over all the cities. besides, it will also generate two files which is the "solution.csv" and "Route.png". 

## b. The "solution.csv"

---

Once the algorithm finished, it will automiclly generate an output file called "`solution.csv`" which has the shortest travel path the algorithm found in this turn. 

To read solution.csv, we can using the following scripts:

```bash
cat solution.csv
```

![image](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fa18108cc-4556-4c48-a66e-abce881704f8%2F2020-10-18_20.52.13.png?table=block&id=d7d4e6f8-996b-49b5-b9cb-3c52a3396ab1&width=1250&userId=&cache=v2)

## c. Travelling Path

---

when the algorithm finished finding shorted routing path, the program will ask you if you want a visualize travelling path of this turn. If you input 1 for yes, it will start to paint all the cities on the painting board and use lines to show the shortest travelling path the algorithms found.

![image](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F6fae6c46-6c1a-46a5-8f03-cbacb2b0486b%2F2020-10-18_21.25.45.png?table=block&id=39925ad4-9bd1-48d2-b7f8-d71efe811e11&width=1800&userId=&cache=v2)

a280.tsp path with “Simulated Annealing Exponential Cooling”.

![image](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F0205693a-bc31-41e5-817b-637b45a8d905%2F2020-10-19_17.47.47.png?table=block&id=0a95b293-9663-47e6-a921-7b9e5b7687da&width=1510&userId=&cache=v2)
