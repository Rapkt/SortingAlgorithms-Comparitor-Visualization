package com.example.demo.Control;


import com.example.demo.Dto.*;
import com.example.demo.Service.Benchmark;
import com.example.demo.Service.GenerateData;
import com.example.demo.Service.Pagination;
import com.example.demo.Wrapper.InstrumentedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class SortingController {
private final Benchmark benchmark;
private final Pagination pagination;
private final GenerateData generateData;
private List<steps> allsteps = new ArrayList<>();
private List<Integer> originalArr = new ArrayList<>();
@Autowired
    public SortingController(Benchmark benchmark, Pagination pagination, GenerateData generateData){
    this.benchmark = benchmark;
    this.pagination = pagination;
    this.generateData = generateData;
}
@GetMapping("/run")
public respond start(@RequestParam String algo,@RequestParam String type,@RequestParam int size){
 respond res = benchmark.runBenchMark(algo,type,size,1);
 this.allsteps = res.getSteps();

 res.setSteps(new ArrayList<>());
 return res;
}
@GetMapping("/steps")
    public List<steps> Steps(@RequestParam int page,@RequestParam int end){
    return pagination.getPagination(allsteps,page,end);
}
@PostMapping("/startVis")
public List<Integer> initializeVis(@RequestBody visInput input){
    if(input.getArr() == null || input.getArr().isEmpty()){
        List<Integer> arr = generateData.generate(input.getType(), input.getSize());
        this.originalArr = arr;
        return arr;
    }
    else{
        this.originalArr = input.getArr();
        return input.getArr();
    }
}
@PostMapping("/startTheater")
public void startTheather(@RequestParam String algoId){
    respond res = benchmark.SortWithArr(this.originalArr,algoId);
    System.out.println(res.getTotalComparisons());
    this.allsteps = res.getSteps();
}
@PostMapping("/benchmark")
    public CompareResponed compare(@RequestBody CompareInput input){
    respond resultA = benchmark.runBenchMark(input.getAlgoIdA(), input.getType(), input.getSize(),input.getIterations());
    respond resultB = benchmark.runBenchMark(input.getAlgoIdB(), input.getType(), input.getSize(),input.getIterations());
    CompareResponed res = new CompareResponed();
    res.setResultsA(resultA);
    res.setResultsB(resultB);
    return res;
}
}
