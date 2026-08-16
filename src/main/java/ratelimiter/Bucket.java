package ratelimiter;

import java.util.concurrent.locks.ReentrantLock;

public class Bucket {

    long tokens;
    ReentrantLock lock;
    long lastRefillTime;



    public Bucket(long tokens){
        this.tokens = tokens;
        lock = new ReentrantLock();
        this.lastRefillTime = System.currentTimeMillis();

    }

    public boolean isAllowRequest(String username){
        lock.lock();

        try{

            refill();
        if(tokens > 0){
            tokens --;
            System.out.println("Token left for user '" + username + "' : " + tokens);
            return  true;
        }
        else {
            System.out.println("TOKENS EMPTY FOR USER "+username);
            return false;
        }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }

    }

    public void refill(){
        long elapsedTime= (System.currentTimeMillis() - lastRefillTime)/(2000);

        tokens = Math.min(5,tokens + elapsedTime);
        if(elapsedTime !=0) {
            lastRefillTime = System.currentTimeMillis();
        }

    }
}
