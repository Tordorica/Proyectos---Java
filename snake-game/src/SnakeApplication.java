
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;

public class SnakeApplication extends Application{
	
	public static int WIDTH = 600;
	public static int HEIGHT = 600;
	
	@Override
	public void start(Stage window) {
		Pane layout = new Pane();
		
		Head head = new Head(WIDTH / 2, HEIGHT / 2, 20);
		
		Character snake = new Character(head);
		
		layout.getChildren().add(snake.getCharacter());
		
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				snake.move();
				snake.accelerate();
			}
		}.start();
		
		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		window.setScene(scene);
		window.show();
	}
	
	public static void main(String[] args) {
		System.out.println("Hola");
		launch(args);
	}
	
}
