package com.example.Dice.entity;

import com.example.Dice.Symbol;

public class Game {
    int boardSize;
    boolean currentPlayer;
    Symbol board[][];
    boolean isOccupied[][];
    int totalSpace;

    public Game(int boardSize) {
        this.boardSize = boardSize;
        board = new Symbol[boardSize][boardSize];
        isOccupied = new boolean[boardSize][boardSize];
        totalSpace = boardSize * boardSize;
    }


    public void insertValue(int x, int y, Symbol symbol) {
        if (check(x, y) && notOccupied(x, y)) {
            isOccupied[x][y] = true;
            board[x][y] = symbol;
            totalSpace--;
            System.out.println(symbol + " Inserted at position " + x + " " + y);
        } else {
            System.out.println("INCORRECT POSITION");
            return;
        }

        if (checkWinner(symbol)) {
            System.out.println("WINNER IS " + (currentPlayer ? "Player2" : "Player1"));
            return;
        }
        else{
            currentPlayer = !currentPlayer;
        }


        if(totalSpace == 0 )System.out.println("DRAW");



    }

    public boolean check(int x, int y) {


        return x >= 0 && y >= 0 && x < boardSize && y < boardSize;

    }

    public boolean notOccupied(int x, int y) {
       // System.out.println(!isOccupied[x][y]);
        return !isOccupied[x][y];
    }

    public boolean checkWinner(Symbol symbol) {


        for (int i = 0; i < boardSize; i++) {
            boolean check = true;
            for (int j = 0; j < boardSize; j++) {
                  if(board[i][j]== null || !board[i][j].equals(symbol)){
                      check = false;
                      break;
                  }
            }
            if(check) return true;
        }




        for (int i = 0; i < boardSize; i++) {
            boolean check = true;
            for (int j = 0; j < boardSize; j++) {
                if(board[j][i]== null ||  !board[j][i].equals(symbol)){
                    check = false;
                    break;
                }
            }
            if(check) return true;
        }


        boolean check = true;
        for (int i = 0; i < boardSize; i++) {

            for (int j = i; j <= i; j++) {
                if(board[j][i]== null || !board[j][i].equals(symbol)){
                    check = false;
                    break;
                }
            }

        }
        if(check) return true;

      check = true;
        for (int i = 0; i < boardSize; i++) {

            for (int j = i; j <= i; j++) {
                if(board[j][(boardSize-1)-j]== null ||  !board[j][(boardSize - 1) - j].equals(symbol)){
                    check = false;
                    break;
                }
            }

        }
        if(check) return true;
        return false;

    }
}