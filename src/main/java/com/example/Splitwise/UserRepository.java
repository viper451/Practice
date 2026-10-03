package com.example.Splitwise;

import com.example.Splitwise.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple in-memory repository for users. Intended for LLD/demo purposes.
 */
public class UserRepository {

	private static final Map<Integer, User> users = new ConcurrentHashMap<>();

	public static void addUser(User user) {
		if (user == null) return;
		users.put(user.getId(), user);
	}

	public static User findUserById(int id) {
		return users.get(id);
	}

}


