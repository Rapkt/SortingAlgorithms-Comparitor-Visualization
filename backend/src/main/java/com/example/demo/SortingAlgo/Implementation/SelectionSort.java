package com.example.demo.SortingAlgo.Implementation;


import com.example.demo.SortingAlgo.Engine;
import com.example.demo.Wrapper.InstrumentedList;

import java.util.List;

public class SelectionSort implements Engine {
    public List<Integer> Sorted(InstrumentedList<Integer> numbers){
        for (int i = 0; i < numbers.size()-1; i++) {
            int idx = i;
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.compareValue(j,idx) <0) {
                    idx = j;
                }
            }
            if (idx != i) {
                numbers.swap(i,idx);
            }
        }
        return numbers;
        }
    }

