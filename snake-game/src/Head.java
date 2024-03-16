import javafx.scene.shape.Circle;

public class Head extends Circle{

	public Head(double x, double y, double r) {
		super(x, y, r);
		super.setRotate(-90);
	}
}
