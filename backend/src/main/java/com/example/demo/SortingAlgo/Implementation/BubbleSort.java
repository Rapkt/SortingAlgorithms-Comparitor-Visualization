package com.example.demo.SortingAlgo.Implementation;

import com.example.demo.SortingAlgo.Engine;
import com.example.demo.Wrapper.InstrumentedList;

import java.util.List;

public class BubbleSort implements Engine {

    @Override
    public List<Integer> Sorted(InstrumentedList<Integer> numbers) {
        for (int i=0;i<numbers.size();i++){
            boolean swaped = false;
            for (int j = 0; j < numbers.size()-i-1; j++) {
                if(numbers.compare(j,j+1) >0){
                    numbers.swap(j+1,j);
                    swaped=true;
                }
            }
            if(!swaped) break;
        }
        return numbers;
    }
}
