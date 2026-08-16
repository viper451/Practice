package com.example.Splitwise.stratergy;

import java.util.HashMap;
import java.util.List;

public interface SplitStratergy {


    public HashMap<Integer,Integer> splitStratergy(int amount, List<Integer> involvedUserId);
}
