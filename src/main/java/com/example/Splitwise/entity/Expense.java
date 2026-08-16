package com.example.Splitwise.entity;

import com.example.Splitwise.Balance;
import com.example.Splitwise.stratergy.SplitStratergy;

import java.util.HashMap;
import java.util.List;

public class Expense {
    int groupId;
    int expenseId;
    int paidUserId;
    List<Integer> involvedUserId;
    String splitType;
    int amount;
    SplitStratergy splitStratergy;

    public Expense(int groupId,int expenseId,int paidUserId,List<Integer>involvedUserId,String splitType,int amount,SplitStratergy splitStratergy){
        this.groupId = groupId;
        this.expenseId = expenseId;
        this.paidUserId = paidUserId;
        this.involvedUserId = involvedUserId;
        this.splitType = splitType;
        this.amount = amount;
        this.splitStratergy = splitStratergy;
    }

    public void calculateExpense(){

        //A -> 3000
        //B -> 3000
        //

        HashMap <Integer,Integer> userDetails = splitStratergy.splitStratergy(amount,involvedUserId);

        User user = User.findUserById(paidUserId);
        Balance.updateBalance(user,userDetails);



    }
}
