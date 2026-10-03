package com.example.solidp;

public class LiskivSub {
    public interface Bird{
        // to do;
    }

    public interface FlyingBird extends Bird{
        public default void fly(){}
    }

    public interface WalkingBird extends Bird{
        public default void walk(){}
    }

    public static class Parrot  implements FlyingBird, WalkingBird {
        public void fly() { // to do}
            //     void walk(){ // to do }
        }
    }

        public static class Penguin implements WalkingBird {
            public void walk() { // to do }
            }
        }
    }
