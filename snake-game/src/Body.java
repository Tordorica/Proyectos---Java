import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;

public class Body extends Shape{
	private Pane layout;
	
	
	public Body(Pane layout) {
		this.layout = layout;
	}
	
	public void addTo(Character snake) {
		Rectangle body = new Rectangle(50, 50);
		body.setTranslateX(snake.getTranslateX() + snake.getBoundsInLocal().getWidth());
		body.setTranslateY(snake.getTranslateY());
		this.layout.getChildren().add(snake);
	}
}
