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
    private int i;
    private int j;
    private int startIdx;
    private int level;

    public steps(List<Integer> currentList, long comparisons, long interchanges,int j, int i
    ,int startIdx,int level) {
        this.currentList = currentList;
        this.comparisons = comparisons;
        this.interchanges = interchanges;
        this.j = j;
        this.i = i;
        this.startIdx=startIdx;
        this.level=level;
    }
}
