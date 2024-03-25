
import java.util.List;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class SnakeApplication extends Application{
	
	public static int WIDTH = 600;
	public static int HEIGHT = 600;
	
	@Override
	public void start(Stage window) {
		Pane layout = new Pane();
		
//		Head head = new Head(WIDTH / 2, HEIGHT / 2, 15);
		
//		Character snake = new Character(head);

		List<Body> snakeBody = new ArrayList<>(); 
		Body head = new Body();
		snakeBody.add(head);
		
		Chef chef = new Chef(layout);
		chef.cook();
		
		layout.getChildren().add(snakeBody.get(0).getCharacter());
				
		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		
		new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				scene.setOnKeyPressed((event) -> {
					if (event.getCode() == KeyCode.UP) {
						snakeBody.get(0).moveUp();
						turnBody(snakeBody);
						System.out.println("up");
					}
					if (event.getCode() == KeyCode.DOWN) {
						snakeBody.get(0).moveDown();
						turnBody(snakeBody);
						System.out.println("down");
					}
					if (event.getCode() == KeyCode.LEFT) {
						snakeBody.get(0).moveLeft();
						turnBody(snakeBody);
						System.out.println("left");
					}
					if (event.getCode() == KeyCode.RIGHT) {
						snakeBody.get(0).moveRight();
						turnBody(snakeBody);
						System.out.println("right");
					}
				});
				
				// Declarando los limites:
				// Se detiene cuando choca contra la barrera derecha
				if (snakeBody.get(0).getCharacter().getTranslateX() + 9.9 > WIDTH / 2) {
					this.stop();
				}
				// Se detiene cuando choca contra la barrera izquierda
				if (snakeBody.get(0).getCharacter().getTranslateX() - 9.9 < WIDTH / -2) {
					this.stop();
				}
				// Barrera superior
				if (snakeBody.get(0).getCharacter().getTranslateY() - 9.9 < HEIGHT / -2) {
					this.stop();
				}
				// Barrera inferior
				if (snakeBody.get(0).getCharacter().getTranslateY() + 9.9 > HEIGHT / 2) {
					this.stop();
				}
				
				Food food = chef.getFood();
				if (snakeBody.get(0).collide(food)) {
				
					layout.getChildren().remove(food);
					chef.cook();
					
					Character head = snakeBody.get(snakeBody.size() - 1);
					snakeBody.add(new Body(head));
					snakeBody.stream()
					.filter(body -> {
						return !(layout.getChildren().contains(body.getCharacter()));
					})
					.forEach(body -> {
						body.setHeadMovement();
						body.getCharacter().setRotate(body.getHead().getCharacter().getRotate());
						body.accelerate();
						body.move();
						System.out.println("Rotation: " + body.getCharacter().getRotate());
						layout.getChildren().add(body.getCharacter());
					});
					
					System.out.println(snakeBody.size());
//					this.stop();
				
				}

				snakeBody.stream()
					.forEach(body -> {
						body.accelerate();
						body.move();
					});
			}
		}.start();
		
		
		window.setScene(scene);
		window.show();
	}
	
	public void turnBody(List<Body> body) {
		for (int i = 1; body.size() > i; i++) {
			double rotation = body.get(i).getHead().getCharacter().getRotate();
			body.get(i).getCharacter().setRotate(rotation);
			body.get(i).setHeadMovement();
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("Hola");
		launch(args);
	}
	
}
