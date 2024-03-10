package asteroids;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Character {
    
    private Polygon character;
    private Point2D movement;
    private Character destroyer;
    
    public Character(Polygon character, int x, int y) {
        this.character = character;
        this.movement = new Point2D(0, 0);
        
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        
        this.destroyer = null;
    }
    
    public Polygon getCharacter() {
        return this.character;
    }
    
    public Point2D getMovement() {
        return this.movement;
    }
    
    public void setMovement(Point2D movement) {
        this.movement = movement;
    }
    
    public Character getDestroyer() {
        return this.destroyer;
    }
    
    public void setDestroyer(Character destroyer) {
        this.destroyer = destroyer;
    }
    
    public void turnLeft() {
        this.character.setRotate(this.character.getRotate() - 5);
    }
    
    public void turnRight() {
        this.character.setRotate(this.character.getRotate() + 5);
    }
    
    public void move() {
        this.character.setTranslateX(this.character.getTranslateX() + movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + movement.getY());
        
        if (this.character.getTranslateX() < 0) {
            this.character.setTranslateX(this.character.getTranslateX() + AsteroidsApplication.WIDTH);
        }
        if (this.character.getTranslateX() > AsteroidsApplication.WIDTH) {
            this.character.setTranslateX(this.character.getTranslateX() % AsteroidsApplication.WIDTH);
        }
        if (this.character.getTranslateY() < 0) {
            this.character.setTranslateY(this.character.getTranslateY() + AsteroidsApplication.HEIGHT);
        }
        if (this.character.getTranslateY() > AsteroidsApplication.HEIGHT) {
            this.character.setTranslateY(this.character.getTranslateY() % AsteroidsApplication.HEIGHT);
        }
    }
    
    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.character.getRotate()));
        changeX *= 0.025;
        changeY *= 0.025;
        
        this.movement = this.movement.add(changeX, changeY);
    }
    
    public boolean collide(Character other) {
        Shape collideArea = Shape.intersect(this.character, other.character);
        return collideArea.getBoundsInLocal().getWidth() != -1;
    }
}
