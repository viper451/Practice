package com.example.Splitwise;

import com.example.Splitwise.entity.User;

import java.util.HashMap;
import java.util.Map;

public class Balance {

    public static void updateBalance(User paidBy, HashMap<Integer, Integer> shares){
        for(Map.Entry<Integer,Integer>itr: shares.entrySet())
        {
            if (itr.getKey() == paidBy.getId()) continue;
            int key = itr.getKey();
            int value = itr.getValue();
            paidBy.getCredit().put(paidBy.getId(),paidBy.getCredit().get(key) == null ? value: paidBy.getCredit().get(key) + value);

        }

        // There is a issue i have interger of debt one but I cant map it to user since i havent have tahat details
        // need to implement a feature where


    }
}
