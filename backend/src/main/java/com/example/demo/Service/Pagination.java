package com.example.demo.Service;

import com.example.demo.Dto.steps;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Pagination {

    public List<steps> getPagination(List<steps> Steps,int page,int Size){
        int start = (page-1)* Size;
        int end = Math.min(start+Size, Steps.size());

        return Steps.subList(start,end);
    }
}
