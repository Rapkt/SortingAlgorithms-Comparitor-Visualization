package com.example.demo.SortingAlgo;

import SortingAlgo.Implementation.Wrapper.InstrumentedList;

import java.util.List;

public interface Engine {
    public List<Integer> Sorted(InstrumentedList<Integer> numbers);
}
