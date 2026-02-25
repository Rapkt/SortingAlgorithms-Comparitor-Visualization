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
        while(true){
            if(lastadded ==0) break;
            int newparent = (lastadded - 1) / 2;
            if(numbers.compare(lastadded,newparent) >0 ){
                int temp = numbers.get(newparent);
                numbers.set(newparent,numbers.get(lastadded));
                numbers.set(lastadded,temp);
                lastadded = newparent;
            }
            else{
                break;
            }
        }
    }
    private void deleting (InstrumentedList<Integer> numbers,int end){
        if(end <= 0) return;
        int temp = numbers.get(0);
        numbers.set(0,numbers.get(end));
        numbers.set(end,temp);
        swaping(numbers,0,end);
        end--;
        deleting(numbers,end);

    }
    private void swaping(InstrumentedList<Integer> numbers, int toswaped,int end){
        if(toswaped >= end-1) return;
        int child1 = toswaped*2 +2;
        int child2 = toswaped*2 +1;
        if(child2 < end && child1 < end){
            int largerChildIdx = child2;
            if (numbers.compare(child1,child2) >0) {
                largerChildIdx = child1;
            }
            if(numbers.compare(toswaped,largerChildIdx) <0){
                int temp = numbers.get(toswaped);
                numbers.set(toswaped,numbers.get(largerChildIdx));
                numbers.set(largerChildIdx,temp);
                swaping(numbers,largerChildIdx,end);
            }
        }
        else if(child2 < end){
            if(numbers.compare(toswaped,child2) <0){
                int temp = numbers.get(toswaped);
                numbers.set(toswaped,numbers.get(child2));
                numbers.set(child2,temp);
                swaping(numbers,child2,end);
            }
        }
        else if(child1 < end){
            if(numbers.compare(toswaped,child1) <0){
                int temp = numbers.get(toswaped);
                numbers.set(toswaped,numbers.get(child1));
                numbers.set(child1,temp);
                swaping(numbers,child1,end);
            }
        }
    }
}
