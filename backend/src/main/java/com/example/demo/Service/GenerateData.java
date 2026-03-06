package com.example.demo.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
public class GenerateData {
    private Random randomint = new Random();
    public List<Integer> generate(String mode,int numberOfElements){
        switch (mode){
            case"Random":
                return Random(numberOfElements);
            case "Sorted":
                return Sorted(numberOfElements);
            case "Inverted":
                return Reverse(numberOfElements);
            default:
                return null;
        }
    }
    private List<Integer> Random(int numberOfELements){
        List<Integer> res = new ArrayList<>(numberOfELements);
        for (int i = 0; i < numberOfELements; i++) {
            res.add(randomint.nextInt(10000));
        }
        return res;
    }
    private List<Integer> Sorted(int numberOfElements){
        List<Integer> res = new ArrayList<>(numberOfElements);
        int gap = randomint.nextInt(200);
        for(int i= 1;i<numberOfElements;i++){
            res.add(i* gap);
        }
        return res;
    }
    private List<Integer> Reverse(int numberOfElements){
        List<Integer> res = new ArrayList<>(numberOfElements);
        int gap = randomint.nextInt(200);
        for(int i= numberOfElements;i>=0;i--){
            res.add(i*gap);
        }
        return res;
    }
}
