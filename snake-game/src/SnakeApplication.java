
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
			private long time = 200_000_000;
			
			private Character head;
			
			private long startTime = System.nanoTime();
			@Override
			public void handle(long now) {

				if ((now - startTime) >= time) {
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

					// Aca suscede la magia
					Character lastBody = snakeBody.get(snakeBody.size() - 1);
					Point2D firstBodyMovement = snakeBody.get(0).getMovement();
					lastBody.setMovement(firstBodyMovement);
					snakeBody.remove(lastBody);
					snakeBody.add(0, lastBody);
					
					scene.setOnKeyPressed((event) -> {
						double currentX = snakeBody.get(0).getMovement().getX();
						double currentY = snakeBody.get(0).getMovement().getY();
						
						if (event.getCode() == KeyCode.UP) {
							if (currentY != 1) {
								snakeBody.get(0).moveUp();
							}
							System.out.println("up");
						}
						if (event.getCode() == KeyCode.DOWN) {
							if (currentY != -1) {
								snakeBody.get(0).moveDown();
							}
							System.out.println("down");
						}
						if (event.getCode() == KeyCode.LEFT) {
							if (currentX != 1) {
								snakeBody.get(0).moveLeft();
							}
							System.out.println("left");
						}
						if (event.getCode() == KeyCode.RIGHT) {
							if (currentX != -1) {
								snakeBody.get(0).moveRight();
							}
							System.out.println("right");
						}
					});	
					
					if (snakeBody.get(0).collide(randomFood.getFood())) {
						layout.getChildren().remove(randomFood.getFood());
						snakeBody.add(new Character(new Rectangle(25, 25)));
//						Character tail = snakeBody.get(snakeBody.size() - 1);
						layout.add(snakeBody.get(snakeBody.size() - 1).getCharacter(), x, y);
						randomFood.cook();
						// Game Speed
						long rate = 10_000_000 / snakeBody.size();
						time -= rate;
						System.out.println(rate);
						System.out.println(x + ", " + y);
						if (snakeBody.size() == 3) {
							this.stop();
						}
						
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
					
					
					snakeBody.stream()
					.forEach(bodyPart -> {
						if (bodyPart.getCharacter().getId().equals("1")) {
							head = bodyPart;	
						}
					});
					snakeBody.stream()
					.filter(bodyPart -> {
						return !(this.head.getCharacter().equals(bodyPart.getCharacter()));
					})
					.forEach(bodyPart -> {
						if (this.head.collide(bodyPart.getCharacter())) {
							this.stop();
						}
					});
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
