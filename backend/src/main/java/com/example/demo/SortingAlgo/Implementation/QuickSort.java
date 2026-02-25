package com.example.demo.SortingAlgo.Implementation;

import SortingAlgo.Implementation.SortingAlgo.Engine;
import SortingAlgo.Implementation.Wrapper.InstrumentedList;

import java.util.List;

public class QuickSort implements Engine {
    @Override
    public List<Integer> Sorted(InstrumentedList<Integer> numbers) {

        quick(numbers,0,numbers.size()-1);
        return numbers;
    }
    private void quick(InstrumentedList<Integer> numbers, int start,int end){
        if(start>= end){
                return;
        }
        int pivot = numbers.get(end);
        int i=start-1;
        int j=start;
        while(j < end){
            if(numbers.compareValue(pivot,j) >0){
                i++;
                int temp = numbers.get(i);
                numbers.set(i,numbers.get(j));
                numbers.set(j,temp);
            }
            j++;
        }
        i++;
        int temp = numbers.get(i);
        numbers.set(i,numbers.get(j));
        numbers.set(j,temp);

        quick(numbers,start,i-1);
        quick(numbers,i+1,end);
    }
}
