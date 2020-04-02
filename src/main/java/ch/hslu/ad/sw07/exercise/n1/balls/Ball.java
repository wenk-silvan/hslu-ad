package ch.hslu.ad.sw07.exercise.n1.balls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Ball implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Ball.class);
    private static final String[] colors = {"blue", "red", "yellow", "green", "magenta", "black"};
    private Circle circle;

    @Override
    public void run() {
        var random = new Random();
        var circle = this.createBall(random);
        var speed = Math.max(random.nextInt(7), 1);
        try {
            while (!Thread.currentThread().isInterrupted()) {
                circle.moveVertical(speed);
                if(circle.getY() > 350) {
                    circle.makeInvisible();
                    this.stopRequest();
                }
            }
        } catch (Exception ex) {
            LOGGER.info("Exception thrown: " + ex.getMessage() + "\n StackTrace: ");
            ex.printStackTrace();
        } finally {
            this.stopRequest();
        }
    }

    private Circle createBall(Random random) {
        var xPos = random.nextInt(550);
        var diameter = Math.max(random.nextInt(50), 10);
        var circle = new Circle(diameter, xPos, 0, "black");
        var color = colors[random.nextInt(5)];
        circle.changeColor(color);
        circle.makeVisible();
        return circle;
    }

    private void stopRequest() {
        Thread.currentThread().interrupt();
    }

    public static void main(final String[] args) {
        for (int i = 0; i < 5; i++) {
            createThread(i + 1);
        }
    }

    private static void createThread(int number) {
        final Ball ball = new Ball();
        final Thread thread = new Thread(ball, "Ball-Thread" + number);
        thread.start();
    }
}
