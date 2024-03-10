package asteroids;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.shape.Polygon;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import java.lang.Math;

public class AsteroidsApplication extends Application{
    
    public static int WIDTH = 600;
    public static int HEIGHT = 400;
    
    @Override
    public void start(Stage window) {
        
        Pane layout = new Pane();
        
        AtomicInteger counter = new AtomicInteger();
        
        Text points = new Text(10, 20, "Points: 0");
        points.setFont(new Font(16));
        
        Ship ship = new Ship(WIDTH / 2, HEIGHT - 25);
        ship.getCharacter().setRotate(-90);
        
        List<Asteroid> asteroids = new ArrayList<>();
        
        Random rnd = new Random();
        for (int i = 0; 5 > i; i++) {
            asteroids.add(new Asteroid(rnd.nextInt(WIDTH / 2) + 100, rnd.nextInt(HEIGHT / 4) + 20));
        }

        layout.getChildren().addAll(ship.getCharacter(), points);
        asteroids.forEach(asteroid -> layout.getChildren().add(asteroid.getCharacter()));
        
        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        
        HashMap<KeyCode, Boolean> keyPressed = new HashMap<>();
        
        scene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.LEFT) {
                keyPressed.put(event.getCode(), true);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                keyPressed.put(event.getCode(), true);
            }
            if (event.getCode() == KeyCode.UP) {
                keyPressed.put(event.getCode(), true);
            }
            if (event.getCode() == KeyCode.SPACE) {
                keyPressed.put(event.getCode(), true);
            }
        });
        
        scene.setOnKeyReleased((event) -> {
            if (event.getCode() == KeyCode.LEFT) {
                keyPressed.put(event.getCode(), false);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                keyPressed.put(event.getCode(), false);
            }
            if (event.getCode() == KeyCode.UP) {
                keyPressed.put(event.getCode(), false);
            }
            if (event.getCode() == KeyCode.SPACE) {
                keyPressed.put(event.getCode(), false);
            }
            
        });
        
        List<Projectile> projectiles = new ArrayList<>();
        
        new AnimationTimer() {
            @Override 
            public void handle(long now) {
                
                if (keyPressed.getOrDefault(KeyCode.LEFT, false)) {
                    ship.turnLeft();
                }
                if (keyPressed.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.turnRight();
                }
                if (keyPressed.getOrDefault(KeyCode.UP, false)) {
                    ship.accelerate();
                }
                if (keyPressed.getOrDefault(KeyCode.SPACE, false) && projectiles.size() < 3) {
                    Projectile projectile = new Projectile((int) ship.getCharacter().getTranslateX(), (int) ship.getCharacter().getTranslateY());
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                    
                    projectiles.add(projectile);

                    projectile.accelerate();
                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));
                    
                    layout.getChildren().add(projectile.getCharacter());
                }
                
                ship.move();
                asteroids.forEach(asteroid -> asteroid.move());
                projectiles.forEach(projectile -> projectile.move());
                
                asteroids.forEach(asteroid -> {
                    if (asteroid.collide(ship)) {
                        this.stop();
                    }});
                
                List<Asteroid> collide = new ArrayList<>();
                
                projectiles.forEach(projectile -> {
                    asteroids.stream()
                        .forEach(asteroid -> {
                            if (projectile.collide(asteroid)) {
                                collide.add(asteroid);
                                asteroid.setDestroyer(projectile);
                            }
                        }); 
                });
                
                collide.forEach(asteroid -> {
                    Character projectile = asteroid.getDestroyer();
                    layout.getChildren().removeAll(asteroid.getCharacter(), projectile.getCharacter());
                    asteroids.remove(asteroid);
                    projectiles.remove(projectile);
                    
                    counter.getAndAdd(1000);
                    points.setText("Points: " + counter.get());
                });
                
                if (Math.random() < 0.005) {
                    Asteroid asteroid = new Asteroid(WIDTH, HEIGHT);
                    if (! asteroid.collide(ship)) {
                        layout.getChildren().add(asteroid.getCharacter());
                        asteroids.add(asteroid);
                    }
                }
            }
        }.start();
        
        
        
        window.setTitle("Asteroids Game!");
        window.setScene(scene);
        window.show();
        
        
        
    }
    
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        launch(args);
    }

    public static int partsCompleted() {
        // State how many parts you have completed using the return value of this method
        return 4;
    }

}
