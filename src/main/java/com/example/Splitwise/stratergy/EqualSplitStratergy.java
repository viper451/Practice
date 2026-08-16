package com.example.Splitwise.stratergy;

import java.util.HashMap;
import java.util.List;

public class EqualSplitStratergy implements  SplitStratergy{

    @Override
    public HashMap<Integer, Integer> splitStratergy(int amount, List<Integer>involvedUserId){
        HashMap<Integer,Integer> mp = new HashMap<>();

        int equalAmount = amount / involvedUserId.size();

        for(int i =0;i<involvedUserId.size();i++){
            mp.put(involvedUserId.get(i),equalAmount);
        }
        return mp;

    }


}
