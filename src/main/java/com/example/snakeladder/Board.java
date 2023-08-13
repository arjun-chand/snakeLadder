package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>> positionCoordinates;
    ArrayList<Integer> snakeLadderPosition;

    public Board(){
        positionCoordinates = new ArrayList<>();
        populatePositionCoordiantes();
        populatesnakeLadder();
    }

    private void populatePositionCoordiantes(){

        positionCoordinates.add(new Pair<>(0,0));//dummy value

        for (int i = 0; i < snakeLadder.height; i++) {
            for (int j = 0; j < snakeLadder.width; j++) {

                //x coordinate
                int xcord = 0;
                if(i % 2 == 0){
                    xcord = j * snakeLadder.tileSize + snakeLadder.tileSize/2;
                }
                else{
                    xcord = snakeLadder.tileSize * snakeLadder.height - (j * snakeLadder.tileSize) - snakeLadder.tileSize/2;
                }

                //y coordinate
                int ycord = snakeLadder.tileSize * snakeLadder.height - (i * snakeLadder.tileSize) - snakeLadder.tileSize/2;
                positionCoordinates.add(new Pair<>(xcord,ycord));

            }
        }
    }

    private void populatesnakeLadder(){
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(4,25);
        snakeLadderPosition.set(21,39);
        snakeLadderPosition.set(29,74);
        snakeLadderPosition.set(30,7);
        snakeLadderPosition.set(43,76);
        snakeLadderPosition.set(47,15);
        snakeLadderPosition.set(56,19);
        snakeLadderPosition.set(63,80);
        snakeLadderPosition.set(71,89);
        snakeLadderPosition.set(73,51);
        snakeLadderPosition.set(82,42);
        snakeLadderPosition.set(92,75);
        snakeLadderPosition.set(98,65);
    }

    //get X coordinate to move player in player class
    int getXCoordinates(int position){
        if(position >= 1 && position <= 100){
            return positionCoordinates.get(position).getKey();
        }
        return -1;
    }

    public int getNewPosition(int currPosition){
        if(currPosition > 0 && currPosition <= 100){
            return snakeLadderPosition.get(currPosition);
        }
        return -1;
    }

    //get Y coordinates to move player
    int getYCoordinates(int position){
        if(position >= 1 && position <= 100){
            return positionCoordinates.get(position).getValue();
        }
        return -1;
    }

    public static void main(String[] args) {
        Board board = new Board();
        for (int i = 0; i < board.positionCoordinates.size(); i++) {
            System.out.println(i + " $ x : "+ board.positionCoordinates.get(i).getKey() +
                    "   y : "+ board.positionCoordinates.get(i).getValue());

        }
    }
}
