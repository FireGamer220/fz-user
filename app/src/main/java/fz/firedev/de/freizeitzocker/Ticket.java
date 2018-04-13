package fz.firedev.de.freizeitzocker;

/**
 * Created by fireg on 13.04.2018.
 */

public class Ticket {
    private String thema,text,fzid,erledigt,username;

    public Ticket() {
    }

    public Ticket(String thema, String text, String fzid, String erledigt, String username) {
        this.thema = thema;
        this.text = text;
        this.fzid = fzid;
        this.erledigt = erledigt;
        this.username = username;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String theme) {
        this.thema = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFzid() {
        return fzid;
    }

    public void setFzid(String fzid) {
        this.fzid = fzid;
    }

    public String getErledigt() {
        return erledigt;
    }

    public void setErledigt(String erledigt) {
        this.erledigt = erledigt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
