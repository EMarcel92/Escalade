package com.emmanuel.escalade.model;

import javax.persistence.*;

@Entity
@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int siteid;
    private String nomSite;
    private String region;
    private int difficulteMin;
    private int difficulteMax;

    public int getDifficulteMin() {
        return difficulteMin;
    }

    public void setDifficulteMin(int difficulteMin) {
        this.difficulteMin = difficulteMin;
    }

    public int getDifficulteMax() {
        return difficulteMax;
    }

    public void setDifficulteMax(int difficulteMax) {
        this.difficulteMax = difficulteMax;
    }

}
