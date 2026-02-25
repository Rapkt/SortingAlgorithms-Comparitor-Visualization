package com.example.demo.Wrapper;

import com.example.demo.Dto.steps;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class InstrumentedList<T extends Comparable<T>> extends AbstractList<T> {
    private final List<T> internalList;
    private long comparisons=0;
    private long interchanges=0;
    private List<steps> sortingSteps = new ArrayList<>();
    public InstrumentedList(List<T> list){
        this.internalList = new ArrayList<>(list);
        captureStep();
    }
    @Override
    public T get(int index){
        return internalList.get(index);
    }
    @Override
    public T set(int index, T element) {
        interchanges++;
        T old = internalList.set(index,element);
        if(internalList.size()<=100){
            captureStep();
        }
        return old;
    }
    private void captureStep(){
        sortingSteps.add(new steps(
                new ArrayList<>((List<Integer>) internalList),
                this.comparisons,
                this.interchanges
        ));
    }
    public List<steps> getSortingSteps(){
        return sortingSteps;
    }
    public int compare(int indexA,int indexB){
        comparisons++;
        return internalList.get(indexA).compareTo(internalList.get(indexB));
    }
    public int compareValue(T value,int index){
        comparisons++;
        return value.compareTo(internalList.get(index));
    }
    public int compareValues(T value1, T value2) {
        comparisons++;
        return value1.compareTo(value2);
    }

    @Override
    public int size() {
        return internalList.size();
    }
    public long getComparisons() { return comparisons; }
    public long getInterchanges() { return interchanges; }
}
