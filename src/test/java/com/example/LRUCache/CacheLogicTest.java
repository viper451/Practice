package com.example.LRUCache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LRU Cache Tests")
class CacheLogicTest {

    private CacheLogic cache;

    @BeforeEach
    void setUp() {
        cache = new CacheLogic(2); // Initialize with capacity 2 for most tests
    }

    @Test
    @DisplayName("Test 1: Basic put and get operations")
    void testBasicPutAndGet() {
        cache.put(1, 10);
        assertEquals(10, cache.get(1), "Should return value 10 for key 1");

        cache.put(2, 20);
        assertEquals(20, cache.get(2), "Should return value 20 for key 2");
    }

    @Test
    @DisplayName("Test 2: LRU Eviction - least recently used item is removed")
    void testLRUEviction() {
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30); // Should evict key 1 as it's least recently used

        // Key 1 should be evicted
        assertThrows(NullPointerException.class, () -> {
            cache.get(1);
        }, "Key 1 should be evicted and cause exception");
    }

    @Test
    @DisplayName("Test 3: Get operation marks item as recently used")
    void testGetMarksMostRecent() {
        cache.put(1, 10);
        cache.put(2, 20);
        cache.get(1); // Access key 1 to mark it as recently used
        cache.put(3, 30); // Should evict key 2, not key 1

        assertEquals(10, cache.get(1), "Key 1 should still exist");
        assertThrows(NullPointerException.class, () -> {
            cache.get(2);
        }, "Key 2 should be evicted");
    }

    @Test
    @DisplayName("Test 4: Update existing key - should not reduce capacity")
    void testUpdateExistingKey() {
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(1, 100); // Update existing key

        assertEquals(100, cache.get(1), "Key 1 value should be updated to 100");
        assertEquals(20, cache.get(2), "Key 2 should still exist");
    }

    @Test
    @DisplayName("Test 5: Update existing key moves it to most recent")
    void testUpdateMovesToMostRecent() {
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(1, 100); // Update key 1, making it most recent
        cache.put(3, 30); // Should evict key 2, not key 1

        assertEquals(100, cache.get(1), "Key 1 should still exist");
        assertThrows(NullPointerException.class, () -> {
            cache.get(2);
        }, "Key 2 should be evicted");
    }

    @Test
    @DisplayName("Test 6: Capacity of 1 - only one item stored at a time")
    void testCapacityOne() {
        CacheLogic singleCache = new CacheLogic(1);
        singleCache.put(1, 10);
        assertEquals(10, singleCache.get(1), "Should store and retrieve single item");

        singleCache.put(2, 20);
        assertThrows(NullPointerException.class, () -> {
            singleCache.get(1);
        }, "First key should be evicted");
        assertEquals(20, singleCache.get(2), "Second key should be stored");
    }

    @Test
    @DisplayName("Test 7: Multiple evictions with larger cache")
    void testMultipleEvictionsLargeCache() {
        CacheLogic largeCache = new CacheLogic(3);
        largeCache.put(1, 10);
        largeCache.put(2, 20);
        largeCache.put(3, 30);

        // All three should exist
        assertEquals(10, largeCache.get(1));
        assertEquals(20, largeCache.get(2));
        assertEquals(30, largeCache.get(3));

        // Adding 4th item - should evict least recently used
        largeCache.put(4, 40);
        assertThrows(NullPointerException.class, () -> {
            largeCache.get(1); // Key 1 is least recent
        });
    }

    @Test
    @DisplayName("Test 8: Sequential operations with get ordering")
    void testSequentialOperationsWithGetOrdering() {
        cache.put(1, 10);
        cache.put(2, 20);
        cache.get(1); // Make key 1 recently used
        cache.get(1); // Access again
        cache.put(3, 30); // Should evict key 2

        assertEquals(10, cache.get(1), "Key 1 should exist");
        assertThrows(NullPointerException.class, () -> {
            cache.get(2);
        }, "Key 2 should be evicted");
        assertEquals(30, cache.get(3), "Key 3 should exist");
    }

    @Test
    @DisplayName("Test 9: Alternating put and get operations")
    void testAlternatingPutAndGet() {
        cache.put(1, 100);
        cache.put(2, 200);
        cache.get(1);
        cache.put(3, 300);
        cache.get(2);
        cache.put(4, 400);

        assertEquals(100, cache.get(1), "Key 1 should exist");
        assertEquals(300, cache.get(3), "Key 3 should exist");
        assertEquals(400, cache.get(4), "Key 4 should exist");
    }

    @Test
    @DisplayName("Test 10: Zero capacity edge case")
    void testZeroCapacity() {
        CacheLogic zeroCache = new CacheLogic(0);
        zeroCache.put(1, 10);

        assertThrows(NullPointerException.class, () -> {
            zeroCache.get(1);
        }, "Zero capacity cache should not store items");
    }

    @Test
    @DisplayName("Test 11: Same key multiple updates")
    void testMultipleUpdatesOnSameKey() {
        cache.put(1, 10);
        cache.put(1, 20);
        cache.put(1, 30);

        assertEquals(30, cache.get(1), "Key 1 should have latest value");
    }

    @Test
    @DisplayName("Test 12: Cache with negative values")
    void testCacheWithNegativeValues() {
        cache.put(1, -10);
        cache.put(2, -20);

        assertEquals(-10, cache.get(1), "Should handle negative values");
        assertEquals(-20, cache.get(2), "Should handle negative values");
    }

    @Test
    @DisplayName("Test 13: Cache with zero value")
    void testCacheWithZeroValue() {
        cache.put(1, 0);
        assertEquals(0, cache.get(1), "Should handle zero value");
    }

    @Test
    @DisplayName("Test 14: Large values")
    void testCacheWithLargeValues() {
        cache.put(1, Integer.MAX_VALUE);
        cache.put(2, Integer.MIN_VALUE);

        assertEquals(Integer.MAX_VALUE, cache.get(1), "Should handle Integer.MAX_VALUE");
        assertEquals(Integer.MIN_VALUE, cache.get(2), "Should handle Integer.MIN_VALUE");
    }

    @Test
    @DisplayName("Test 15: Stress test - many operations")
    void testStressTest() {
        CacheLogic stressCache = new CacheLogic(5);
        
        // Add 10 items to stress the cache
        for (int i = 1; i <= 10; i++) {
            stressCache.put(i, i * 100);
        }

        // Only keys 6-10 should remain (capacity is 5)
        for (int i = 1; i <= 5; i++) {
            final int key = i;
            assertThrows(NullPointerException.class, () -> {
                stressCache.get(key);
            }, "Keys 1-5 should be evicted");
        }

        for (int i = 6; i <= 10; i++) {
            assertEquals(i * 100, stressCache.get(i), "Keys 6-10 should exist");
        }
    }
}

