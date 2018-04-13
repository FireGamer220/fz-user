package fz.firedev.de.freizeitzocker;

/**
 * Created by fireg on 13.04.2018.
 */

public class Team {
    private String name,rolle;

    public Team() {
    }

    public Team(String name, String rolle) {
        this.name = name;
        this.rolle = rolle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
}
