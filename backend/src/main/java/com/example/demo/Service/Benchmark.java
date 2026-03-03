package com.example.demo.Service;

import com.example.demo.Dto.respond;
import com.example.demo.Dto.steps;
import com.example.demo.SortingAlgo.Engine;
import com.example.demo.SortingAlgo.Implementation.*;
import com.example.demo.Wrapper.InstrumentedList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Benchmark {

    public respond runBenchMark(String algo,String arrayType,int size,int runs){

        GenerateData datagen = new GenerateData();
        List<Double> runtimes = new ArrayList<>();

        long finalComparisons=0;
        long finalinterchanges=0;
        List<steps> finalSteps= new ArrayList<>();

        for(int i=0;i<runs;i++){
            List<Integer> data = datagen.generate(arrayType,size);
            InstrumentedList<Integer> instrumented = new InstrumentedList<>(data);

            Engine engine = getEngine(algo);
            long startTime = System.nanoTime();
            engine.Sorted(instrumented);
            long endTime = System.nanoTime();
            double duration = (endTime- startTime)/1_000_000.0;
            runtimes.add(duration);
            if(i == runs-1){
                finalComparisons = instrumented.getComparisons();
                finalinterchanges = instrumented.getInterchanges();
                finalSteps = instrumented.getSortingSteps();
            }
        }
        respond res = new respond();
        res.setAverageRuntime(runtimes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0));
        res.setMinRuntime(runtimes.stream().mapToDouble(Double::doubleValue).min().orElse(0.0));
        res.setMaxRuntime(runtimes.stream().mapToDouble(Double::doubleValue).max().orElse(0.0));
        res.setTotalComparisons((double) finalComparisons);
        res.setTotalInterchanges((double) finalinterchanges);
        res.setTotalSteps(finalSteps.size());
        return res;
    }
   /* public respond BenchWithArray(List<Integer> arr,String algo,String arrayType,int size ){
        long finalComparisons=0;
        long finalinterchanges=0;
        List<steps> finalSteps = new ArrayList<>();

        InstrumentedList<Integer> list = new InstrumentedList<>(arr);
        Engine engine = getEngine(algo);
        long startTime = System.nanoTime();
        engine.Sorted(list);
        long endTime = System.nanoTime();

        double duration = (endTime- startTime)/1_000_000.0;

        finalComparisons = list.getComparisons();
        finalinterchanges = list.getInterchanges();
        finalSteps = list.getSortingSteps();
        respond res = new respond();

    }*/
        private Engine getEngine(String name){
            switch (name.toLowerCase()) {
                case "bubble":
                    return new BubbleSort();
                case "heap":
                    return new HeapSort();
                case "merge":
                    return new MergeSort();
                case "quick":
                    return new QuickSort();
                case "selection":
                    return new SelectionSort();
                case "insertion":
                    return new InsertionSort();
                default:
                    throw new IllegalArgumentException("Invalid Algorithm: " + name);
            }
        }
}
