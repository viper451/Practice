package com.example.Dice;

import com.example.Dice.entity.Game;

public class Main {

    public static void main(String args[]){
        System.out.println("TIC TAC TOE");

        Game game = new Game(3);

        game.insertValue(0, 0, Symbol.X);
        game.insertValue(1, 1, Symbol.O);
        game.insertValue(0, 1, Symbol.X);
        game.insertValue(2, 2, Symbol.O);
        game.insertValue(0, 2, Symbol.X);
    }
}
