package com.emmanuel.escalade.model;

public class Topo {
    private String nomTopo;
    private String descritpionTopo;
    private int difficulteMin;
    private int difficulteMax;

    public String getNomTopo() {
        return nomTopo;
    }

    public Topo(String nomTopo, String descritpionTopo, int difficulteMin, int difficulteMax) {
        this.nomTopo = nomTopo;
        this.descritpionTopo = descritpionTopo;
        this.difficulteMin = difficulteMin;
        this.difficulteMax = difficulteMax;
    }

    public void setNomTopo(String nomTopo) {
        this.nomTopo = nomTopo;
    }

    public String getDescritpionTopo() {
        return descritpionTopo;
    }

    public void setDescritpionTopo(String descritpionTopo) {
        this.descritpionTopo = descritpionTopo;
    }

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
