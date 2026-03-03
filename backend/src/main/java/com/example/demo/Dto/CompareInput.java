package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompareInput {
    private String algoIdA;
    private String algoIdB;
    private int size;
    private int iterations;
    private String type;
}
