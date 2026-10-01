package org.example.historyinaglimpse;

import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;

/** One shared, ordered drawing specification keeps the desktop chart and preview identical. */
final class RecreatedChart {
    static void draw(Pane pane, IntConsumer openStory) {
        pane.getChildren().clear();
        try (var size = RecreatedChart.class.getResourceAsStream("/chart-size.tsv")) {
            if (size == null) throw new IOException("Missing chart dimensions");
            var reader = new BufferedReader(new InputStreamReader(size, StandardCharsets.UTF_8));
            String[] dimensions = reader.readLine().split("\t");
            pane.setPrefSize(n(dimensions[0]), n(dimensions[1]));
        } catch (IOException ex) { throw new IllegalStateException("Cannot load chart dimensions", ex); }
        Map<String, Image> images = new HashMap<>();
        try (var source = RecreatedChart.class.getResourceAsStream("/chart-layout.tsv")) {
            if (source == null) throw new IOException("Missing chart layout");
            var reader = new BufferedReader(new InputStreamReader(source, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank() || line.startsWith("#")) continue;
                String[] f = line.split("\t", -1);
                Node node;
                switch (f[0]) {
                    case "RECT" -> {
                        Rectangle r = new Rectangle(n(f[1]), n(f[2]), n(f[3]), n(f[4]));
                        r.setFill(paint(f[5])); r.setStroke(paint(f[6])); r.setStrokeWidth(n(f[7])); node = r;
                    }
                    case "PATH" -> {
                        SVGPath p = new SVGPath(); p.setContent(f[4]); p.setFill(paint(f[1]));
                        p.setStroke(paint(f[2])); p.setStrokeWidth(n(f[3])); node = p;
                    }
                    case "TEXT" -> {
                        Text t = new Text(f[6]); t.setX(n(f[1])); t.setY(n(f[2]));
                        t.setTextOrigin(VPos.TOP); t.setFill(paint(f[5]));
                        t.setFont(Font.font("Times New Roman", f[4].equals("bold") ? FontWeight.BOLD : FontWeight.NORMAL, n(f[3])));
                        node = t;
                    }
                    case "IMAGE" -> {
                        Image image = images.computeIfAbsent(f[1], name -> {
                            var url = RecreatedChart.class.getResource("/images/" + name);
                            if (url == null) throw new IllegalStateException("Missing chart image: " + name);
                            Image loaded = new Image(url.toExternalForm());
                            if (loaded.isError()) throw new IllegalStateException("Cannot load chart image: " + name);
                            return loaded;
                        });
                        ImageView v = new ImageView(image);
                        v.setX(n(f[2])); v.setY(n(f[3])); v.setFitWidth(n(f[4])); v.setFitHeight(n(f[5]));
                        if (f.length == 10) {
                            double w = image.getWidth() / n(f[8]), h = image.getHeight() / n(f[9]);
                            v.setViewport(new Rectangle2D(n(f[6]) * w, n(f[7]) * h, w, h));
                        }
                        node = v;
                    }
                    case "HOTSPOT" -> {
                        int index = Integer.parseInt(f[1]);
                        Rectangle r = new Rectangle(n(f[2]), n(f[3]), n(f[4]), n(f[5]));
                        r.setFill(Color.TRANSPARENT); r.setStroke(Color.TRANSPARENT); r.setCursor(Cursor.HAND);
                        Tooltip.install(r, new Tooltip(f[6]));
                        r.setOnMouseEntered(e -> { r.setStroke(Color.web("#9b482b")); r.setStrokeWidth(2); });
                        r.setOnMouseExited(e -> r.setStroke(Color.TRANSPARENT));
                        r.setOnMouseClicked(e -> openStory.accept(index)); node = r;
                    }
                    default -> throw new IOException("Unknown chart drawing type: " + f[0]);
                }
                pane.getChildren().add(node);
            }
        } catch (IOException | NumberFormatException ex) {
            throw new IllegalStateException("Cannot read recreated chart", ex);
        }
    }
    private static double n(String value) { return Double.parseDouble(value); }
    private static Color paint(String value) { return value.equals("none") ? Color.TRANSPARENT : Color.web(value); }
    private RecreatedChart() {}
}
