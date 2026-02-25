package com.example.demo.Wrapper;

import java.util.AbstractList;
import java.util.List;

public class InstrumentedList<T extends Comparable<T>> extends AbstractList<T> {
    private final List<T> internalList;
    private long comparisons=0;
    private long interchanges=0;
    public InstrumentedList(List<T> list){
        this.internalList = list;
    }
    @Override
    public T get(int index){
        return internalList.get(index);
    }
    @Override
    public T set(int index, T element) {
        interchanges++;
        return internalList.set(index, element);
    }
    public int compare(int indexA,int indexB){
        comparisons++;
        return internalList.get(indexA).compareTo(internalList.get(indexB));
    }
    public int compareValue(T value,int index){
        comparisons++;
        return value.compareTo(internalList.get(index));
    }

    @Override
    public int size() {
        return internalList.size();
    }
    public long getComparisons() { return comparisons; }
    public long getInterchanges() { return interchanges; }
}
