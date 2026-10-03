package com.example.Splitwise.stratergy;

import java.util.HashMap;
import java.util.List;

public class EqualSplitStratergy implements  SplitStratergy{

    @Override
    public HashMap<Integer, Integer> splitStratergy(int amount, List<Integer>involvedUserId){
        HashMap<Integer,Integer> mp = new HashMap<>();

        int size = involvedUserId.size();
        if (size == 0) return mp;

        int equalAmount = amount / size;
        int remainder = amount % size; // distribute remainder deterministically to first `remainder` users

        for (int i = 0; i < size; i++) {
            int share = equalAmount + (i < remainder ? 1 : 0);
            mp.put(involvedUserId.get(i), share);
        }
        return mp;

    }


}
