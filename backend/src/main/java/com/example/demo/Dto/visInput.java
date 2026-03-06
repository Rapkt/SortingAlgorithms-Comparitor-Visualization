package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class visInput {
    private String algoId;
    private String type;
    private int size;
    private List<Integer> arr;
}
