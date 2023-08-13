package com.example.snakeladder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//for making grid
//divide pane into 10 x 10 grid
public class Tile extends Rectangle{
    public Tile(int tileSize){
        setWidth(tileSize);
        setHeight(tileSize);
        setFill(Color.AZURE);
        setStroke(Color.BLACK);
    }
}
