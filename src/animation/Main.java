package animation;

import java.awt.Button;
import java.awt.Image;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.text.html.ImageView;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application implements Initializable {

	private Stage window;

	@FXML private ComboBox<String> animationCombos;
	@FXML private Button playPauseButton;
	@FXML private StackPane stackPane;
	@FXML private CheckBox slowMotionBox;
	@FXML private CheckBox reverseBox;

	private Long animationStart;
	private Animation currentAnimation;
	private List<Animation> animations;
	private SpriteData spriteData;
	private SpriteSheet spriteSheet;
	private int spriteWidth, spriteHeight;
	private boolean isPaused = false;
	private boolean isReversed = false;
	private int slowMotion = 1;

	// private Service<Void> backgroundThread;

	/**
	 * Main method for GUI
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("/animation/main.fxml"));
		window.setScene(new Scene(root, 977, 651));
		window.setTitle("Animation Player");
		window.show();
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
	 * Linked to restart button
	 * 
	 * Just calls change animation so that the currently selected animation restarts
	 */
	public void restart() {
		changeAnimation();
	}

	/**
	 * Linked to slowMotion checkBox
	 * 
	 * Multiplies the frame timer by 2 when true
	 */
	public void slowMotion() {
		if (slowMotionBox.isSelected())
			slowMotion = 2;
		else
			slowMotion = 1;
	}

	/**
	 * Linked to reverse checkBox
	 * 
	 * Sets reverse boolean
	 */
	public void reverse() {
		isReversed = reverseBox.isSelected();
	}

	/**
	 * Linked to playPause button
	 * 
	 * Changes button text Flips isPaused boolean value
	 */
	public void playPause() {
		isPaused = !isPaused;
		if (isPaused)
			playPauseButton.setText("Play");
		else
			playPauseButton.setText("Pause");
	}

	/**
	 * Linked to nextFrame button
	 * 
	 * Displays the next frame of the animation
	 * 
	 * Depends on if the animation is reversed
	 */
	public void nextFrame() {
		isPaused = true;
		playPauseButton.setText("Play");
		if (isReversed)
			displaySprite(spriteSheet.getSprite(currentAnimation.getPrevious()[0]));
		else
			displaySprite(spriteSheet.getSprite(currentAnimation.overrideGetNext()[0]));
	}

	/**
	 * Linked to previousFrame button
	 * 
	 * Displays the previous frame of the animation
	 * 
	 * Depends on if the animation is reversed
	 */
	public void previousFrame() {
		isPaused = true;
		playPauseButton.setText("Play");
		// call previous frame
		if (isReversed)
			displaySprite(spriteSheet.getSprite(currentAnimation.overrideGetNext()[0]));
		else
			displaySprite(spriteSheet.getSprite(currentAnimation.getPrevious()[0]));
	}

	/**
	 * Linked to comboBox selection used for changing current animation Invokes play
	 * of the selected animation
	 */
	public void changeAnimation() {
		currentAnimation = animations.get(animationCombos.getItems().indexOf(animationCombos.getValue()));
		animationStart = System.currentTimeMillis();
		play(currentAnimation, animationStart);
	}

	/**
	 * Converts BufferedImage into JavaFX friendly Image and then displays the
	 * sprite on the stackPane using ImageView
	 * 
	 * @param sprite
	 */
	private void displaySprite(BufferedImage sprite) {
		Image img = SwingFXUtils.toFXImage(sprite, null);
		ImageView imgView = new ImageView(img);
		imgView.setFitHeight(spriteHeight * 5);
		imgView.setFitWidth(spriteWidth * 5);
		stackPane.getChildren().clear();
		stackPane.getChildren().add(imgView);
	}

	/**
	 * plays the given animation and uses threadStart to see if the currentAnimation
	 * is the latest animation takes into account slowMotion, isPaused, isReversed
	 * 
	 * @param animation
	 *            the animaiton to be played on the GUI
	 * @param threadStart
	 *            checked against animationStart to decide whether to keep playing
	 *            animation
	 */
	public void play(Animation animation, Long threadStart) {
		displaySprite(spriteSheet.getSprite(animation.start()[0])); // display the first Frame
		isPaused = false; // unPause if paused
		playPauseButton.setText("Pause");
		Thread taskThread = new Thread(() -> { // needs to be threaded to prevent Non Responsive GUI
			int[] frame = animation.start(); // get data of the first frame
			int frameTimer = frame[1]; // time the frame should be displayed for
			do {
				try {
					Thread.sleep(frameTimer * 30 * slowMotion); // display the frame for this much time, I used 30 to make animations look normal
				} catch (InterruptedException e) {
				}
				if (!isPaused && animationStart == threadStart) { // show frame if correct animation is selected and if animation is not paused
					if (isReversed) frame = animation.getPrevious();
					else frame = animation.getNext();
					final int sn = frame[0];
					frameTimer = frame[1];
					Platform.runLater(new Runnable() { // updates GUI on main thread
						@Override
						public void run() {
							displaySprite(spriteSheet.getSprite(sn));
						}
					});
				}
			} while (animationStart == threadStart);
			// the last animation start time tells us that this animation is suppoed to be playing, and so is continued
			// if only animations were compared, there would be a mess with restarting animations
		});
		taskThread.setDaemon(true); // if GUI is closed so are these
		if (animation.size() != 1) { // thread is not needed if there is only 1 frame
			taskThread.start();
		}
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		try {
			spriteData = new SpriteData("src/data/LockeX2.txt"); // Parse data
			animations = spriteData.getAnimations(); // get the animation list
			animationCombos.getItems().addAll(spriteData.getNames()); // add names to comboBox
			String spriteLocation = spriteData.getSpriteLocation(); // gets the location of the image
			spriteWidth = spriteData.spriteWidth; // meta data stuff
			spriteHeight = spriteData.spriteHeight;
			int numberOfSprites = spriteData.numberOfSprites;
			spriteSheet = new SpriteSheet(spriteLocation, spriteWidth, spriteHeight, numberOfSprites); // sprite sheet
																										// holding each
																										// frame
		} catch (IOException e) {
			displayAlert("ERROR", "Could not load Locke.txt");
		}
	}

}
