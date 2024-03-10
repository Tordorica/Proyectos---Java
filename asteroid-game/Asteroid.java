
package asteroids;

import javafx.scene.shape.Polygon;
import java.util.Random;

public class Asteroid extends Character{
    
    private double rotationalMovement;
    
    public Asteroid(int x, int y) {
        super(new PolygonFactory().createAsteroid(), x, y);
        
        Random rnd = new Random();
        
        int accelerationCalls = rnd.nextInt(15);
        
        for (int i = 0; accelerationCalls > i; i++) {
            accelerate();
        }
        
        this.rotationalMovement = rnd.nextDouble() - 0.5;
    }
    
    @Override
    public void move() {
        super.move();
        super.getCharacter().setRotate(super.getCharacter().getRotate() + this.rotationalMovement);
    }
    
}
