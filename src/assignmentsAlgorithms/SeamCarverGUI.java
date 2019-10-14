package assignmentsAlgorithms;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SeamCarverGUI extends Application {

	Button button2, button1, button3;
	Stage window;
	Scene scene1;
	String resourceFile = "";
	SeamCarver originalImage;
	StackPane sp = new StackPane();
	StackPane sp2 = new StackPane();
	boolean hasRun = false;
	BufferedImage imgSource;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Displays an alert box
	 * 
	 * @param title
	 *            of the alert box
	 * @param alertText
	 *            message of the alert box
	 */
	public static void displayAlert(String title, String alertText) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(350);
		Label label = new Label(alertText);
		Button closeBUtton = new Button("Close");
		closeBUtton.setOnAction(e -> window.close());
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeBUtton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.show();
	}

	/**
	 * JavaFX default
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Seam Carver");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		FileChooser.ExtensionFilter openExtFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *jpeg)",
				"*.png", "*.jpg", ".jpeg");
		fileChooser.getExtensionFilters().add(openExtFilter);

		FileChooser saveFileChooser = new FileChooser();
		saveFileChooser.setTitle("Select Save File");
		FileChooser.ExtensionFilter saveExtFilter = new FileChooser.ExtensionFilter("PNG file (*.png)", "*.png");
		saveFileChooser.getExtensionFilters().add(saveExtFilter);

		button1 = new Button("Choose Input File");
		TextField textField1 = new TextField();

		textField1.setPrefWidth(160);
		textField1.setEditable(false);

		button1.setOnAction(e -> { // Choosing a resource file
			resourceFile = fileChooser.showOpenDialog(window).getPath();
			String baseName = reverseString(resourceFile);
			baseName = baseName.substring(0, baseName.indexOf("\\"));
			textField1.setText(reverseString(baseName));
			try {
				imgSource = ImageIO.read(new File(resourceFile));
			} catch (IOException e1) {
				displayAlert("ERROR", "Something went wrong");
			}
			Image img = SwingFXUtils.toFXImage(imgSource, null);
			ImageView imgView = new ImageView(img);
			imgView.setFitHeight(300);
			imgView.setFitWidth(533);
			window.setWidth(933.333333); // extend stage for image to fit
			sp.getChildren().clear();
			sp.getChildren().add(imgView);
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds(); // centre
			primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
		});
		TextField newWidth = new TextField();
		newWidth.setPromptText("new width");
		newWidth.setPrefWidth(80);

		TextField newHeight = new TextField();
		newHeight.setPromptText("new height");
		newHeight.setPrefWidth(80);

		// integer only input
		newWidth.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					newWidth.setText(oldValue);
				}
			}
		});
		// integer only input
		newHeight.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					newHeight.setText(oldValue);
				}
			}
		});

		button2 = new Button("Seam Carve");
		button3 = new Button("Save carved image");

		button2.setOnAction(e -> {
			// checks if input has a valid filename before proceding to run SeamCarver
			if (resourceFile.equals("")) {
				displayAlert("ERROR", "No file selected");
			}
			int newW, newH;
			// check if width and height are appropriate
			try {
				newW = Integer.parseInt(newWidth.getText());
			} catch (NumberFormatException exception) {
				newW = imgSource.getWidth();
			}
			try {
				newH = Integer.parseInt(newHeight.getText());
			} catch (NumberFormatException exception) {
				newH = imgSource.getHeight();
			}

			if (newW > imgSource.getWidth() || newH > imgSource.getHeight()) {
				displayAlert("ERROR", "Invald new width/height selected");
			} else {
				button2.setDisable(true);
				originalImage = new SeamCarver(imgSource);
				seamCarve(originalImage.getHeight() - newH, originalImage.getWidth() - newW);
				Image img2 = SwingFXUtils.toFXImage(originalImage.getImage(), null);
				ImageView imgView2 = new ImageView(img2);
				imgView2.setFitHeight(400);
				imgView2.setFitWidth(300);
				window.setWidth(1233.33333);
				sp2.getChildren().add(imgView2);
				Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
				primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
				button2.setDisable(false);
				hasRun = true;
				displayAlert("Note", "output is scaled, to see true output, please save to file");
			}
		});

		button3.setOnAction(e -> {
			if (hasRun) {
				saveToFile(saveFileChooser.showSaveDialog(window).getPath());
			} else {
				displayAlert("ERROR", "No last run");
			}
		});

		GridPane grid = new GridPane();
		grid.setVgap(8);
		grid.setHgap(10);
		GridPane.setConstraints(button1, 2, 2);
		GridPane.setConstraints(textField1, 3, 2, 2, 1);
		GridPane.setConstraints(newWidth, 3, 3);
		GridPane.setConstraints(newHeight, 4, 3);
		GridPane.setConstraints(button2, 2, 3);
		GridPane.setConstraints(button3, 2, 4);
		GridPane.setConstraints(sp, 0, 1, 1, 20);
		GridPane.setConstraints(sp2, 6, 1, 1, 20);

		grid.getChildren().addAll(button1, textField1, newWidth, newHeight, button2, button3, sp, sp2);
		scene1 = new Scene(grid, 400, 300);
		window.setScene(scene1);
		window.show();
	}

	/**
	 * Saves the carved image to a path given
	 * 
	 * @param path
	 */
	private void saveToFile(String path) {
		String extension = "";
		int i = path.lastIndexOf('.');
		if (i > 0) {
			extension = path.substring(i + 1);
		}
		File outputfile = new File(path);
		try {
			ImageIO.write(originalImage.getImage(), extension, outputfile);
		} catch (IOException e) {
			displayAlert("ERROR", "Could not save image");
		}
	}

	/**
	 * Helper function that organizes the amount of carves needed to be done
	 * 
	 * @param horizontalCarves
	 * @param verticalCarves
	 */
	private void seamCarve(int horizontalCarves, int verticalCarves) {
		for (int w = 0; w < horizontalCarves; w++) {
			originalImage.removeHorizontalSeam(originalImage.findHorizontalSeam());
		}
		for (int h = 0; h < verticalCarves; h++) {
			originalImage.removeVerticalSeam(originalImage.findVerticalSeam());
		}
	}

	/**
	 * Reverses a string, helpful to figure out the basename for a given file path
	 * 
	 * @param str
	 *            this is the path to file
	 * @return
	 */
	private String reverseString(String str) {
		String reverse = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			reverse = reverse + str.charAt(i);
		}
		return reverse;
	}
}
