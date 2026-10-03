package com.example.Splitwise.entity;


import lombok.Data;

import java.util.HashMap;

@Data
public class User {
    int id;
    String name;
    int groupId;
    HashMap<Integer,Integer> own;
    HashMap<Integer,Integer> credit;

    // Default constructor
    public User() {
        this.own = new HashMap<>();
        this.credit = new HashMap<>();
    }

    public User(int id, String name, int groupId) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.own = new HashMap<>();
        this.credit = new HashMap<>();
    }

    public static User findUserById(int paidUserId) {
        return com.example.Splitwise.UserRepository.findUserById(paidUserId);
    }
}
