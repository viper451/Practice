package com.example.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class CacheLogic {
    int capacity;
    Node head;
    Node tail;
    Map<Integer, Node> mp;

    public CacheLogic(int capacity) {
        this.capacity = capacity;
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        mp = new HashMap<>();
    }

    public void put(int key,int value){
        if (mp.containsKey(key)) {
            // Update existing key
            Node existingNode = mp.get(key);
            existingNode.value = value;
            removeNode(existingNode);
            addNode(existingNode);
        } else {
            // Add new key
            Node newNode = new Node(key, value);
            addNode(newNode);
            mp.put(key, newNode);
            
            // Check if capacity exceeded
            if (mp.size() > capacity) {
                Node lruNode = tail.prev;
                removeNode(lruNode);
                mp.remove(lruNode.key);
            }
        }
    }

    public void addNode(Node node) {
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    public void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    public int get(int key) {
        Node node = mp.get(key);
        if (node == null) {
            return -1;
        }
        // Move to head (most recently used)
        removeNode(node);
        addNode(node);
        return node.value;
    }
}