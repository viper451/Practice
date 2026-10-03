package com.example.Splitwise;

import com.example.Splitwise.entity.Expense;
import com.example.Splitwise.entity.User;
import com.example.Splitwise.stratergy.EqualSplitStratergy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Minimal demo to exercise current LLD
        com.example.Splitwise.entity.User u1 = new com.example.Splitwise.entity.User(1, "Alice", 1);
        com.example.Splitwise.entity.User u2 = new com.example.Splitwise.entity.User(2, "Bob", 1);

        com.example.Splitwise.UserRepository.addUser(u1);
        com.example.Splitwise.UserRepository.addUser(u2);

        EqualSplitStratergy strat = new EqualSplitStratergy();
        Expense e = new Expense(1, 1, 1, Arrays.asList(1, 2), "EQUAL", 3001, strat);
        e.calculateExpense();

        System.out.println("=== After expense ===");
        System.out.println("User1 credit: " + u1.getCredit());
        System.out.println("User1 own:    " + u1.getOwn());
        System.out.println("User2 credit: " + u2.getCredit());
        System.out.println("User2 own:    " + u2.getOwn());
    }
}
