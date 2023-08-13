package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private int currentPosition;
    private String name;

    private static Board gameBoard = new Board();
    public Player(int tileSize, Color coinColor, String playerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        currentPosition = 0;
        movePlayer(1);
        name = playerName;
    }

    public void startingPosition(){
        int currentPosition = 0;
        movePlayer(0);

    }
    boolean isWinner(){
        return currentPosition == 100;
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }

    //moving players
    public void movePlayer(int diceValue){
        if(currentPosition + diceValue <= 100){
            currentPosition += diceValue;
            TranslateTransition secondMove = null, firstMove = translateAnimation(diceValue);

            int newPosition = gameBoard.getNewPosition(currentPosition);
            if(newPosition != currentPosition && newPosition != -1){
                currentPosition = newPosition;
                secondMove = translateAnimation(6);
            }

            if(secondMove == null){
                firstMove.play();
            }
            else {
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove,
                        new PauseTransition(Duration.millis(500)), secondMove);
                sequentialTransition.play();
            }
        }

        /*int x = gameBoard.getXCoordinates(currentPosition);// getting coordiantes from Board
        int y = gameBoard.getYCoordinates(currentPosition);

        // set Coins in board coordinates
        coin.setTranslateX(x);
        coin.setTranslateY(y);*/



    }

    // putting some animation in movents of coins
    private TranslateTransition translateAnimation(int diceValue){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), coin);
        animate.setToX(gameBoard.getXCoordinates(currentPosition));
        animate.setToY(gameBoard.getYCoordinates(currentPosition));
        animate.setAutoReverse(false); //because it can go back
        //play the animation
        return animate;
    }
}
