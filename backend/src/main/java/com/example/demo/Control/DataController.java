package com.example.demo.Control;

import com.example.demo.Dto.DataResults;
import com.example.demo.Service.Benchmark;
import com.example.demo.Service.GenerateData;
import com.example.demo.Wrapper.InstrumentedList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/Data")
public class DataController {
    private final GenerateData generator;
    private final Benchmark benchmark;
    private List<Integer> arr;
    public DataController(GenerateData generator, Benchmark benchmark) {
        this.generator = generator;
        this.benchmark = benchmark;
    }
    @GetMapping("/getArr")
    public List<Integer> DataArr( @RequestParam int size, @RequestParam String type){
        this.arr = generator.generate(type,size);
        return this.arr;
    }
    @GetMapping
    public DataResults runBenchmark(@RequestParam String algo, @RequestParam int size, @RequestParam String type){
        InstrumentedList<Integer> list = new InstrumentedList<>(this.arr);
        long startTime = System.nanoTime();
        benchmark.SortWithArr(list,algo);
        long endTime = System.nanoTime();
        return new DataResults(
                algo,size,type,
                list.getComparisons(),
                list.getInterchanges(),
                (endTime- startTime)/1_000_000.0
        );
    }
}
