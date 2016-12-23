package org.jointheleague.curriculum.level_1.holiday_card;

import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class SnowParticle extends Circle {
    private static final double MAX_RADIUS = 4.0;
    private static final double MAX_BLUR = 3.0;
    private static final double MAX_STAGGER = 2.0;

    private double x;
    private double y;
    private double radius;
    private Color color;
    private BoxBlur blur;

    private Random random = new Random();

    public SnowParticle(double initX) {
        super(initX, 0.0, 0.0);
        setRadius(random.nextDouble() * MAX_RADIUS);
        setFill(Color.rgb(255, 255, 255, random.nextDouble()));
        double blurSize = random.nextDouble() * MAX_BLUR + MAX_BLUR;
        setEffect(new BoxBlur(blurSize, blurSize, 2));
    }

    public double update() {
        x += random.nextDouble() * MAX_STAGGER - MAX_STAGGER / 2.0;
        y += random.nextDouble() * MAX_STAGGER;

        this.setTranslateX(x);
        this.setTranslateY(y);

        return y;
    }
}