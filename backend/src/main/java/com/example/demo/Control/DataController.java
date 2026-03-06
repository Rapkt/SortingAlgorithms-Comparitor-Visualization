package com.example.demo.Control;

import com.example.demo.Dto.DataResults;
import com.example.demo.Dto.respond;
import com.example.demo.Service.Benchmark;
import com.example.demo.Service.GenerateData;
import com.example.demo.Wrapper.InstrumentedList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Data")
public class DataController {
    private final GenerateData generator;
    private final Benchmark benchmark;

    public DataController(GenerateData generator, Benchmark benchmark) {
        this.generator = generator;
        this.benchmark = benchmark;
    }

    @GetMapping
    public DataResults runBenchmark(@RequestParam String algo, @RequestParam int size, @RequestParam String type){
        long startTime = System.nanoTime();
        respond res = benchmark.runBenchMark(algo,type,size,3);
        long endTime = System.nanoTime();
        return new DataResults(
                algo,size,type,
                res.getTotalComparisons(),
                res.getTotalInterchanges(),
                (double) (endTime - startTime) /1000000
        );
    }
}
