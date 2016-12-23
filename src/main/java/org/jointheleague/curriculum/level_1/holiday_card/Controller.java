package org.jointheleague.curriculum.level_1.holiday_card;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import sun.awt.AWTAccessor;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    public AnchorPane pane;
    private ArrayList<SnowParticle> particles;
    private Random random = new Random();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Animation for snow flying
        AnimationTimer timer = new AnimationTimer() {
            private long startExport = 0;
            boolean capturing = false;

            @Override
            public void handle(long now) {
                snowUpdate();
                int w = (int) pane.getBoundsInParent().getWidth();
                int h = (int) pane.getBoundsInParent().getHeight();
                WritableImage img = new WritableImage(w, h);
                pane.snapshot(null, img);
            }
        };
        timer.start();
    }

    private void initSnowParticles() {
        particles = new ArrayList<>();

        // Initially, there are 5 snow particles
        int size = random.nextInt(5);
        for (int i = 0; i < size; i++) {
            SnowParticle particle = new SnowParticle(random.nextDouble() * pane.getBoundsInLocal().getWidth());
            pane.getChildren().add(particle);
            particles.add(particle);
        }
    }

    private void snowUpdate() {
        if (particles == null) {
            initSnowParticles();
        }

        Iterator<SnowParticle> it = particles.iterator();
        while (it.hasNext()) {
            SnowParticle particle = it.next();

            double y = particle.update();
            if (y >= pane.getHeight()) {
                it.remove();
            }
        }

        if (random.nextInt(3) == 0) {
            SnowParticle particle = new SnowParticle(random.nextDouble() * pane.getBoundsInLocal().getWidth());
            pane.getChildren().add(particle);
            particles.add(particle);
        }
    }
}
