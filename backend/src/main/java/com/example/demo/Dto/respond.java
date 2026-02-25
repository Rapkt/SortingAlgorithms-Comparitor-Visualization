package com.example.demo.Dto;

import java.util.ArrayList;
import java.util.List;

public class respond {

    private Double Comparisons;
    private Double Interchange;
    private Double AverageRuntime;
    private Double minRuntime;
    private Double maxRuntime;
    private List<steps> Steps;

    public respond() {
        Comparisons = 0.0;
        Interchange = 0.0;
        AverageRuntime = 0.0;
        this.minRuntime = 0.0;
        Steps = new ArrayList<>();
        this.maxRuntime = 0.0;
    }

    public Double getAverageRuntime() {
        return AverageRuntime;
    }

    public void setAverageRuntime(Double averageRuntime) {
        AverageRuntime = averageRuntime;
    }

    public Double getMinRuntime() {
        return minRuntime;
    }

    public void setMinRuntime(Double minRuntime) {
        this.minRuntime = minRuntime;
    }

    public Double getMaxRuntime() {
        return maxRuntime;
    }

    public void setMaxRuntime(Double maxRuntime) {
        this.maxRuntime = maxRuntime;
    }


    public Double getInterchange() {
        return Interchange;
    }

    public Double getComparisons() {
        return Comparisons;
    }


    public void setComparisons(Double comparisons) {
        Comparisons = comparisons;
    }

    public void setInterchange(Double interchange) {
        Interchange = interchange;
    }

    public List<steps> getSteps() {
        return Steps;
    }

    public void setSteps(List<steps> steps) {
        Steps = steps;
    }
}
