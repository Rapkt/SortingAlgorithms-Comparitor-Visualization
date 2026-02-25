package com.example.demo.SortingAlgo.Implementation;


import com.example.demo.SortingAlgo.Engine;
import com.example.demo.Wrapper.InstrumentedList;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements Engine {

    @Override
    public List<Integer> Sorted(InstrumentedList<Integer> numbers) {
        mergeSort(numbers);
        return numbers;
    }
    private void mergeSort(InstrumentedList<Integer> numbers){
        int length = numbers.size();
        if(length <=1) return;
        int middle = length/2;
        InstrumentedList<Integer> Left = new InstrumentedList<>( new ArrayList<>(numbers.subList(0,middle)));
        InstrumentedList<Integer> Right = new InstrumentedList<>(new ArrayList<>(numbers.subList(middle,length)));
        mergeSort(Left);
        mergeSort(Right);
        merge(Left,Right,numbers);

    }
    private void merge (InstrumentedList<Integer> Left,InstrumentedList<Integer> Right,InstrumentedList<Integer> parent){
        int rightIdx =0;
        int leftIdx =0;
        for(int i=0;i <parent.size();i++){
            if(rightIdx < Right.size()  && leftIdx < Left.size()){
                if(parent.compareValues(Left.get(leftIdx), Right.get(rightIdx)) <0){
                    parent.set(i,Left.get(leftIdx));
                    leftIdx++;
                }
                else{
                    parent.set(i,Right.get(rightIdx));
                    rightIdx++;
                }
            } else if (rightIdx < Right.size()) {
                parent.set(i,Right.get(rightIdx));
                rightIdx++;
            }
            else{
                parent.set(i,Left.get(leftIdx));
                leftIdx++;
            }
        }
    }
}
