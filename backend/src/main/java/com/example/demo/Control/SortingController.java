package com.example.demo.Control;


import com.example.demo.Dto.respond;
import com.example.demo.Dto.steps;
import com.example.demo.Service.Benchmark;
import com.example.demo.Service.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sort")
@CrossOrigin("http://localhost:8080")
public class SortingController {
private final Benchmark benchmark;
private final Pagination pagination;
private List<steps> allsteps = new ArrayList<>();
@Autowired
    public SortingController(Benchmark benchmark, Pagination pagination){
    this.benchmark = benchmark;
    this.pagination = pagination;
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
}
