package com.example.demo.SortingAlgo.Implementation;

import SortingAlgo.Implementation.SortingAlgo.Engine;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements Engine {

    @Override
    public List<Integer> Sorted(List<Integer> numbers) {
        mergeSort(numbers);
        return numbers;
    }
    private void mergeSort(List<Integer> numbers){
        int length = numbers.size();
        if(length <=1) return;
        int middle = length/2;
        List<Integer> Left = new ArrayList<>(numbers.subList(0,middle));
        List<Integer> Right = new ArrayList<>(numbers.subList(middle,length));
        mergeSort(Left);
        mergeSort(Right);
        merge(Left,Right,numbers);

    }
    private void merge (List<Integer> Left,List<Integer> Right,List<Integer> parent){
        int rightIdx =0;
        int leftIdx =0;
        for(int i=0;i <parent.size();i++){
            if(rightIdx < Right.size() && leftIdx < Left.size()){
                if(Left.get(leftIdx) < Right.get(rightIdx)){
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
