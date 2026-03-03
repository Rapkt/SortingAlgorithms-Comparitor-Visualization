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
        int start = randomint.nextInt(10000);

        for(int i= 0;i<numberOfElements;i++){
            res.add(start+i );
        }
        return res;
    }
    private List<Integer> Reverse(int numberOfElements){
        List<Integer> res = new ArrayList<>(numberOfElements);
        int start = randomint.nextInt(10000);

        for(int i= 0;i<numberOfElements;i++){
            res.add(start-i );
        }
        return res;
    }
}
