package com.example.concurrency;

public class ReadWriteLock {
    int reader = 0;
    boolean isWrite = false;
   int write = 0;
    public  synchronized void  lockRead() throws InterruptedException {

        while(isWrite || write > 0){
             wait();
        }

        reader++;
//        try{
//
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }
//        finally{
//            reader--;
//        }

    }

    public synchronized void unlockRead() {
      reader --;

      if(reader == 0) notifyAll();

        // your code
    }

    public  synchronized  void lockWrite() throws InterruptedException {

        while(reader > 0 || isWrite ){
            wait();
        }
        write++;
        isWrite = true;

    }

    public  synchronized  void unlockWrite() throws InterruptedException {

        isWrite = false;
        write--;
        notifyAll();

    }


}

