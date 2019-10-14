package javaFXDemo;

import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	Button button1, button2;
	Stage window;
	// stage: called the window and shit
	// scene: stuff inside the window, buttons go here
	Scene scene1, scene2;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		Label label1 = new Label("Welcome to the first scene");
		window.setTitle("Lesson 1");
		button1 = new Button("Go to scene two");
		button1.setOnAction(e -> {
			boolean result = ConfirmBox.display("Confirm", "Do you want to go to scene 2?");
			if(result) {
				window.setScene(scene2);
			}
		});
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(label1, button1);
		scene1 = new Scene(layout1, 200, 200);
		
		button2 = new Button("Go to scene 1");
		button2.setOnAction(e -> window.setScene(scene1));
		
		StackPane layout2 = new StackPane();
		layout2.getChildren().add(button2);
		scene2 = new Scene(layout2, 300, 250);
		window.setScene(scene1);
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
			});
		window.show();
	}

	// if Class implements EventHandler<ActionEvent>
	// @Override
	// public void handle(ActionEvent event) {
	// // TODO Auto-generated method stub
	// if(event.getSource() == button) {
	// System.out.println("Yay you click a button");
	// }
	// else if(event.getSource() == button2) {
	// System.out.println("you click button2");
	// }
	// }

	private void closeProgram() {
		System.out.println("SOME CODE RAN BEFORE PROGRAM CLOSED");
		window.close();
//		Platform.exit();
	}
}
