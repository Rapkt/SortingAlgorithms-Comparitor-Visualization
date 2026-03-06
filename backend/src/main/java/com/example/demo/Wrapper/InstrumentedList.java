package com.example.demo.Wrapper;

import com.example.demo.Dto.steps;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class InstrumentedList<T extends Comparable<T>> extends AbstractList<T> {
    private final List<T> internalList;
    private long comparisons=0;
    private long interchanges=0;
    private int i;
    private int j;
    private int level;
    private int startIdx;

    private List<steps> sortingSteps = new ArrayList<>();
    public InstrumentedList(List<T> list){
        this(list, new ArrayList<>(),0,0);
    }
    public InstrumentedList(List<T> list, List<steps> sharedHistory,int startIdx,int level){
        this.internalList = new ArrayList<>(list);
        this.sortingSteps = sharedHistory;
        this.startIdx=startIdx;
        this.level=level;
        captureStep();
    }


    @Override
    public T get(int index){
        return internalList.get(index);
    }
    @Override
    public T set(int index, T element) {
        if(internalList.size()<=100){
            internalList.set(index,element);
            captureStep();
        }
        return internalList.set(index, element);
    }
    public void setWithCapture(int index, T element) {
        internalList.set(index, element);
        interchanges++;
        this.i = index;
        this.j =-1;
        if(internalList.size() <= 100) {
            captureStep();
        }
    }
    public void swap(int idxA,int idxB){
        if(idxA == idxB) return;

        T temp = internalList.get(idxA);
        internalList.set(idxA, internalList.get(idxB));
        internalList.set(idxB, temp);
        this.interchanges++;
        this.i = idxA;
        this.j = idxB;

        if(internalList.size()<=100){
            captureStep();
        }

    }
    public void captureStep(){
        sortingSteps.add(new steps(
                new ArrayList<>((List<Integer>) internalList),
                this.comparisons,
                this.interchanges,
                this.i,
                this.j,
                this.startIdx,
                this.level

        ));
    }
    public List<steps> getSortingSteps(){
        return sortingSteps;
    }

    public int compare(int indexA,int indexB){
        this.comparisons++;
        if(internalList.size()<=100){
            captureStep();
        }
        return internalList.get(indexA).compareTo(internalList.get(indexB));
    }
    public int compareValue(T value,int index){
        comparisons++;
        if(internalList.size()<=100){
            captureStep();
        }
        return value.compareTo(internalList.get(index));
    }
    public int compareValues(T value1, T value2) {
        comparisons++;
        if(internalList.size()<=100){
            captureStep();
        }
        return value1.compareTo(value2);
    }

    @Override
    public int size() {
        return internalList.size();
    }
    public long getComparisons() { return comparisons; }
    public long getInterchanges() { return interchanges; }
    public void incrementComaprisons(){
        this.comparisons++;
    }
}
