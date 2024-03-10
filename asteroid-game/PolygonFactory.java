package asteroids;

import javafx.scene.shape.Polygon;
import java.util.Random;

public class PolygonFactory {
    
    public Polygon createAsteroid() {
        Random rnd = new Random();
        int size = (rnd.nextInt(10) + 1) * 5;
        
        double pi = Math.PI; 
        double c1 = Math.cos((2.0/5) * pi) * size;
        double c2 = Math.cos((pi/5)) * size;
        double s1 = Math.sin((2.0/5) * pi) * size;
        double s2 = Math.sin((4.0/5) * pi) * size;
        
        Polygon pentagon = new Polygon(0, size, s1, c1, s2, -c2, -s2, -c2, -s1, c1);
        
        for (int i = 0; i < pentagon.getPoints().size(); i++) {
            int change = rnd.nextInt(5) - 2;
            pentagon.getPoints().set(i, pentagon.getPoints().get(i) + change);
        }
        
        int angle = rnd.nextInt(360) - 180;
        pentagon.setRotate(angle);

        return pentagon;
    }
}
