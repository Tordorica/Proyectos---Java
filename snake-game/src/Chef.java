
import javafx.scene.layout.Pane;

import java.util.Random;

public class Chef {
	private Pane layout;
	private Food food;
	
	public Chef(Pane layout) {
		this.layout = layout;
	}
	
	public void cook() {
		Random random = new Random();
		this.food = new Food (random.nextInt(560) + 20, random.nextInt(560) + 20, 5);
		this.layout.getChildren().add(this.food);
	}
	
	public Food getFood() {
		return this.food;
	}
}
