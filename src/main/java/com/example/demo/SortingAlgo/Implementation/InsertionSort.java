package com.example.demo.SortingAlgo.Implementation;

import SortingAlgo.Implementation.SortingAlgo.Engine;
import SortingAlgo.Implementation.Wrapper.InstrumentedList;

import java.util.List;

public class InsertionSort implements Engine {
    public List<Integer> Sorted(InstrumentedList<Integer> numbers){
        for (int i = 1; i < numbers.size() ; i++) {
            int temp = numbers.get(i);
            int j = i;
            for( j=i; j>0 ; j--){
                if( numbers.compareValue(temp,j-1) <0){
                    numbers.set(j,numbers.get(j-1));
                }
                else{
                    break;
                }
            }
                numbers.set(j,temp);
        }
        return numbers;
    }
}
