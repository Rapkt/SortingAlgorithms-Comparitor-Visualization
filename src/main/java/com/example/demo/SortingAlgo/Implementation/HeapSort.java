package com.example.demo.SortingAlgo.Implementation;

import SortingAlgo.Implementation.SortingAlgo.Engine;
import SortingAlgo.Implementation.Wrapper.InstrumentedList;

import java.util.ArrayList;
import java.util.List;

public class HeapSort implements Engine {
    @Override
    public List<Integer> Sorted(InstrumentedList<Integer> numbers) {
        List<Integer> sort = new ArrayList<>();
        for(int i=0;i <numbers.size();i++){
            sort.add(numbers.get(i));
            sortWithParents(sort);
        }
        deleting(sort,sort.size()-1);
        return sort;
    }
    private void sortWithParents (List<Integer> numbers){
        if(numbers.size() <=1) return;
        int lastadded = numbers.size()-1;

        while(true){
            if(lastadded ==0) break;
            int newparent = (lastadded - 1) / 2;
            if(numbers.get(lastadded) > numbers.get(newparent)){
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
    private void deleting (List<Integer> numbers,int end){
        if(end ==0) return;
        int temp = numbers.getFirst();
        numbers.set(0,numbers.get(end));
        numbers.set(end,temp);
        swaping(numbers,0,end,numbers.size());
        end--;
        deleting(numbers,end);

    }
    private void swaping(List<Integer> numbers, int toswaped,int end,int len){
        if(toswaped >= end-1) return;
        int child1 = toswaped*2 +2;
        int child2 = toswaped*2 +1;
        if(child2 < end && child1 < end){
            int largerChildIdx = child2;
            if (child1 < end && numbers.get(child1) > numbers.get(child2)) {
                largerChildIdx = child1;
            }
            if(numbers.get(toswaped) < numbers.get(largerChildIdx)){
                int temp = numbers.get(toswaped);
                numbers.set(toswaped,numbers.get(largerChildIdx));
                numbers.set(largerChildIdx,temp);
                swaping(numbers,largerChildIdx,end,len);
            }
        }
        else if(child2 < end){
            if(numbers.get(toswaped) < numbers.get(child2)){
                int temp = numbers.get(toswaped);
                numbers.set(toswaped,numbers.get(child2));
                numbers.set(child2,temp);
                swaping(numbers,child2,end,len);
            }
        }
        else if(child1 < end){
            if(numbers.get(toswaped) < numbers.get(child1)){
                int temp = numbers.get(toswaped);
                numbers.set(toswaped,numbers.get(child1));
                numbers.set(child1,temp);
                swaping(numbers,child1,end,len);
            }
        }
    }
}
