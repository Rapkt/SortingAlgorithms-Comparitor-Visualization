package com.example.demo.SortingAlgo.Implementation;


import com.example.demo.SortingAlgo.Engine;
import com.example.demo.Wrapper.InstrumentedList;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements Engine {

    @Override
    public List<Integer> Sorted(InstrumentedList<Integer> numbers) {
        mergeSort(numbers,0,numbers.size()-1);
        numbers.captureStep();
        return numbers;
    }
    private void mergeSort(InstrumentedList<Integer> numbers,int left,int right){
        if(left < right) {
            int mid = left + (right - left)/2;
            mergeSort(numbers,left,mid);
            mergeSort(numbers,mid+1,right);
            merge(numbers,left,mid,right);
        }
    }
    private void merge (InstrumentedList<Integer> numbers,int left,int mid,int right){
        List<Integer> temp =  new ArrayList<>();
        int i=left;
        int j = mid+1;
        while(i <= mid && j<=right){
            if(numbers.compare(i,j) <=0){
                temp.add(numbers.get(i++));
            }
            else{
                temp.add(numbers.get(j++));
            }
        }
        while(i <=mid){
            temp.add(numbers.get(i++));
        }
        while(j <=right){
            temp.add(numbers.get(j++));
        }
        for(int k=0;k<temp.size();k++){
            numbers.set(left+k,temp.get(k));
        }
    }
}
