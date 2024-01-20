import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public Button btnPlay;
    public Label lblAttempts;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblScore;
    @FXML
    private Button btnReset;
    @FXML
    private TextField tfGuessedNumber;
    int mysteryNumber;
    int attempts = 3;
    int score;
    void randomizeMysteryNumber(){
        Random random = new Random(System.currentTimeMillis());
        mysteryNumber = random.nextInt(20);
    }

    @FXML
    void play() {
        if(btnPlay.getText().equals("Guess")){
            try{
                int guessedNumber = Integer.parseInt(tfGuessedNumber.getText());
                if(mysteryNumber == guessedNumber){
                    lblStatus.setText("You Won!!!");
                    lblStatus.setTextFill(Color.GREEN);
                    btnPlay.setText("Play again");
                    lblScore.setText("Score: "+(++score));
                    btnReset.setDisable(false);
                }else if(Math.abs(mysteryNumber-guessedNumber)<5){
                    lblStatus.setText("You are close");
                    lblStatus.setTextFill(Color.ORANGE);
                    lblAttempts.setText("Attempts: "+(--attempts));
                }else if((guessedNumber-mysteryNumber)>=5){
                    lblStatus.setText("Too high!");
                    lblStatus.setTextFill(Color.RED);
                    lblAttempts.setText("Attempts: "+(--attempts));
                }else{
                    lblStatus.setText("Too low!");
                    lblStatus.setTextFill(Color.RED);
                    lblAttempts.setText("Attempts: "+(--attempts));
                }
            } catch (Exception e){
                lblStatus.setText("Enter only numbers.");
                lblStatus.setTextFill(Color.RED);
            }
        }else {
            lblStatus.setText("");
            tfGuessedNumber.clear();
            randomizeMysteryNumber();
            btnPlay.setText("Guess");
            attempts = 3;
            lblAttempts.setText("Attempts: "+attempts);
        }
        if(attempts==0){
            lblStatus.setText("You lost.");
            attempts=3;
            randomizeMysteryNumber();
        }
    }
    @FXML
    void resetScore() {
        score = 0;
        lblScore.setText("Score: "+score);
        lblStatus.setText("");
        btnReset.setDisable(true);
        tfGuessedNumber.clear();
    }
    @FXML
    void clearStatusLabelOnTextFieldMouseClick() {
        lblStatus.setText("");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        randomizeMysteryNumber();
    }
}
