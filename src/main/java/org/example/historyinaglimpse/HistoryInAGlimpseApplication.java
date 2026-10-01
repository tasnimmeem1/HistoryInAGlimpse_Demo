package org.example.historyinaglimpse;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/** Native JavaFX desktop demo: an original timeline overview and animated story. */
public class HistoryInAGlimpseApplication extends Application {
    private final BorderPane root = new BorderPane();
    private final ScrollPane chartScroll = new ScrollPane();
    private final Pane chart = new Pane();
    private final Label zoomLabel = new Label();
    private double zoom = .65;
    private ScenePlayback playback;
    private Animation storyAnimation;

    private Image asset(String name) {
        var url = getClass().getResource("/images/" + name);
        if (url == null) throw new IllegalStateException("Missing image: " + name);
        Image image = new Image(url.toExternalForm());
        if (image.isError()) throw new IllegalStateException("Cannot read image: " + name, image.getException());
        return image;
    }

    @Override public void start(Stage stage) {
        buildOriginalChart();
        chartScroll.setContent(new Group(new Group(chart)));
        chartScroll.setPannable(true);
        chartScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        chartScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        showSplash();
        Scene scene = new Scene(root, 1200, 780);
        scene.getStylesheets().add(getClass().getResource("/chart.css").toExternalForm());
        stage.setTitle("History in a Glimpse");
        stage.setScene(scene);
        stage.setMinWidth(850);
        stage.setMinHeight(600);
        stage.setOnCloseRequest(e -> stopAnimation());
        stage.show();
    }

    private void showSplash() {
        stopAnimation();
        Label title = new Label("History in a Glimpse");
        title.getStyleClass().add("splash-title");
        title.setWrapText(true); title.setMaxWidth(850);
        title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        Label subtitle = new Label("Explore history through illustrated stories");
        subtitle.getStyleClass().add("splash-subtitle"); subtitle.setWrapText(true);
        subtitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        Separator divider = new Separator(); divider.setMaxWidth(240);
        Label credit = new Label("Inspired by Sebastian C. Adams");
        credit.getStyleClass().add("splash-credit"); credit.setWrapText(true);
        credit.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        Label chartName = new Label("Synchronological Chart of Universal History");
        chartName.setWrapText(true); chartName.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        Hyperlink reference = new Hyperlink("View the historical inspiration");
        reference.setOnAction(e -> getHostServices().showDocument("https://www.davidrumsey.com/blog/2012/3/28/timeline-maps"));
        Button enter = button("Explore history", this::showChart);
        enter.setId("enter-history"); enter.setDefaultButton(true); enter.getStyleClass().add("splash-enter");
        VBox content = new VBox(22, title, subtitle, divider, credit, chartName, reference, enter);
        content.setAlignment(javafx.geometry.Pos.CENTER); content.setPadding(new Insets(60));
        content.setMaxWidth(1000); content.getStyleClass().add("splash-card");
        StackPane splash = new StackPane(content); splash.getStyleClass().add("splash-root");
        splash.setPadding(new Insets(45));
        root.setTop(null); root.setBottom(null); root.setCenter(splash);
    }

    private void buildOriginalChart() {
        RecreatedChart.draw(chart, index -> {
            showEvent(EventCatalog.EVENTS.get(index));
        });
    }

    private void showEvent(HistoricalEvent event) {
        stopAnimation();
        boolean harborScene = event.title().equals("Phoenician seafaring");
        boolean campScene = EventCatalog.EVENTS.indexOf(event) == 11;
        boolean animatedScene = harborScene || campScene;
        Label status = new Label(); status.setWrapText(true);
        CheckBox sounds = new CheckBox("Scene sounds");
        boolean hasSounds = !Soundscapes.forEvent(event).file().isBlank();
        sounds.setSelected(hasSounds); sounds.setDisable(!hasSounds);
        ToggleButton voice = new ToggleButton("Narration"); voice.setSelected(false);
        voice.setId("narration-button");
        voice.setTooltip(new Tooltip("Turn story narration on or off"));
        Slider volume = new Slider(0, 1, .65); volume.setPrefWidth(95);
        volume.setTooltip(new Tooltip("Scene sound volume"));
        Label heading = new Label(event.title()); heading.setMaxWidth(230);
        root.setTop(new ToolBar(button("← Back to chart", this::showChart), heading,
            sounds, voice, new Label("Volume"), volume));
        Label title = new Label(event.title()); title.getStyleClass().add("story-title"); title.setWrapText(true);
        VBox text = new VBox(18, title, note(event.date()), note(event.description()), note("Source: " + event.source()));
        text.setPadding(new Insets(28)); ScrollPane reading = new ScrollPane(text); reading.setFitToWidth(true);
        StackPane picture;
        if (harborScene) {
            LivingHarbor harbor = new LivingHarbor(); picture = harbor; storyAnimation = harbor.animation();
        } else if (campScene) {
            LivingCamp camp = new LivingCamp(); picture = camp; storyAnimation = camp.animation();
        } else if (event.illustrationSheet().isBlank()) {
            Label name = new Label(event.title()); name.getStyleClass().add("story-title"); name.setWrapText(true);
            VBox inscription = new VBox(28, name, note(event.date())); inscription.setPadding(new Insets(48));
            picture = new StackPane(inscription);
        } else {
            Image atlas = asset(event.illustrationSheet());
            int index = event.illustrationCell() >= 0 ? event.illustrationCell() : EventCatalog.EVENTS.indexOf(event);
            int columns = event.illustrationSheet().equals("prehistory-vignettes.png") ? 3 : 4;
            int rows = event.illustrationSheet().equals("prehistory-vignettes.png") ? 2 : 3;
            ImageView art = new ImageView(atlas);
            double w = atlas.getWidth()/columns, h = atlas.getHeight()/rows;
            art.setViewport(new Rectangle2D((index%columns)*w, (index/columns)*h, w, h));
            art.setPreserveRatio(true); picture = new StackPane(art);
            art.fitWidthProperty().bind(picture.widthProperty().subtract(40));
            art.fitHeightProperty().bind(picture.heightProperty().subtract(40));
        }
        picture.getStyleClass().add("original-chart");
        BorderPane plate = new BorderPane(picture); plate.setBottom(note(event.date()));
        Tab sceneTab = new Tab(animatedScene ? "Animation" : "Scene", plate); sceneTab.setClosable(false);
        ScrollPane mapView = new ScrollPane(EraMap.create(event, url -> getHostServices().showDocument(url)));
        mapView.setFitToWidth(true);
        Tab mapTab = new Tab("Era map", mapView); mapTab.setClosable(false);
        // Figures open first and start immediately; the map is a secondary choice.
        TabPane visuals = new TabPane(sceneTab, mapTab);
        ScenePlayback current = new ScenePlayback(event, storyAnimation, status::setText);
        playback = current;
        sounds.selectedProperty().addListener((o,a,b) -> current.setSoundsEnabled(b));
        voice.selectedProperty().addListener((o,a,b) -> current.setNarrationEnabled(b));
        volume.valueProperty().addListener((o,a,b) -> current.setVolume(b.doubleValue()));
        visuals.getSelectionModel().selectedItemProperty().addListener((o,oldTab,selected) -> {
            current.setActive(selected == sceneTab);
        });
        SplitPane split = new SplitPane(visuals, reading); split.setDividerPositions(.58);
        root.setCenter(split); root.setBottom(status);
        current.setActive(true);
    }

    private Button button(String text, Runnable action) {
        Button b = new Button(text);
        b.setOnAction(e -> action.run());
        return b;
    }

    private void showChart() {
        stopAnimation();
        Label title = new Label("History in a Glimpse");
        title.getStyleClass().add("title");
        ToolBar bar = new ToolBar(title, new Separator(),
            button("−", () -> setZoom(zoom / 1.25)),
            button("+", () -> setZoom(zoom * 1.25)),
            button("100%", () -> setZoom(1)), zoomLabel);
        root.setTop(bar);
        root.setCenter(chartScroll);
        root.setBottom(null);
        setZoom(zoom);
    }

    private void setZoom(double value) {
        zoom = Math.max(.4, Math.min(4, value));
        chart.setScaleX(zoom);
        chart.setScaleY(zoom);
        zoomLabel.setText(Math.round(zoom * 100) + "%");
    }

    private Label note(String text) {
        Label l = new Label(text);
        l.setWrapText(true);
        l.setPadding(new Insets(12));
        return l;
    }

    private void stopAnimation() {
        if (storyAnimation != null) { storyAnimation.stop(); storyAnimation = null; }
        if (playback != null) { playback.close(); playback = null; }
    }

    public static void main(String[] args) { launch(args); }
}
