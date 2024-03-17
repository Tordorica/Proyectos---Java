
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

public class Character extends Shape{
	
	private Point2D movement;
	private Shape character;
	
	public Character(Shape character) {
		this.character = character;
		this.movement = new Point2D(0, 0);
	}
	
	public Shape getCharacter() {
		return this.character;
	}
	
	public Point2D getMovement() {
		return this.movement;
	}
	
	public void setMovement(Point2D point) {
		this.movement = point;
	}
	
	public void moveLeft() {
		if (this.character.getRotate() != 0) {
		
			if (this.character.getRotate() != -180) {	
				this.movement = new Point2D(this.character.getTranslateX(), this.character.getTranslateY());
			}
			this.character.setRotate(-180);
		}
	}
	
	public void moveRight() {
		if (this.character.getRotate() != -180 ) {
			
			if (this.character.getRotate() != 0) {
				this.movement = new Point2D(this.character.getTranslateX(), this.character.getTranslateY());
			}
			this.character.setRotate(0);
		
		}
	}
	
	public void moveUp() {
		if (this.character.getRotate() != 90 ) {
			
			if (this.character.getRotate() != -90) {
				this.movement = new Point2D(this.character.getTranslateX(), this.character.getTranslateY());
			}
			this.character.setRotate(-90);
		}
	}
	
	public void moveDown() {
		if (this.character.getRotate() != -90) {
			
			if (this.character.getRotate() != 90) {
				this.movement = new Point2D(this.character.getTranslateX(), this.character.getTranslateY());
			}
			this.character.setRotate(90);
		}
	}
	
	public void move() {
		this.character.setTranslateX(this.movement.getX());
		this.character.setTranslateY(this.movement.getY());
	}
	
	public void accelerate() {
		double accelerateX = Math.cos(Math.toRadians(this.character.getRotate()));
		double accelerateY = Math.sin(Math.toRadians(this.character.getRotate()));
		
		accelerateX *= 3.33;
		accelerateY *= 3.33;
		
		this.movement = movement.add(accelerateX, accelerateY);	
	}
	
	public boolean collide(Shape other) {
		Shape shape = Shape.intersect(this.character, other);
		if (shape.getBoundsInLocal().getWidth() != -1.0) {
			return true;
		}
		return false;
	}
}
