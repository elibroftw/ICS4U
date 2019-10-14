package javaFXDemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmbeddedLayouts extends Application {
	
	Stage window;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Embedded");
		
		HBox topMenu = new HBox();
		Button buttonA = new Button("File");
		Button buttonB = new Button("Edit");
		Button buttonC = new Button("View");
		topMenu.getChildren().addAll(buttonA, buttonB, buttonC);
		
		VBox leftMenu = new VBox();
		Button buttonD = new Button("A");
		Button buttonE = new Button("E");
		Button buttonF = new Button("F");
		leftMenu.getChildren().addAll(buttonD, buttonE, buttonF);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);
		borderPane.setLeft(leftMenu);
		
//		StackPane layout = new StackPane();
		Scene scene = new Scene(borderPane, 300, 250);
		window.setScene(scene);
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
