
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

public class SnakeApplication extends Application{
	
	public static int WIDTH = 600;
	public static int HEIGHT = 600;
	
	@Override
	public void start(Stage window) {
		Pane layout = new Pane();
		
		Head head = new Head(WIDTH / 2, HEIGHT / 2, 15);
		
		Character snake = new Character(head);
		
		int squareSize = 25;
		Shape square = new Rectangle(WIDTH / 2 - squareSize / 2, HEIGHT / 2 - squareSize / 2, squareSize , squareSize );
		Body body = new Body(square);
		
		Chef chef = new Chef(layout);
		chef.cook();
		
		layout.getChildren().add(snake.getCharacter());
		
		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				
				scene.setOnKeyPressed((event) -> {
					if (event.getCode() == KeyCode.UP) {
						snake.moveUp();
						System.out.println("up");
					}
					if (event.getCode() == KeyCode.DOWN) {
						snake.moveDown();
						System.out.println("down");
					}
					if (event.getCode() == KeyCode.LEFT) {
						snake.moveLeft();
						System.out.println("left");
					}
					if (event.getCode() == KeyCode.RIGHT) {
						snake.moveRight();
						System.out.println("right");
					}
				});
				
				// Declarando los limites:
				// Se detiene cuando choca contra la barrera derecha
				if (snake.getCharacter().getTranslateX() + 20 > WIDTH / 2) {
					this.stop();
				}
				// Se detiene cuando choca contra la barrera izquierda
				if (snake.getCharacter().getTranslateX() - 20 < WIDTH / -2) {
					this.stop();
				}
				// Barrera superior
				if (snake.getCharacter().getTranslateY() - 20 < HEIGHT / -2) {
					this.stop();
				}
				// Barrera inferior
				if (snake.getCharacter().getTranslateY() + 20 > HEIGHT / 2) {
					this.stop();
				}
				snake.move();
				snake.accelerate();
				
				Food food = chef.getFood();
				if (snake.collide(food)) {
					
					// El orden es importante con estos tres metodos
					body.getCharacter().setRotate(snake.getCharacter().getRotate());
					body.setMovement(new Point2D(snake.getCharacter().getTranslateX(), snake.getCharacter().getTranslateY()));
					
					
					layout.getChildren().remove(food);
					chef.cook();
					layout.getChildren().add(body.getCharacter());
//					this.stop();
				
				};
				
				body.move();
				body.accelerate();
				
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
