package ru.stairenx.viergo.youleadomsk.ItemPack;

/**
 * Created by viergo on 25.09.16.
 */
public class ProgramItem {

    private String time;
    private String program;
    private String title;
    private String speaker;
    private String background;

    public ProgramItem(String time, String program, String title, String speaker, String background) {
        this.time = time;
        this.program = program;
        this.title = title;
        this.speaker = speaker;
        this.background = background;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
