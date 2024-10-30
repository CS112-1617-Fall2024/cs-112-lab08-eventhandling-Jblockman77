package edu.miracosta.cs112.lab08eventhandling;

//IMPORTED PACKAGES
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Random;

public class Main extends Application {
    //CONSTANTS
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    //CLASS-LEVEL VARIABLES
    private Label titleLabel, messageLabel;
    private ImageView cardImageView;
    private Button drawCardButton;
    private ProgressBar gameProgressBar;

    //SHUFFLER, WHY CANT WE JUST USE LISTS
    public static LoteriaCard[] shuffleArray(LoteriaCard[] array) {
        Random rand = new Random();
        LoteriaCard[] randomizer = new LoteriaCard[array.length];
        int[] pulledNumbers = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            pulledNumbers[i] = i;
        }
        for (int i = array.length - 1; i > 0; i--) {
            int randomNumber = rand.nextInt(0, i);
            int temp = pulledNumbers[i];
            pulledNumbers[i] = pulledNumbers[randomNumber];
            pulledNumbers[randomNumber] = temp;
        }
        for (int i = 0; i < array.length; i++) {
            randomizer[i] = array[pulledNumbers[i]];
        }
        return randomizer;
    }

    //GUI METHODS
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        titleLabel = new Label("Loteria Cards!");
        messageLabel = new Label("Click the button and you shall draw a random card!");
        cardImageView = new ImageView("0.png");
        drawCardButton = new Button("Draw Random Card");
        gameProgressBar = new ProgressBar(0);
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        messageLabel.setWrapText(true);
        cardImageView.setFitWidth(350);
        cardImageView.setFitHeight(400);

        layout.getChildren().addAll(titleLabel, cardImageView, messageLabel, drawCardButton, gameProgressBar);

        Scene scene = new Scene(layout, 350, 500);
        primaryStage.setTitle("Loteria Cards - Rafail Lishman");
        primaryStage.setScene(scene);
        primaryStage.show();
        drawCardButton.setOnAction(new EventHandler<ActionEvent>() {

            private int i = 0;
            LoteriaCard[] itsRandomized = shuffleArray(LOTERIA_CARDS);
            @Override
            public void handle(ActionEvent actionEvent) {
                if(i < itsRandomized.length) {
                    cardImageView.setImage(itsRandomized[i].getImage());
                    gameProgressBar.setProgress((double) (i+1) /(itsRandomized.length));
                    i++;
                }
                else{
                    messageLabel.setText("\tGAME OVER. No more cards! \nExit and run program again to reset ^_^");
                    //gameProgressBar.setBackground(Background.fill(Color.RED));
                    cardImageView.setImage(new Image("0.png"));
                    gameProgressBar.setStyle("-fx-accent: red");
                    drawCardButton.setDisable(true);
                }
            }
        });
    }
}