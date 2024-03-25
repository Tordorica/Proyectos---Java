import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Body extends Character{
	private Shape body;
	private Character head;
	
	public Body() {
		super(new Rectangle(SnakeApplication.WIDTH / 2 - 20 / 2, SnakeApplication.HEIGHT / 2 - 20/ 2, 20 , 20 ));
		this.body = super.getCharacter();
	}
	public Body(Character head) {
		this();
		this.head = head;
	}
	
	public Character getHead() {
		return this.head;
	}
	
	public void setRotation(int rotation) {
		this.body.setRotate(rotation);
	}
	
	public void setMovement(Point2D movement) {
		super.setMovement(movement);
	}
	
	public void setHeadMovement() {
		Point2D headMovement = this.head.getMovement();
		double headRotation = this.head.getCharacter().getRotate();
		if (headRotation == 0) {
			headMovement = headMovement.add(-25, 0);
			super.setMovement(headMovement);
		}
		if (headRotation == -180) {
			headMovement = headMovement.add(25, 0);
			super.setMovement(headMovement);
		}
		if (headRotation == -90) {
			headMovement = headMovement.add(0, 25);
			super.setMovement(headMovement);
		}
		if (headRotation == 90) {
			headMovement = headMovement.add(0, -25);
			super.setMovement(headMovement);
		}
	}
}
