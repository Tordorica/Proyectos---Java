import javafx.scene.shape.Shape;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class Character extends Node{
	
	private Point2D movement;
	private Node character;
	
	public Character(Node character) {
		this.character = character;
		this.movement = new Point2D(0, 0);
	}
	
	public Node getCharacter() {
		return this.character;
	}
	
	public void move() {
		this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
		this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
	}
	
	public void accelerate() {
		double accelerateX = Math.cos(Math.toRadians(this.character.getRotate()));
		double accelerateY = Math.sin(Math.toRadians(this.character.getRotate()));
		
		
		this.movement = movement.add(accelerateX, accelerateY);
		System.out.println(movement);
		
	}
}
