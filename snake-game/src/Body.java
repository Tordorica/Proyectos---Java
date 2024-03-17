import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;

public class Body extends Character{
	private Shape body;
	private double rotation;
	
	
	public Body(Shape rectangle) {
		super(rectangle);
		this.body = super.getCharacter();
	}
	
	public void setRotation(int rotation) {
		this.body.setRotate(rotation);
	}
	
	public void setMovement(Point2D movement) {
		movement = movement.add(-25, 0);
		super.setMovement(movement);
	}
}
