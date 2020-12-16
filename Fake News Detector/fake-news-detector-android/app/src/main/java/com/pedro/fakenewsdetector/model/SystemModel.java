package com.pedro.fakenewsdetector.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class SystemModel {
    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField()
    private boolean tutorialSeen;

    public SystemModel() {
    }

    public SystemModel(boolean tutorialSeen) {
        this.id = 1;
        this.tutorialSeen = tutorialSeen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isTutorialSeen() {
        return tutorialSeen;
    }

    public void setTutorialSeen(boolean tutorialSeen) {
        this.tutorialSeen = tutorialSeen;
    }
}
