
import java.util.List;
import java.util.ArrayList;

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

public class SnakeApplication extends Application{
	
	public static int WIDTH = 600;
	public static int HEIGHT = 600;
	
	@Override
	public void start(Stage window) {
		GridPane layout = new GridPane();
		
		for (int row = 1; row < 25; row++) {
			for (int column = 1; column < 25; column++) {
				ColumnConstraints columnConstraint = new ColumnConstraints(25);
				layout.getColumnConstraints().add(columnConstraint);
			}
			RowConstraints rowConstraint = new RowConstraints(25);
			layout.getRowConstraints().add(rowConstraint);
		}
	
		layout.setPrefSize(WIDTH, HEIGHT);		

		Rectangle square = new Rectangle(25, 25);
		square.setFill(Color.RED);
		
//		List<Body> snakeBody = new ArrayList<>(); 
//		Body head = new Body();
//		snakeBody.add(head);
		
//		layout.getChildren().add(snakeBody.get(0).getCharacter());
				
		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		
		new AnimationTimer() {
			private int index;
			
			@Override
			public void handle(long now) {
				
				scene.setOnKeyPressed((event) -> {
					if (event.getCode() == KeyCode.UP) {
						
						System.out.println("up");
					}
					if (event.getCode() == KeyCode.DOWN) {
						
						System.out.println("down");
					}
					if (event.getCode() == KeyCode.LEFT) {
						
						System.out.println("left");
					}
					if (event.getCode() == KeyCode.RIGHT) {
						
						System.out.println("right");
					}
				});
				
				layout.getChildren().remove(square);
				
				layout.add(square, index, 0);
				index++;

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
