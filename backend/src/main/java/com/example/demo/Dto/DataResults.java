package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataResults {
    private String algo;
    private int size;

    public DataResults(String algo, int size, String type, double comparisons, double interchanges, double time) {
        this.algo = algo;
        this.size = size;
        this.type = type;
        Comparisons = comparisons;
        Interchanges = interchanges;
        this.time = time;
    }

    private String type;
    private double Comparisons;
    private double Interchanges;
    private double time;
}
