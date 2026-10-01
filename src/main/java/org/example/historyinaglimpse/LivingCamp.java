package org.example.historyinaglimpse;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

/** Five individually controlled illustrated actors over a fixed camp backdrop. */
final class LivingCamp extends StackPane {
    private static final double SIZE=1000;
    private final Group world;
    private final Timeline animation=new Timeline(new KeyFrame(Duration.seconds(60)));
    private final List<Sprite> actors=new ArrayList<>();
    LivingCamp() {
        ImageView background=new ImageView(image("hunter-camp-background.png"));
        background.fitWidthProperty().bind(widthProperty());background.fitHeightProperty().bind(heightProperty());
        background.setPreserveRatio(false);background.setSmooth(true);
        Image people=image("hunter-individual-poses.png");
        // Atlas rows have distinct heights; these are the inspected, transparent row boundaries.
        Sprite father=new Sprite("father",people,0,398,10,370,510,6.7,.0,false);
        Sprite mother=new Sprite("mother",people,398,737,650,470,390,5.3,.9,false);
        Sprite child=new Sprite("child",people,737,986,430,430,290,4.1,1.7,false);
        Sprite girl=new Sprite("girl",people,986,1448,620,135,500,8.9,2.4,false);
        Image flames=image("hunter-fire-poses.png");
        Sprite fire=new Sprite("fire",flames,0,flames.getHeight(),220,550,330,.87,.13,true);
        actors.addAll(List.of(father,mother,child,girl,fire));
        // Back-to-front order puts the standing girl and child behind the seated adults/fire.
        Pane layers=new Pane(girl.view,child.view,father.view,mother.view,fire.view);
        layers.resize(SIZE,SIZE);layers.setPrefSize(SIZE,SIZE);world=new Group(layers);
        getChildren().addAll(background,world);setMinSize(0,0);setPrefSize(650,650);
        setStyle("-fx-background-color: #d7b58a;");
        Rectangle clip=new Rectangle();clip.widthProperty().bind(widthProperty());clip.heightProperty().bind(heightProperty());setClip(clip);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.statusProperty().addListener((o,oldStatus,status) -> {
            for (Sprite actor:actors) {
                if (status == Animation.Status.RUNNING) actor.motion.play();
                else if (status == Animation.Status.PAUSED) actor.motion.pause();
                else actor.motion.stop();
            }
        });
    }
    Animation animation(){return animation;}
    @Override protected void layoutChildren(){
        super.layoutChildren();
        double s=Math.max(0,Math.min(getWidth()/SIZE,getHeight()/SIZE));
        world.getTransforms().setAll(new Scale(s,s,0,0));
        world.relocate((getWidth()-SIZE*s)/2,(getHeight()-SIZE*s)/2);
    }
    /** A sprite owns its own transition, frame state and phase; no shared family viewport. */
    private static final class Sprite {
        final String name; final ImageView view; final Animation motion;
        final Image atlas; final double cellWidth,rowTop; final Rectangle2D content;
        int frame=-1;
        Sprite(String name,Image atlas,double rowTop,double rowEnd,double x,double y,double height,
               double seconds,double phase,boolean flame) {
            this.name=name;this.atlas=atlas;this.rowTop=rowTop;cellWidth=atlas.getWidth()/3;
            content=contentBounds(atlas,rowTop,rowEnd,cellWidth);
            view=new ImageView(atlas);view.setId("camp-"+name);view.setFitHeight(height);
            view.setFitWidth(height*content.getWidth()/content.getHeight());view.setPreserveRatio(false);view.setSmooth(true);
            view.setLayoutX(x);view.setLayoutY(y);
            motion=new Transition(){
                {setCycleDuration(Duration.seconds(seconds));setCycleCount(Animation.INDEFINITE);setInterpolator(Interpolator.LINEAR);}
                @Override protected void interpolate(double fraction){
                    double p=(fraction+phase/seconds)%1;
                    int[] sequence=flame?new int[]{0,1,2,1}:new int[]{0,0,1,2,2,1,0,0};
                    show(sequence[Math.min(sequence.length-1,(int)(p*sequence.length))]);
                }
            };
            show(0);
        }
        void show(int value){
            if(frame==value)return;
            view.setViewport(new Rectangle2D(value*cellWidth+content.getMinX(),rowTop+content.getMinY(),content.getWidth(),content.getHeight()));frame=value;
        }
        private static Rectangle2D contentBounds(Image atlas,double top,double end,double width){
            int minX=(int)width,minY=(int)(end-top),maxX=0,maxY=0;
            var pixels=atlas.getPixelReader();
            for(int col=0;col<3;col++)for(int y=(int)top;y<(int)end;y++)for(int x=0;x<(int)width;x++) {
                if(((pixels.getArgb((int)(col*width)+x,y)>>>24)&255)>80){
                    minX=Math.min(minX,x);maxX=Math.max(maxX,x);minY=Math.min(minY,y-(int)top);maxY=Math.max(maxY,y-(int)top);
                }
            }
            if(maxX<=minX || maxY<=minY)throw new IllegalStateException("Empty sprite row");
            return new Rectangle2D(minX,minY,maxX-minX+1,maxY-minY+1);
        }
    }
    private static Image image(String name){
        var resource=LivingCamp.class.getResource("/images/"+name);
        if(resource==null)throw new IllegalStateException("Missing camp layer: "+name);
        Image image=new Image(resource.toExternalForm());
        if(image.isError())throw new IllegalStateException("Cannot load camp layer: "+name);
        return image;
    }
}
