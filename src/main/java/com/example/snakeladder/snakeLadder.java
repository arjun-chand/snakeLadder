package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class snakeLadder extends Application {
    public static final int tileSize = 45, width = 10, height = 10;
    public static final int buttonLine = height * tileSize + 40, infoLine = buttonLine - 30;

    private static Dice dice = new Dice();
    private Player playerOne, playerTwo;
    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;
    private Pane createContent(){
        Pane root = new Pane();

        //set Pain size
        //50 extra space for placing buttons
        root.setPrefSize(width * tileSize, height * tileSize + 85);

        //add grids(Tiles) in Pane
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Tile tile = new Tile(tileSize);//user made class
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                root.getChildren().add(tile);
            }
        }

        //put image of snake and ladder with numbers
        //2nd line for display image
        Image img = new Image("C:\\Users\\Owner\\IdeaProjects\\snakeLadder\\src\\main\\SLimage.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height * tileSize);
        board.setFitWidth(width * tileSize);

        //create buttons
        Button playerOneButton = new Button("Player 1");
        Button playerTwoButton = new Button("Player 2");
        Button startButton = new Button("Start");

        //set buttons
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(348);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(200);

        //Create Information display like textarea,label
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("");

        //set Labels
        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(18);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(345);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(180);

        //Initialize players
        playerOne = new Player(tileSize, Color.BLACK, "Arjun");
        playerTwo = new Player(tileSize - 10, Color.SIENNA,"Shivam");

        //Player Action
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        //moving
                        int diceValue = dice.getRollDiceValue(); //dice rolled
                        diceLabel.setText("Dice Value : "+ diceValue);
                        playerOne.movePlayer(diceValue);

                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is : "+playerOne.getName());
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            //enabling other player
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart Game");
                        }
                        else{
                            //disabling 1st player
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            //enabling other player
                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn : "+ playerTwo.getName());
                        }
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){

                        //moving
                        int diceValue = dice.getRollDiceValue(); //dice rolled
                        diceLabel.setText("Dice Value : "+ diceValue);
                        playerTwo.movePlayer(diceValue);

                        //Winning Condition
                        if(playerTwo.isWinner()){
                            diceLabel.setText("Winner is : "+playerTwo.getName());
                            //disabling 1st player
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            //enabling other player
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart Game");
                        }
                        else{
                            //disabling 1st player
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");


                            //enabling other player
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn : "+ playerTwo.getName());

                        }

                    }
                }
            }
        });
        //start Button Action
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);

                playerOneTurn = true;
                playerOneLabel.setText("Your Turn "+ playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();

                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();
            }
        });

        //add image, buttons, Labels in Pane
        root.getChildren().addAll(board,
                playerOneButton, playerTwoButton, startButton,
                playerOneLabel, playerTwoLabel, diceLabel,
                playerOne.getCoin(), playerTwo.getCoin());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}