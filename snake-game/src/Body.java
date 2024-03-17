import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;

public class Body extends Character{
	private Shape body;
	private Shape snake;
	
	
	public Body(Shape rectangle) {
		super(rectangle);
		this.body = super.getCharacter();
	}
	
	public void addTo(Shape snake) {
		this.snake = snake;
		this.body.setTranslateX(snake.getTranslateX());
		this.body.setTranslateY(snake.getTranslateY());		
	}
	
	public void setRotation(int rotation) {
		this.body.setRotate(rotation);
	}
	
	public void setMovement(Point2D movement) {
		super.setMovement(movement);
	}
}
