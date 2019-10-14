package javaFXDemo;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	public Button button;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
    }
    
    public void handButtonClick() {
    	System.out.println("run some code");
    	button.setText("stop it");
    }

}