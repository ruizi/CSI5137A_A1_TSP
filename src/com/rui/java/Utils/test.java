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
    private static final String STORE_PATH = "src/com/rui/java/Utils/result.png";
    private static final String TEMPLATE_PATH = "src/com/rui/java/Utils/temp.jpeg";

    public void Paint(Route route) throws Exception {

        long start = System.currentTimeMillis();

        BufferedImage image = ImageIO.read(new File(TEMPLATE_PATH));

        // create graphics
        Graphics2D graphics = image.createGraphics();

        // set graphics profile
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        // draw line
        graphics.setPaint(Color.ORANGE);
        ArrayList<City> cities = route.getCities();
        for (int i = 0; i < cities.size() - 1; i++) {
            int x1 = cities.get(i).getCoordinate_x();
            int y1 = cities.get(i).getCoordinate_y();
            int x2 = cities.get(i + 1).getCoordinate_x();
            int y2 = cities.get(i + 1).getCoordinate_y();
            graphics.drawLine(x1, y1, x2, y2);
        }
        int x1 = cities.get(cities.size() - 1).getCoordinate_x();
        int y1 = cities.get(cities.size() - 1).getCoordinate_y();
        int x2 = cities.get(0).getCoordinate_x();
        int y2 = cities.get(0).getCoordinate_y();
        graphics.drawLine(x1, y1, x2, y2);
        // store and end
        graphics.dispose();
        ImageIO.write(image, "PNG", new File(STORE_PATH));

        System.out.println(System.currentTimeMillis() - start);
    }
}
