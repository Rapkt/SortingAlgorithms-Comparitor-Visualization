package com.example.demo.SortingAlgo.Implementation;


import com.example.demo.SortingAlgo.Engine;
import com.example.demo.Wrapper.InstrumentedList;

import java.util.List;

public class InsertionSort implements Engine {
    public List<Integer> Sorted(InstrumentedList<Integer> numbers){
        for (int i = 1; i < numbers.size() ; i++) {
            int temp = numbers.get(i);
            int j = i-1;
            for( ; j>=0 ; j--){
                if( numbers.compareValue(temp,j) <0){
                    numbers.setWithCapture(j+1,numbers.get(j));
                }
                else{
                    break;
                }
            }
                numbers.setWithCapture(j+1,temp);
        }
        return numbers;
    }
}
