package com.example.demo.Dto;




import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class steps{
    private List<Integer> currentList;
    private long comparisons;
    private long interchanges;

    public steps(List<Integer> currentList, long comparisons, long interchanges) {
        this.currentList = currentList;
        this.comparisons = comparisons;
        this.interchanges = interchanges;
    }
}
