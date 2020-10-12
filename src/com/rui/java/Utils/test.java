package com.rui.java.Utils;

import com.rui.java.City;
import com.rui.java.Route;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class test {
//    private static final String STORE_PATH = "src/com/rui/java/Utils/Route.png";
//    private static final String TEMPLATE_PATH = "src/com/rui/java/Utils/VisualBoard.jpeg";

    public void Paint(Route route,String VisualBoard_Loc,String Route_Loc) throws Exception {

        long start = System.currentTimeMillis();

        BufferedImage image = ImageIO.read(new File(VisualBoard_Loc));
        int boardHeight = image.getHeight();
        int boardWidth = image.getWidth();
//        System.out.println(boardHeight);
//        System.out.println(boardWidth);
        // create graphics
        Graphics2D graphics = image.createGraphics();

        // set graphics profile
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        // draw line
        graphics.setPaint(Color.BLACK);
        ArrayList<City> cities = route.getCities();
        double X_MAX = route.getLoc_X_Max();
        double Y_MAX = route.getLoc_Y_Max();
//        System.out.println(X_MAX);
//        System.out.println(Y_MAX);
        for (int i = 0; i < cities.size() - 1; i++) {
            double x1 = (cities.get(i).getCoordinate_x() * 1.0 / X_MAX) * boardWidth * 0.95;
            double y1 = (cities.get(i).getCoordinate_y() * 1.0 / Y_MAX) * boardHeight * 0.95;
            double x2 = (cities.get(i + 1).getCoordinate_x() * 1.0 / X_MAX) * boardWidth * 0.95;
            double y2 = (cities.get(i + 1).getCoordinate_y() * 1.0 / Y_MAX) * boardHeight * 0.95;
            graphics.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
        double x1 = (cities.get(cities.size() - 1).getCoordinate_x() * 1.0 / X_MAX) * boardWidth * 0.95;
        double y1 = (cities.get(cities.size() - 1).getCoordinate_y() * 1.0 / Y_MAX) * boardHeight * 0.95;
        double x2 = (cities.get(0).getCoordinate_x() * 1.0 / X_MAX) * boardWidth * 0.95;
        double y2 = (cities.get(0).getCoordinate_y() * 1.0 / Y_MAX) * boardHeight * 0.95;
        graphics.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        // store and end
        graphics.dispose();
        ImageIO.write(image, "PNG", new File(Route_Loc));

        //System.out.println(System.currentTimeMillis() - start);
    }
}
