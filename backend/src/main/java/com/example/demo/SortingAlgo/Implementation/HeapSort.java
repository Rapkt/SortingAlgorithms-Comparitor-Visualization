package com.example.demo.SortingAlgo.Implementation;


import com.example.demo.SortingAlgo.Engine;
import com.example.demo.Wrapper.InstrumentedList;

import java.util.ArrayList;
import java.util.List;

public class HeapSort implements Engine {
    @Override
    public List<Integer> Sorted(InstrumentedList<Integer> numbers) {
        
        for(int i=0;i <numbers.size();i++){
            sortWithParents(numbers,i);
        }
        deleting(numbers,numbers.size()-1);
        return numbers;
    }
    private void sortWithParents (InstrumentedList<Integer> numbers,int lastadded){
        while(lastadded >0){
            int newparent = (lastadded - 1) / 2;
            
            if(numbers.compare(lastadded,newparent) >0 ){
                numbers.swap(lastadded,newparent);
                lastadded = newparent;
            }
            else{
                break;
            }
        }
    }
    private void deleting (InstrumentedList<Integer> numbers,int end){
        if(end <= 0) return;
        numbers.swap(0,end);
        
        swaping(numbers,0,end);
        deleting(numbers,end-1);

    }
    private void swaping(InstrumentedList<Integer> numbers, int toswaped,int end){
        if(toswaped >= end-1) return;
        int rightChild = toswaped*2 +2;
        int leftChild = toswaped*2 +1;
        int largest = toswaped;

        if(leftChild < end && numbers.compare(leftChild,largest) >0){
            largest = leftChild;
        }
        if(rightChild < end && numbers.compare(rightChild,largest) >0){
            largest = rightChild;
        }
        if(largest != toswaped){
            numbers.swap(toswaped,largest);
            swaping(numbers,largest,end);
        }
    }
}
