package com.example.Splitwise.entity;


import lombok.Data;

import java.util.HashMap;

@Data
public class User {
    int id;
    String name;
    int groupId;
    HashMap<Integer,Integer> own ;
    HashMap<Integer,Integer> credit;

    public static User findUserById(int paidUserId) {
    }
}
