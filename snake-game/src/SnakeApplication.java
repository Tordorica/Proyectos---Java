
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SnakeApplication extends Application{
	
	public static int WIDTH = 600;
	public static int HEIGHT = 600;
	
	@Override
	public void start(Stage window) {
		GridPane layout = new GridPane();
		layout.setStyle("-fx-background-color: #000;");
		
		for (int row = 0; row < 24; row++) {
			for (int column = 0; column < 24; column++) {
				ColumnConstraints columnConstraint = new ColumnConstraints(25);
				layout.getColumnConstraints().add(columnConstraint);
			}
			RowConstraints rowConstraint = new RowConstraints(25);
			layout.getRowConstraints().add(rowConstraint);
		}
	
		layout.setPrefSize(WIDTH, HEIGHT);		
		
		AtomicInteger counter = new AtomicInteger(0);
		
		Text points = new Text("Points : 0");
		points.setFont(new Font(18).font("Arial", FontWeight.BOLD, 18));
		points.setFill(Color.WHITE);
		layout.add(points, 0, 0);
		
		Text gameOver = new Text("Game over!");
		gameOver.setFont(new Font(60));
		gameOver.setFill(Color.RED);
		
		Color snakeColor = Color.GREEN;
		List<Character> snakeBody = new ArrayList<>(); 
		
		Character body = new Character(new Rectangle(25, 25, snakeColor));
		snakeBody.add(body);
		snakeBody.get(0).getCharacter().setId("1");
		
		Chef randomFood = new Chef();
		randomFood.cook();
		Point2D location = randomFood.getLocation();
		layout.add(randomFood.getFood(), (int) location.getX(), (int) location.getY());
		
		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		
		new AnimationTimer() {
			private int x = 11;
			private int y = 11;
			private long time = 200_000_000;
			
			private long startTime = System.nanoTime();
			@Override
			public void handle(long now) {

				if ((now - startTime) >= time) {
					startTime = now;
					
					// Si la serpiente se sale del tablero, el juego se detiene.
					Point2D currentMovement = snakeBody.get(0).getMovement();
					if (((x + currentMovement.getX()) == 24 || (y + currentMovement.getY() == 24)) || ((x + currentMovement.getX()) == -1 || y + currentMovement.getY() == -1)) {
						layout.add(gameOver, 6, 10);
						
						this.stop();
						return;
					}
					
					layout.getChildren().stream()
					.filter(child -> child instanceof Rectangle)
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
					
					HashSet<Point2D> coordinates = new HashSet<>();
				
					layout.getChildren().stream()
					.filter(child -> {
						return child instanceof Rectangle;
					})
					.forEach(child -> {
//						System.out.println(child.getId() + ": " + GridPane.getColumnIndex(child) + ", " + GridPane.getRowIndex(child));
						Point2D coordinate = new Point2D(GridPane.getColumnIndex(child), GridPane.getRowIndex(child));
						coordinates.add(coordinate);
					});
						
					Point2D futureHeadPosition = new Point2D(x + snakeBody.get(0).getMovement().getX(), y + snakeBody.get(0).getMovement().getY());
					// Si la posicion futura de la cabeza coincide con una parte del cuerpo, el juego se detiene.
					if (!coordinates.add(futureHeadPosition)) {
						layout.add(gameOver, 6, 10);
						this.stop();
					}
					
					//Si la cabeza de la serpiente colisiona con comida, se agrega 1.
					if (snakeBody.get(0).collide(randomFood.getFood())) {
						layout.getChildren().remove(randomFood.getFood());
						
						counter.getAndAdd(10);
						points.setText("Points : " + counter.get());
					
						snakeBody.add(new Character(new Rectangle(25, 25, snakeColor)));						
						
						//Si la ubicacion de la comida no coicide con la de la serpiente, agregarla al tablero.
						randomFood.cook();
						Point2D location = randomFood.getLocation();
						if (coordinates.add(location)) {
							layout.add(randomFood.getFood(), (int) location.getX(), (int) location.getY());
						}
						
						// Game Speed
						long rate = 10_000_000 / (snakeBody.size() / 2);
						time -= rate;
						System.out.println(rate);
						
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
