/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htl.spielergebnisse.model;

/**
 *
 * @author elias
 */
public class Team {

    private int id;
    private String name;
    private String shortName;
    private String iconUrl;

    public Team(int id, String name, String shortName, String iconUrl) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
