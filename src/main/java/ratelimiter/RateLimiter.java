package ratelimiter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {

    ConcurrentHashMap<String,Bucket> userData = new ConcurrentHashMap<>();


    public boolean isAllowed(String userName){

        Bucket bucket = userData.computeIfAbsent(
                userName,
                k -> new Bucket(5)
        );
       return bucket.isAllowRequest(userName);
    }


}
