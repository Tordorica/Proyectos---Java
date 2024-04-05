
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

public class Character extends Shape{
	
	private Point2D movement;
	private Shape character;
	
	public Character(Shape character) {
		this.character = character;
		this.movement = new Point2D(1, 0);
	}
	
	public Shape getCharacter() {
		return this.character;
	}
	
	public Point2D getMovement() {
		return this.movement;
	}
	
//	public Character getById(String id) {
//		if (this.character.getId().equals(id)) {
//			return this;
//		}
//		return null;
//	}
		
	public void setMovement(Point2D point) {
		this.movement = point;
	}
	
	public void moveLeft() {
		this.movement = new Point2D(-1, 0);
	}
	
	public void moveRight() {
		this.movement = new Point2D(1, 0);
	}
	
	public void moveUp() {
		this.movement = new Point2D(0, -1);
	}
	
	public void moveDown() {
		this.movement = new Point2D(0, 1);
	}
	
	public void move() {
		this.character.setTranslateX(this.movement.getX());
		this.character.setTranslateY(this.movement.getY());
	}
	
	public boolean collide(Shape other) {
		Shape shape = Shape.intersect(this.character, other);
		if (shape.getBoundsInLocal().getWidth() != -1.0) {
			return true;
		}
		return false;
	}
}
