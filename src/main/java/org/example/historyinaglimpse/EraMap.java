package org.example.historyinaglimpse;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/** Sourced physical geography with explicitly dated historical context where available. */
final class EraMap {
    private static final List<double[]> LAND = loadLand();
    static VBox create(HistoricalEvent event, Consumer<String> openReference) {
        int index = EventCatalog.EVENTS.indexOf(event);
        String[] setting = setting(index);
        Label heading = label(setting[1] + " · " + event.date());
        heading.setStyle("-fx-font-size: 23px; -fx-font-weight: bold;");
        VBox box = new VBox(12, heading);
        box.setMinWidth(0);
        box.setStyle("-fx-padding: 20; -fx-background-color: #efdfbb;");
        if (index == 52 || index == 24) {
            boolean persia = index == 52;
            String stem = persia ? "persia-500bce" : "rome-117ce";
            box.getChildren().add(label(persia ? "Historical snapshot: approximately 500 BCE" : "Historical snapshot: 117 CE · the united Roman Empire"));
            ImageView map = new ImageView(new Image(EraMap.class.getResource("/maps/" + stem + ".png").toExternalForm()));
            map.setPreserveRatio(true); map.fitWidthProperty().bind(box.widthProperty().subtract(40));
            box.getChildren().addAll(map, label(persia
                ? "A reconstruction within the Achaemenid period, not its founding extent in 550 BCE. Ancient frontiers are approximate."
                : "This snapshot predates the later division into eastern and western administrations. It is not a map of the western empire in 476 CE."));
            box.getChildren().add(label(persia ? "Anton Gutsunaev and Uirauna · CC BY-SA 3.0 · SVG rendered to PNG."
                : "ArdadN · public domain · SVG rendered to PNG."));
            link(box, "Map source and attribution", "https://commons.wikimedia.org/wiki/File:" + (persia ? "Achaemenid_Empire_En.svg" : "RomanEmpire_117.svg"), openReference);
            if (persia) link(box,"Map license: CC BY-SA 3.0","https://creativecommons.org/licenses/by-sa/3.0/",openReference);
        }
        box.getChildren().add(label("Geographic context · approximate place locations"));
        Canvas canvas = new Canvas(760, 390);
        box.getChildren().addAll(canvas, label(setting[2]), label("Physical coastlines: Natural Earth, 1:50 million. Present-day geography provides orientation; ancient shorelines may differ. Markers are places or regions, not borders or territorial claims."));
        canvas.widthProperty().bind(box.widthProperty().subtract(40));
        canvas.widthProperty().addListener((o,a,b) -> draw(canvas, setting));
        draw(canvas, setting);
        link(box,"Historical reference",event.source(),openReference);
        link(box,"Geography source · Made with Natural Earth","https://www.naturalearthdata.com/",openReference);
        return box;
    }
    private static void link(VBox box,String title,String url,Consumer<String> action) {
        Hyperlink link = new Hyperlink(title); link.setOnAction(e -> action.accept(url)); box.getChildren().add(link);
    }
    private static Label label(String text) { Label label = new Label(text); label.setWrapText(true); return label; }
    private static String[] setting(int index) {
        try (var input = EraMap.class.getResourceAsStream("/era-settings.tsv")) {
            if (input == null) throw new IllegalStateException("Missing era settings");
            var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t", -1);
                if (Integer.parseInt(fields[0]) == index) return fields;
            }
        } catch (java.io.IOException ex) { throw new IllegalStateException("Cannot load era settings", ex); }
        throw new IllegalStateException("Missing map setting for entry " + index);
    }
    private static List<double[]> loadLand() {
        List<double[]> result = new ArrayList<>();
        try (var input = EraMap.class.getResourceAsStream("/maps/land.tsv")) {
            if (input == null) throw new IllegalStateException("Missing geographic data");
            var reader = new BufferedReader(new InputStreamReader(input,StandardCharsets.UTF_8));
            String line;
            while ((line=reader.readLine())!=null) {
                String[] pairs=line.split(";"); double[] polygon=new double[pairs.length*2];
                for(int i=0;i<pairs.length;i++) { String[] p=pairs[i].split(","); polygon[i*2]=Double.parseDouble(p[0]); polygon[i*2+1]=Double.parseDouble(p[1]); }
                result.add(polygon);
            }
        } catch(java.io.IOException ex) { throw new IllegalStateException("Cannot load geography",ex); }
        return List.copyOf(result);
    }
    private static double mercator(double latitude) { return Math.toDegrees(Math.log(Math.tan(Math.PI/4+Math.toRadians(Math.max(-80,Math.min(80,latitude)))/2))); }
    private static void draw(Canvas canvas, String[] setting) {
        double width=canvas.getWidth(), height=canvas.getHeight(); if(width<100)return;
        List<String[]> places=new ArrayList<>();
        double west=180,east=-180,south=80,north=-80;
        for(String place:setting[3].split(";")) {
            String[] p=place.split(",",3); places.add(p);
            double lon=Double.parseDouble(p[0]), lat=mercator(Double.parseDouble(p[1]));
            west=Math.min(west,lon); east=Math.max(east,lon); south=Math.min(south,lat); north=Math.max(north,lat);
        }
        double cx=(west+east)/2,cy=(south+north)/2;
        double spanY=Math.max(14,north-south+8), spanX=Math.max(20,east-west+12);
        spanX=Math.max(spanX,spanY*width/height); spanY=spanX*height/width;
        west=cx-spanX/2; north=cy+spanY/2;
        GraphicsContext g=canvas.getGraphicsContext2D(); g.setFill(Color.web("#c6d6d4"));g.fillRect(0,0,width,height);
        g.save();g.beginPath();g.rect(0,0,width,height);g.clip();
        g.setLineWidth(.6);
        for(double[] polygon:LAND) {
            int count=polygon.length/2;double[] px=new double[count],py=new double[count];
            for(int i=0;i<count;i++) {px[i]=(polygon[2*i]-west)/spanX*width;py[i]=(north-mercator(polygon[2*i+1]))/spanY*height;}
            g.setFill(Color.web("#decd9e"));g.fillPolygon(px,py,count);g.setStroke(Color.web("#8c7959"));g.strokePolygon(px,py,count);
        }
        g.setStroke(Color.web("#718d8b",.35));
        for(int lon=-180;lon<=180;lon+=5) {double x=(lon-west)/spanX*width;g.strokeLine(x,0,x,height);}
        for(int lat=-75;lat<=75;lat+=5) {double y=(north-mercator(lat))/spanY*height;g.strokeLine(0,y,width,y);}
        List<Double> labelRows=new ArrayList<>();
        for(String[] p:places) {
            double px=(Double.parseDouble(p[0])-west)/spanX*width,py=(north-mercator(Double.parseDouble(p[1])))/spanY*height;
            double labelY=Math.max(22,Math.min(height-18,py-10));
            boolean overlap=true;
            while(overlap) {overlap=false; for(double used:labelRows)if(Math.abs(labelY-used)<23){labelY+=24;overlap=true;break;}}
            labelRows.add(labelY);
            double labelX=Math.max(8,Math.min(width-190,px+12));
            g.setStroke(Color.web("#8d3c2c"));g.strokeLine(px,py,labelX,labelY-5);
            g.setFill(Color.web("#8d3c2c"));g.fillOval(px-4,py-4,8,8);
            g.setFont(Font.font("Times New Roman",16));g.setLineWidth(3);g.setStroke(Color.web("#efdfbb"));g.strokeText(p[2],labelX,labelY);g.setFill(Color.web("#4e3329"));g.fillText(p[2],labelX,labelY);g.setLineWidth(.6);
        }
        g.restore();g.setStroke(Color.web("#7d7258"));g.strokeRect(0,0,width,height);
    }
    private EraMap() {}
}
