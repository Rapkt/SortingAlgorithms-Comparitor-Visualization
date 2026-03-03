package com.example.demo.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Data
public class respond {
    private Double totalComparisons;
    private Double totalInterchanges;
    private int TotalSteps;
    private Double AverageRuntime;
    private Double minRuntime;
    private Double maxRuntime;
    private List<steps> Steps;

    public respond() {
        this.totalComparisons=0.0;
        this.totalInterchanges=0.0;
        this.AverageRuntime = 0.0;
        this.minRuntime = 0.0;
        Steps = new ArrayList<>();
        this.maxRuntime = 0.0;
    }
}
