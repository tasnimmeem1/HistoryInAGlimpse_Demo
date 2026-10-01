package org.example.historyinaglimpse;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

/** Independently moving figures over a stationary illustrated harbor. */
final class LivingHarbor extends StackPane {
    private static final double WIDTH = 1000, HEIGHT = 2000.0 / 3;
    private final Group world;
    private final ImageView boat;
    private final ImageView merchant;
    private final Image poses;
    private final Ellipse wake = new Ellipse();
    private final Animation motion;
    private int previousFrame = -1;

    LivingHarbor() {
        ImageView background = view(image("phoenician-harbor-background.png"), WIDTH, HEIGHT);
        boat = view(image("phoenician-boat.png"), 340, 680.0 / 3);
        poses = image("phoenician-merchant-walk.png");
        merchant = view(poses, 170, 170);
        wake.setFill(Color.TRANSPARENT);
        wake.setStroke(Color.web("#e7efdd", .58));
        wake.setStrokeWidth(2); wake.setRadiusX(130); wake.setRadiusY(9);
        Pane scene = new Pane(background, wake, boat, merchant);
        scene.resize(WIDTH, HEIGHT);
        scene.setPrefSize(WIDTH, HEIGHT);
        scene.setClip(new Rectangle(WIDTH, HEIGHT));
        world = new Group(scene);
        getChildren().add(world);
        setMinSize(0, 0);
        setPrefSize(760, 510);
        motion = new Transition() {
            {
                setCycleDuration(Duration.seconds(48));
                setCycleCount(Animation.INDEFINITE);
                setInterpolator(Interpolator.LINEAR);
            }
            @Override protected void interpolate(double fraction) { render(fraction * 48); }
        };
        render(0);
    }

    Animation animation() { return motion; }

    private void render(double seconds) {
        // The boat sails across open water, turning at either end of its route.
        double sailing = (seconds % 48) / 48;
        double boatProgress = triangle(sailing);
        double boatX = 40 + 310 * boatProgress;
        double bob = Math.sin(seconds * Math.PI * 2 / 3) * 2;
        boat.setLayoutX(boatX); boat.setLayoutY(360 + bob);
        boat.setScaleX(sailing < .5 ? 1 : -1);
        boat.setRotate(Math.sin(seconds * Math.PI * 2 / 4) * 1.4);
        wake.setCenterX(boatX + 170); wake.setCenterY(581 + bob);
        wake.setRadiusY(8 + Math.sin(seconds * Math.PI * 2 / 3) * 2);

        // Change the leg poses while the merchant follows the perspective of the quay.
        double walking = (seconds % 16) / 16;
        double progress = triangle(walking);
        double centerX = 690 + 170 * progress;
        double feetY = 310 + 42.5 * progress;
        merchant.setLayoutX(centerX - 85);
        merchant.setLayoutY(feetY - 167);
        merchant.setScaleX(walking < .5 ? 1 : -1);
        int frame = (int) (seconds / .12) % 8;
        if (frame != previousFrame) {
            double width = poses.getWidth() / 4, height = poses.getHeight() / 2;
            merchant.setViewport(new Rectangle2D((frame % 4) * width, (frame / 4) * height, width, height));
            previousFrame = frame;
        }
    }

    private static double triangle(double phase) { return phase < .5 ? phase * 2 : (1 - phase) * 2; }

    @Override protected void layoutChildren() {
        double scale = Math.max(0, Math.min(getWidth() / WIDTH, getHeight() / HEIGHT));
        world.getTransforms().setAll(new Scale(scale, scale, 0, 0));
        world.relocate((getWidth() - WIDTH * scale) / 2, (getHeight() - HEIGHT * scale) / 2);
    }

    private static ImageView view(Image image, double width, double height) {
        ImageView result = new ImageView(image);
        result.setFitWidth(width); result.setFitHeight(height);
        result.setPreserveRatio(false); result.setSmooth(true);
        return result;
    }
    private static Image image(String name) {
        var resource = LivingHarbor.class.getResource("/images/" + name);
        if (resource == null) throw new IllegalStateException("Missing harbor layer: " + name);
        Image image = new Image(resource.toExternalForm());
        if (image.isError()) throw new IllegalStateException("Cannot read harbor layer: " + name);
        return image;
    }
}
