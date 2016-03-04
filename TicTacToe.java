/*
    Yonny Garcia
    TicTacToe.java
    2016-02-23
    *This project simulates a tic tac toe game using
    enumarations along with 2D arrays!
*/
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class TicTacToe {
    public enum Status { ONGOING, DRAW, PLAYER_1_WIN, PLAYER_2_WIN }; 
    private char[][] board = new char[3][3];
    private int numMoves;
    private char[] player = new char[2];
    private Status status;
    
    public boolean checkForWin(char c){
        //row 1
        if (c == board[0][0] && c == board[0][1] && c == board[0][2]){
            return true;
        //row2
        }else if (c == board[1][0] && c == board[1][1] && c == board[1][2]){
            return true;
        //row3
        }else if (c == board[2][0] && c == board[2][1] && c == board[2][2]){
            return true;
        //column1
        }else if (c == board[0][0] && c == board[1][0] && c == board[2][0]){
            return true;
        //column2
        }else if (c == board[0][1] && c == board[1][1] && c == board[2][1]){
            return true;
        //column3
        }else if (c == board[0][2] && c == board[1][2] && c == board[2][2]){
            return true;
        //diaganol1
        }else if (c == board[0][0] && c == board[1][1] && c == board[2][2]){ 
            return true;
        //diaganol2
        }else if (c == board[2][0] && c == board[1][1] && c == board[0][2]){
            return true;
        }
        return false;
    }
    
    public TicTacToe(){
        reset();
    }
    
    public void reset(){
        player[0] = 'X';
        player[1] = 'O';
        numMoves = 0;
        status = Status.ONGOING;
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                board[row][col] = ' ';
            }
        }
    }
    
    public Status getStatus(){
        return status;
    }
    
    public char getPlayer1(){
        return player[0];
    }
    
    public char getPlayer2(){
        return player[1];
    }
    
    public char nextToMove(){
        // *** This should be based on numMoves % 2
        if((numMoves % 2) == 0){
            return player[0];
        }else{
            return player[1];
        }
    }
    public void displayInstructions(){
        System.out.println("This is the game of Tic Tac Toe.\n"
        + "X moves first. Each player chooses their\n"
        + "move by selecting the cell they want to place\n"
        + "their mark in. The cells are numbered as follows: \n"
        + "1  2  3 \n"
        + "4  5  6 \n"
        + "7  8  9");
        
    }
    public String toString(){
        String temp = "";
        
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                temp += board[r][c] + "|";
            }
            temp = temp.substring(0, temp.length() - 1);
            temp += "\n";
            for(int i = 0; r != 3-1 && i < 3; i++){
                temp += "- ";
            }
            temp += "\n";
        }
        return temp;
    }
    public boolean move(int cell){
        int r, c;
        r = (cell - 1)/3;
        c = (cell - 1)%3;
        int count;
        if(cell >= 1 && cell <= 9 && status == status.ONGOING && board[r][c] == ' '){
            board[r][c] = nextToMove();
            numMoves++;
            if(checkForWin(getPlayer1())){
                status = Status.PLAYER_1_WIN;
            }else if(checkForWin(getPlayer2())){
                status = Status.PLAYER_2_WIN;
            }else if(numMoves == 9){
                status = Status.DRAW;
            }
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){
        TicTacToe game = new TicTacToe();
        boolean play = true;
        int move = 0;
        Keyboard kbd = new Keyboard();
        while(play == true){
            game.reset();
            game.displayInstructions();
            System.out.print(game.toString());
            while(game.getStatus() == Status.ONGOING){
                try {
                    move = kbd.getInt("Enter move for player " + game.nextToMove() + ":");
                }catch(Exception e){
                    System.out.println("Invalid input");
                }
                while(!game.move(move)){
                    try{
                        move = kbd.getInt("Enter move for player " + game.nextToMove() + ":");
                    }catch(Exception e){
                        System.out.println("Invalid input");
                    }
                }
                System.out.print(game.toString());
            }
            if (game.getStatus() == Status.PLAYER_1_WIN){
                System.out.println("Player " + game.getPlayer1() + " won");
            }else if(game.getStatus() == Status.PLAYER_2_WIN){
                System.out.println("Player " + game.getPlayer2() + " won");
            }else if(game.getStatus() == Status.DRAW){
                System.out.println("The game is a draw.");
            }
            char playAgain = kbd.getChar("Play again? (Y/N)", "YyNn");
            play = (playAgain == 'Y' || playAgain == 'y' );
        }
    }
}
