
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SnakeApplication extends Application{
	
	public static int WIDTH = 600;
	public static int HEIGHT = 600;
	
	@Override
	public void start(Stage window) {
		GridPane layout = new GridPane();
		
		for (int row = 0; row < 24; row++) {
			for (int column = 0; column < 24; column++) {
				ColumnConstraints columnConstraint = new ColumnConstraints(25);
				layout.getColumnConstraints().add(columnConstraint);
			}
			RowConstraints rowConstraint = new RowConstraints(25);
			layout.getRowConstraints().add(rowConstraint);
		}
	
		layout.setPrefSize(WIDTH, HEIGHT);		

		List<Character> snakeBody = new ArrayList<>(); 
		Character body = new Character(new Rectangle(25, 25));
		snakeBody.add(body);
		
		snakeBody.get(0).getCharacter().setId("1");
		
		Chef randomFood = new Chef(layout);
		randomFood.cook();
		
		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		
		new AnimationTimer() {
			private int x;
			private int y;
			
			private long startTime = System.nanoTime();
			@Override
			public void handle(long now) {

				if ((now - startTime) >= 250_000_000) {
					startTime = now;
					
					layout.getChildren().stream()
					.filter(child -> !(child instanceof Circle))
					.forEach(bodyPart -> {
						bodyPart.setId(String.valueOf((Integer.valueOf(bodyPart.getId()) + 1)));
					});
					snakeBody.stream()
						.forEach(bodyPart -> {
							if (Integer.valueOf(bodyPart.getCharacter().getId()) > snakeBody.size()) {
								layout.getChildren().remove(bodyPart.getCharacter());
							}
						});
					Collections.reverse(snakeBody);
					
					scene.setOnKeyPressed((event) -> {
						if (event.getCode() == KeyCode.UP) {
							snakeBody.get(0).moveUp();
							System.out.println("up");
						}
						if (event.getCode() == KeyCode.DOWN) {
							snakeBody.get(0).moveDown();
							System.out.println("down");
						}
						if (event.getCode() == KeyCode.LEFT) {
							snakeBody.get(0).moveLeft();
							System.out.println("left");
						}
						if (event.getCode() == KeyCode.RIGHT) {
							snakeBody.get(0).moveRight();
							System.out.println("right");
						}
					});
					
					if (snakeBody.get(0).collide(randomFood.getFood())) {
						layout.getChildren().remove(randomFood.getFood());
						snakeBody.add(new Character(new Rectangle(25, 25)));
						layout.add(snakeBody.get(snakeBody.size() - 1).getCharacter(), x, y);
						randomFood.cook();
						System.out.println(x + ", " + y);
						this.stop();
					}
					
					for (int i = 0; snakeBody.size() > i; i++) {
						if (i == 0) {
							x += snakeBody.get(0).getMovement().getX();
							y += snakeBody.get(0).getMovement().getY();
							
						}
						
						if (layout.getChildren().contains(snakeBody.get(i).getCharacter())) {
							snakeBody.get(i).getCharacter().setId(String.valueOf(i + 1));
							continue;
						}
						
						snakeBody.get(i).getCharacter().setId(String.valueOf(i + 1));
						layout.add(snakeBody.get(i).getCharacter(), x, y);
					}
					
					
				}
			}	
		}.start();
		
		
		window.setScene(scene);
		window.show();
	}
	
	public static void main(String[] args) {
		System.out.println("Hola");
		launch(args);
	}
	
}
