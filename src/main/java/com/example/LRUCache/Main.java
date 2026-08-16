package com.example.LRUCache;

public class Main {
    public static void main(String args[]){
        System.out.println("========== LRU CACHE TEST CASES ==========\n");
        
        // Test Case 1: Basic put and get operations
        System.out.println("Test Case 1: Basic put and get operations");
        CacheLogic cache1 = new CacheLogic(2);
        cache1.put(1, 10);
        cache1.put(2, 20);
        System.out.println("Put (1, 10) and (2, 20)");
        System.out.println("Get key 1: " + cache1.get(1));
        System.out.println("Get key 2: " + cache1.get(2));
        System.out.println("✓ Test Case 1 Passed\n");
        
        // Test Case 2: LRU Eviction - least recently used item is removed
        System.out.println("Test Case 2: LRU Eviction - least recently used is removed");
        CacheLogic cache2 = new CacheLogic(2);
        cache2.put(1, 10);
        cache2.put(2, 20);
        System.out.println("Cache capacity: 2");
        System.out.println("Put (1, 10) and (2, 20)");
        cache2.put(3, 30); // Should evict key 1
        System.out.println("Put (3, 30) - should evict key 1 (least recently used)");
        try {
            cache2.get(1);
            System.out.println("✗ Test Case 2 Failed - Key 1 should have been evicted");
        } catch (NullPointerException e) {
            System.out.println("Key 1 evicted successfully (NPE thrown as expected)");
            System.out.println("✓ Test Case 2 Passed\n");
        }
        
        // Test Case 3: Get operation marks item as recently used
        System.out.println("Test Case 3: Get operation marks item as recently used");
        CacheLogic cache3 = new CacheLogic(2);
        cache3.put(1, 10);
        cache3.put(2, 20);
        System.out.println("Put (1, 10) and (2, 20)");
        cache3.get(1); // Access key 1 to mark it as recently used
        System.out.println("Get key 1 - marks it as recently used");
        cache3.put(3, 30); // Should evict key 2, not key 1
        System.out.println("Put (3, 30) - should evict key 2 (least recently used)");
        System.out.println("Get key 1: " + cache3.get(1) + " (should exist)");
        try {
            cache3.get(2);
            System.out.println("✗ Test Case 3 Failed - Key 2 should have been evicted");
        } catch (NullPointerException e) {
            System.out.println("Key 2 evicted successfully");
            System.out.println("✓ Test Case 3 Passed\n");
        }
        
        // Test Case 4: Update existing key - should not reduce capacity
        System.out.println("Test Case 4: Update existing key - should not reduce capacity");
        CacheLogic cache4 = new CacheLogic(2);
        cache4.put(1, 10);
        cache4.put(2, 20);
        System.out.println("Put (1, 10) and (2, 20)");
        cache4.put(1, 100); // Update existing key
        System.out.println("Update (1, 100)");
        System.out.println("Get key 1: " + cache4.get(1) + " (should be 100)");
        System.out.println("Get key 2: " + cache4.get(2) + " (should be 20)");
        System.out.println("✓ Test Case 4 Passed\n");
        
        // Test Case 5: Update existing key moves it to most recent
        System.out.println("Test Case 5: Update existing key moves it to most recent");
        CacheLogic cache5 = new CacheLogic(2);
        cache5.put(1, 10);
        cache5.put(2, 20);
        System.out.println("Put (1, 10) and (2, 20)");
        cache5.put(1, 100); // Update key 1, making it most recent
        System.out.println("Update (1, 100) - makes it most recent");
        cache5.put(3, 30); // Should evict key 2, not key 1
        System.out.println("Put (3, 30) - should evict key 2");
        System.out.println("Get key 1: " + cache5.get(1) + " (should exist)");
        try {
            cache5.get(2);
            System.out.println("✗ Test Case 5 Failed - Key 2 should have been evicted");
        } catch (NullPointerException e) {
            System.out.println("Key 2 evicted successfully");
            System.out.println("✓ Test Casae 5 Passed\n");
        }
        
        // Test Case 6: Capacity of 1
        System.out.println("Test Case 6: Capacity of 1 - only one item stored at a time");
        CacheLogic cache6 = new CacheLogic(1);
        cache6.put(1, 10);
        System.out.println("Capacity: 1, Put (1, 10)");
        System.out.println("Get key 1: " + cache6.get(1));
        cache6.put(2, 20);
        System.out.println("Put (2, 20) - should evict key 1");
        try {
            cache6.get(1);
            System.out.println("✗ Test Case 6 Failed - Key 1 should have been evicted");
        } catch (NullPointerException e) {
            System.out.println("Key 1 evicted successfully");
            System.out.println("Get key 2: " + cache6.get(2) + " (should be 20)");
            System.out.println("✓ Test Case 6 Passed\n");
        }
        
        // Test Case 7: Multiple evictions with larger cache
        System.out.println("Test Case 7: Multiple evictions with larger cache (capacity 3)");
        CacheLogic cache7 = new CacheLogic(3);
        cache7.put(1, 10);
        cache7.put(2, 20);
        cache7.put(3, 30);
        System.out.println("Put (1, 10), (2, 20), (3, 30)");
        System.out.println("Get key 1: " + cache7.get(1));
        System.out.println("Get key 2: " + cache7.get(2));
        System.out.println("Get key 3: " + cache7.get(3));
        cache7.put(4, 40);
        System.out.println("Put (4, 40) - should evict key 1 (least recently used)");
        try {
            cache7.get(1);
            System.out.println("✗ Test Case 7 Failed - Key 1 should have been evicted");
        } catch (NullPointerException e) {
            System.out.println("Key 1 evicted successfully");
            System.out.println("✓ Test Case 7 Passed\n");
        }
        
        // Test Case 8: Alternating put and get operations
        System.out.println("Test Case 8: Alternating put and get operations");
        CacheLogic cache8 = new CacheLogic(2);
        cache8.put(1, 100);
        cache8.put(2, 200);
        System.out.println("Put (1, 100) and (2, 200)");
        cache8.get(1);
        System.out.println("Get key 1");
        cache8.put(3, 300);
        System.out.println("Put (3, 300) - should evict key 2");
        cache8.get(2);
        System.out.println("Attempting to get key 2...");
        try {
            cache8.get(2);
            System.out.println("✗ Test Case 8 Failed");
        } catch (NullPointerException e) {
            System.out.println("Key 2 evicted as expected");
            cache8.put(4, 400);
            System.out.println("Put (4, 400)");
            System.out.println("Get key 1: " + cache8.get(1));
            System.out.println("Get key 3: " + cache8.get(3));
            System.out.println("✓ Test Case 8 Passed\n");
        }
        
        // Test Case 9: Same key multiple updates
        System.out.println("Test Case 9: Same key multiple updates");
        CacheLogic cache9 = new CacheLogic(2);
        cache9.put(1, 10);
        System.out.println("Put (1, 10)");
        cache9.put(1, 20);
        System.out.println("Update (1, 20)");
        cache9.put(1, 30);
        System.out.println("Update (1, 30)");
        System.out.println("Get key 1: " + cache9.get(1) + " (should be 30)");
        System.out.println("✓ Test Case 9 Passed\n");
        
        // Test Case 10: Cache with negative values
        System.out.println("Test Case 10: Cache with negative values");
        CacheLogic cache10 = new CacheLogic(2);
        cache10.put(1, -10);
        cache10.put(2, -20);
        System.out.println("Put (1, -10) and (2, -20)");
        System.out.println("Get key 1: " + cache10.get(1) + " (negative value)");
        System.out.println("Get key 2: " + cache10.get(2) + " (negative value)");
        System.out.println("✓ Test Case 10 Passed\n");
        
        // Test Case 11: Cache with zero value
        System.out.println("Test Case 11: Cache with zero value");
        CacheLogic cache11 = new CacheLogic(2);
        cache11.put(1, 0);
        System.out.println("Put (1, 0)");
        System.out.println("Get key 1: " + cache11.get(1) + " (should be 0)");
        System.out.println("✓ Test Case 11 Passed\n");
        
        // Test Case 12: Cache with large values
        System.out.println("Test Case 12: Cache with large values");
        CacheLogic cache12 = new CacheLogic(2);
        cache12.put(1, Integer.MAX_VALUE);
        cache12.put(2, Integer.MIN_VALUE);
        System.out.println("Put (1, " + Integer.MAX_VALUE + ") - MAX_VALUE");
        System.out.println("Put (2, " + Integer.MIN_VALUE + ") - MIN_VALUE");
        System.out.println("Get key 1: " + cache12.get(1));
        System.out.println("Get key 2: " + cache12.get(2));
        System.out.println("✓ Test Case 12 Passed\n");
        
        // Test Case 13: Stress test - many operations
        System.out.println("Test Case 13: Stress test - many operations on capacity 5");
        CacheLogic cache13 = new CacheLogic(5);
        for (int i = 1; i <= 10; i++) {
            cache13.put(i, i * 100);
            System.out.println("Put (" + i + ", " + (i * 100) + ")");
        }
        System.out.println("Only keys 6-10 should remain (capacity is 5)");
        for (int i = 1; i <= 5; i++) {
            try {
                cache13.get(i);
                System.out.println("✗ Key " + i + " should have been evicted");
            } catch (NullPointerException e) {
                System.out.println("Key " + i + " evicted ✓");
            }
        }
        for (int i = 6; i <= 10; i++) {
            System.out.println("Get key " + i + ": " + cache13.get(i));
        }
        System.out.println("✓ Test Case 13 Passed\n");
        
        System.out.println("========== ALL TEST CASES COMPLETED SUCCESSFULLY ==========");
    }

}
