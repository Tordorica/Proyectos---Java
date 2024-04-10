
import javafx.scene.layout.GridPane;
import javafx.geometry.Point2D;

import java.util.Random;

public class Chef {
//	private GridPane layout;
	private Food food;
	private Point2D location;
	
	public Chef() {
//		this.layout = layout;
	}
	
	public void cook() {
		this.food = new Food(12.5, 12.5, 8);
		
		Random random = new Random();
		int randomX = random.nextInt(22 + 1);
		int randomY = random.nextInt(22 + 1);
		location = new Point2D(randomX, randomY);
//		System.out.println("Food(x,y): " + randomX + ", " + randomY);
//		this.layout.add(this.food, randomX, randomY);
	}
	
	public Food getFood() {
		return this.food;
	}
	
	public Point2D getLocation() {
		return this.location;
	}
	
}
