package com.example.InterviewCodePrep.ThreadSafeLRUCache;



import lombok.val;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafe {

    ReentrantLock lock = new ReentrantLock();

    HashMap<Integer, Node>mp = new HashMap<>();

    int capacity;


    Node head = new Node(0,0);
    Node tail = new Node(0,0);

      public ThreadSafe(int capacity) {
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }


    public void insert(int number,Node node){

          try{
              lock.lock();
              Node tempAhead = head.next;
              head.next = node;
              node.prev = head;
              node.next = tempAhead;
              tempAhead.prev = node;
              mp.put(number,node);
              if(mp.size()>capacity){
                  delete(tail.prev.getKey());
              }
          }
          catch(Exception e){
              System.out.println("ERROR");
          } finally{
              lock.unlock();
        }



    }

    public void delete(int number){

          if(!mp.containsKey(number)) return;



      Node node = mp.get(number);
        mp.remove(number);

        Node nextNode = node.next;
        Node prevNode = node.prev;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
    }

    public Node get(int number){

          try{
              lock.lock();

              if(mp.containsKey(number)){
                  Node insertNode = mp.get(number);
                  delete(number);
                    insert(number,insertNode);

                  return mp.get(number);
              }
              else{
                  return  null;
              }

          }

          catch(Exception e){
              System.out.println("ERROR");
          } finally{
              lock.unlock();
          }
      return null;
    }

}
