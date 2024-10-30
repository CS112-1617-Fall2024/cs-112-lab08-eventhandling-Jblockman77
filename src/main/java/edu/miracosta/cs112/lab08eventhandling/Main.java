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

    //GUI METHODS
    public static void main(String[] args) {
        launch(args);
    }

    Random rand = new Random();
    for(int i = LOTERIA_CARDS.length - 1; i > 0; i--){

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
            @Override
            public void handle(ActionEvent actionEvent) {
                if(i < LOTERIA_CARDS.length) {
                    cardImageView.setImage(LOTERIA_CARDS[i].getImage());
                    gameProgressBar.setProgress((double) (i+1) /(LOTERIA_CARDS.length));
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