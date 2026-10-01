package org.example.historyinaglimpse;

import javafx.animation.Animation;
import java.util.function.Consumer;

/** One scene lifecycle controls figures, ambient sound and optional descriptive voice. */
final class ScenePlayback implements AutoCloseable {
    private final HistoricalEvent event;
    private final Animation animation;
    private final Consumer<String> status;
    private final EnvironmentAudio environment;
    private final String soundLabel;
    private WindowsNarrator narrator;
    private boolean narrationPlaying;
    private boolean active, paused, closed;
    private boolean soundsEnabled=true, narrationEnabled=false;
    private double volume=.65;

    ScenePlayback(HistoricalEvent event, Animation animation, Consumer<String> status) {
        this.event=event; this.animation=animation; this.status=status;
        var setting=Soundscapes.forEvent(event);
        environment=setting.file().isBlank() ? null : new EnvironmentAudio(setting.file(),status); soundLabel=setting.label();
        soundsEnabled=environment != null;
        status.accept("Open the scene for " + soundLabel.toLowerCase() + ". Narration is optional.");
    }
    boolean isPaused() { return paused; }
    void setActive(boolean value) { active=value; refresh(); }
    void setPaused(boolean value) { paused=value; refresh(); }
    void setSoundsEnabled(boolean value) { soundsEnabled=value && environment != null; refresh(); }
    void setNarrationEnabled(boolean value) { narrationEnabled=value; refresh(); }
    void setVolume(double value) { volume=value; refresh(); }

    private void refresh() {
        if (closed) return;
        boolean playing=active && !paused;
        if (animation != null) {
            if (playing) animation.play(); else animation.pause();
        }
        // Keep the environmental bed softer while a user-selected narrator is speaking.
        if (environment != null) {
            environment.setVolume(volume*(narrationEnabled ? .32 : 1));
            if (playing && soundsEnabled) environment.play(); else environment.pause();
        }
        if (playing && narrationEnabled) {
            if (narrator == null) {
                narrator=new WindowsNarrator(status,event.title()+". "+event.date()+". "+event.description());
                narrator.command("START"); narrationPlaying=true;
            } else if (!narrationPlaying) { narrator.command("PLAY"); narrationPlaying=true; }
        } else if (narrator != null) {
            if (!narrationEnabled) { narrator.close(); narrator=null; narrationPlaying=false; }
            else if (narrationPlaying) { narrator.command("PAUSE"); narrationPlaying=false; }
        }
        status.accept(!active ? "Era map · choose the scene tab to explore the illustration." :
            paused ? "Scene paused" : environment == null ? soundLabel : soundsEnabled ? soundLabel :
            narrationEnabled ? "Narration selected" : "Scene sounds off");
    }
    @Override public void close() {
        if (closed) return;
        closed=true;
        if (animation != null) animation.stop();
        if (environment != null) environment.close();
        if (narrator != null) { narrator.close(); narrator=null; }
    }
}
